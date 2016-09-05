package io.kindstrom.senderremote.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DurationTest {
    @Test
    public void inCommandFormat() {
        assertEquals("S1", new Duration(0, 0, 1).inCommandFormat());
        assertEquals("M3S4", new Duration(0, 3, 4).inCommandFormat());
        assertEquals("T10M3S4", new Duration(10, 3, 4).inCommandFormat());
    }

    @Test
    public void inCommandFormatConversion() {
        assertEquals("M2S3", new Duration(0, 0, 123).inCommandFormat());
        assertEquals("T3M58S3", new Duration(0, 236, 123).inCommandFormat());
    }
}