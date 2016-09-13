package io.kindstrom.senderremote.domain.database;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Port;

interface PortRepository {
    int insert(int senderId, Port port);

    Port get(int id);

    List<Port> getForSender(int senderId);
}
