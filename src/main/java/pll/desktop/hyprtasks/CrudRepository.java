package pll.desktop.hyprtasks;

import java.sql.ResultSet;
import java.util.List;

public interface CrudRepository<T> {
    T findById(int id);
    List<T> findAll();
    Task findLast();
    void save(T entity);
    void update(T entity);
    void delete(int id);
    T mapRow(ResultSet resultSet);
}
