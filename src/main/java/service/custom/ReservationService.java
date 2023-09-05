package service.custom;

import dto.ReservationDto;
import dto.StudentDto;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface ReservationService extends SuperService {
    String saveReservation(ReservationDto reservationDto);

    ReservationDto getReservation(String id);

    boolean updateReservation(ReservationDto reservationDto);

    boolean deleteReservation(ReservationDto reservationDto);

    List<String> loadStudentIds();

    List<String> loadRoomIds();

    ObservableList<ReservationDto> getDetailsToTableView();
}
