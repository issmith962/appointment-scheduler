package service;

public class SchedulingAPIService {
    private static final String START_PATH = "/Start";
    private static final String STOP_PATH = "/Stop";
    private static final String APPOINTMENT_REQUEST_PATH = "/AppointmentRequest";
    private static final String SCHEDULE_PATH = "/Schedule";

    public SchedulingAPIService() {}

    public static StartResponse startAPI() {}
    public static StopResponse stopAPI() {}
    public static NextAppointmentResponse getNextAppointmentRequest() {}
    public static InitialScheduleResponse getInitialSchedule () {}
    public static ScheduleAppointmentResponse scheduleAppointment(ScheduleAppointmentRequest request) {}
}
