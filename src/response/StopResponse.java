package response;

import model.Appointment;

import java.util.List;

public class StopResponse extends Response {
    private List<Appointment> appointments;

    public StopResponse(List<Appointment> appointments) {
        super(true);
        this.appointments = appointments;
    }

    public StopResponse(String message) {
        super(false, message);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
