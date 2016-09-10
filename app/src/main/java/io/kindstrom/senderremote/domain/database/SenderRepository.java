package io.kindstrom.senderremote.domain.database;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Sender;

public interface SenderRepository {
    int insert(Sender sender);

    int delete(int id);

    Sender get(int id);

    List<Sender> getAll();
}
