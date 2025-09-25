package edu.ccrm.domain;

// Represents a course identifier like "CS-101".
// This is an immutable value object.
public final class CourseCode
{
    private final String departmentCode;
    private final int courseNumber;

    public CourseCode(String deptCode, int number)
    {
        // It's good practice to validate inputs for value objects.
        if (deptCode == null || deptCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Department code cannot be empty.");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("Course number must be a positive value.");
        }
        this.departmentCode = deptCode;
        this.courseNumber = number;
    }

    // --- Field Accessors ---
    public String getDepartmentCode() { return this.departmentCode; }
    public int getCourseNumber() { return this.courseNumber; }


    @Override
    public String toString()
    {
        // A more readable format like "CS-101".
        return this.departmentCode + "-" + this.courseNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CourseCode that = (CourseCode) o;

        // First, compare the primitive int, as it's faster.
        if (this.courseNumber != that.courseNumber) {
            return false;
        }

        // Then, do a null-safe comparison of the String.
        if (this.departmentCode != null) {
            return this.departmentCode.equals(that.departmentCode);
        } else {
            return that.departmentCode == null;
        }
    }

    @Override
    public int hashCode()
    {
        // A classic manual hash code implementation.
        int result = 17; // Start with a non-zero prime
        result = 31 * result + this.courseNumber;
        result = 31 * result + (this.departmentCode == null ? 0 : this.departmentCode.hashCode());
        return result;
    }
}

