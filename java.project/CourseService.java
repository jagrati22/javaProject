package edu.ccrm.service;

import edu.ccrm.data.DataStore;
import edu.ccrm.model.Course;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseService implements Searchable<Course> {

    private final DataStore dataStore = DataStore.getInstance();

    /**
     * Retrieves all courses.
     * @return A list of all courses.
     */
    public List<Course> getAllCourses() {
        return dataStore.getCourses();
    }
    
    /**
     * Finds a course by its ID.
     * @param id The ID of the course to find.
     * @return An Optional containing the course if found, otherwise empty.
     */
    public Optional<Course> findCourseById(int id) {
        return dataStore.getCourses().stream()
                .filter(course -> course.getCourseId() == id)
                .findFirst();
    }

    /**
     * Searches for courses using a lambda expression as a filter.
     * Implements the Searchable interface.
     * @param filter A Predicate defining the search condition.
     * @return A list of courses matching the filter.
     */
    @Override
    public List<Course> search(Predicate<Course> filter) {
        return dataStore.getCourses().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }
}