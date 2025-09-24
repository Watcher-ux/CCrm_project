package edu.ccrm.domain;

public enum Grade {
    S("Outstanding", 10.0),
    A("Excellent", 9.0),
    B("Very Good", 8.0),
    C("Good", 7.0),
    D("Average", 6.0),
    E("Pass", 5.0),
    F("Fail", 0.0),
    NA("Not Available", 0.0); // For courses not yet graded

    private final String description;
    private final double gradePoint;

    Grade(String description, double gradePoint) {
        this.description = description;
        this.gradePoint = gradePoint;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public String getDescription() {
        return description;
    }
}