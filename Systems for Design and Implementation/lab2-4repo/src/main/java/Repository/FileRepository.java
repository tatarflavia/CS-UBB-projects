package Repository;

import Domain.BaseEntity;
import Exception.ValidatorException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Stores the entities in a file
 * @param <ID> unique identifier of the entity
 * @param <T> entity identified by ID
 */
abstract public class FileRepository<ID, T extends BaseEntity<ID>> implements Repository<ID,T> {

    protected Path path;
    protected String fileName;
    protected Map<ID,T> entities;

    /**
     * Creates a new repository.
     * @param fileName name of the file stored in the project folder
     */
    public FileRepository(String fileName) {
        this.fileName=fileName;
        path= Paths.get(new File("."+File.separator+fileName).getAbsolutePath());
        entities=readFile();
    }

    /**
     * @return a Map containing the unique identifiers and the entities from the file
     */
    abstract Map<ID,T> readFile();

    /**
     * Writes all the entities to the file.
     */
    abstract void writeFile();

    @Override
    public Optional<T> findOne(ID id) {
        return  Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        T existingValue=entities.putIfAbsent(entity.getId(),entity);
        writeFile();
        return Optional.ofNullable(existingValue);
    }

    @Override
    public Optional<T> delete(ID id) {
        T existingValue=entities.remove(id);
        writeFile();
        return Optional.ofNullable(existingValue);
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        T newValue=entities.computeIfPresent(entity.getId(), (k, v) -> entity);
        writeFile();
        return Optional.ofNullable(newValue);
    }
}
