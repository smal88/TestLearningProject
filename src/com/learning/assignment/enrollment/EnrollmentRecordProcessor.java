package com.learning.assignment.enrollment;

import java.io.*;
import java.util.*;

/**
 * EnrollmentRecordProcessor to process enrollment records
 */

public class EnrollmentRecordProcessor {
    private final String inputDirectory;
    private final String outputDirectory;

    public EnrollmentRecordProcessor(String inputDirectory, String outputDirectory) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
    }
    public void processFiles() {

        File folder = new File(inputDirectory);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".csv")) {
                            processCSV(file,outputDirectory);
                    }
                }
            }
        }
    }

    /**
     * to process enrollment file and generate enrollment record files based on each system.
     */
    private static void processCSV(File csvFile, String outputDirectory) {
        List<EnrollmentRecord> enrollmentRecordsList = readCSV(csvFile);

        Map<String, List<EnrollmentRecord>> companyEnrollRecordsMap = new HashMap<>();
        for (EnrollmentRecord enrollRecord : enrollmentRecordsList) {
            companyEnrollRecordsMap.putIfAbsent(enrollRecord.getSystem(), new ArrayList<>());
            companyEnrollRecordsMap.get(enrollRecord.getSystem()).add(enrollRecord);
        }

        for (Map.Entry<String, List<EnrollmentRecord>> entry : companyEnrollRecordsMap.entrySet()) {
            String company = entry.getKey();
            List<EnrollmentRecord> enrollmentRecords = entry.getValue();
            enrollmentRecords.sort(Comparator.comparing(EnrollmentRecord::getLastName)
                    .thenComparing(EnrollmentRecord::getFirstName)
                    .thenComparingInt(EnrollmentRecord::getVersion));

            Map<String, EnrollmentRecord> uniqueEnrollRecord = new HashMap<>();
            for (EnrollmentRecord record : enrollmentRecords) {
                if (!uniqueEnrollRecord.containsKey(record.getUserId())
                        || uniqueEnrollRecord.get(record.getUserId()).getVersion() < record.getVersion()) {
                    uniqueEnrollRecord.put(record.getUserId(), record);
                }
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputDirectory + company + "_EnrollRecords.csv"))) {
                for (EnrollmentRecord enrollee : uniqueEnrollRecord.values()) {
                    writer.println(enrollee);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * to read enroll csv file and return lisy of enrollment records.
     */
    private static List<EnrollmentRecord> readCSV(File file) {
        List<EnrollmentRecord> enrollmentRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    enrollmentRecords.add(new EnrollmentRecord(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enrollmentRecords;
    }
}

