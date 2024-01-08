package nvb.dev.busticketreservation.base.repository.impl;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import nvb.dev.busticketreservation.base.entity.BaseEntity;
import nvb.dev.busticketreservation.base.repository.BaseRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public abstract class BaseRepositoryImpl<ID extends Serializable, Entity extends BaseEntity<ID>>
        implements BaseRepository<ID, Entity> {

    protected final EntityManager entityManager;

    public abstract Class<Entity> getEntityClass();

    @Override
    public void save(Entity entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Entity entity) {
        if (findById(entity.getId()).isPresent()) entityManager.merge(entity);
    }

    @Override
    public void remove(Entity entity) {
        entityManager.remove(entity);
    }

    @Override
    public Optional<Entity> findById(ID id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(), id));
    }

    @Override
    public Collection<Entity> findAll() {
        return entityManager.createQuery("from " + getEntityClass().getSimpleName(), getEntityClass())
                .getResultList();
    }
}
