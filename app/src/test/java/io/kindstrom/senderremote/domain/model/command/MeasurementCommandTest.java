package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class MeasurementCommandTest {
    @Test
    public void commandString() throws Exception {
        MeasurementCommand command = new MeasurementCommand(0, "", "");
        assertEquals("MEAS 1234", command.commandString(Pin.create("1234")));
    }
}