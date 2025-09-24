package edu.ccrm.domain;

public class Course {
    // final makes these fields immutable after creation
    private final String code;
    private final String title;
    private final int credits;
    private final String instructor; // Can be Instructor object later
    private final Semester semester;
    private final String department;

    // Private constructor - can only be called from within this class (by the Builder)
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
        this.department = builder.department;
    }

    // Only getters, no setters, to ensure immutability
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    // ... other getters ...

    @Override
    public String toString() {
        return String.format("[%s] %s - %d Credits", code, title, credits);
    }

    // --- Static Nested Builder Class ---
    public static class Builder {
        private final String code; // Required field
        private String title;
        private int credits;
        private String instructor;
        private Semester semester;
        private String department;

        public Builder(String code) {
            this.code = code;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this; // Return the builder for chaining methods
        }

        public Builder withCredits(int credits) {
            this.credits = credits;
            return this;
        }
        // Inside the Course class in Course.java

       

        public Course build() {
            // Here you can add validation logic, e.g., check if title is not null
            return new Course(this);
        }

		public Builder withDepartment(String dept) {
			// TODO Auto-generated method stub
			this.department = department;
            return this;
		}
    }

	public String getDepartment() {
		// TODO Auto-generated method stub
	
		return department;
	}
}