package main.service;

public class AppointmentOption {
    // High Priority matches doctor preference and day preference
    // Medium priority matches an xor preference
    //      Could add a 4th level if we want to prioritize matching one preference over the other
    // Low priority has no preference matches

    enum Priority {LOW, MEDIUM, HIGH}
    private String appointmentTime;
    private Integer doctorId;
    private Priority priority;

    public AppointmentOption() {
    }

    public AppointmentOption(String appointmentTime, Integer doctorId, Priority priority) {
        this.appointmentTime = appointmentTime;
        this.doctorId = doctorId;
        this.priority = priority;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
