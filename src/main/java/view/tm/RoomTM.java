package view.tm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomTM {
    private String roomTypeId;
    private String type;
    private String keyMoney;
    private int qty;
    private int availableRooms;
    private int perRoom;
}
