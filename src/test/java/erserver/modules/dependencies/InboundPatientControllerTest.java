package erserver.modules.dependencies;

import org.junit.Test;

import static org.junit.Assert.*;

public class InboundPatientControllerTest {

    @Test
    public void currentInboundPatients() {
        assertNotNull(InboundPatientController.getPatients("xml"));
    }
}
