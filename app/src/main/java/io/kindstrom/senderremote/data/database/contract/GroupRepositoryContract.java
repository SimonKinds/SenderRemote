package io.kindstrom.senderremote.data.database.contract;

import android.provider.BaseColumns;


public final class GroupRepositoryContract implements BaseColumns {
    public static final String TABLE_NAME = "groups";
    public static final String COLUMN_NAME = "name";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ("
            + _ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NAME + " TEXT"
            + ")";

    private GroupRepositoryContract() {
    }
}
