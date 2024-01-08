package nvb.dev.busticketreservation.base.service.impl;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import nvb.dev.busticketreservation.base.entity.BaseEntity;
import nvb.dev.busticketreservation.base.repository.BaseRepository;
import nvb.dev.busticketreservation.base.service.BaseService;
import org.hibernate.TransactionException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class BaseServiceImpl<ID extends Serializable, Entity extends BaseEntity<ID>,
        Repository extends BaseRepository<ID, Entity>> implements BaseService<ID, Entity> {

    protected final EntityManager entityManager;
    protected final Repository repository;

    @Override
    public void save(Entity entity) {
        try {
            entityManager.getTransaction().begin();
            repository.save(entity);
            entityManager.getTransaction().commit();
        } catch (TransactionException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void update(Entity entity) {
        try {
            entityManager.getTransaction().begin();
            repository.update(entity);
            entityManager.getTransaction().commit();
        } catch (TransactionException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void remove(Entity entity) {
        try {
            entityManager.getTransaction().begin();
            repository.remove(entity);
            entityManager.getTransaction().commit();
        } catch (TransactionException e) {
            e.getStackTrace();
        }
    }

    @Override
    public Optional<Entity> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Entity> findAll() {
        entityManager.getTransaction().begin();
        Collection<Entity> entities = repository.findAll();
        entityManager.getTransaction().commit();
        return entities;
    }
}
