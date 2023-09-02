package service;

import service.custom.ReservationService;
import service.custom.RoomService;
import service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getServiceFactory() {
       return (serviceFactory == null) ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public enum ServiceType{
        STUDENT,ROOM,RESERVATION,LOGIN,NEW_ACCOUNT,SETTING
    }

    public <T extends SuperService>T getService(ServiceType type) {
        switch (type) {
            case STUDENT:
                return (T)new StudentServiceImpl();
            case ROOM:
                return (T)new RoomServiceImpl();
            case RESERVATION:
                return (T) new ReservationServiceImpl();
            case LOGIN:
                return (T) new LoginServiceImpl();
            case NEW_ACCOUNT:
                return (T) new NewAccountServiceImpl();
            case SETTING:
                return (T) new SettingServiceImpl();
            default:
                return null;
        }
    }
}
