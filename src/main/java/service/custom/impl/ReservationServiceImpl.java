package service.custom.impl;

import config.SessionFactoryConfig;
import dto.ReservationDto;
import dto.StudentDto;
import entity.Reservation;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.RepositoryFactory;
import repositrory.custom.QueryRepository;
import repositrory.custom.ReservationRepository;
import repositrory.custom.RoomRepository;
import repositrory.custom.StudentRepository;
import repositrory.custom.impl.ReservationRepositoryImpl;
import service.custom.ReservationService;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryType.RESERVATION);
    QueryRepository queryRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryType.QUERY);
    RoomRepository roomRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryType.ROOM);

    @Override
    public String saveReservation(ReservationDto reservationDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            String studentId = reservationRepository.save(reservationDto.toEntity());
            updateAvailableRooms(reservationDto);
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

    public void updateAvailableRooms(ReservationDto reservationDTO){
        String roomTypeId = reservationDTO.toEntity().getRoomTypeId();

        int count = reservationRepository.getReservationCount(roomTypeId);

        List<Object[]> list = reservationRepository.getMaxPersonsPerRoom(roomTypeId);
        Object[] result = list.get(0);
        int perRoom = (Integer) result[0];
        int roomQuantity = (Integer) result[1];

        int unavailable_rooms = count / perRoom;
        int available_rooms = roomQuantity - unavailable_rooms;

        reservationRepository.updateAvailableRooms(available_rooms, roomTypeId);

    }

    @Override
    public ReservationDto getReservation(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            reservationRepository.setSession(session);
            Reservation reservation = reservationRepository.getId(id);
            session.close();
            return reservation.toDto();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateReservation(ReservationDto reservationDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            reservationRepository.update(reservationDto.toEntity());
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
    public boolean deleteReservation(ReservationDto reservationDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            reservationRepository.delete(reservationDto.toEntity());
            updateAvailableRooms(reservationDto);
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
    public List<String> loadStudentIds() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            queryRepository.setSession(session);
            List<String> ids = queryRepository.loadStudentIds();
            transaction.commit();
            session.close();
            return ids;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println(e);
            return null;
        }

    }

    @Override
    public List<String> loadRoomIds() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            List<String> ids = roomRepository.loadRoomIds();
            transaction.commit();
            session.close();
            return ids;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ObservableList<ReservationDto> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            List<Reservation> studentList = reservationRepository.getDetailsToTableView();
            ObservableList<ReservationDto> studentObList = FXCollections.observableArrayList();
            for (Reservation rts: studentList){
                studentObList.add(
                        new ReservationDto(
                                rts.getResId(),
                                rts.getDate(),
                                rts.getStudentId(),
                                rts.getRoomTypeId(),
                                rts.getStatus()
                        )
                );
            }
            transaction.commit();
            session.close();
            return studentObList;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }


}
