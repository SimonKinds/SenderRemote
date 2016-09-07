package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class HumidityCommandTest {
    @Test
    public void commandString() throws Exception {
        HumidityCommand command = new HumidityCommand(0, "", "");
        assertEquals("HUMID 1234", command.commandString(Pin.create("1234")));
    }
}