package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class TechnicalStatusCommandTest {

    @Test
    public void commandString() throws Exception {
        TechnicalStatusCommand command = new TechnicalStatusCommand(0, "", "");
        assertEquals("SW 1234", command.commandString(Pin.create("1234")));
    }
}