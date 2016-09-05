package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.*;

public class PinCommandTest {
    @Test
    public void getCommandString() throws Exception {
        assertEquals("PIN 1111 2222",
                new PinCommand(Pin.create("1111")).getCommandString(Pin.create("2222")));
        assertEquals("PIN 1234 55687",
                new PinCommand(Pin.create("1234")).getCommandString(Pin.create("55687")));
    }

    @Test
    public void getCommandStringInvalidPin() throws Exception {
        assertNull(new PinCommand(Pin.create("1234")).getCommandString(Pin.create("")));
    }

    @Test
    public void getCommandStringInvalidNewPin() throws Exception {
        assertNull(new PinCommand(Pin.create("")).getCommandString(Pin.create("1234")));
    }
}