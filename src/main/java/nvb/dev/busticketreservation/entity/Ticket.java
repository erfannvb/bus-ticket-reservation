package nvb.dev.busticketreservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import nvb.dev.busticketreservation.base.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "tbl_ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
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

    @Column(name = "move_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate moveDate;

    @Column(name = "move_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime moveTime;

    @Column(name = "travel_code", nullable = false, unique = true)
    private Integer travelCode;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Ticket(String ticketOwner, String start, String destination,
                  LocalDate moveDate, LocalTime moveTime) {
        this.ticketOwner = ticketOwner;
        this.start = start;
        this.destination = destination;
        this.moveDate = moveDate;
        this.moveTime = moveTime;
        this.travelCode = new Random().nextInt(900) + 100;
    }
}
