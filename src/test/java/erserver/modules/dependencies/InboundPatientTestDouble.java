package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;

import java.util.ArrayList;
import java.util.List;

public class InboundPatientTestDouble implements InboundPatientSource {


    public List<Patient> getInbounds() {
        return inbounds;
    }

    private List<Patient> inbounds = new ArrayList<>();


    @Override
    public List<Patient> currentInboundPatients() {
        return inbounds;
    }

    @Override
    public void informOfPatientArrival(int transportId) {

    }

    public void simulateNewInboundPatient(Patient inbound) {
        inbounds.add(inbound);
    }
}
