package com.learning.assignment.enrollment;

public class EnrollmentProcessor {
    public static void main(String[] args) {
        String inputDirectory = "input/";
        String outputDirectory = "output/";

        EnrollmentRecordProcessor processor = new EnrollmentRecordProcessor(inputDirectory, outputDirectory);
        processor.processFiles();
    }
}
