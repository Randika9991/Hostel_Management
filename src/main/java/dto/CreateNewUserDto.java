package dto;

import entity.CreateNewUser;
import entity.Student;
import jakarta.persistence.Column;

public class CreateNewUserDto {
    private int userId;
    private String userName;
    private String userPassword;

    public CreateNewUserDto(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public CreateNewUserDto() {
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
        return "CreateNewUserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public CreateNewUser toEntity() {
        CreateNewUser createNewUser = new CreateNewUser();
        createNewUser.setUserId(this.userId);
        createNewUser.setUserName(this.userName);
        createNewUser.setUserPassword(this.userPassword);
        return createNewUser;
    }


}
