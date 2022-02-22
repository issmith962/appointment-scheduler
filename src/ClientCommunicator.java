import java.net.http.HttpResponse;

public class ClientCommunicator {
    private static final String SCHEDULER_API_URL = "http://scheduling-interview-2021-265534043.us-west-2.elb.amazonaws.com/api/Scheduling";
    private static final String AUTH_TOKEN = "3206d505-c34c-4aa8-be83-7dba807ea7c2";

    public static HttpResponse<String> doPost(String endpointPath, String requestBody) {}
    public static HttpResponse<String> doGet(String endpointPath) {}
}
