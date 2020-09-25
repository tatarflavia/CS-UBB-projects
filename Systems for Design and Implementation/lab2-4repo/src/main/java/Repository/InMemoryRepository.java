package Repository;

import Domain.BaseEntity;
import Exception.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * In memory repository that holds entities that have a unique identifier.
 * @param <ID> the type of the unique identifier
 * @param <T> the type of the entities stored in the repository
 */
public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    private Map<ID, T> entities;

    /**
     * Creates an empty repository.
     */
    public InMemoryRepository() {
        entities = new HashMap<>();
    }

    /**
     * @param id a unique identifier
     * @return the entity corresponding to the id
     */
    @Override
    public Optional<T> findOne(ID id) {
        //Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id must not be null"));
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * @return all the entities in the repository
     */
    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    /**
     * Adds the entity to the collection if it is valid and its ID isn't already present.
     * @param entity object with its unique identifier
     * @return null if the ID did not previously exist in the collection,
     * otherwise returns the entity that is in the collection and has the given ID
     * @throws ValidatorException if one of its fields is invalid according to the specific conditions of the type of entity
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        //Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("entity must not be null"));
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    /**
     * Removes the entity corresponding to the id from the collection.
     * @param id unique identifier of entity
     * @return null if the id is not present, otherwise the entity corresponding to the id
     */
    @Override
    public Optional<T> delete(ID id) {
        //Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id must not be null"));
        return Optional.ofNullable(entities.remove(id));
    }

    /**
     * Updates the entity identified by the contained id with the other information contained in the entity.
     * @param entity uniquely identified object
     * @return null if no value is associated with the id, otherwise the new entity in the collection
     * @throws ValidatorException if one of its fields is invalid according to the specific conditions of the type of entity
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        //Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("entity must not be null"));
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
