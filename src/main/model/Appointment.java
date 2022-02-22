package main.model;

public class Appointment {
    private int doctorId;
    private int personId;
    private String appointmentTime;
    private boolean isNewPatientAppointment;

    public Appointment() {}

    public Appointment(int doctorId, int personId, String appointmentTime, boolean isNewPatientAppointment) {
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

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public boolean isNewPatientAppointment() {
        return isNewPatientAppointment;
    }
}
