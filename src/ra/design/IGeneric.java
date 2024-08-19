package ra.design;

import java.util.List;

public interface IGeneric<T,E>{
    List<T> getAll();
    void save(T t);
    T findById(E e);
    void delete(E e);
}
