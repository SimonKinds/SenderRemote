package io.kindstrom.senderremote.data.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.domain.database.Repository;

public abstract class RepositoryImpl<E> implements Repository<E>, Mapper<E> {
    protected final SQLiteDatabase db;


    public RepositoryImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public final List<E> map(Cursor cursor) {
        List<E> items = new ArrayList<>(cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                items.add(mapSingle(cursor));
            } while (cursor.moveToNext());
        }

        return items;
    }
}
