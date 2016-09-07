package io.kindstrom.senderremote.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LimitsTest {
    @Test
    public void lowHigherThanHigh() throws Exception {
        assertNull(Limits.create(1, 0));
        assertNull(Limits.create(100, 0));
        assertNull(Limits.create(-9999, -10000));
    }

    @Test
    public void lowEqualsHigh() throws Exception {
        assertNull(Limits.create(1, 1));
        assertNull(Limits.create(0, 0));
        assertNull(Limits.create(-100, -100));
    }

    @Test
    public void inCommandFormat() throws Exception {
        assertEquals("L15", Limits.create(15, null).inCommandFormat());
        assertEquals("-L25 -H10", Limits.create(-25, -10).inCommandFormat());
    }
}