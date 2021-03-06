package main;

import main.model.Appointment;
import main.request.ScheduleAppointmentRequest;
import main.response.*;
import main.service.AppointmentService;
import main.service.SchedulingAPIService;

import java.util.List;

/* SCHEDULING RESTRICTIONS:
            - Possible Times:
                Start (Inclusive): November 1, 2021 @ 8am
                End (Inclusive): December 31, 2021 @ 4pm
                All days in interval are available from 8am to 4pm (exactly on each hour)
                    UNLESS new patient, then only 3pm and 4pm are available
            - One appointment per doctor per hour
            - A patient's appointments must be split by a week
                If appointment is on Day:Time, we compare using:
                    Start of block (inclusive): Day - 6:truncate(Time)
                    End of block (exclusive): Day + 7:truncate(Time)
                    MAKE SURE TO TEST THESE TIMES --> NOT SURE IF MATH IS EXACTLY CORRECT */
public class Scheduler {
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

        /* 3. Loop through the appointment requests, schedule one by one.
           - Update current appointment list member variable in main.service.AppointmentService.java
           - Schedule the appointment through the API */
        while (true) {
            NextAppointmentResponse nextAppointmentResponse = SchedulingAPIService.getNextAppointmentRequest();
            if (!nextAppointmentResponse.isSuccess()) {
                throw new SchedulerException(nextAppointmentResponse.getMessage());
            }
            // End of main.request list reached -- I want to find a more elegant way than this, but this should work for now
            if ((nextAppointmentResponse.getRequestId() == null)
                    || nextAppointmentResponse.getPersonId() == null
                    || nextAppointmentResponse.getPreferredDays() == null
                    || nextAppointmentResponse.getPreferredDocs() == null
                    || nextAppointmentResponse.getNew() == null) {
                break;
            }
            Appointment appointment = AppointmentService.getInstance().findBestAvailableSlot(nextAppointmentResponse.getPersonId(),
                   nextAppointmentResponse.getPreferredDays(), nextAppointmentResponse.getPreferredDocs(), nextAppointmentResponse.getNew());
            if (appointment == null) {
                throw new SchedulerException("No open slots! Must a bug, since API was configured to have open slots for all requests.");
            }
            ScheduleAppointmentRequest scheduleAppointmentRequest = new ScheduleAppointmentRequest(nextAppointmentResponse.getRequestId(),
                    appointment.getDoctorId(), appointment.getPersonId(), appointment.getAppointmentTime(), appointment.isNewPatientAppointment());
            ScheduleAppointmentResponse scheduleAppointmentResponse = SchedulingAPIService.scheduleAppointment(scheduleAppointmentRequest);
            if (!scheduleAppointmentResponse.isSuccess()) {
                throw new SchedulerException(scheduleAppointmentResponse.getMessage());
            }
            AppointmentService.getInstance().addAppointment(appointment);
        }

        /* 4. Stop the API, check to make sure the resulting schedule matches the local current appt. list */
        StopResponse stopResponse = SchedulingAPIService.stopAPI();
        if (!stopResponse.isSuccess()) {
            throw new SchedulerException(stopResponse.getMessage());
        }
        /*  Fastest way to check finished list?
            Maybe I should sort them by date/time and check if they are equal?
            Would need to override equals() and compareTo() for appointment though... not sure
                how equals works on Lists...
               For now, I'll just print them out.
         */
        System.out.println("Remote Appointments");
        for (Appointment appt : stopResponse.getAppointments()) {
            System.out.println(appt);
        }

        System.out.println("Local Appointments");
        for (Appointment appt : AppointmentService.getInstance().getScheduledAppointments()) {
            System.out.println(appt);
        }
    }

}
