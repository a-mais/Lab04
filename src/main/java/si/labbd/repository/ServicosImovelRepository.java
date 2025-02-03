package si.labbd.repository;

import si.labbd.models.ServicosImovel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ServicosImovelRepository extends BaseRepository<ServicosImovel> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab04");

    public ServicosImovelRepository() {
        super(ServicosImovel.class);
    }

    // Buscar todos os serviços associados a um imóvel APENAS se houver uma locação ativa
    public List<ServicosImovel> findByImovelComLocacaoAtiva(int imovelId) {
        EntityManager em = emf.createEntityManager();

        try {
            // Verificar se o imóvel tem uma locação ativa
            Long locacaoAtivaCount = em.createQuery(
                    "SELECT COUNT(l) FROM Locacao l WHERE l.imovel.id = :imovelId AND l.ativo = true",
                    Long.class)
                .setParameter("imovelId", imovelId)
                .getSingleResult();

            // Se não houver locação ativa, retornar uma lista vazia
            if (locacaoAtivaCount == 0) {
                return List.of();
            }

            // Buscar todos os serviços executados no imóvel
            List<ServicosImovel> servicos = em.createQuery(
                    "SELECT s FROM ServicosImovel s WHERE s.imovel.id = :imovelId",
                    ServicosImovel.class)
                .setParameter("imovelId", imovelId)
                .getResultList();

            return servicos;
        } catch (Exception e) {
            return List.of(); // Retorna lista vazia em caso de erro
        } finally {
            em.close();
        }
    }
}
