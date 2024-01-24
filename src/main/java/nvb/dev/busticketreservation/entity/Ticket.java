package nvb.dev.busticketreservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvb.dev.busticketreservation.base.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tbl_ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket extends BaseEntity<Long> {

    @NotNull(message = "ticketOwner cannot be null")
    @NotEmpty(message = "ticketOwner cannot be empty")
    @Column(name = "ticket_owner", nullable = false)
    private String ticketOwner;

    @NotNull(message = "start cannot be null")
    @NotEmpty(message = "start cannot be empty")
    @Column(name = "start", nullable = false)
    private String start;

    @NotNull(message = "destination cannot be null")
    @NotEmpty(message = "destination cannot be empty")
    @Column(name = "destination", nullable = false)
    private String destination;

    @NotNull(message = "moveDate cannot be null")
    @NotEmpty(message = "moveDate cannot be empty")
    @Column(name = "move_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate moveDate;

    @NotNull(message = "moveTime cannot be null")
    @NotEmpty(message = "moveTime cannot be empty")
    @Column(name = "move_time", nullable = false)
    private LocalDateTime moveTime;

    @NotNull(message = "travelId cannot be null")
    @NotEmpty(message = "travelId cannot be empty")
    @Column(name = "travel_id", nullable = false)
    private UUID travelId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Ticket(String ticketOwner, String start, String destination,
                  LocalDate moveDate, LocalDateTime moveTime, UUID travelId) {
        this.ticketOwner = ticketOwner;
        this.start = start;
        this.destination = destination;
        this.moveDate = moveDate;
        this.moveTime = moveTime;
        this.travelId = travelId;
    }
}
