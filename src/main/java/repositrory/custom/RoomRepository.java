package repositrory.custom;

import entity.Room;
import entity.Student;
import org.hibernate.Session;
import repositrory.CrudRepository;

public interface RoomRepository extends CrudRepository<Room,String> {
    void setSession(Session session);
}
