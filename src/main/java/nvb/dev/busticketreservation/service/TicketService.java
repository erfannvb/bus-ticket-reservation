package nvb.dev.busticketreservation.service;

import nvb.dev.busticketreservation.base.service.BaseService;
import nvb.dev.busticketreservation.entity.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService extends BaseService<Long, Ticket> {

    boolean validate(Ticket ticket);

    List<Ticket> findTicketByStartAndDestinationAndMoveDate(String start, String destination, LocalDate moveDate);

}
