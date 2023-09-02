package repositrory;

public interface CrudRepository <T,ID> extends SuperRepository{


    public String save(T customer) ;

    public T getId(ID id) ;

    public void update(T student) ;

    public void delete(T student) ;
}
