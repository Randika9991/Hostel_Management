package repositrory.custom.impl;

import entity.Reservation;
import org.hibernate.Session;
import repositrory.custom.ReservationRepository;

public class ReservationRepositoryImpl implements ReservationRepository {

    private Session session;
    public ReservationRepositoryImpl() {}

    @Override
    public void setSession(Session session) {
        this.session = session;
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
