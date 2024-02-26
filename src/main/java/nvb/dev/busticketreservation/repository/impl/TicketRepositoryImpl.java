package nvb.dev.busticketreservation.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import nvb.dev.busticketreservation.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.busticketreservation.entity.Ticket;
import nvb.dev.busticketreservation.repository.TicketRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Ticket> findTicketByStartAndDestinationAndMoveDate(String start, String destination, LocalDate date) {
        String hql = "from Ticket where start =: start and destination =: destination and date =: date order by time";
        TypedQuery<Ticket> ticketTypedQuery = entityManager.createQuery(hql, Ticket.class);
        ticketTypedQuery.setParameter("start", start);
        ticketTypedQuery.setParameter("destination", destination);
        ticketTypedQuery.setParameter("date", date);
        return ticketTypedQuery.getResultList();
    }
}
