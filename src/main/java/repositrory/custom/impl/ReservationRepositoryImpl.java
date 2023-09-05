package repositrory.custom.impl;

import entity.Reservation;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repositrory.custom.ReservationRepository;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    private Session session;
    public ReservationRepositoryImpl() {}

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Reservation> getDetailsToTableView() {
        String hql = "SELECT R FROM Reservation AS R";
        Query query = session.createQuery(hql);
        List<Reservation> reservationList = query.list();
        return reservationList;
    }

    @Override
    public int getReservationCount(String roomTypeId) {
        String sql = "SELECT COUNT(r) FROM Reservation r WHERE r.roomTypeId = :room_type_id";
        Query query = session.createQuery(sql);
        query.setParameter("room_type_id", roomTypeId);
        long count = (long) query.getSingleResult();
        return (int) count;
    }

    @Override
    public List<Object[]> getMaxPersonsPerRoom(String roomTypeId) {
        String sql = "SELECT r.perRoom,r.qty FROM Room r WHERE r.roomTypeId = :room_type_id";
        Query query = session.createQuery(sql);
        query.setParameter("room_type_id", roomTypeId);
        List list = (List) query.list();
        return list;
    }

    @Override
    public void updateAvailableRooms(int available_rooms, String roomTypeId) {
        Room room = session.get(Room.class,roomTypeId);
        System.out.println(room);
        room.setAvailableRooms(available_rooms);
        System.out.println(room);
        session.merge(room);
    }

    @Override
    public String save(Reservation reservation) {
        return  (String)session.save(reservation);
    }

    @Override
    public Reservation getId(String id) {
        return session.get(Reservation.class, id);
    }

    @Override
    public void update(Reservation reservation) {
        session.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        session.delete(reservation);
    }
}
