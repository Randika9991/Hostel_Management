package service.impl;

import config.SessionFactoryConfig;
import dto.CreateNewUserDto;
import dto.StudentDto;
import entity.CreateNewUser;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.StudentRepository;
import repositrory.UserRepository;
import repositrory.impl.StudentRepositoryimpl;
import repositrory.impl.UserRepositoryImpl;
import service.UserService;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;
    private final UserRepository userRepository;

    public UserServiceImpl(){
        userRepository = (UserRepository) new UserRepositoryImpl();
    }

    public static UserServiceImpl getInstance() {
        return null == userService
                ? userService = new UserServiceImpl()
                : userService;
    }


    @Override
    public int saveuser(CreateNewUserDto createNewUserDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
           userRepository.setSession(session);
           int UserId = userRepository.save(createNewUserDto.toEntity());
            transaction.commit();
            session.close();
           return UserId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public CreateNewUserDto getUser(int id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
           userRepository.setSession(session);
            CreateNewUser createNewUser = userRepository.getId(id);
            session.close();
             return createNewUser.toDto();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateUser(CreateNewUserDto createNewUserDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            userRepository.update(createNewUserDto.toEntity());
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
    public boolean deleteUser(CreateNewUserDto createNewUserDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            //studentRepository.setSession(session);
           // studentRepository.deleteStudent(customer.toEntity());
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


}
