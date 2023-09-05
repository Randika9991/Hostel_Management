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
    private String studentId;
    private String roomTypeId;
    private String status;

    public Reservation toEntity() {
        Reservation reservation = new Reservation();
        reservation.setResId(this.resId);
        reservation.setDate(this.date);
        reservation.setStatus(this.status);
        reservation.setStudentId(this.studentId);
        reservation.setRoomTypeId(this.roomTypeId);
        return reservation;
    }
}
