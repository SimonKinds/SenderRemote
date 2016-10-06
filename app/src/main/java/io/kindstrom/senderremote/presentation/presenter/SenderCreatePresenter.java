package io.kindstrom.senderremote.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.kindstrom.senderremote.domain.interactor.CreateSenderInteractor;
import io.kindstrom.senderremote.domain.interactor.GetGroupsInteractor;
import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.domain.model.Pin;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.view.SenderCreateView;

@PerActivity
public class SenderCreatePresenter implements Presenter<SenderCreateView> {
    private final int fromGroupId;
    private final GetGroupsInteractor getGroupsInteractor;
    private final CreateSenderInteractor createSenderInteractor;
    private SenderCreateView view;

    @Inject
    public SenderCreatePresenter(@Named("groupId") int fromGroupId,
                                 GetGroupsInteractor getGroupsInteractor,
                                 CreateSenderInteractor createSenderInteractor) {
        this.fromGroupId = fromGroupId;
        this.getGroupsInteractor = getGroupsInteractor;
        this.createSenderInteractor = createSenderInteractor;
    }

    @Override
    public void attach(SenderCreateView view) {
        this.view = view;

        List<Group> groups = getGroupsInteractor.execute();
        view.showGroups(groups);
        selectGroup(groups);
    }

    private void selectGroup(List<Group> groups) {
        Group selectGroup = null;
        for (Group g : groups) {
            if (g.getId() == fromGroupId) {
                selectGroup = g;
                break;
            }
        }

        if (selectGroup != null) {
            view.selectGroup(selectGroup);
        }
    }

    @Override
    public void detach() {
        view = null;
    }

    public void createSenderClicked() {
        boolean hasError = false;
        String name = view.getName();
        if (name.isEmpty()) {
            view.showErrorName();
            hasError = true;
        }

        String number = view.getNumber();
        if (number.isEmpty()) {
            view.showErrorNumber();
            hasError = true;
        }

        String pin = view.getPin();
        if (pin.isEmpty()) {
            view.showErrorPinNeeded();
            hasError = true;
        } else if (Pin.create(pin) == null) {
            view.showErrorPinTooShort();
            hasError = true;
        }

        if (!hasError) {
            view.showPortNamingView(4, 2);
        }
    }

    public void portNamesReceived(@NonNull String[] inputNames, @NonNull String[] outputNames) {
        String name = view.getName();
        String number = view.getNumber();
        String pin = view.getPin();
        List<Integer> groupIds = view.getGroups();

        createSender(name, number, pin, inputNames, outputNames, groupIds);
    }

    private void createSender(String name, String number, String pin, String[] inputNames, String[] outputNames, List<Integer> groupIds) {
        createSenderInteractor.setParameters(name, number, Pin.create(pin),
                inputNames, outputNames, groupIds);
        createSenderInteractor.execute();

        view.returnToPreviousView();
    }
}
