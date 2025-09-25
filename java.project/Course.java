package edu.ccrm.domain;

// Holds all the information for a specific course offered in a given semester.
public class Course
{
    private CourseCode courseCode;
    private String title;
    private int credits;
    private Instructor instructor;
    private Semester semester;
    private String department;

    public Course(CourseCode code, String title, int credits, Instructor prof, Semester term, String dept)
    {
        this.courseCode = code;
        this.title = title;
        this.credits = credits;
        this.instructor = prof;
        this.semester = term;
        this.department = dept;
    }

    // --- Standard Getters & Setters ---
    public CourseCode getCourseCode() { return this.courseCode; }
    public void setCourseCode(CourseCode courseCode) { this.courseCode = courseCode; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return this.credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public Instructor getInstructor() { return this.instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public Semester getSemester() { return this.semester; }
    public void setSemester(Semester semester) { this.semester = semester; }
    public String getDepartment() { return this.department; }
    public void setDepartment(String department) { this.department = department; }


    @Override
    public String toString()
    {
        // A simple, readable format for debugging.
        return this.title + " (" + this.courseCode + ") - " + this.semester;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Course that = (Course) o;

        // A course is uniquely identified by its code and the semester it's offered.
        boolean codeIsEqual = (this.courseCode != null)
            ? this.courseCode.equals(that.courseCode)
            : that.courseCode == null;

        // Enums can be compared directly with ==
        boolean semesterIsEqual = this.semester == that.semester;

        return codeIsEqual && semesterIsEqual;
    }

    @Override
    public int hashCode()
    {
        // A classic hash code implementation based on the fields used in equals().
        int result = 17;
        result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }
}