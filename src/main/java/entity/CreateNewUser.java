package entity;

import dto.CreateNewUserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class CreateNewUser {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_passwerd")
    private String userPassword;

    public CreateNewUser() {
    }

    public CreateNewUser(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "CreateNewUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public CreateNewUserDto toDto() {
        CreateNewUserDto createNewUserDto = new CreateNewUserDto();
        createNewUserDto.setUserId(this.userId);
        createNewUserDto.setUserName(this.userName);
        createNewUserDto.setUserPassword(this.userPassword);
        return createNewUserDto;
    }
}
