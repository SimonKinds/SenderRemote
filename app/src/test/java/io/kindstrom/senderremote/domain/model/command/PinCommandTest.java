package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PinCommandTest {
    @Test
    public void commandStringInvalidNewPin() throws Exception {
        PinCommand command = new PinCommand(0, "", "", Pin.create(""));
        assertNull(command.commandString(Pin.create("1234")));
    }

    @Test
    public void commandString() throws Exception {
        PinCommand command = new PinCommand(0, "", "", Pin.create("456789"));
        assertEquals("PIN 456789 1234", command.commandString(Pin.create("1234")));
    }
}