package si.labbd.repository;

import si.labbd.models.Locacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class LocacaoRepository extends BaseRepository<Locacao> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab04");

    public LocacaoRepository() {
        super(Locacao.class);
    }

    // Listar todas as locações de um cliente (por ID do cliente)
    public List<Locacao> findByClienteId(int clienteId) {
        EntityManager em = emf.createEntityManager();
        List<Locacao> lista = em.createQuery("SELECT l FROM Locacao l WHERE l.inquilino.id = :clienteId", Locacao.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
        em.close();
        return lista;
    }


    // Verificar se um imóvel já está alugado
    private boolean isImovelAlugado(int imovelId) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(l) FROM Locacao l WHERE l.imovel.id = :imovelId", Long.class)
                .setParameter("imovelId", imovelId)
                .getSingleResult();
        em.close();
        return count > 0;
    }

    // Verificar se um imóvel está disponível para locação (checa o campo disponível)
    // Verificar se um imóvel está disponível para locação com base no campo ativo da Locacao
    private boolean isImovelDisponivel(int imovelId) {
        EntityManager em = emf.createEntityManager();

        // Verificar se há locação ativa para o imóvel
        Long count = em.createQuery(
                        "SELECT COUNT(l) FROM Locacao l WHERE l.imovel.id = :imovelId AND l.ativo = true", Long.class)
                .setParameter("imovelId", imovelId)
                .getSingleResult();

        em.close();

        // Se count > 0, significa que há uma locação ativa, então o imóvel NÃO está disponível
        return count == 0;
    }


    // Salvar uma nova locação, garantindo que o imóvel está disponível e não alugado
    @Override
    public void save(Locacao locacao) {
        if (!isImovelDisponivel(locacao.getImovel().getId())) {
            throw new RuntimeException("Erro: O imóvel não está disponível para locação.");
        }
        if (isImovelAlugado(locacao.getImovel().getId())) {
            throw new RuntimeException("Erro: O imóvel já está alugado.");
        }
        super.save(locacao);
    }
}
