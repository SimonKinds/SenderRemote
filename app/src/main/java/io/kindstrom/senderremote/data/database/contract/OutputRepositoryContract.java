package io.kindstrom.senderremote.data.database.contract;

import android.provider.BaseColumns;

public final class OutputRepositoryContract implements BaseColumns {
    public static final String TABLE_NAME = "outputs";
    public static final String COLUMN_SENDER_ID = "sender_id";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_NAME = "name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "("
            + _ID + " INTEGER PRIMARY KEY, "
            + COLUMN_SENDER_ID + " INTEGER NOT NULL, "
            + COLUMN_NUMBER + " INTEGER NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL, "

            + "FOREIGN KEY(" + COLUMN_SENDER_ID + ")"
            + "REFERENCES " + SenderRepositoryContract.TABLE_NAME + "(" + SenderRepositoryContract._ID + ")"
            + " ON DELETE CASCADE"

            + ")";

    private OutputRepositoryContract() {
    }
}
