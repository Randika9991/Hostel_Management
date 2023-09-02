package entity;

import jakarta.persistence.*;
import lombok.*;
import dto.StudentDto;

import java.time.LocalDate;

import lombok.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "student_name")
    private String name;
    @Column(name = "adress")
    private String address;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "dob")
    private LocalDate dateOfBirth;
    @Column(name="gender")
    private String gender;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "student")
    private List<Reservation> reservations = new ArrayList<>();

    public StudentDto toDto() {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(this.studentId);
        studentDto.setName(this.name);
        studentDto.setAddress(this.address);
        studentDto.setContactNo(this.contactNo);
        studentDto.setDateOfBirth(this.dateOfBirth);
        studentDto.setGender(this.gender);
        return studentDto;
    }
}
