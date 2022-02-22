package main.service;

import main.model.Appointment;

import java.time.ZoneId;
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
        ZonedDateTime startSlot = ZonedDateTime.of(2021, 11,1,8,0,0,0, ZoneId.of("UTC"));
        ZonedDateTime endSlot = ZonedDateTime.of(2021, 12, 31, 4, 0, 0, 0, ZoneId.of("UTC"));

        ZonedDateTime slot = startSlot;
        while(slot.isBefore(endSlot) || slot.isEqual(endSlot)) {
            ZonedDateTime slotPlusHour = slot;
            ZonedDateTime lastSlotOfDay = slot.withHour(16);
            while (slotPlusHour.isBefore(lastSlotOfDay) || slotPlusHour.isEqual(lastSlotOfDay)) {
                allTimeSlots.add(slotPlusHour);
                slotPlusHour = slotPlusHour.plusHours(1);
            }
            slot = slot.plusDays(1);
        }
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

    public void addAppointment(Appointment appointment) {
        this.scheduledAppointments.add(appointment);
    }

    public Appointment findBestAvailableSlot(Integer personId, List<ZonedDateTime> preferredDays, List<Integer> preferredDocs, Boolean isNew) {
        // return an appointment for person during best open slot (according to preferences)
        return null; // for now
    }
}
