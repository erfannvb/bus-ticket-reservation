package nvb.dev.busticketreservation.service.impl;

import jakarta.persistence.EntityManager;
import nvb.dev.busticketreservation.base.service.impl.BaseServiceImpl;
import nvb.dev.busticketreservation.entity.User;
import nvb.dev.busticketreservation.repository.UserRepository;
import nvb.dev.busticketreservation.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<Long, User, UserRepository> implements UserService {

    protected final EntityManager entityManager;

    public UserServiceImpl(EntityManager entityManager, UserRepository repository) {
        super(entityManager, repository);
        this.entityManager = entityManager;
    }
}
