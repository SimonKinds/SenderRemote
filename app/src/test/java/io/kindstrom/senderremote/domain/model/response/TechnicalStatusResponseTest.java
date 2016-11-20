package io.kindstrom.senderremote.domain.model.response;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class TechnicalStatusResponseTest {
    @Test
    public void getFields() throws Exception {
        TechnicalStatusResponse response = new TechnicalStatusResponse("ID: Test59209658,Typ: Airborne DC Dual SS,SW: 1.16.58,Signal: 17,Switch: 2,Power: OK,Battery: OK,Tamper: OK,IP: OK");

        assertEquals(response.getFields().get(0).type, TechnicalStatusResponse.Type.ID);
        assertEquals(response.getFields().get(0).value, "Test59209658");
        assertEquals(response.getFields().get(8).type, TechnicalStatusResponse.Type.IP);
        assertEquals(response.getFields().get(8).value, "OK");
    }

    @Test
    public void getFieldsWithError() throws Exception {
        TechnicalStatusResponse response = new TechnicalStatusResponse("Error, invalid PIN code");

        assertNull(response.getFields());
    }

}