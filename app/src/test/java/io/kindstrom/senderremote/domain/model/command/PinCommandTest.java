package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PinCommandTest {
    @Test
    public void commandStringInvalidNewPin() throws Exception {
        assertNull(new PinCommand(Pin.create("")).commandString(Pin.create("1234")));
    }

    @Test
    public void commandString() throws Exception {
        assertEquals("PIN 456789 1234",
                new PinCommand(Pin.create("456789")).commandString(Pin.create("1234")));
    }
}