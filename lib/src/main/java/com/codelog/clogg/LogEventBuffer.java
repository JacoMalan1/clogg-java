package com.codelog.clogg;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;

public class LogEventBuffer implements LogEventSubscriber {

    private List<LogEvent> past;
    private Queue<LogEvent> unhandled;

    public LogEventBuffer() {
        past = new ArrayList<>();
        unhandled = new ArrayDeque<>();
    }

    public Queue<LogEvent> getUnhandledEvents() { return unhandled; }
    public List<LogEvent> getPastEvents() { return past; }

    public LogEvent popEvent() {
        var event = unhandled.remove();
        past.add(event);
        return event;
    }

    @Override
    public void logEvent(LogEvent logEvent) {
        unhandled.add(logEvent);
    }
}
