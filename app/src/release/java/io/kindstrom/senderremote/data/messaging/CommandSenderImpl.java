package io.kindstrom.senderremote.data.messaging;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.telephony.SmsManager;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.messaging.CommandSender;
import io.kindstrom.senderremote.domain.model.CommandSendingState;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

@PerActivity
public class CommandSenderImpl implements CommandSender {

    private static final String ACTION_SENT = CommandSender.class.getPackage().getName() + ".SENT";
    private static final String ACTION_DELIVERED = CommandSender.class.getPackage().getName() + ".DELIVERED";

    private static final int REQUEST_CODE_SENT = 0;
    private static final int REQUEST_CODE_DELIVERED = 1;

    private final Context context;
    private final Sender sender;

    private BroadcastReceiver receiver;
    private boolean isSubscribed = false;

    @Inject
    public CommandSenderImpl(Context context, Sender sender) {
        this.context = context;
        this.sender = sender;
    }

    @NonNull
    @Override
    public Observable<CommandSendingState> send(@NonNull final String message) {
        isSubscribed = true;

        PendingIntent sentPI = PendingIntent.getBroadcast(context, REQUEST_CODE_SENT,
                new Intent(ACTION_SENT), PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(context,
                REQUEST_CODE_DELIVERED, new Intent(ACTION_DELIVERED),
                PendingIntent.FLAG_UPDATE_CURRENT);

        return Observable.create(
                (ObservableEmitter<CommandSendingState> emitter) -> {
                    receiver = new BroadcastReceiver() {
                        @Override public void onReceive(Context context, Intent intent) {
                            CommandSendingState state = map(intent, getResultCode());
                            emitter.onNext(state);

                            // complete if error
                            switch (state) {
                                case ERROR_EMPTY_MESSAGE:
                                case ERROR_UNKNOWN:
                                case ERROR_NO_SERVICE:
                                case ERROR_RADIO_OFF:
                                    emitter.onComplete();

                            }
                            // complete if message is delivered
                            if(intent.getAction().equals(ACTION_DELIVERED)) {
                                emitter.onComplete();
                            }
                        }
                    };

                    context.registerReceiver(receiver, new IntentFilter(ACTION_SENT));
                    context.registerReceiver(receiver, new IntentFilter(ACTION_DELIVERED));

                    SmsManager.getDefault().sendTextMessage(sender.getNumber(), null, message, sentPI,
                            deliveredPI);
                })
                .doOnComplete(this::unsubscribeReceiver)
                .doOnDispose(this::unsubscribeReceiver);

    }

    private void unsubscribeReceiver() {
        if (isSubscribed) {
            context.unregisterReceiver(receiver);
            isSubscribed = false;
        }
    }

    private CommandSendingState map(Intent intent, int resultCode) {
        String action = intent.getAction();
        switch(resultCode) {
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                return CommandSendingState.ERROR_NO_SERVICE;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                return CommandSendingState.ERROR_RADIO_OFF;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                return CommandSendingState.ERROR_EMPTY_MESSAGE;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                return CommandSendingState.ERROR_UNKNOWN;
            default:
                if (action.equals(ACTION_SENT)) {
                    return CommandSendingState.SENT;
                } else {
                    return CommandSendingState.DELIVERED;
                }

        }
    }
}
