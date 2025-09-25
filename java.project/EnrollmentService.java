package edu.ccrm.service;

import edu.ccrm.data.DataStore;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import edu.ccrm.model.Course;
import edu.ccrm.model.Enrollment;
import edu.ccrm.model.Student;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class EnrollmentService {

    public static final int MAX_CREDITS_PER_STUDENT = 20;

    private final DataStore dataStore = DataStore.getInstance();
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final AtomicInteger lastEnrollmentId = new AtomicInteger(100);

    /**
     * Enrolls a student in a course, applying business logic rules.
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     * @return The new Enrollment object.
     * @throws DuplicateEnrollmentException if the student is already enrolled.
     * @throws MaxCreditLimitExceededException if the student's credit limit is exceeded.
     * @throws NoSuchElementException if the student or course is not found.
     */
    public Enrollment enrollStudent(int studentId, int courseId)
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {

        // 1. Find Student and Course
        Student student = studentService.findStudentById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));
        Course course = courseService.findCourseById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found with ID: " + courseId));

        // 2. Business Rule: Check for duplicate enrollment
        boolean isAlreadyEnrolled = dataStore.getEnrollments().stream()
                .anyMatch(e -> e.getStudentId() == studentId && e.getCourseId() == courseId);

        if (isAlreadyEnrolled) {
            throw new DuplicateEnrollmentException(
                "Student " + student.getName() + " is already enrolled in " + course.getCourseName()
            );
        }

        // 3. Business Rule: Check if credit limit will be exceeded
        if (student.getTotalCredits() + course.getCredits() > MAX_CREDITS_PER_STUDENT) {
            throw new MaxCreditLimitExceededException(
                "Enrollment failed: " + student.getName() + " would exceed the " +
                MAX_CREDITS_PER_STUDENT + " credit limit."
            );
        }

        // 4. If all rules pass, create the enrollment
        student.setTotalCredits(student.getTotalCredits() + course.getCredits()); // Update student's credits
        Enrollment newEnrollment = new Enrollment(
            lastEnrollmentId.incrementAndGet(), studentId, courseId, LocalDate.now()
        );
        dataStore.getEnrollments().add(newEnrollment);

        System.out.println("✅ Success! Enrolled " + student.getName() + " in " + course.getCourseName());
        return newEnrollment;
    }
}