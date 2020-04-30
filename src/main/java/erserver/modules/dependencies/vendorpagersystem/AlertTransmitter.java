package erserver.modules.dependencies.vendorpagersystem;

public interface AlertTransmitter {
    void transmit(String targetDevice, String pageText);

    void transmitRequiringAcknowledgement(String targetDevice, String pageText);

    void initialize();
}
