package service.custom.impl;

import config.SessionFactoryConfig;
import dto.RoomDto;
import dto.StudentDto;
import entity.Room;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.RepositoryFactory;
import repositrory.custom.RoomRepository;
import repositrory.custom.impl.RoomRepositoryImpl;
import service.ServiceFactory;
import service.custom.RoomService;
import service.custom.StudentService;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    RoomRepository roomRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryType.ROOM);

    @Override
    public String saveRoom(RoomDto roomDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            Room room = roomDto.toEntity();
            room.setAvailableRooms(roomDto.getQty());
            String studentId = roomRepository.save(room);
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

    @Override
    public ObservableList<RoomDto> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            roomRepository.setSession(session);
            List<Room> studentList = roomRepository.getDetailsToTableView();
            ObservableList<RoomDto> roomObList = FXCollections.observableArrayList();
            for (Room s: studentList){
                roomObList.add(
                        new RoomDto(
                                s.getRoomTypeId(),
                                s.getType(),
                                s.getKeyMoney(),
                                s.getQty(),
                                s.getAvailableRooms(),
                                s.getPerRoom()
                        )
                );
            }
            transaction.commit();
            session.close();
            return roomObList;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }


}
