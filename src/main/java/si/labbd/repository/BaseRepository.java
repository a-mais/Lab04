package si.labbd.repository;

import java.util.List;
import java.util.Optional;

public class BaseRepository<T> implements GenericRepository<T> {
    private final GenericDAO<T> genericDAO;

    public BaseRepository(Class<T> entityClass) {
        this.genericDAO = new GenericDAO<>(entityClass);
    }

    @Override
    public void save(T entity) {
        genericDAO.salvar(entity);
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.ofNullable(genericDAO.buscarPorId(id));
    }

    @Override
    public List<T> findAll() {
        return genericDAO.listarTodos();
    }

    @Override
    public void update(T entity) {
        genericDAO.atualizar(entity);
    }

    @Override
    public void deleteById(int id) {
        genericDAO.deletar(id);
    }
}

