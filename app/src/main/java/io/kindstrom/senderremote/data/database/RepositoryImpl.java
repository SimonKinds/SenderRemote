package io.kindstrom.senderremote.data.database;

import android.database.sqlite.SQLiteDatabase;

public class RepositoryImpl {
    protected final SQLiteDatabase db;

    public RepositoryImpl(SQLiteDatabase db) {
        this.db = db;
    }
}
