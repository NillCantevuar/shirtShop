package pl.practic.shirtshop.interfaces;

public interface CRUDService<D> {

    public D find(int id);

    public int save(D d);


    public D update(D d,int id);

    public void delete(int id);


}
