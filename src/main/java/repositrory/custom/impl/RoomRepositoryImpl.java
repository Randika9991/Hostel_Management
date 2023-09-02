package repositrory.custom.impl;

import entity.Room;
import org.hibernate.Session;
import repositrory.custom.RoomRepository;

public class RoomRepositoryImpl implements RoomRepository {
    private Session session;
    public RoomRepositoryImpl() {}
    @Override
    public void setSession(Session session) {
        this.session = session;
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
