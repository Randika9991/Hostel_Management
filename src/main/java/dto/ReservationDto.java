package dto;

import entity.Reservation;
import entity.Room;
import entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ReservationDto {
    private String resId;
    private LocalDate date;
    private String status;
    private Student student;
    private Room room;

    public Reservation toEntity() {
        Reservation reservation = new Reservation();
        reservation.setResId(this.resId);
        reservation.setDate(this.date);
        reservation.setStatus(this.status);
        reservation.setStudent(this.student);
        reservation.setRoom(this.room);
        return reservation;
    }
}
