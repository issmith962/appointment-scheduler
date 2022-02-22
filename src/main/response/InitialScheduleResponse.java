package response;

import model.Appointment;

import java.util.List;

public class InitialScheduleResponse extends Response {
    private List<Appointment> appointments;

    public InitialScheduleResponse(List<Appointment> appointments) {
        super(true);
        this.appointments = appointments;
    }

    public InitialScheduleResponse(String message) {
        super(false, message);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
