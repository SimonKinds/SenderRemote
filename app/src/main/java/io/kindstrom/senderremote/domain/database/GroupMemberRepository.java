package io.kindstrom.senderremote.domain.database;

import java.util.List;

public interface GroupMemberRepository {
    int insert(int groupId, int senderId);

    int delete(int groupId, int senderId);

    List<Integer> get(int groupId);
}
