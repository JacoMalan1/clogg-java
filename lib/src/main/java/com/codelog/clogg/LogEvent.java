package com.codelog.clogg;

import java.time.Instant;

public class LogEvent {

    String message;
    Instant timeStamp;
    LogLevel logLevel;

    public LogEvent(String message, Instant timeStamp, LogLevel logLevel) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }
}
