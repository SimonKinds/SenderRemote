package io.kindstrom.senderremote.data.messaging;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.messaging.ResponseReceiver;
import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Response;
import io.kindstrom.senderremote.domain.model.command.HumidityCommand;
import io.kindstrom.senderremote.domain.model.command.LimitsCommand;
import io.kindstrom.senderremote.domain.model.command.MeasurementCommand;
import io.kindstrom.senderremote.domain.model.command.OutputActionCommand;
import io.kindstrom.senderremote.domain.model.command.PinCommand;
import io.kindstrom.senderremote.domain.model.command.StatusCommand;
import io.kindstrom.senderremote.domain.model.command.TechnicalStatusCommand;
import io.kindstrom.senderremote.domain.model.command.TemperatureCommand;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;


@PerActivity
public class ResponseReceiverImpl implements ResponseReceiver {
    private final Command command;

    @Inject
    public ResponseReceiverImpl(Command command) {
        this.command = command;
    }

    @NonNull
    @Override
    public Observable<Response> listen() {
        return Observable.create((ObservableEmitter<Response> e) -> {
            e.onNext(new Response(createMockText()));
            e.onComplete();
        });
    }

    @Override
    public void stop() {
    }

    private String createMockText() {
        if (command instanceof OutputActionCommand) {
            return "OK, output control executed";
        } else if (command instanceof TemperatureCommand) {
            return "Temperatur: IN04=!+26C,IN05=-15C,IN06=+13C";
        } else if (command instanceof HumidityCommand) {
            return "Humidity: IN03=+16%";
        } else if (command instanceof MeasurementCommand) {
            return "Measurements: IN01=76, IN02=1, IN03=+16%, IN04=!+26C, IN05=-15C, IN06=+13C, IN07=1293";
        } else if (command instanceof LimitsCommand) {
            return "OK, limits set: +15C, +30C";
        } else if (command instanceof StatusCommand) {
            return "Status: IN01=76, IN02=1, IN03=+16%, IN04=!+26C,IN05=-15C, IN06=+13C, IN07=1293, IN08=0, OUT01=0, OUT02=!1";
        } else if (command instanceof TechnicalStatusCommand) {
            return "ID: Test59209658,Typ: Airborne DC Dual SS,SW: 1.16.58,Signal: 17,Switch: 2,Power: OK,Battery: OK,Tamper: OK,IP: OK";
        } else if (command instanceof PinCommand) {
            return "OK, new PIN lagrad";
        } else {
            return null;
        }
    }
}
