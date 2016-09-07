package io.kindstrom.senderremote.domain.database;

import java.util.List;

public interface Repository<E> {
    int insert(E e);

    int delete(int id);

    E get(int id);

    List<E> getAll();
}
