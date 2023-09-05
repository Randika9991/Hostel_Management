package repositrory.custom;

import entity.CreateNewUser;
import entity.Student;
import org.hibernate.Session;
import repositrory.CrudRepository;

public interface UserRepository extends CrudRepository<CreateNewUser,String> {
    void setSession(Session session);
}
