package io.kindstrom.senderremote.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.data.database.contract.SenderRepositoryContract;
import io.kindstrom.senderremote.domain.model.Pin;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.domain.repository.SenderRepository;

public class SenderRepositoryImpl extends RepositoryImpl<Sender> implements SenderRepository {

    @Inject
    public SenderRepositoryImpl(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Sender mapSingle(Cursor cursor) {
        int idIndex = cursor.getColumnIndexOrThrow(SenderRepositoryContract._ID);
        int nameIndex = cursor.getColumnIndexOrThrow(SenderRepositoryContract.COLUMN_NAME);
        int numberIndex = cursor.getColumnIndexOrThrow(SenderRepositoryContract.COLUMN_NUMBER);
        int pinIndex = cursor.getColumnIndexOrThrow(SenderRepositoryContract.COLUMN_PIN);

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);
        String number = cursor.getString(numberIndex);
        String pin = cursor.getString(pinIndex);

        return new Sender(id, name, number, Pin.create(pin), null, null, null);
    }

    @Override
    public int insert(Sender sender) {
        ContentValues vals = new ContentValues(3);
        vals.put(SenderRepositoryContract.COLUMN_NAME, sender.getName());
        vals.put(SenderRepositoryContract.COLUMN_NUMBER, sender.getNumber());

        if (sender.getPin() != null) {
            vals.put(SenderRepositoryContract.COLUMN_PIN, sender.getPin().getPin());
        }

        return (int) db.insert(SenderRepositoryContract.TABLE_NAME, null, vals);
    }

    @Override
    public int delete(int id) {
        String whereClause = SenderRepositoryContract._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(SenderRepositoryContract.TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public Sender get(int id) {
        String[] columns = {SenderRepositoryContract._ID,
                SenderRepositoryContract.COLUMN_NAME,
                SenderRepositoryContract.COLUMN_NUMBER,
                SenderRepositoryContract.COLUMN_PIN};
        String whereClause = SenderRepositoryContract._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = db.query(SenderRepositoryContract.TABLE_NAME, columns, whereClause,
                whereArgs, null, null, null);
        cursor.moveToFirst();
        return mapSingle(cursor);
    }

    @Override
    public List<Sender> getAll() {
        String[] columns = {SenderRepositoryContract._ID,
                SenderRepositoryContract.COLUMN_NAME,
                SenderRepositoryContract.COLUMN_NUMBER,
                SenderRepositoryContract.COLUMN_PIN};

        Cursor cursor = db.query(SenderRepositoryContract.TABLE_NAME, columns, null,
                null, null, null, null);
        return map(cursor);
    }
}
