package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class StatusCommandTest {

    @Test
    public void commandString() throws Exception {
        StatusCommand command = new StatusCommand(0, "name", "description");
        assertEquals("STATUS 1234", command.commandString(Pin.create("1234")));
    }
}