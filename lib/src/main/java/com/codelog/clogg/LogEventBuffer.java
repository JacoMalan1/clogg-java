package com.codelog.clogg;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LogEventBuffer implements LogEventSubscriber {

    private List<LogEvent> past;
    private Stack<LogEvent> unhandled;

    public LogEventBuffer() {
        past = new ArrayList<>();
        unhandled = new Stack<>();
    }

    public Stack<LogEvent> getUnhandledEvents() { return unhandled; }
    public List<LogEvent> getPastEvents() { return past; }

    public LogEvent popEvent() {
        var event = unhandled.pop();
        past.add(event);
        return event;
    }

    @Override
    public void logEvent(LogEvent logEvent) {
        unhandled.push(logEvent);
    }
}
