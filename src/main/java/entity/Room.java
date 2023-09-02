package entity;
import dto.RoomDto;
import dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "room_type_id")
    private String roomTypeId;
    @Column(name = "type")
    private String type;
    @Column(name = "key_manye")
    private String keyMoney;
    @Column(name = "qty")
    private int qty;
    @Column(name = "available_room_qty", nullable = true)
    private int availableRooms;
    @Column(name = "per_room", nullable = false)
    private int perRoom;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "room")
    private List<Reservation> orders = new ArrayList<>();

    public RoomDto toDto() {
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomTypeId(this.roomTypeId);
        roomDto.setType(this.type);
        roomDto.setKeyMoney(this.keyMoney);
        roomDto.setQty(this.qty);
        roomDto.setAvailableRooms(this.availableRooms);
        roomDto.setPerRoom(this.perRoom);

        return roomDto;
    }
}
