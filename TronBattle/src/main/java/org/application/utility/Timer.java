package org.application.utility;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Timer {
    private static Timer timer;
    private LocalDateTime start;

    private Timer() {}
    public static Timer getInstance() {
        if (timer == null) {
            timer = new Timer();
        }
        return timer;
    }

    // funzioni per determinare il tempo di esecuzione
    public void start() {
        start = LocalDateTime.now();
    }
    public void end(String name) {
        long millis = ChronoUnit.MILLIS.between(start, LocalDateTime.now());
        System.out.println("Tempo di esecuzione " +  name + ": " + millis + " millisecondi");
    }
}
