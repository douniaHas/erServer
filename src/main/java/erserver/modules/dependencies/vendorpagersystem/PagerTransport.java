package erserver.modules.dependencies.vendorpagersystem;

public final class PagerTransport implements AlertTransmitter {

    public void initialize() {
        throw new RuntimeException("represents a vendor class requiring install on server");
    }

    @Override
    public void transmit(String targetDevice, String pageText) {
        throw new RuntimeException("represents a vendor class requiring install on server");
    }

    @Override
    public void transmitRequiringAcknowledgement(String targetDevice, String pageText) {
        throw new RuntimeException("represents a vendor class requiring install on server");
    }
}
