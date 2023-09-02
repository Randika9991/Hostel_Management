package service.custom.impl;

import config.SessionFactoryConfig;
import dto.RoomDto;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.custom.RoomRepository;
import repositrory.custom.impl.RoomRepositoryImpl;
import service.custom.RoomService;

public class RoomServiceImpl implements RoomService {

    private static RoomServiceImpl roomService;
    private final RoomRepository roomRepository;

    public RoomServiceImpl(){
        roomRepository = (RoomRepository) new RoomRepositoryImpl();
    }

    public static RoomServiceImpl getInstance() {
        return null == roomService
                ? roomService = new RoomServiceImpl()
                : roomService;
    }

    @Override
    public String saveRoom(RoomDto roomDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            String studentId = roomRepository.save(roomDto.toEntity());
            transaction.commit();
            session.close();
            return studentId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RoomDto getRoom(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            roomRepository.setSession(session);
            Room room = roomRepository.getId(id);
            session.close();
            return room.toDto();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateRoom(RoomDto roomDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            roomRepository.update(roomDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRoom(RoomDto roomDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            roomRepository.delete(roomDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }
}
