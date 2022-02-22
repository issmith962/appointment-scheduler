package response;

public class Response {
    private boolean success;
    private String message;

    public Response(boolean success) {
        this.success = success;
        this.message = null;
    }

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
