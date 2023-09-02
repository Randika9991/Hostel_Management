package service.custom;

import dto.CreateNewUserDto;
import service.SuperService;

public interface NewAccountService extends SuperService {
    String saveuser(CreateNewUserDto customer);
}
