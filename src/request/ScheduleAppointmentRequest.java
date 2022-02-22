package request;

import java.time.ZonedDateTime;

public class ScheduleAppointmentRequest {
    private Integer requestId;
    private Integer doctorId;
    private Integer personId;
    private ZonedDateTime appointmentTime;
    private Boolean isNewPatientAppointment;

    public ScheduleAppointmentRequest(Integer requestId, Integer doctorId, Integer personId, ZonedDateTime appointmentTime, boolean isNewPatientAppointment) {
        this.requestId = requestId;
        this.doctorId = doctorId;
        this.personId = personId;
        this.appointmentTime = appointmentTime;
        this.isNewPatientAppointment = isNewPatientAppointment;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public ZonedDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public boolean isNewPatientAppointment() {
        return isNewPatientAppointment;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setAppointmentTime(ZonedDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setNewPatientAppointment(boolean newPatientAppointment) {
        isNewPatientAppointment = newPatientAppointment;
    }
}
