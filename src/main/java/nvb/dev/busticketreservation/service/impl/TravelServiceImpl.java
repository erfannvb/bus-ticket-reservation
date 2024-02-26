package nvb.dev.busticketreservation.service.impl;

import jakarta.persistence.EntityManager;
import nvb.dev.busticketreservation.base.service.impl.BaseServiceImpl;
import nvb.dev.busticketreservation.entity.Travel;
import nvb.dev.busticketreservation.repository.TravelRepository;
import nvb.dev.busticketreservation.service.TravelService;

import java.util.List;

public class TravelServiceImpl extends BaseServiceImpl<Long, Travel, TravelRepository> implements TravelService {

    protected final EntityManager entityManager;

    public TravelServiceImpl(EntityManager entityManager, TravelRepository repository) {
        super(entityManager, repository);
        this.entityManager = entityManager;
    }

    @Override
    public List<Travel> findTravelByUserId(long userId) {
        return repository.findTravelByUserId(userId);
    }

    @Override
    public void deleteTravelById(long travelId) {
        repository.deleteTravelById(travelId);
    }
}
