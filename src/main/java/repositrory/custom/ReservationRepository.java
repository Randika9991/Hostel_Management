package repositrory.custom;

import entity.Reservation;
import org.hibernate.Session;
import repositrory.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation,String> {
        void setSession(Session session);

}
