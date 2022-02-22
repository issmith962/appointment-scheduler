package main.service;

import main.model.Appointment;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class AppointmentService {
    private static AppointmentService instance = null;
    private ArrayList<Appointment> scheduledAppointments;
    private ArrayList<String> allTimeSlots;
    private HashMap<String, ArrayList<Integer>> timeToDoctorOccupied;

    private AppointmentService() {
        scheduledAppointments = new ArrayList<>();
        allTimeSlots = new ArrayList<>();
        timeToDoctorOccupied = new HashMap<String, ArrayList<Integer>>();

        // Make list of all time slots...
        ZonedDateTime startSlot = ZonedDateTime.of(2021, 11,1,8,0,0,0, ZoneId.of("UTC"));
        ZonedDateTime endSlot = ZonedDateTime.of(2021, 12, 31, 4, 0, 0, 0, ZoneId.of("UTC"));

        ZonedDateTime slot = startSlot;
        while(slot.isBefore(endSlot) || slot.isEqual(endSlot)) {
            ZonedDateTime slotPlusHour = slot;
            ZonedDateTime lastSlotOfDay = slot.withHour(16);
            while (slotPlusHour.isBefore(lastSlotOfDay) || slotPlusHour.isEqual(lastSlotOfDay)) {
                allTimeSlots.add(slotPlusHour.toString());
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

    public Appointment findBestAvailableSlot(Integer personId, List<String> preferredDays, List<Integer> preferredDocs, Boolean isNew) {
        ArrayList<AppointmentOption> options = new ArrayList<>();

        ArrayList<ArrayList<ZonedDateTime>> offLimitIntervals = new ArrayList<>();
        for (Appointment appt : scheduledAppointments) {
            if (appt.getPersonId() == personId) {
                ArrayList<ZonedDateTime> plusMinusOneWeek = new ArrayList<>();
                ZonedDateTime zdt = ZonedDateTime.parse(appt.getAppointmentTime());
                plusMinusOneWeek.add(zdt.minusDays(6).truncatedTo(DAYS).withHour(8));
                plusMinusOneWeek.add(zdt.plusDays(7).truncatedTo(DAYS).withHour(16));

                offLimitIntervals.add(plusMinusOneWeek);
            }
            if (!timeToDoctorOccupied.containsKey(appt.getAppointmentTime())) {
                timeToDoctorOccupied.put(appt.getAppointmentTime(), new ArrayList<>());
            }
            timeToDoctorOccupied.get(appt.getAppointmentTime()).add(appt.getDoctorId());
        }
        for (String timeSlot : allTimeSlots) {
            boolean isOption = true;
            ZonedDateTime zdt = ZonedDateTime.parse(timeSlot);
            for (ArrayList<ZonedDateTime> interval : offLimitIntervals) {
                if (zdt.isBefore(interval.get(0)) || zdt.isEqual(interval.get(1)) || zdt.isAfter(interval.get(1))) {
                    isOption = false;
                    break;
                }
            }
            if (!isOption) {
                continue;
            }
            if (timeToDoctorOccupied.containsKey(timeSlot) && timeToDoctorOccupied.get(timeSlot).size() == 3) {
                continue;
            }
            if (isNew && !(zdt.getHour() == 15 || zdt.getHour() == 16)) {
                continue;
            }
            // Check preferences, add new object to options with priority if in preferences
        }
        return null;
    }
}
