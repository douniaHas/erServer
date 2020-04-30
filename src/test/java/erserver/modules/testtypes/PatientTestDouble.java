package erserver.modules.testtypes;

import java.time.LocalDate;

public class PatientTestDouble extends Patient {
    private LocalDate current;

    @Override
    public LocalDate getSystemCurrentDate() {
        return current;
    }

    public void setCurrent(LocalDate current) {
        this.current = current;
    }
}
