package service.custom.impl;

import config.SessionFactoryConfig;
import dto.ReservationDto;
import entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositrory.custom.ReservationRepository;
import service.custom.ReservationService;

public class ReservationServiceImpl implements ReservationService {

    private static ReservationServiceImpl reservationService;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(){
        reservationRepository = (ReservationRepository) new ReservationServiceImpl();
    }

    public static ReservationServiceImpl getInstance() {
        return null == reservationService
                ? reservationService = new ReservationServiceImpl()
                : reservationService;
    }

    @Override
    public String saveReservation(ReservationDto reservationDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            String studentId = reservationRepository.save(reservationDto.toEntity());
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
