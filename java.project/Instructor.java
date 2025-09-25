package edu.ccrm.domain;

// Represents a professor or other instructor at the university.
public class Instructor extends Person
{
    private String department;

    public Instructor(Long id, String fullName, String email, String department)
    {
        super(id, fullName, email);
        this.department = department;
    }

    // --- Class-specific properties ---
    public String getDepartment() { return this.department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString()
    {
        // A simple format for easy identification.
        return "Instructor: " + this.fullName + " [" + this.department + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        // First, confirm the 'Person' part of the objects are the same.
        if (!super.equals(obj)) {
            return false;
        }

        Instructor other = (Instructor) obj;

        // Now, check the instructor-specific field for equality.
        if (this.department != null) {
            return this.department.equals(other.department);
        } else {
            return other.department == null;
        }
    }

    @Override
    public int hashCode()
    {
        // Combine the parent hash code with this class's fields.
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (department == null ? 0 : department.hashCode());
        return result;
    }
}