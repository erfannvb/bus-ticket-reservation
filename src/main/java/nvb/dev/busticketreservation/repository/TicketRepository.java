package nvb.dev.busticketreservation.repository;

import nvb.dev.busticketreservation.base.repository.BaseRepository;
import nvb.dev.busticketreservation.entity.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends BaseRepository<Long, Ticket> {

    List<Ticket> findTicketByStartAndDestinationAndMoveDate(String start, String destination, LocalDate moveDate);

}
