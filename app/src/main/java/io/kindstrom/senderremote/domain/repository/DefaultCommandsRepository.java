package io.kindstrom.senderremote.domain.repository;

import android.support.annotation.NonNull;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Command;

public interface DefaultCommandsRepository {
    @NonNull
    List<Command> getDefaultCommands();
}
