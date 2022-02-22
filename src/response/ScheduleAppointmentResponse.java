package response;

public class ScheduleAppointmentResponse extends Response {
    public ScheduleAppointmentResponse(boolean success) {
        super(success, null);
    }

    public ScheduleAppointmentResponse(boolean success, String message) {
        super(success, message);
    }
}
