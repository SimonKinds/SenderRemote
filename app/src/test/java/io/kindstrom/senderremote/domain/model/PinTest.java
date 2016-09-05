package io.kindstrom.senderremote.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PinTest {

    @Test
    public void createValid() throws Exception {
        assertNotNull(Pin.create("1234"));
        assertNotNull(Pin.create("12345"));
        assertNotNull(Pin.create("123456"));
        assertNotNull(Pin.create("1234567"));
        assertNotNull(Pin.create("12345678"));
        assertNotNull(Pin.create("5678123"));
    }

    @Test
    public void createEmpty() throws Exception {
        assertNull(Pin.create(""));
    }

    @Test
    public void createTooShort() throws Exception {
        assertNull(Pin.create("1"));
        assertNull(Pin.create("12"));
        assertNull(Pin.create("123"));
    }

    @Test
    public void createTooLong() throws Exception {
        assertNull(Pin.create("123456789"));
        assertNull(Pin.create("0123456789"));
        assertNull(Pin.create("0123456789123871293619826312"));
    }

    @Test
    public void createNonNumbers() throws Exception {
        assertNull(Pin.create("a"));
        assertNull(Pin.create("b1"));
        assertNull(Pin.create("one1two2"));
    }
}