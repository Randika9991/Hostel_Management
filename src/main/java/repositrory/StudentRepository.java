package repositrory;

import entity.Student;
import org.hibernate.Session;

public interface StudentRepository extends CrudRepository<Student,Integer>{
    void setSession(Session session);
}
