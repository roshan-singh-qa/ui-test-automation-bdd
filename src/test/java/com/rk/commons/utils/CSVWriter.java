package com.rk.commons.utils;

import lombok.Cleanup;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

public class CSVWriter {

    private CSVPrinter csvPrinter;

    public CSVWriter(String fileName, String... headers) throws IOException {
        archiveExistingFile(fileName);
        @Cleanup BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(fileName),
                StandardOpenOption.CREATE);
        writer.write('\ufeff');
        csvPrinter = new CSVPrinter(writer, CSVFormat.RFC4180.withHeader(headers));
    }

    public void write(Object... values) {
        try {
            csvPrinter.printRecord(values);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        try {
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void archiveExistingFile(String fileName) {
        if (Files.exists(Paths.get(fileName))) {
            var oldFileName = new File(fileName);
            var newFileName = new File(getDateTime() + fileName);
            oldFileName.renameTo(newFileName);
        }
    }

    private String getDateTime() {
        var timeStampPattern = DateTimeFormatter.ofPattern("yyyy_MM_dd_HHmmss_");
        return timeStampPattern.format(java.time.LocalDateTime.now());
    }

}
