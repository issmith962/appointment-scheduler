package response;

import java.time.ZonedDateTime;
import java.util.List;

public class NextAppointmentResponse extends Response {
    private int requestId;
    private int personId;
    private List<ZonedDateTime> preferredDays;
    private List<Integer> preferredDocs;
    private boolean isNew;

    public NextAppointmentResponse(int requestId, int personId, List<ZonedDateTime> preferredDays, List<Integer> preferredDocs, boolean isNew) {
        super(true, null);
        this.requestId = requestId;
        this.personId = personId;
        this.preferredDays = preferredDays;
        this.preferredDocs = preferredDocs;
        this.isNew = isNew;
    }

    public NextAppointmentResponse(boolean success, String message) {
        super(success, message);
        this.requestId = -1; 
    }

    public Integer getRequestId() {
        return requestId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public List<ZonedDateTime> getPreferredDays() {
        return preferredDays;
    }

    public List<Integer> getPreferredDocs() {
        return preferredDocs;
    }

    public Boolean getNew() {
        return isNew;
    }
}
