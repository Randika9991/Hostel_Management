package repositrory;

import repositrory.custom.impl.ReservationRepositoryImpl;
import repositrory.custom.impl.RoomRepositoryImpl;
import repositrory.custom.impl.StudentRepositoryimpl;
import repositrory.custom.impl.UserRepositoryImpl;
import service.ServiceFactory;
import service.SuperService;
import service.custom.impl.*;

public class RepositoryFactory {
    private static RepositoryFactory repositoryFactory;

    private RepositoryFactory() {
    }

    public static RepositoryFactory getRepositoryFactory() {
        return (repositoryFactory == null) ? repositoryFactory = new RepositoryFactory() : repositoryFactory;
    }
    public enum RepositoryType{
       ROOM,RESERVATION,USER,STUDENT
    }
    public <T extends SuperRepository>T getRepository(RepositoryType type) {
        switch (type) {
            case ROOM:
                return (T)new RoomRepositoryImpl();
            case RESERVATION:
                return (T)new ReservationRepositoryImpl();
            case USER:
                return (T)new UserRepositoryImpl();
            case STUDENT:
                return (T)new StudentRepositoryimpl();
            default:
                return null;
        }
    }

}
