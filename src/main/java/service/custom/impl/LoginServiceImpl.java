package service.custom.impl;

import config.SessionFactoryConfig;
import dto.CreateNewUserDto;
import entity.CreateNewUser;
import entity.Room;
import org.hibernate.Session;
import repositrory.RepositoryFactory;
import repositrory.custom.StudentRepository;
import repositrory.custom.UserRepository;
import service.custom.LoginService;

public class LoginServiceImpl implements LoginService {
    UserRepository userRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryType.USER);
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
}
