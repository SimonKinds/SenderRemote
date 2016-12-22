package io.kindstrom.senderremote.presentation.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.interactor.GetCommandsInteractor;
import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.view.CommandListView;

@PerActivity
public class CommandListPresenter implements Presenter<CommandListView> {
    private final Sender sender;
    private final GetCommandsInteractor getCommandsInteractor;
    private CommandListView view;

    @Inject
    public CommandListPresenter(@NonNull Sender sender, @NonNull GetCommandsInteractor getCommandsInteractor) {
        this.sender = sender;
        this.getCommandsInteractor = getCommandsInteractor;
    }

    @Override
    public void attach(CommandListView view) {
        this.view = view;
        view.setTitle(sender.getName());
        view.setCommands(getCommandsInteractor.execute());
    }

    @Override
    public void detach() {
        view = null;
    }

    public void onCommandClicked(Command command) {
        view.showCommand(command);
    }
}
