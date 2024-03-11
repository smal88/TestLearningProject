package com.learning.assignment.enrollment;

public class EnrollmentRecord {
    private String userId;
    private String firstName;
    private String lastName;
    private int version;
    private String system;

    public EnrollmentRecord(String userId, String firstName, String lastName, int version, String system) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.version = version;
        this.system = system;
    }

    public String getUserId() {
        return userId;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public int getVersion() {
        return version;
    }

    public String getSystem() {
        return system;
    }

    @Override
    public String toString() {
        return userId + "," + firstName + "," + lastName + "," + version + "," + system;
    }
}


