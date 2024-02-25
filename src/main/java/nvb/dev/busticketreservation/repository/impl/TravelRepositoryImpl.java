package nvb.dev.busticketreservation.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import nvb.dev.busticketreservation.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.busticketreservation.entity.Travel;
import nvb.dev.busticketreservation.repository.TravelRepository;

import java.util.List;

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

    @Override
    public List<Travel> findTravelByUserId(long userId) {
        String hql = "from Travel t where t.user.id =: userId";
        TypedQuery<Travel> travelTypedQuery = entityManager.createQuery(hql, Travel.class);
        travelTypedQuery.setParameter("userId", userId);
        return travelTypedQuery.getResultList();
    }
}
