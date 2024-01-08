package nvb.dev.busticketreservation.service.impl;

import jakarta.persistence.EntityManager;
import nvb.dev.busticketreservation.base.service.impl.BaseServiceImpl;
import nvb.dev.busticketreservation.entity.Ticket;
import nvb.dev.busticketreservation.repository.TicketRepository;
import nvb.dev.busticketreservation.service.TicketService;

public class TicketServiceImpl extends BaseServiceImpl<Long, Ticket, TicketRepository> implements TicketService {

    protected final EntityManager entityManager;

    public TicketServiceImpl(EntityManager entityManager, TicketRepository repository) {
        super(entityManager, repository);
        this.entityManager = entityManager;
    }
}
