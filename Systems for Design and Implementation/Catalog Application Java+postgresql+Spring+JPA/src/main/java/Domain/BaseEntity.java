package Domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Generic entity characterized by an ID.
 * @param <ID> represents unique identifier
 */
@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Serializable{
    @Id

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
