package com.codelog.clogg;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter implements LogEventSubscriber {
    private final BufferedWriter writer;

    public LogWriter(OutputStream outputStream) {
        OutputStreamWriter osWriter = new OutputStreamWriter(outputStream);
        this.writer = new BufferedWriter(osWriter);
    }

    public LogWriter(Writer writer) {
        this.writer = new BufferedWriter(writer);
    }

    @Override
    public void logEvent(LogEvent logEvent) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = Date.from(logEvent.getTimeStamp());
        String output = String.format(
                "(%s)[%s]: %s\n",
                formatter.format(date),
                logEvent.getLogLevel().name(),
                logEvent.getMessage());

        try {
            writer.write(output);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Could not write log event!");
            e.printStackTrace(System.err);
        }
    }
}
