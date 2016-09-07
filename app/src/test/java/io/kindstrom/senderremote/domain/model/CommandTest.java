package io.kindstrom.senderremote.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandTest {
    private final Pin pin = Pin.create("1234");

    @Test
    public void temperature() throws Exception {
        assertEquals("TEMP 1234", Command.temperature(pin));
    }

    @Test
    public void humidity() throws Exception {
        assertEquals("HUMID 1234", Command.humidity(pin));
    }

    @Test
    public void measurements() throws Exception {
        assertEquals("MEAS 1234", Command.measurements(pin));
    }

    @Test
    public void status() throws Exception {
        assertEquals("STATUS 1234", Command.status(pin));
    }

    @Test
    public void pin() throws Exception {
        assertEquals("PIN 456789 1234", Command.pin(Pin.create("456789"), pin));
    }

    @Test
    public void limit() throws Exception {
        assertEquals("LIMITS -L25 H15 1234", Command.limits(Limits.create(-25, 15), pin));
    }

    @Test
    public void sw() throws Exception {
        assertEquals("SW 1234", Command.sw(pin));
    }

    @Test
    public void onNoDuration() throws Exception {
        assertEquals("ON 7 1234", Command.on(7, pin));
    }

    @Test
    public void onWithDuration() throws Exception {
        assertEquals("ON 5 T1M2S3 1234", Command.on(5, new Duration(1, 2, 3), pin));
    }

    @Test
    public void offNoDuration() throws Exception {
        assertEquals("OFF 7 1234", Command.off(7, pin));
    }

    @Test
    public void offWithDuration() throws Exception {
        assertEquals("OFF 5 T1M2S3 1234", Command.off(5, new Duration(1, 2, 3), pin));
    }
}