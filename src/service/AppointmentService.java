package service;

import model.Appointment;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private static AppointmentService instance = null;
    private ArrayList<Appointment> scheduledAppointments;
    private ArrayList<ZonedDateTime> allTimeSlots;

    private AppointmentService() {
        scheduledAppointments = new ArrayList<Appointment>();
        allTimeSlots = new ArrayList<ZonedDateTime>();

        // Make list of all time slots...
    }

    public static AppointmentService getInstance() {
        if (instance == null) {
            instance = new AppointmentService();
        }
        return instance;
    }

    public void setScheduledAppointments(List<Appointment> appointments) {
        this.scheduledAppointments = (ArrayList<Appointment>) appointments;
    }

    public List<Appointment> getScheduledAppointments() {
        return this.scheduledAppointments;
    }
}
