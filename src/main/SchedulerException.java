package main;

public class SchedulerException extends Exception {
    private String message;

    public SchedulerException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}