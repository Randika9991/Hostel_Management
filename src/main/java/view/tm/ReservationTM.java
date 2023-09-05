package view.tm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ReservationTM {
    private String resId;
    private LocalDate date;
    private String studentId;
    private String roomTypeId;
    private String status;
}
