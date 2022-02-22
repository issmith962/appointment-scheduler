package response;

public class StartResponse extends Response {
    public StartResponse(boolean success) {
        super(success, null);
    }

    public StartResponse(boolean success, String message) {
        super(success, message);
    }
}
