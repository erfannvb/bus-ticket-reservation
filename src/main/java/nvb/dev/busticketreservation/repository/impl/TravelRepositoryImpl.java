package nvb.dev.busticketreservation.repository.impl;

import jakarta.persistence.EntityManager;
import nvb.dev.busticketreservation.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.busticketreservation.entity.Travel;
import nvb.dev.busticketreservation.repository.TravelRepository;

public class TravelRepositoryImpl extends BaseRepositoryImpl<Long, Travel> implements TravelRepository {

    protected final EntityManager entityManager;

    public TravelRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Travel> getEntityClass() {
        return Travel.class;
    }
}
