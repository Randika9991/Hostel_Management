package entity;

import dto.CreateNewUserDto;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class CreateNewUser {
    @Id
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_passwerd")
    private String userPassword;

    public CreateNewUserDto toDto() {
        CreateNewUserDto createNewUserDto = new CreateNewUserDto();
        createNewUserDto.setUserName(this.userName);
        createNewUserDto.setUserPassword(this.userPassword);
        return createNewUserDto;
    }
}
