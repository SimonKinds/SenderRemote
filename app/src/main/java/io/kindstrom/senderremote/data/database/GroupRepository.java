package io.kindstrom.senderremote.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.data.database.contract.GroupRepositoryContract;
import io.kindstrom.senderremote.domain.model.Group;

public class GroupRepository extends RepositoryImpl<Group> {

    @Inject
    public GroupRepository(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public int insert(Group group) {
        ContentValues vals = new ContentValues(1);
        vals.put(GroupRepositoryContract.COLUMN_NAME, group.getName());
        return (int) db.insert(GroupRepositoryContract.TABLE_NAME, null, vals);
    }

    @Override
    public int delete(int id) {
        String whereClause = GroupRepositoryContract._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(GroupRepositoryContract.TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public Group get(int id) {
        String columns[] = {GroupRepositoryContract._ID, GroupRepositoryContract.COLUMN_NAME};
        String selection = GroupRepositoryContract._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(GroupRepositoryContract.TABLE_NAME,
                columns, selection, selectionArgs, null, null, null);

        cursor.moveToFirst();
        return mapSingle(cursor);
    }

    @Override
    public List<Group> getAll() {
        String columns[] = {GroupRepositoryContract._ID, GroupRepositoryContract.COLUMN_NAME};
        Cursor cursor = db.query(GroupRepositoryContract.TABLE_NAME,
                columns, null, null, null, null, null);

        return map(cursor);
    }

    @Override
    public Group mapSingle(Cursor cursor) {
        int idIndex = cursor.getColumnIndexOrThrow(GroupRepositoryContract._ID);
        int nameIndex = cursor.getColumnIndexOrThrow(GroupRepositoryContract.COLUMN_NAME);

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);
        return new Group(id, name, null);
    }
}
