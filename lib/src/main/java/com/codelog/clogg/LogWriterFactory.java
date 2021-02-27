package com.codelog.clogg;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class LogWriterFactory {

    public static LogWriter createWriter(OutputStream stream) {
        return new LogWriter(stream);
    }

    public static LogWriter createPrintStreamLogWriter(PrintStream infoStream, PrintStream errorStream) {
        return new PrintStreamLogWriter(infoStream, errorStream);
    }

    public static LogWriter createPrintStreamLogWriter() {
        return createPrintStreamLogWriter(System.out, System.err);
    }

    public static LogWriter createFileLogWriter(String logFileName) throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date date = Date.from(Instant.now());

        int num = 0;
        boolean _result = new File("logs").mkdir();
        String originalName = logFileName;
        logFileName = String.format("logs/%s_%s_%s.log", originalName, formatter.format(date), num);
        while (Files.exists(Path.of(logFileName))) {
            num++;
            logFileName = String.format("logs/%s_%s_%s.log", originalName, formatter.format(date), num);
        }

        return new LogWriter(new FileWriter(logFileName));

    }

}
