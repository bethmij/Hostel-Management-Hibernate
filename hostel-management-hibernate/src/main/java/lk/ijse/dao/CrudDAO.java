package lk.ijse.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T, ID> extends SuperDAO{
    List<T> getAll() ;

    boolean save(T entity);

    boolean update(T entity) ;

    boolean delete(ID id);

    T search(String id) ;
}
