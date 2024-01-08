package nvb.dev.busticketreservation.service;

import nvb.dev.busticketreservation.base.service.BaseService;
import nvb.dev.busticketreservation.entity.User;

import java.util.Optional;

public interface UserService extends BaseService<Long, User> {

    boolean validate(User user);

    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
