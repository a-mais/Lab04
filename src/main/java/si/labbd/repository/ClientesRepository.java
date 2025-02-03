package si.labbd.repository;

import si.labbd.models.Clientes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClientesRepository extends BaseRepository<Clientes> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab04");

    public ClientesRepository() {
        super(Clientes.class);
    }

    @Override
    public void save(Clientes cliente) {
        if (cpfExists(cliente.getCpf())) {
            throw new RuntimeException("Erro: CPF já cadastrado no sistema!");
        }
        super.save(cliente);
    }

    public boolean cpfExists(String cpf) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(c) FROM Clientes c WHERE c.cpf = :cpf", Long.class)
                       .setParameter("cpf", cpf)
                       .getSingleResult();
        em.close();
        return count > 0;
    }
}
