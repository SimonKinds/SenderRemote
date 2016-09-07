package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Duration;
import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class OnCommandTest {
    @Test
    public void commandStringNoDuration() throws Exception {
        assertEquals("ON 7 1234", new OnCommand(7).commandString(Pin.create("1234")));
    }

    @Test
    public void commandStringWithDuration() throws Exception {
        assertEquals("ON 5 T1M2S3 1234",
                new OnCommand(5, new Duration(1, 2, 3)).commandString(Pin.create("1234")));
    }

}