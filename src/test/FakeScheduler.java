package test;

import main.service.AppointmentService;
import main.service.SchedulingAPIService;

public class FakeScheduler {
    public static void main(String[] args) {
        /* 0. Initialize main.service instances */
        SchedulingAPIService schedulingAPIService = new SchedulingAPIService();
        AppointmentService appointmentService = AppointmentService.getInstance();
    }
}
