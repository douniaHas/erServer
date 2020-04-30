package erserver.modules.dependencies;

import java.util.Arrays;
import java.util.List;

public class StaffDouble implements StaffProvider {
    @Override
    public List<Staff> getShiftStaff() {
        return Arrays.asList(new Staff(1,"Jeff", StaffRole.DOCTOR),
                            new Staff(2,"Pat", StaffRole.RESIDENT));
    }
}
