package test;

import main.SchedulerException;
import main.model.Appointment;
import main.response.InitialScheduleResponse;
import main.response.StartResponse;
import main.service.AppointmentService;
import main.service.SchedulingAPIService;

import java.util.List;

public class FakeScheduler {
    public static void main(String[] args) throws SchedulerException {
        /* 0. Initialize main.service instances */
        SchedulingAPIService schedulingAPIService = new SchedulingAPIService();
        AppointmentService appointmentService = AppointmentService.getInstance();

        /* 1. Start API */
        StartResponse startResponse = SchedulingAPIService.startAPI();
        if (!startResponse.isSuccess()) {
            throw new SchedulerException(startResponse.getMessage());
        }

        /* 2. Get initial appointment list */
        InitialScheduleResponse initialScheduleResponse = SchedulingAPIService.getInitialSchedule();
        if (!initialScheduleResponse.isSuccess()) {
            throw new SchedulerException(initialScheduleResponse.getMessage());
        }
        List<Appointment> appointments = initialScheduleResponse.getAppointments();
        AppointmentService.getInstance().setScheduledAppointments(appointments);

        System.out.println("Local Appointments");
        for (Appointment appt : AppointmentService.getInstance().getScheduledAppointments()) {
            System.out.println(appt);
        }
    }
}
