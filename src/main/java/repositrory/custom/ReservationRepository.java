package repositrory.custom;

import entity.Reservation;
import org.hibernate.Session;
import repositrory.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,String> {
    void setSession(Session session);

    List<Reservation> getDetailsToTableView();

    int getReservationCount(String roomTypeId);

    List<Object[]> getMaxPersonsPerRoom(String roomTypeId);

    void updateAvailableRooms(int available_rooms, String roomTypeId);
}
