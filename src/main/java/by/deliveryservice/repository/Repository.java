package by.deliveryservice.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    List<T> getAll();

    Optional<T> get(int id);

    void delete(int id);

    T save(T t);
}
