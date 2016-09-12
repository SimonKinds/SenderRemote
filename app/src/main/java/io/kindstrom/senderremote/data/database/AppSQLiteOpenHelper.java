package io.kindstrom.senderremote.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.kindstrom.senderremote.data.database.contract.CommandRepositoryContract;
import io.kindstrom.senderremote.data.database.contract.GroupMemberRepositoryContract;
import io.kindstrom.senderremote.data.database.contract.GroupRepositoryContract;
import io.kindstrom.senderremote.data.database.contract.SenderRepositoryContract;


@Singleton
public class AppSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sender_remote";
    private static final int DATABASE_VERSION = 1;

    @Inject
    public AppSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GroupRepositoryContract.CREATE_TABLE);
        db.execSQL(SenderRepositoryContract.CREATE_TABLE);
        db.execSQL(GroupMemberRepositoryContract.CREATE_TABLE);

        db.execSQL(CommandRepositoryContract.CREATE_TABLE);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nop atm
    }
}
