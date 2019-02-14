package storage;

import java.util.List;

public interface GenericDAO<T> {
    List<T> findAll();

    T findById(Long id);

    void update(T value);

    T add(T value);

    void delete(T value);

    T deleteById(Long id);
}
