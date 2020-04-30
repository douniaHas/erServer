package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;

import java.util.ArrayList;
import java.util.List;

public class AlertScannerTestingSubClass extends AlertScanner {

    private List<Patient> alertFor = new ArrayList<>();

    public AlertScannerTestingSubClass(InboundPatientSource inboundPatientSource) {
        super(inboundPatientSource);
    }

    @Override
    protected void alertForNewCriticalPatient(Patient patient) {
        alertFor.add(patient);
    }

    public List<Patient> getAlertFor() {
        return alertFor;
    }
}
