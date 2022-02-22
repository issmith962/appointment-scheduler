package main.model;

import java.time.ZonedDateTime;

public class Appointment {
    private int doctorId;
    private int personId;
    private ZonedDateTime appointmentTime;
    private boolean isNewPatientAppointment;

    public Appointment() {}

    public Appointment(int doctorId, int personId, ZonedDateTime appointmentTime, boolean isNewPatientAppointment) {
        this.doctorId = doctorId;
        this.personId = personId;
        this.appointmentTime = appointmentTime;
        this.isNewPatientAppointment = isNewPatientAppointment;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getPersonId() {
        return personId;
    }

    public ZonedDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public boolean isNewPatientAppointment() {
        return isNewPatientAppointment;
    }
}
