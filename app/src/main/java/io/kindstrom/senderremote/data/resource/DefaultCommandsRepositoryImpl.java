package io.kindstrom.senderremote.data.resource;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.R;
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
import io.kindstrom.senderremote.domain.repository.DefaultCommandsRepository;


public class DefaultCommandsRepositoryImpl implements DefaultCommandsRepository {

    private final Resources resources;

    @Inject
    public DefaultCommandsRepositoryImpl(Resources resources) {
        this.resources = resources;
    }

    @NonNull
    @Override
    public List<Command> getDefaultCommands() {
        List<Command> commands = new ArrayList<>(DefaultCommand.values().length);

        for (DefaultCommand val : DefaultCommand.values()) {
            //noinspection TryWithIdenticalCatches
            try {
                String name = resources.getString(val.getName());
                String description = resources.getString(val.getDescription());

                Command command =
                        (Command) val.getClazz()
                                .getConstructor(int.class, String.class, String.class)
                                .newInstance(-1, name, description);

                commands.add(command);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return commands;
    }

    private enum DefaultCommand {
        ON(OnCommand.class, R.string.command_on_name, R.string.command_on_description),
        OFF(OffCommand.class, R.string.command_off_name, R.string.command_off_description),
        TEMP(TemperatureCommand.class, R.string.command_temp_name, R.string.command_temp_description),
        HUMID(HumidityCommand.class, R.string.command_humid_name, R.string.command_humid_description),
        MEAS(MeasurementCommand.class, R.string.command_meas_name, R.string.command_meas_description),
        LIMITS(LimitsCommand.class, R.string.command_limits_name, R.string.command_limits_description),
        STATUS(StatusCommand.class, R.string.command_status_name, R.string.command_status_description),
        SW(TechnicalStatusCommand.class, R.string.command_sw_name, R.string.command_sw_description),
        PIN(PinCommand.class, R.string.command_pin_name, R.string.command_pin_description);


        private final Class<?> clazz;
        private final int name;
        private final int description;

        DefaultCommand(Class<?> clazz, @StringRes int name, @StringRes int description) {
            this.clazz = clazz;
            this.name = name;
            this.description = description;
        }

        public int getName() {
            return name;
        }

        public int getDescription() {
            return description;
        }

        public Class<?> getClazz() {
            return clazz;
        }
    }
}
