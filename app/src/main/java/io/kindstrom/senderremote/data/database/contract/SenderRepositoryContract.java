package io.kindstrom.senderremote.data.database.contract;

import android.provider.BaseColumns;

public final class SenderRepositoryContract implements BaseColumns {
    public static final String TABLE_NAME = "senders";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_PIN = "pin";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ("
            + _ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_NUMBER + " TEXT NOT NULL, "
            + COLUMN_PIN + " TEXT "
            + " )";

    private SenderRepositoryContract() {
    }
}
