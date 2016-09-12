package io.kindstrom.senderremote.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.data.database.contract.CommandRepositoryContract;
import io.kindstrom.senderremote.domain.database.CommandRepository;
import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.command.HumidityCommand;
import io.kindstrom.senderremote.domain.model.command.LimitsCommand;
import io.kindstrom.senderremote.domain.model.command.MeasurementCommand;
import io.kindstrom.senderremote.domain.model.command.OffCommand;
import io.kindstrom.senderremote.domain.model.command.OnCommand;
import io.kindstrom.senderremote.domain.model.command.PinCommand;
import io.kindstrom.senderremote.domain.model.command.StatusCommand;
import io.kindstrom.senderremote.domain.model.command.TechnicalStatusCommand;
import io.kindstrom.senderremote.domain.model.command.TemperatureCommand;


public class CommandRepositoryImpl extends RepositoryImpl<Command> implements CommandRepository {

    private static final Class<?>[] types = new Class[]{
            OnCommand.class,
            OffCommand.class,
            TemperatureCommand.class,
            HumidityCommand.class,
            MeasurementCommand.class,
            LimitsCommand.class,
            StatusCommand.class,
            TechnicalStatusCommand.class,
            PinCommand.class
    };

    @Inject
    public CommandRepositoryImpl(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Command mapSingle(Cursor cursor) {
        int idIndex = cursor.getColumnIndexOrThrow(CommandRepositoryContract._ID);
        int nameIndex = cursor.getColumnIndexOrThrow(CommandRepositoryContract.COLUMN_NAME);
        int descriptionIndex = cursor.getColumnIndexOrThrow(CommandRepositoryContract.COLUMN_DESCRIPTION);
        int typeIndex = cursor.getColumnIndexOrThrow(CommandRepositoryContract.COLUMN_TYPE);

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);
        String description = cursor.getString(descriptionIndex);
        int type = cursor.getInt(typeIndex);

        return createCommand(id, name, description, type);
    }

    private Command createCommand(int id, String name, String description, int type) {
        //noinspection TryWithIdenticalCatches
        try {
            return (Command) types[type].getConstructor(int.class, String.class, String.class).newInstance(id, name, description);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    private int getType(Command command) {
        for (int i = 0; i < types.length; ++i) {
            if (command.getClass().equals(types[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int insert(int senderId, Command command) {
        ContentValues vals = new ContentValues();
        vals.put(CommandRepositoryContract.COLUMN_SENDER_ID, senderId);
        vals.put(CommandRepositoryContract.COLUMN_NAME, command.getName());
        vals.put(CommandRepositoryContract.COLUMN_DESCRIPTION, command.getDescription());
        vals.put(CommandRepositoryContract.COLUMN_TYPE, getType(command));

        return (int) db.insert(CommandRepositoryContract.TABLE_NAME, null, vals);
    }

    @Override
    public int delete(int id) {
        String whereClause = CommandRepositoryContract._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        return db.delete(CommandRepositoryContract.TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public Command get(int id) {
        String[] columns = {CommandRepositoryContract._ID,
                CommandRepositoryContract.COLUMN_TYPE,
                CommandRepositoryContract.COLUMN_NAME,
                CommandRepositoryContract.COLUMN_DESCRIPTION};
        String whereClause = CommandRepositoryContract._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = db.query(CommandRepositoryContract.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        return mapSingle(cursor);
    }

    @Override
    public List<Command> getForSender(int senderId) {
        String[] columns = {CommandRepositoryContract._ID,
                CommandRepositoryContract.COLUMN_TYPE,
                CommandRepositoryContract.COLUMN_NAME,
                CommandRepositoryContract.COLUMN_DESCRIPTION};
        String whereClause = CommandRepositoryContract.COLUMN_SENDER_ID + " = ?";
        String[] whereArgs = {String.valueOf(senderId)};

        return map(db.query(CommandRepositoryContract.TABLE_NAME, columns, whereClause, whereArgs, null, null, null));
    }
}
