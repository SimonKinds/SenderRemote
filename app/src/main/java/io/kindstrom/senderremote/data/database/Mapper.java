package io.kindstrom.senderremote.data.database;

import android.database.Cursor;

import java.util.List;

public interface Mapper<E> {
    E mapSingle(Cursor cursor);

    List<E> map(Cursor cursor);
}
