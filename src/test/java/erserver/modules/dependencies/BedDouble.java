package erserver.modules.dependencies;

import java.util.Arrays;
import java.util.List;

public class BedDouble implements BedProvider {
    @Override
    public List<Bed> getAllBeds() {
        return Arrays.asList(new Bed(1, true), new Bed(2,false));
    }
}
