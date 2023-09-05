package repositrory.custom;

import entity.Student;
import org.hibernate.Session;
import repositrory.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    void setSession(Session session);

    List<Student> getDetailsToTableView();

}
