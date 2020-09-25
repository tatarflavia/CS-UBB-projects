package Repository;

import Domain.Student;
import Exception.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StudentFileRepository extends FileRepository<Long,Student> {

    public StudentFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    Map<Long,Student> readFile() throws FileException {
        HashMap<Long,Student> map=new HashMap<>();
        try (Stream<String> lines = Files.lines(path))
        {
            lines.map(line->line.split(";"))
                    .forEach(lineSplit -> map.put(Long.parseLong(lineSplit[1]),new Student(lineSplit[0],Long.parseLong(lineSplit[1]))));
            return map;
        } catch (Exception e) {
            throw new FileException("File containing students could not be found or is poorly formatted!");
        }
    }

    @Override
    void writeFile() throws FileException {
        try {
            Files.write(path,()-> entities.entrySet().stream()
            .<CharSequence>map(entity -> entity.getValue().getName()+";"+ entity.getKey())
                    .iterator());
        } catch (IOException e) {
            throw new FileException("File containing students could not be found or is poorly formatted!");
        }
    }
}
