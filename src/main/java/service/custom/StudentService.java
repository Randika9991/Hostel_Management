package service.custom;

import dto.StudentDto;
import javafx.collections.ObservableList;
import service.SuperService;

public interface StudentService extends SuperService {
    String saveStudent(StudentDto customer);

    StudentDto getStudent(int id);

    boolean updateStudent(StudentDto customer);

    boolean deleteStudent(StudentDto customer);

    ObservableList<StudentDto> getDetailsToTableView();
}
