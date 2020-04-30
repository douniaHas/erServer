package erserver.modules.dependencies;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EREndpointsTest {
    @Test
    public void getPhysiciansOnDuty(){
        StaffProvider staff = new StaffDouble();
        BedProvider bed = new BedDouble();
        StaffAssignmentManager manager = new StaffAssignmentManager(staff, bed);
        List<Staff> staffs = manager.getPhysiciansOnDuty();
        assertEquals(2,staffs.size());
    }

    @Test
    public void getPhysiciansOnDutyProviderMocked(){
        StaffProvider staff = new StaffRepository();
        BedProvider bed = new BedRepository();
        StaffAssignmentManager manager = new StaffAssignmentManager(staff, bed);
        List<Staff> staffs = manager.getPhysiciansOnDuty();
        assertEquals(2,staffs.size());
    }

}
