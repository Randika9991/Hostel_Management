package service;

import dto.StudentDto;

public interface StudentService {
    int saveStudent(StudentDto customer);

    StudentDto getStudent(int id);

    boolean updateStudent(StudentDto customer);

    boolean deleteStudent(StudentDto customer);
}
