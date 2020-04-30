package erserver.modules.dependencies;

import erserver.modules.dependencies.vendorpagersystem.AlertTransmitter;
import erserver.modules.dependencies.vendorpagersystem.PagerTransport;
import erserver.modules.testtypes.Patient;
import erserver.modules.dependencies.vendorpagersystem.PagerSystem;

import java.util.ArrayList;
import java.util.List;

public class AlertScanner {

   private static final String ADMIN_ON_CALL_DEVICE = "111-111-1111";

   private StaffAssignmentManager staffAssignmentManager;
   private InboundPatientSource inboundPatientSource;
   private ArrayList<Integer> criticalPatientNotificationsSent;
   private AlertTransmitter transport;

   public AlertScanner(InboundPatientSource inboundPatientSource) {
      this.transport = PagerSystem.getTransport();
      this.inboundPatientSource = inboundPatientSource;
      criticalPatientNotificationsSent = new ArrayList<>();
   }

   //added to test transport methods
   public AlertScanner(InboundPatientSource inboundPatientSource, AlertTransmitter transport) {
      this.transport = transport;
      this.inboundPatientSource = inboundPatientSource;
      criticalPatientNotificationsSent = new ArrayList<>();
   }

   //added to conform to pluralsight code
   public AlertScanner(StaffAssignmentManager staffAssignmentManager, InboundPatientSource inboundPatientSource) {
      this.staffAssignmentManager = staffAssignmentManager;
      this.inboundPatientSource = inboundPatientSource;
      criticalPatientNotificationsSent = new ArrayList<>();
   }

   public void scan() {
      System.out.println("Scanning for situations requiring alerting...");
      List<Patient> inbound = inboundPatientSource.currentInboundPatients();
      for (Patient patient : inbound) {
         if (Priority.RED.equals(patient.getPriority()) ||
                 Priority.YELLOW.equals(patient.getPriority()) && "heart arrhythmia".equalsIgnoreCase(patient.getCondition())) {
            if (!criticalPatientNotificationsSent.contains(patient.getTransportId())) {
               alertForNewCriticalPatient(patient);
            }
         }
      }
   }

   protected void alertForNewCriticalPatient(Patient patient) {
      try {
         transmitAck(patient, transport);
         criticalPatientNotificationsSent.add(patient.getTransportId());
      } catch (Throwable t) {
         System.out.println("Failed attempt to use pager system to device " + ADMIN_ON_CALL_DEVICE);
      }
   }

   private void transmitAck(Patient patient, AlertTransmitter transport) {
      transport.initialize();
      transport.transmitRequiringAcknowledgement(ADMIN_ON_CALL_DEVICE, "New inbound critical patient: " +
         patient.getTransportId());
   }

}
