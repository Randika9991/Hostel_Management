package dto;

import entity.Student;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentDto {
    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dateOfBirth;
    private String gender;

    public Student toEntity() {
        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setName(this.name);
        student.setAddress(this.address);
        student.setContactNo(this.contactNo);
        student.setDateOfBirth(this.dateOfBirth);
        student.setGender(this.gender);
        return student;
    }

}
