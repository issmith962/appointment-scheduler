package main.service;

import main.api.ClientCommunicator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.model.Appointment;
import main.request.ScheduleAppointmentRequest;
import main.response.*;

import java.net.http.HttpResponse;
import java.util.List;

public class SchedulingAPIService {
    private static final String START_PATH = "/Start";
    private static final String STOP_PATH = "/Stop";
    private static final String APPOINTMENT_REQUEST_PATH = "/AppointmentRequest";
    private static final String SCHEDULE_PATH = "/Schedule";

    public SchedulingAPIService() {}

    public static StartResponse startAPI() {
        try {
            HttpResponse<String> apiResponse = ClientCommunicator.doPost(START_PATH, "");
            switch (apiResponse.statusCode()) {
                case 200:
                    return new StartResponse(true);
                case 401:
                    return new StartResponse(false, "401: Token is invalid");
                default:
                    return new StartResponse(false, "Unexpected main.response code: " + apiResponse.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new StartResponse(false, e.getMessage());
        }
    }

    public static StopResponse stopAPI() {
        try {
            HttpResponse<String> apiResponse = ClientCommunicator.doPost(STOP_PATH, "");
            switch (apiResponse.statusCode()) {
                case 200:
                    ObjectMapper objectMapper = new ObjectMapper();
                    return new StopResponse(objectMapper.readValue(apiResponse.body(), new TypeReference<List<Appointment>>(){}));
                case 401:
                    return new StopResponse("401: Token is invalid");
                default:
                    return new StopResponse("Unexpected main.response code: " + apiResponse.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new StopResponse(e.getMessage());
        }
    }

    public static NextAppointmentResponse getNextAppointmentRequest() {
        try {
            HttpResponse<String> apiResponse = ClientCommunicator.doGet(APPOINTMENT_REQUEST_PATH);
            switch (apiResponse.statusCode()) {
                case 200:
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(apiResponse.body(), NextAppointmentResponse.class);
                case 204:
                    return new NextAppointmentResponse(true, "There are no more requests to handle");
                case 401:
                    return new NextAppointmentResponse(false, "401: Token is invalid");
                case 405:
                    return new NextAppointmentResponse(false, "405: You have already called the stop endpoint for this run");
                default:
                    return new NextAppointmentResponse(false, "Unexpected main.response code: " + apiResponse.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new NextAppointmentResponse(false, e.getMessage());
        }
    }

    public static InitialScheduleResponse getInitialSchedule() {
        try {
            HttpResponse<String> apiResponse = ClientCommunicator.doGet(SCHEDULE_PATH);
            switch (apiResponse.statusCode()) {
                case 200:
                    ObjectMapper objectMapper = new ObjectMapper();
                    return new InitialScheduleResponse(objectMapper.readValue(apiResponse.body(), new TypeReference<List<Appointment>>(){}));
                case 401:
                    return new InitialScheduleResponse("401: Token is invalid");
                case 405:
                    return new InitialScheduleResponse("405: You have already called the stop endpoint for this run");
                default:
                    return new InitialScheduleResponse("Unexpected main.response code: " + apiResponse.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new InitialScheduleResponse(e.getMessage());
        }
    }

    public static ScheduleAppointmentResponse scheduleAppointment(ScheduleAppointmentRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestAsJsonString = objectMapper.writeValueAsString(request);
            HttpResponse apiResponse = ClientCommunicator.doPost(SCHEDULE_PATH, requestAsJsonString);
            switch (apiResponse.statusCode()) {
                case 200:
                    return new ScheduleAppointmentResponse(true);
                case 405:
                    return new ScheduleAppointmentResponse(false, "405: You have already called the stop endpoint for this run");
                case 500:
                    return new ScheduleAppointmentResponse(false, "500: The schedule was unable to accommodate your requested appointment");
                default:
                    return new ScheduleAppointmentResponse(false, "Unexpected main.response code: " + apiResponse.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ScheduleAppointmentResponse(false, e.getMessage());
        }
    }
}
