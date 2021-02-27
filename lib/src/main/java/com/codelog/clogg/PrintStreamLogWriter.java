package com.codelog.clogg;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintStreamLogWriter extends LogWriter {

    private PrintStream infoStream;
    private PrintStream errorStream;

    public PrintStreamLogWriter(PrintStream infoStream, PrintStream errorStream) {
        super(infoStream);
        this.infoStream = infoStream;
        this.errorStream = errorStream;
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

        if (logEvent.getLogLevel().ordinal() >= LogLevel.Warn.ordinal())
            errorStream.print(output);
        else
            infoStream.print(output);

    }
}
