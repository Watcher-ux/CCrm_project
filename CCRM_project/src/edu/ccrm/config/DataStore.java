package edu.ccrm.config;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import java.util.HashMap;
import java.util.Map;

// The Singleton pattern ensures we have only one data store.
public class DataStore {
    // 1. A private static final instance of itself
    private static final DataStore instance = new DataStore();

    // Data is stored in memory using HashMaps
    public final Map<String, Student> students = new HashMap<>();
    public final Map<String, Course> courses = new HashMap<>();

    // 2. A private constructor so no one else can create an instance
    private DataStore() {}

    // 3. A public static method to get the single instance
    public static DataStore getInstance() {
        return instance;
    }
}