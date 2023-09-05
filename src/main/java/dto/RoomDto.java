package dto;

import entity.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomDto {
    private String roomTypeId;
    private String type;
    private String keyMoney;
    private int qty;
    private int availableRooms;
    private int perRoom;

    public Room toEntity() {
        Room room = new Room();
        room.setRoomTypeId(this.roomTypeId);
        room.setType(this.type);
        room.setKeyMoney(this.keyMoney);
        room.setQty(this.qty);
        room.setPerRoom(this.perRoom);
        room.setAvailableRooms(this.availableRooms);
        return room;
    }
}
