package pl.practic.shirtshop.interfaces;

public interface CRUDService<T> {

    public T find(int id);

    public int save(T t);

    public int update(T t,int id);

    public void delete(int id);


}
