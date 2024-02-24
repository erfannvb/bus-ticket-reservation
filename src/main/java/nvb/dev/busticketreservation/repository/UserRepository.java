package nvb.dev.busticketreservation.repository;

import nvb.dev.busticketreservation.base.repository.BaseRepository;
import nvb.dev.busticketreservation.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Long, User> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
