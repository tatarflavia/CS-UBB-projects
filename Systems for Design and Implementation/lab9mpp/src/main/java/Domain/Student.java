package Domain;

import javax.persistence.Entity;

@Entity
public class Student  extends BaseEntity<Long> {
    private String name;

    public Student() {
    }

    /**Creates Student with unique id.
     * @param name full name of the student
     * @param id unique identifier
     */
    public Student(String name,long id) {
        this.name=name;
        super.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String representation of student
     */
    @Override
    public String toString() {
        return "Student{"+ "name="+name+", id="+super.getId()+"}";
    }

    /**
     * @return full name of the student
     */
    public String getName() {
        return name;
    }
}
