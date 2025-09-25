package edu.ccrm.domain;

// Represents a basic person in the university system.
// Meant to be extended by more specific classes like Student or Staff.
public abstract class Person {

    protected Long id;
    protected String fullName;
    protected String email;

    public Person(Long personId, String name, String emailAddress) {
        this.id = personId;
        this.fullName = name;
        this.email = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        // Simple string building for the output.
        return "Person[ID=" + id + ", Name='" + fullName + "', Email='" + email + "']";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // The same object in memory
        }
        if (obj == null) {
            return false; // Can't be equal to null
        }
        if (getClass() != obj.getClass()) {
            return false; // Must be the exact same class
        }
        
        final Person other = (Person) obj;

        // Manually check the 'id' field for equality, handling nulls.
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }

        // Manually check the 'email' field for equality, handling nulls.
        if (this.email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!this.email.equals(other.email)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        // A traditional way to calculate a hash code.
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }
}