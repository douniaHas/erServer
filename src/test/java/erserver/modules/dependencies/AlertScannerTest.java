package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;
import org.junit.Test;

import javax.lang.model.util.ElementScanner6;

import static org.junit.Assert.*;

public class AlertScannerTest {

    /**
     * Je veux tester la méthode scan de la classe AlertScanner , sauf qu'elle a une dépendance , donc je créer un TestDouble de la dépendance.
     * J'ai donc remplacé les comportements de la dépendance pour mes tests.
     * Sauf que ça ne suffit pas puisque j'ai une méthode de AlertScanner qui fait appel à un service externe.
     * Pour remplacer le comportement de cette méthode, je crée une sous-classe de AlertScanner où je re-spécifie le nouveau comportement que je veux.
     */
    @Test
    public void shouldScanRedPatient() {
        InboundPatientTestDouble inboundPatientSource = new InboundPatientTestDouble();
        inboundPatientSource.simulateNewInboundPatient(createPatient(1, Priority.RED, null));
        AlertScannerTestingSubClass alertScanner = new AlertScannerTestingSubClass(inboundPatientSource);
        alertScanner.scan();

        assertEquals(1,alertScanner.getAlertFor().size());
    }

    @Test
    public void shouldScanYellowPatientWithArrhythmia() {
        InboundPatientTestDouble inboundPatientSource = new InboundPatientTestDouble();
        inboundPatientSource.simulateNewInboundPatient(createPatient(2, Priority.YELLOW, "heart arrhythmia"));
        AlertScannerTestingSubClass alertScanner = new AlertScannerTestingSubClass(inboundPatientSource);
        alertScanner.scan();

        assertEquals(1,alertScanner.getAlertFor().size());
    }

    private Patient createPatient(int transportId, Priority priority, String condition) {
        Patient patient = new Patient();
        patient.setTransportId(transportId);
        patient.setPriority(priority);
        patient.setCondition(condition);
        return patient;
    }
}