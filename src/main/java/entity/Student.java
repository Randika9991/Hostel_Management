package entity;

import dto.StudentDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;
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

    public Student() {
    }

    public Student(String studentId, String name, String address, String contactNo, LocalDate dateOfBirth, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                '}';
    }

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
