package service.custom.impl;

import config.SessionFactoryConfig;
import dto.CreateNewUserDto;
import entity.CreateNewUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.RepositoryFactory;
import repositrory.custom.UserRepository;
import repositrory.custom.impl.UserRepositoryImpl;
import service.custom.SettingService;

public class SettingServiceImpl implements SettingService {

    UserRepository userRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryType.USER);

    @Override
    public String saveuser(CreateNewUserDto createNewUserDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
           userRepository.setSession(session);
           String UserId = userRepository.save(createNewUserDto.toEntity());
            transaction.commit();
            session.close();
           return UserId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CreateNewUserDto getUser(String id) {
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
