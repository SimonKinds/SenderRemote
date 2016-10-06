package io.kindstrom.senderremote.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.data.database.contract.GroupMemberRepositoryContract;
import io.kindstrom.senderremote.domain.repository.GroupMemberRepository;


public class GroupMemberRepositoryImpl extends RepositoryImpl<Integer> implements GroupMemberRepository {

    @Inject
    public GroupMemberRepositoryImpl(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Integer mapSingle(Cursor cursor) {
        int senderIdIndex = cursor.getColumnIndexOrThrow(GroupMemberRepositoryContract.COLUMN_SENDER_ID);

        return cursor.getInt(senderIdIndex);
    }

    @Override
    public int insert(int groupId, int senderId) {
        ContentValues vals = new ContentValues(2);
        vals.put(GroupMemberRepositoryContract.COLUMN_GROUP_ID, groupId);
        vals.put(GroupMemberRepositoryContract.COLUMN_SENDER_ID, senderId);

        return (int) db.insert(GroupMemberRepositoryContract.TABLE_NAME, null, vals);
    }

    @Override
    public int delete(int groupId, int senderId) {
        String whereClause = GroupMemberRepositoryContract.COLUMN_GROUP_ID + " = ? AND "
                + GroupMemberRepositoryContract.COLUMN_SENDER_ID + " = ?";
        String[] whereArgs = {String.valueOf(groupId), String.valueOf(senderId)};
        return db.delete(GroupMemberRepositoryContract.TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public List<Integer> get(int groupId) {
        String[] columns = {GroupMemberRepositoryContract._ID, GroupMemberRepositoryContract.COLUMN_SENDER_ID};
        String whereClause = GroupMemberRepositoryContract.COLUMN_GROUP_ID + " = ?";
        String[] whereArgs = {String.valueOf(groupId)};
        return map(db.query(GroupMemberRepositoryContract.TABLE_NAME, columns, whereClause, whereArgs, null, null, null));
    }
}
