package erserver.modules.dependencies;

import erserver.modules.dependencies.vendorpagersystem.AlertTransmitter;

import javax.naming.CompositeName;
import java.util.ArrayList;
import java.util.List;

public class PagerTransportTestDouble implements AlertTransmitter {


    private List<String> alertsReceived = new ArrayList<>();
    private List<String> alertsRequiringAckReceived = new ArrayList<>();;

    @Override
    public void transmit(String targetDevice, String pageText) {
        alertsReceived.add(getAlert(targetDevice, pageText));
    }

    public String getAlert(String targetDevice, String pageText) {
        return targetDevice + " " + pageText;
    }

    @Override
    public void transmitRequiringAcknowledgement(String targetDevice, String pageText) {
        alertsRequiringAckReceived.add(getAlert(targetDevice, pageText));
    }

    @Override
    public void initialize() {

    }

    public List<String> getAlertsReceived() {
        return alertsReceived;
    }

    public List<String> getAlertsRequiringAckReceived() {
        return alertsRequiringAckReceived;
    }
}
