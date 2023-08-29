package service.impl;

import config.SessionFactoryConfig;
import dto.StudentDto;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.StudentRepository;
import repositrory.impl.StudentRepositoryimpl;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private static StudentServiceImpl studentService;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(){
        studentRepository = (StudentRepository) new StudentRepositoryimpl();
    }

    public static StudentServiceImpl getInstance() {
        return null == studentService
                ? studentService = new StudentServiceImpl()
                : studentService;
    }


    @Override
    public int saveStudent(StudentDto studentDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            int studentId = studentRepository.save(studentDto.toEntity());
            transaction.commit();
            session.close();
            return studentId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public StudentDto getStudent(int id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            studentRepository.setSession(session);
            Student student = studentRepository.getId(id);
            session.close();
            return student.toDto();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateStudent(StudentDto customer) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            studentRepository.update(customer.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(StudentDto customer) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            studentRepository.delete(customer.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    /*public ObservableList<Student> getAllCustomer() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        int id = 0;
        try {
            List<Student> customers = session.createQuery("from Student", Student.class).list();
            ObservableList<Student> dataList = FXCollections.observableArrayList(customers);
            transaction.commit();
            session.close();
            return dataList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            throw e;
        }
    }*/


}
