package service.custom;

import dto.RoomDto;
import dto.StudentDto;
import javafx.collections.ObservableList;
import service.SuperService;

public interface RoomService extends SuperService {
    String saveRoom(RoomDto roomDto);

    RoomDto getRoom(String id);

    boolean updateRoom(RoomDto roomDto);

    boolean deleteRoom(RoomDto roomDto);

    ObservableList<RoomDto> getDetailsToTableView();
}
