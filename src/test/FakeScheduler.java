package test;

import main.SchedulerException;
import main.response.StartResponse;
import main.service.AppointmentService;
import main.service.SchedulingAPIService;

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

        System.out.println(startResponse.isSuccess());;
    }
}
