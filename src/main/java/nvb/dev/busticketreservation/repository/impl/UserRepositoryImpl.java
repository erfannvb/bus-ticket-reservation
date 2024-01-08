package nvb.dev.busticketreservation.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import nvb.dev.busticketreservation.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.busticketreservation.entity.User;
import nvb.dev.busticketreservation.repository.UserRepository;

import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<Long, User> implements UserRepository {

    protected final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        String hql = "from User where username =: username and password =: password";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(hql, User.class);
        userTypedQuery.setParameter("username", username);
        userTypedQuery.setParameter("password", password);
        return Optional.ofNullable(userTypedQuery.getSingleResult());
    }
}
