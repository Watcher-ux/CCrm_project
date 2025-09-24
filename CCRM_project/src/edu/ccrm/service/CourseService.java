package edu.ccrm.service;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Course;
import edu.ccrm.exception.DuplicateCourseException;

import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    private final DataStore dataStore = DataStore.getInstance();

 // Inside the CourseService class in CourseService.java

    public void addCourse(Course course) throws DuplicateCourseException {
        if (dataStore.courses.containsKey(course.getCode())) {
            throw new DuplicateCourseException("A course with code " + course.getCode() + " already exists.");
        }
        dataStore.courses.put(course.getCode(), course);
    }

    public Course findCourseByCode(String courseCode) {
        return dataStore.courses.get(courseCode);
    }
    
    // This is where you can use the Stream API as required
    public List<Course> findCoursesByDepartment(String department) {
        return dataStore.courses.values().stream()
                .filter(course -> course.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }
    
    // TODO: Add more stream-based search methods (e.g., findCoursesByInstructor).
}