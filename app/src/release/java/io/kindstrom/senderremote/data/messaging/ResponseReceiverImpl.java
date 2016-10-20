package io.kindstrom.senderremote.data.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.messaging.ResponseReceiver;
import io.kindstrom.senderremote.domain.model.Response;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;


@PerActivity
public class ResponseReceiverImpl implements ResponseReceiver {
    private static final String ACTION_RESPONSE = "android.provider.Telephony.SMS_RECEIVED";

    private final Context context;
    private final Sender sender;
    private BroadcastReceiver broadcastReceiver;
    private boolean isSubscribed = false;

    @Inject
    public ResponseReceiverImpl(@NonNull Context context, @NonNull Sender sender) {
        this.context = context;
        this.sender = sender;
    }

    @NonNull
    @Override
    public Observable<Response> listen() {
        isSubscribed = true;
        return Observable.create((ObservableEmitter<Response> e) -> {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String response = createMessageFromIntent(intent);
                    if (response != null) {
                        e.onNext(new Response(response));
                        e.onComplete();
                    }
                }
            };
            context.registerReceiver(broadcastReceiver, new IntentFilter(ACTION_RESPONSE));
        })
                .doOnComplete(this::unsubscribeReceiver)
                .doOnDispose(this::unsubscribeReceiver);
    }

    @Override
    public void stop() {
        unsubscribeReceiver();
    }

    private void unsubscribeReceiver() {
        if (isSubscribed) {
            context.unregisterReceiver(broadcastReceiver);
            isSubscribed = false;
        }
    }

    // returns null if no message was from the sender
    private String createMessageFromIntent(@NonNull Intent intent) {
        StringBuilder msg = new StringBuilder();
        Bundle extras = intent.getExtras();
        Object[] pdus = (Object[]) extras.get("pdus");
        String format = extras.getString("format");
        if (pdus != null) {
            for (Object pdu : pdus) {
                SmsMessage sms;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    sms = SmsMessage.createFromPdu((byte[]) pdu, format);
                } else {
                    //we're using the latest method when possible
                    //noinspection deprecation
                    sms = SmsMessage.createFromPdu((byte[]) pdu);
                }

                if (PhoneNumberUtils.compare(sender.getNumber(), sms.getOriginatingAddress())) {
                    msg.append(sms.getDisplayMessageBody());
                }
            }
        }
        return msg.length() > 0 ? msg.toString() : null;
    }
}
