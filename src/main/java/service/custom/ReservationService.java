package service.custom;

import dto.ReservationDto;
import dto.StudentDto;
import service.SuperService;

public interface ReservationService extends SuperService {
    String saveReservation(ReservationDto reservationDto);

    ReservationDto getReservation(String id);

    boolean updateReservation(ReservationDto reservationDto);

    boolean deleteReservation(ReservationDto reservationDto);
}
