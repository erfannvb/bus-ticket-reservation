package nvb.dev.busticketreservation.entity;

import jakarta.persistence.*;
import lombok.*;
import nvb.dev.busticketreservation.base.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_travel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Travel extends BaseEntity<Long> {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "start")
    private String start;

    @Column(name = "destination")
    private String destination;

    @Column(name = "move_date")
    private LocalDate moveDate;

    @Column(name = "move_time")
    private LocalTime moveTime;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "travel_code")
    private Integer travelCode;

}
