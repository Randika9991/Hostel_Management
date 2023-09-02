package dto;

import entity.CreateNewUser;
import entity.Student;
import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateNewUserDto {
    private String userName;
    private String userPassword;

    public CreateNewUser toEntity() {
        CreateNewUser createNewUser = new CreateNewUser();
        createNewUser.setUserName(this.userName);
        createNewUser.setUserPassword(this.userPassword);
        return createNewUser;
    }
}
