package io.kindstrom.senderremote.domain.model.command;

import org.junit.Test;

import io.kindstrom.senderremote.domain.model.Duration;
import io.kindstrom.senderremote.domain.model.Pin;

import static org.junit.Assert.assertEquals;

public class OffCommandTest {
    @Test
    public void offNoDuration() throws Exception {
        assertEquals("OFF 7 1234", new OffCommand(7).commandString(Pin.create("1234")));
    }

    @Test
    public void offWithDuration() throws Exception {
        assertEquals("OFF 5 T1M2S3 1234",
                new OffCommand(5, new Duration(1, 2, 3)).commandString(Pin.create("1234")));
    }
}