package repositrory.impl;

import entity.CreateNewUser;
import entity.Student;
import org.hibernate.Session;
import repositrory.StudentRepository;
import repositrory.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private Session session;

    public UserRepositoryImpl() {}

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Integer save(CreateNewUser createNewUser) {
        return Integer.valueOf((String) session.save(createNewUser));
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
