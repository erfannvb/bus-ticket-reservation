package nvb.dev.busticketreservation.service;

import nvb.dev.busticketreservation.base.service.BaseService;
import nvb.dev.busticketreservation.entity.Travel;

import java.util.List;

public interface TravelService extends BaseService<Long, Travel> {

    List<Travel> findTravelByUserId(long userId);

}
