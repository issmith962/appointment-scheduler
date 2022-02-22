package main;

/* SCHEDULING RESTRICTIONS:
            - Possible Times:
                Start (Inclusive): November 1, 2021 @ 8am
                End (Inclusive): December 31, 2021 @ 4pm
                All days in interval are available from 8am to 4pm (exactly on each hour)
                    UNLESS new patient, then only 3pm and 4pm are available
            - One appointment per doctor per hour
            - A patient's appointments must be split by a week
                If appointment is on Day:Time, we compare using:
                    Start of block (inclusive): Day - 6:truncate(Time)
                    End of block (exclusive): Day + 7:truncate(Time)
                    MAKE SURE TO TEST THESE TIMES --> NOT SURE IF MATH IS EXACTLY CORRECT */
public class Scheduler {
    /* 0. Initialize service instances */
    /* 1. Start API */
    /* 2. Get initial appointment list */
    /* 3. Loop through the appointment requests, schedule one by one.
           - Update current appointment list member variable in service.AppointmentService.java
           - Schedule the appointment through the API */
    /* 4. Stop the API, check to make sure the resulting schedule matches the local current appt. list */
}
