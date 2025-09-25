package edu.ccrm.domain;

// This class links a Student to a Course they are taking.
public class Enrollment
{
    private Student student;
    private Course course;
    private Grade grade;

    public Enrollment(Student student, Course course)
    {
        // Basic check to make sure the enrollment is valid.
        if (student == null || course == null) {
            throw new IllegalArgumentException("Student and Course cannot be null for an enrollment.");
        }
        this.student = student;
        this.course = course;
        this.grade = null; // A grade is usually assigned later.
    }

    // --- Standard Getters and Setters ---
    public Student getStudent() { return this.student; }
    public void setStudent(Student student) { this.student = student; }
    public Course getCourse() { return this.course; }
    public void setCourse(Course course) { this.course = course; }
    public Grade getGrade() { return this.grade; }
    public void setGrade(Grade grade) { this.grade = grade; }

    @Override
    public String toString()
    {
        String gradeString = (this.grade != null) ? this.grade.toString() : "Not Graded";
        return this.student.getFullName() + " in " + this.course.getTitle() + " (" + gradeString + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Enrollment that = (Enrollment) o;

        // An enrollment is considered unique for a given student and course combo.
        boolean studentsMatch = (this.student != null)
                ? this.student.equals(that.student)
                : that.student == null;
            
        boolean coursesMatch = (this.course != null)
                ? this.course.equals(that.course)
                : that.course == null;

        return studentsMatch && coursesMatch;
    }

    @Override
    public int hashCode()
    {
        // A classic manual hash code based on the fields from equals().
        int result = 17;
        result = 31 * result + (student == null ? 0 : student.hashCode());
        result = 31 * result + (course == null ? 0 : course.hashCode());
        
        return result;
    }
}
