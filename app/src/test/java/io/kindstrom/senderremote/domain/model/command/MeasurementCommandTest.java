package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class MeasurementCommandTest {
    @Test
    public void commandString() throws Exception {
        assertEquals("MEAS 1234", new MeasurementCommand().commandString(Pin.create("1234")));
    }
}