package pl.practic.shirtshop.interfaces;

public interface CRUDService<D> {

    public D find(Integer id);

    public Integer save(D d);

    public D update(D d, Integer id);

    public void delete(Integer id);


}
