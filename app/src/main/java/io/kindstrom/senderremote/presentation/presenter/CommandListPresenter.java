package io.kindstrom.senderremote.presentation.presenter;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.interactor.GetCommandsInteractor;
import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.view.CommandListView;

@PerActivity
public class CommandListPresenter implements Presenter<CommandListView> {
    private final GetCommandsInteractor getCommandsInteractor;
    private CommandListView view;

    @Inject
    public CommandListPresenter(GetCommandsInteractor getCommandsInteractor) {
        this.getCommandsInteractor = getCommandsInteractor;
    }

    @Override
    public void attach(CommandListView view) {
        this.view = view;
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
