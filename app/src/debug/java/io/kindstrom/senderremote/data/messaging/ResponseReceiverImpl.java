package io.kindstrom.senderremote.data.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.messaging.ResponseReceiver;
import io.kindstrom.senderremote.domain.model.Response;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;


@PerActivity
public class ResponseReceiverImpl implements ResponseReceiver {
    private final Context context;

    private BroadcastReceiver broadcastReceiver;

    @Inject
    public ResponseReceiverImpl(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Observable<Response> listen() {
        return Observable.create((ObservableEmitter<Response> e) -> {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String response = intent.getStringExtra(CommandSenderImpl.INTENT_EXTRA_RESPONSE);
                    e.onNext(new Response(response));
                    e.onComplete();
                }
            };
            LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver,
                    new IntentFilter(CommandSenderImpl.INTENT_ACTION_RESPONSE));
        })
                .doOnDispose(this::stop)
                .doOnComplete(this::stop);
    }

    @Override
    public void stop() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }
}
