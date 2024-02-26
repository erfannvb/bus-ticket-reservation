package nvb.dev.busticketreservation.repository;

import nvb.dev.busticketreservation.base.repository.BaseRepository;
import nvb.dev.busticketreservation.entity.Travel;

import java.util.List;

public interface TravelRepository extends BaseRepository<Long, Travel> {

    List<Travel> findTravelByUserId(long userId);

    void deleteTravelById(long travelId);

}
