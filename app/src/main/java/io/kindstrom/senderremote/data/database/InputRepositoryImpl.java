package io.kindstrom.senderremote.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.data.database.contract.InputRepositoryContract;
import io.kindstrom.senderremote.domain.model.Port;
import io.kindstrom.senderremote.domain.repository.InputRepository;


public class InputRepositoryImpl extends RepositoryImpl<Port> implements InputRepository {

    @Inject
    public InputRepositoryImpl(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Port mapSingle(Cursor cursor) {
        int idIndex = cursor.getColumnIndexOrThrow(InputRepositoryContract._ID);
        int numberIndex = cursor.getColumnIndexOrThrow(InputRepositoryContract.COLUMN_NUMBER);
        int nameIndex = cursor.getColumnIndexOrThrow(InputRepositoryContract.COLUMN_NAME);

        int id = cursor.getInt(idIndex);
        int number = cursor.getInt(numberIndex);
        String name = cursor.getString(nameIndex);

        return new Port(id, number, name);
    }

    @Override
    public int insert(int senderId, Port port) {
        ContentValues values = new ContentValues();
        values.put(InputRepositoryContract.COLUMN_SENDER_ID, senderId);
        values.put(InputRepositoryContract.COLUMN_NUMBER, port.getPortNumber());
        values.put(InputRepositoryContract.COLUMN_NAME, port.getName());

        return (int) db.insert(InputRepositoryContract.TABLE_NAME, null, values);
    }

    @Override
    public Port get(int id) {
        String[] columns = {
                InputRepositoryContract._ID,
                InputRepositoryContract.COLUMN_NUMBER,
                InputRepositoryContract.COLUMN_NAME
        };
        String whereClause = InputRepositoryContract._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        Cursor cursor = db.query(InputRepositoryContract.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();

        return mapSingle(cursor);
    }

    @Override
    public List<Port> getForSender(int senderId) {
        String[] columns = {
                InputRepositoryContract._ID,
                InputRepositoryContract.COLUMN_NUMBER,
                InputRepositoryContract.COLUMN_NAME
        };
        String whereClause = InputRepositoryContract.COLUMN_SENDER_ID + " = ?";
        String[] whereArgs = {String.valueOf(senderId)};
        Cursor cursor = db.query(InputRepositoryContract.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);

        return map(cursor);
    }
}
