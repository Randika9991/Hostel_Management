package repositrory.custom;

import org.hibernate.Session;
import repositrory.SuperRepository;

import java.util.List;

public interface QueryRepository extends SuperRepository {
    List<String> loadStudentIds();

    void setSession(Session session);

}
