package io.kindstrom.senderremote.domain.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ResponseTest {
    @Test
    public void getErrorInvalidPin() throws Exception {
        Response response = new Response("Error, invalid PIN code");
        assertEquals(response.getError(), Response.Error.INVALID_PIN);
    }

    @Test
    public void getErrorUnknownCommand() throws Exception {
        Response response = new Response("Error, unknown command");
        assertEquals(response.getError(), Response.Error.UNKNOWN_COMMAND);
    }

    @Test
    public void getErrorUnknownError() throws Exception {
        Response response = new Response("Error, this is an unknown error");
        assertEquals(response.getError(), Response.Error.UNKNOWN_ERROR);
    }

}