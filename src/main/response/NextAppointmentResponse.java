package main.response;

import java.util.List;

public class NextAppointmentResponse extends Response {
    private Integer requestId;
    private Integer personId;
    private List<String> preferredDays;
    private List<Integer> preferredDocs;
    private Boolean isNew;

    public NextAppointmentResponse(int requestId, int personId, List<String> preferredDays, List<Integer> preferredDocs, boolean isNew) {
        super(true, null);
        this.requestId = requestId;
        this.personId = personId;
        this.preferredDays = preferredDays;
        this.preferredDocs = preferredDocs;
        this.isNew = isNew;
    }

    public NextAppointmentResponse(boolean success, String message) {
        super(success, message);
        this.requestId = null;
        this.personId = null;
        this.preferredDays = null;
        this.preferredDocs = null;
        this.isNew = null;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public List<String> getPreferredDays() {
        return preferredDays;
    }

    public List<Integer> getPreferredDocs() {
        return preferredDocs;
    }

    public Boolean getNew() {
        return isNew;
    }
}
