package view.tm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class StudentTM {
    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dateOfBirth;
    private String gender;
}
