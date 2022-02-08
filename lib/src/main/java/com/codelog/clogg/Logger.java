package com.codelog.clogg;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    private int logLevel;
    private final List<LogEventSubscriber> logEventSubscribers;

    /**
     * Initializes a new instance of the Logger class
     */
    private Logger() {
        logLevel = LogLevel.Debug.ordinal();
        logEventSubscribers = new ArrayList<>();
    }

    /**
    * Gets an instance of the Logger class
    * @return An instance of Logger
    */
    public static Logger getInstance() {
        if (instance == null)
            instance = new Logger();
        return instance;
    }

    public void addLogEventSubscriber(LogEventSubscriber subscriber) {
        if (!logEventSubscribers.contains(subscriber))
            logEventSubscribers.add(subscriber);
    }

    public void removeLogEventSubscriber(LogEventSubscriber subscriber) {
        logEventSubscribers.remove(subscriber);
    }

    public void setLogLevel(int logLevel) { this.logLevel = logLevel; }
    public int getLogLevel() { return logLevel; }

    public void exception(Exception e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        error(writer.toString());
    }

    private void log(String message, LogLevel logLevel) {
        if (logLevel.ordinal() < this.logLevel)
            return;

        var event = new LogEvent(
                message,
                Instant.now(),
                logLevel
        );
        for (var subscriber : logEventSubscribers)
            subscriber.logEvent(event);
    }

    /**
    * Logs a message with INFO severity
    * @param message Message to be logged
    */
    public void info(String message) {
        log(message, LogLevel.Info);
    }

    public void debug(String message) {
        log(message, LogLevel.Debug);
    }

    public void error(String message) {
        log(message, LogLevel.Error);
    }

    public void warn(String message) {
        log(message, LogLevel.Warn);
    }
}
