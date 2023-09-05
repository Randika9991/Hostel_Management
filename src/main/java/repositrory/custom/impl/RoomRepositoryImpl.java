package repositrory.custom.impl;

import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repositrory.custom.RoomRepository;

import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {
    private Session session;
    public RoomRepositoryImpl() {}
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Room> getDetailsToTableView() {
        String hql = "SELECT R FROM Room AS R";
        Query query = session.createQuery(hql);
        List<Room> roomList = query.list();
        return roomList;
    }

    @Override
    public List<String> loadRoomIds() {
        String sql = "SELECT room_type_id from room";
        Query query = session.createNativeQuery(sql);
        List list = query.list();
        return list;
    }

    @Override
    public String save(Room room) {
        return  (String)session.save(room);
    }

    @Override
    public Room getId(String integer) {
        return session.get(Room.class, integer);
    }

    @Override
    public void update(Room room) {
        session.update(room);
    }

    @Override
    public void delete(Room room) {
        session.delete(room);
    }
}
