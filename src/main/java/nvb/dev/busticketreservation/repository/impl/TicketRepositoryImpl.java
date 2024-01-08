package nvb.dev.busticketreservation.repository.impl;

import jakarta.persistence.EntityManager;
import nvb.dev.busticketreservation.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.busticketreservation.entity.Ticket;
import nvb.dev.busticketreservation.repository.TicketRepository;

public class TicketRepositoryImpl extends BaseRepositoryImpl<Long, Ticket> implements TicketRepository {

    protected final EntityManager entityManager;

    public TicketRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Ticket> getEntityClass() {
        return Ticket.class;
    }
}
