package coursework.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    void save(T element);

    Optional<T> get(Long id);

    List<T> getAll();

    void update(Long id, T element);

    void delete(Long id);
}
