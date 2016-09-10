package io.kindstrom.senderremote.data.database.contract;

import android.provider.BaseColumns;


public final class GroupMemberRepositoryContract implements BaseColumns {
    public static final String TABLE_NAME = "group_members";
    public static final String COLUMN_GROUP_ID = "group_id";
    public static final String COLUMN_SENDER_ID = "sender_id";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "("
            + _ID + " INTEGER PRIMARY KEY, "
            + COLUMN_GROUP_ID + " INTEGER NOT NULL, "
            + COLUMN_SENDER_ID + " INTEGER NOT NULL, "

            + "FOREIGN KEY(" + COLUMN_GROUP_ID + ")"
            + "REFERENCES " + GroupRepositoryContract.TABLE_NAME + "(" + GroupRepositoryContract._ID + ") "
            + " ON DELETE CASCADE,"


            + "FOREIGN KEY(" + COLUMN_SENDER_ID + ")"
            + "REFERENCES " + SenderRepositoryContract.TABLE_NAME + "(" + SenderRepositoryContract._ID + ")"
            + " ON DELETE CASCADE"

            + ")";

    private GroupMemberRepositoryContract() {
    }
}
