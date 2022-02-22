package main.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ClientCommunicator {
    private static final String SCHEDULER_API_URL = "http://scheduling-interview-2021-265534043.us-west-2.elb.amazonaws.com/api/Scheduling";
    private static final String AUTH_TOKEN = "3206d505-c34c-4aa8-be83-7dba807ea7c2";
    private static final int TIMEOUT = 15;

    public static HttpResponse<String> doPost(String endpointPath, String requestBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SCHEDULER_API_URL + endpointPath + "?token=" + AUTH_TOKEN))
                .timeout(Duration.ofSeconds(TIMEOUT))
                .POST(BodyPublishers.ofString(requestBody))
                .header("Content-Type", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> doGet(String endpointPath) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SCHEDULER_API_URL + endpointPath + "?token=" + AUTH_TOKEN))
                .timeout(Duration.ofSeconds(TIMEOUT))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
