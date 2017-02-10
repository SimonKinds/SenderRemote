package io.kindstrom.senderremote.data.messaging;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.messaging.CommandSender;
import io.kindstrom.senderremote.domain.model.CommandSendingState;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

@PerActivity
public class CommandSenderImpl implements CommandSender {

    public static final String INTENT_ACTION_RESPONSE = "response.action";
    public static final String INTENT_EXTRA_RESPONSE = "response text";

    private static final int DELAY = 100; //ms
    @NonNull
    private final Context context;
    @NonNull
    private final Sender sender;

    @Inject
    public CommandSenderImpl(@NonNull Context context, @NonNull Sender sender) {
        this.context = context;
        this.sender = sender;
    }

    @NonNull
    @Override
    public Observable<CommandSendingState> send(@NonNull final String message) {
        return Observable.create(
                (ObservableEmitter<CommandSendingState> emitter) -> {
                    emitter.onNext(CommandSendingState.SENT);
                    Thread.sleep(DELAY);
                    emitter.onNext(CommandSendingState.DELIVERED);
                    Thread.sleep(DELAY);

                    Intent intent = new Intent();
                    intent.setAction(INTENT_ACTION_RESPONSE);
                    intent.putExtra(INTENT_EXTRA_RESPONSE, createMockText(message));
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    emitter.onComplete();
                });
    }

    @NonNull
    private String createMockText(@NonNull final String message) {
        if (message.contains("ON") || message.contains("OFF")) {
            return "OK, output control executed";
        } else if (message.contains("TEMP")) {
            return "Temperatur: IN04=!+26C,IN05=-15C,IN06=+13C";
        } else if (message.contains("HUMID")) {
            return "Humidity: IN03=+16%";
        } else if (message.contains("MEAS")) {
            return "Measurements: IN01=76, IN02=1, IN03=+16%, IN04=!+26C, IN05=-15C, IN06=+13C, IN07=1293";
        } else if (message.contains("LIMITS")) {
            return "OK, limits set: +15C, +30C";
        } else if (message.contains("STATUS")) {
            return "Status: IN01=76, IN02=1, IN03=+16%, IN04=!+26C,IN05=-15C, IN06=+13C, IN07=1293, IN08=0, OUT01=0, OUT02=!1";
        } else if (message.contains("SW")) {
            return "ID: Test59209658,Typ: Airborne DC Dual SS,SW: 1.16.58,Signal: 17,Switch: 2,Power: OK,Battery: OK,Tamper: OK,IP: OK";
        } else if (message.contains("PIN")) {
            return "OK, new PIN lagrad";
        } else {
            return "Invalid message";
        }
    }
}
