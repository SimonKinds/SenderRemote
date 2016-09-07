package io.kindstrom.senderremote.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import io.kindstrom.senderremote.data.database.contract.GroupRepositoryContract;


public class AppSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sender_remote";
    private static final int DATABASE_VERSION = 1;

    public AppSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GroupRepositoryContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nop atm
    }
}
