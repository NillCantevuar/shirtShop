package pl.practic.shirtshop.interfaces;

import java.util.List;

public interface CRUDService<D> {

    public List<D> findAll();

    public D find(Integer id);

    public Integer save(D d);

    public Integer update(D d, Integer id);

    public void delete(Integer id);


}
