package Domain;

/**
 * Generic entity characterized by an ID.
 * @param <ID> represents unique identifier
 */
public class BaseEntity<ID> {
    private ID id;

    /**
     * @return unique identifier
     */
    public ID getId() {
        return id;
    }

    /**
     * set unique identifier
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * @return String representation of entity
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
