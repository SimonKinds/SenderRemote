package io.kindstrom.senderremote.domain.repository;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Group;

public interface GroupRepository {
    int insert(Group sender);

    int delete(int id);

    Group get(int id);

    List<Group> getAll();
}
