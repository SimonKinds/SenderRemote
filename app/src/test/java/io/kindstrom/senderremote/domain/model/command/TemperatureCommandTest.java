package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class TemperatureCommandTest {
    @Test
    public void commandString() throws Exception {
        TemperatureCommand command = new TemperatureCommand(0, "", "");
        assertEquals("TEMP 1234", command.commandString(Pin.create("1234")));
    }
}