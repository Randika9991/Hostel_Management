package service.custom;

import dto.CreateNewUserDto;
import service.SuperService;

public interface LoginService extends SuperService {
    CreateNewUserDto getUser(String id);
}
