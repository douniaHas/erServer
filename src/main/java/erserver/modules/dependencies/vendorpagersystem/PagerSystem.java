package erserver.modules.dependencies.vendorpagersystem;

public final class PagerSystem {

    public static PagerTransport getTransport() {
        return new PagerTransport();
    }

    public static void closeTransport(AlertTransmitter transport) {
        throw new RuntimeException("represents a vendor class requiring install on server");
    }

}
