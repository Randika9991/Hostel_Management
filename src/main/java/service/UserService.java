package service;

import config.SessionFactoryConfig;
import dto.CreateNewUserDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface UserService {
    ;
    public int saveuser(CreateNewUserDto createNewUserDto) ;
    public CreateNewUserDto getUser(int id) ;
    public boolean updateUser(CreateNewUserDto createNewUserDto) ;
    public boolean deleteUser(CreateNewUserDto createNewUserDto) ;
}
