package edu.ccrm.service;

import edu.ccrm.data.DataStore;
import edu.ccrm.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentService {

    private final DataStore dataStore = DataStore.getInstance();
    private final AtomicInteger lastId = new AtomicInteger(10); // For generating new IDs

    /**
     * Retrieves all students.
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        return dataStore.getStudents();
    }

    /**
     * Finds a student by their ID.
     * @param id The ID of the student to find.
     * @return An Optional containing the student if found, otherwise empty.
     */
    public Optional<Student> findStudentById(int id) {
        return dataStore.getStudents().stream()
                .filter(student -> student.getStudentId() == id)
                .findFirst();
    }

    /**
     * Adds a new student to the data store.
     * @param name The name of the new student.
     * @return The newly created Student object.
     */
    public Student addStudent(String name) {
        Student newStudent = new Student(lastId.incrementAndGet(), name, 0);
        dataStore.getStudents().add(newStudent);
        return newStudent;
    }
}