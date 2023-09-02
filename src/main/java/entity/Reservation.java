package entity;
import dto.ReservationDto;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "res_id")
    private String resId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id" ,unique = true)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private Room room;

    public ReservationDto toDto() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setResId(this.resId);
        reservationDto.setDate(this.date);
        reservationDto.setStatus(this.status);
        reservationDto.setStudent(this.student);
        reservationDto.setRoom(this.room);
        return reservationDto;
    }
}
