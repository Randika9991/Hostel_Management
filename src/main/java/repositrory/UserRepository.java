package repositrory;

import entity.CreateNewUser;
import entity.Student;
import org.hibernate.Session;

public interface UserRepository extends CrudRepository<CreateNewUser,Integer>{
    void setSession(Session session);
}
