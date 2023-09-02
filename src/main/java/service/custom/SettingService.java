package service.custom;

import dto.CreateNewUserDto;
import service.SuperService;

public interface SettingService extends SuperService {
    public String saveuser(CreateNewUserDto createNewUserDto) ;
    public CreateNewUserDto getUser(int id) ;
    public boolean updateUser(CreateNewUserDto createNewUserDto) ;
    public boolean deleteUser(CreateNewUserDto createNewUserDto) ;
}
