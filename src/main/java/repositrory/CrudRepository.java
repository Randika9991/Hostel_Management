package repositrory;

import entity.Student;

public interface CrudRepository <T,ID>{


    public ID save(T customer) ;

    public T getId(ID id) ;

    public void update(T student) ;

    public void delete(T student) ;
}
