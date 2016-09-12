package io.kindstrom.senderremote.domain.database;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Command;

public interface CommandRepository {
    int insert(int senderId, Command command);

    int delete(int id);

    Command get(int id);

    List<Command> getForSender(int senderId);
}
