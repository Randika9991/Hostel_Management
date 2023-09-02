package service.custom.impl;

import config.SessionFactoryConfig;
import dto.CreateNewUserDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.RepositoryFactory;
import repositrory.custom.UserRepository;
import service.custom.NewAccountService;

public class NewAccountServiceImpl implements NewAccountService {

    UserRepository userRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryType.USER);

    @Override
    public String saveuser(CreateNewUserDto customer) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            String UserId = userRepository.save(customer.toEntity());
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
}
