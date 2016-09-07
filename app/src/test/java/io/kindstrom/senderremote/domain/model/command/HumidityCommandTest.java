package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class HumidityCommandTest {
    @Test
    public void commandString() throws Exception {
        assertEquals("HUMID 1234", new HumidityCommand().commandString(Pin.create("1234")));
    }
}