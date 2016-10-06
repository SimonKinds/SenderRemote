package io.kindstrom.senderremote.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.data.database.contract.OutputRepositoryContract;
import io.kindstrom.senderremote.domain.model.Port;
import io.kindstrom.senderremote.domain.repository.OutputRepository;


public class OutputRepositoryImpl extends RepositoryImpl<Port> implements OutputRepository {

    @Inject
    public OutputRepositoryImpl(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Port mapSingle(Cursor cursor) {
        int idIndex = cursor.getColumnIndexOrThrow(OutputRepositoryContract._ID);
        int numberIndex = cursor.getColumnIndexOrThrow(OutputRepositoryContract.COLUMN_NUMBER);
        int nameIndex = cursor.getColumnIndexOrThrow(OutputRepositoryContract.COLUMN_NAME);

        int id = cursor.getInt(idIndex);
        int number = cursor.getInt(numberIndex);
        String name = cursor.getString(nameIndex);

        return new Port(id, number, name);
    }

    @Override
    public int insert(int senderId, Port port) {
        ContentValues values = new ContentValues();
        values.put(OutputRepositoryContract.COLUMN_SENDER_ID, senderId);
        values.put(OutputRepositoryContract.COLUMN_NUMBER, port.getPortNumber());
        values.put(OutputRepositoryContract.COLUMN_NAME, port.getName());

        return (int) db.insert(OutputRepositoryContract.TABLE_NAME, null, values);
    }

    @Override
    public Port get(int id) {
        String[] columns = {
                OutputRepositoryContract._ID,
                OutputRepositoryContract.COLUMN_NUMBER,
                OutputRepositoryContract.COLUMN_NAME
        };
        String whereClause = OutputRepositoryContract._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        Cursor cursor = db.query(OutputRepositoryContract.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();

        return mapSingle(cursor);
    }

    @Override
    public List<Port> getForSender(int senderId) {
        String[] columns = {
                OutputRepositoryContract._ID,
                OutputRepositoryContract.COLUMN_NUMBER,
                OutputRepositoryContract.COLUMN_NAME
        };
        String whereClause = OutputRepositoryContract.COLUMN_SENDER_ID + " = ?";
        String[] whereArgs = {String.valueOf(senderId)};
        Cursor cursor = db.query(OutputRepositoryContract.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);

        return map(cursor);
    }
}
