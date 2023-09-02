package repositrory.custom.impl;

import entity.CreateNewUser;
import org.hibernate.Session;
import repositrory.custom.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private Session session;

    public UserRepositoryImpl() {}

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String save(CreateNewUser createNewUser) {
        return (String) session.save(createNewUser);
    }

    @Override
    public CreateNewUser getId(Integer id) {
        return session.get(CreateNewUser.class, id);
    }

    @Override
    public void update(CreateNewUser createNewUser) {
        session.update(createNewUser);
    }

    @Override
    public void delete(CreateNewUser createNewUser) {
        session.delete(createNewUser);
    }
}
