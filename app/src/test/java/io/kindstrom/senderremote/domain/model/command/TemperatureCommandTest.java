package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class TemperatureCommandTest {
    @Test
    public void commandString() throws Exception {
        assertEquals("TEMP 1234", new TemperatureCommand().commandString(Pin.create("1234")));
    }
}