package erserver.modules.dependencies;

import erserver.modules.dependencies.vendorpagersystem.AlertTransmitter;
import erserver.modules.dependencies.vendorpagersystem.PagerTransport;
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

    /**
     * Here we want to test the call of our transport system and akc alerts.
     * We have to make the dependency visible in the constructor so that we can test double it for this test sake
     * so we create a common interface for the transport test double and real impl
     * Then we override the behavior for test double and test it by creating lists. 
     */
    @Test
    public void shouldTransportAck(){
        InboundPatientTestDouble inboundDouble = new InboundPatientTestDouble();
        inboundDouble.simulateNewInboundPatient(createPatient(2, Priority.YELLOW, "heart arrhythmia"));
        PagerTransportTestDouble pagerTransportTestDouble = new PagerTransportTestDouble();

        AlertScanner alertScanner = new AlertScanner(inboundDouble, pagerTransportTestDouble);
        alertScanner.scan();

        assertEquals(0,pagerTransportTestDouble.getAlertsReceived().size());
        assertEquals(1,pagerTransportTestDouble.getAlertsRequiringAckReceived().size());
    }

    private Patient createPatient(int transportId, Priority priority, String condition) {
        Patient patient = new Patient();
        patient.setTransportId(transportId);
        patient.setPriority(priority);
        patient.setCondition(condition);
        return patient;
    }
}