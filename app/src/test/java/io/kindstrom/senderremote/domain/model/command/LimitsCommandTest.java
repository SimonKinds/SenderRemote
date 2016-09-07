package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Limits;
import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class LimitsCommandTest {
    @Test
    public void commandString() throws Exception {
        LimitsCommand command = new LimitsCommand(0, "", "", Limits.create(-25, 15));
        assertEquals("LIMITS -L25 H15 1234", command.commandString(Pin.create("1234")));
    }
}