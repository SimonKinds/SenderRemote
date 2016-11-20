package io.kindstrom.senderremote.domain.model.response;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class PortResponseTest {
    @Test
    public void getFieldsError() throws Exception {
        PortResponse response = new PortResponse("Error, this is some kind of error");
        assertNull(response.getFields());
    }

    @Test
    public void getFieldsSuccess() throws Exception {
        PortResponse response = new PortResponse("Status: IN01=+16%, IN04=!+26C,IN05=-15, OUT02=!1");
        List<PortResponse.ResponseField> expectedFields = new ArrayList<>();
        expectedFields.add(new PortResponse.ResponseField(1, 16, PortResponse.Type.HUMIDITY, false));
        expectedFields.add(new PortResponse.ResponseField(4, 26, PortResponse.Type.TEMPERATURE, true));
        expectedFields.add(new PortResponse.ResponseField(5, -15, PortResponse.Type.COUNTER, false));
        expectedFields.add(new PortResponse.ResponseField(2, 1, PortResponse.Type.OUTPUT, true));
        assertEquals(expectedFields, response.getFields());
    }

}