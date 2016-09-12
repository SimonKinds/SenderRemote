package io.kindstrom.senderremote.data.database.contract;

import android.provider.BaseColumns;


public final class CommandRepositoryContract implements BaseColumns {
    public static final String TABLE_NAME = "commands";
    public static final String COLUMN_SENDER_ID = "sender_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TYPE = "type";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "("
            + _ID + " INTEGER PRIMARY KEY, "
            + COLUMN_SENDER_ID + " INTEGER NOT NULL, "
            + COLUMN_TYPE + " INTEGER NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT, "

            + "FOREIGN KEY(" + COLUMN_SENDER_ID + ")"
            + " REFERENCES " + SenderRepositoryContract.TABLE_NAME + "(" + SenderRepositoryContract._ID + ")"
            + " ON DELETE CASCADE"

            + ")";

    private CommandRepositoryContract() {
    }
}
