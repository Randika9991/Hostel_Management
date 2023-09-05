package service.custom.impl;

import config.SessionFactoryConfig;
import dto.StudentDto;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.RepositoryFactory;
import repositrory.custom.StudentRepository;
import repositrory.custom.UserRepository;
import repositrory.custom.impl.StudentRepositoryimpl;
import service.custom.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentRepository userRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryType.STUDENT);
    @Override
    public String saveStudent(StudentDto studentDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            String studentId = userRepository.save(studentDto.toEntity());
            transaction.commit();
            session.close();
            return studentId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StudentDto getStudent(int id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            userRepository.setSession(session);
            Student student = userRepository.getId(id);
            session.close();
            return student.toDto();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            userRepository.update(studentDto.toEntity());
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
    public boolean deleteStudent(StudentDto studentDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            userRepository.delete(studentDto.toEntity());
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
    public ObservableList<StudentDto> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            userRepository.setSession(session);
            List<Student> studentList = userRepository.getDetailsToTableView();
            ObservableList<StudentDto> studentObList = FXCollections.observableArrayList();
            for (Student s: studentList){
                studentObList.add(
                        new StudentDto(
                                s.getStudentId(),
                                s.getName(),
                                s.getAddress(),
                                s.getContactNo(),
                                s.getDateOfBirth(),
                                s.getGender()
                        )
                );
            }
            transaction.commit();
            session.close();
            return studentObList;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
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
