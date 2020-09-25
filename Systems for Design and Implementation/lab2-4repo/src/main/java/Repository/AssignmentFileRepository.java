package Repository;

import Container.Pair;
import Domain.Grade;
import Exception.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Stores the assignments in a file.
 */
public class AssignmentFileRepository extends FileRepository<Pair<Long,Long>, Grade> {

    public AssignmentFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    Map<Pair<Long,Long>, Grade> readFile() throws FileException {
        HashMap<Pair<Long,Long>,Grade> map=new HashMap<>();
        try (Stream<String> lines = Files.lines(path))
        {
            lines.map(line->line.split(";"))
                    .forEach(lineSplit -> map.put(new Pair(Long.parseLong(lineSplit[0]),Long.parseLong(lineSplit[1])),new Grade(Long.parseLong(lineSplit[0]),Long.parseLong(lineSplit[1]),Integer.parseInt(lineSplit[2]))));
            return map;
        } catch (Exception e) {
            throw new FileException("File containing grades could not be found or is poorly formatted!");
        }
    }

    @Override
    void writeFile() throws FileException {
        try {
            Files.write(path,()-> entities.entrySet().stream()
                    .<CharSequence>map(entity -> entity.getValue().getIdOfTheStudent()+";"+entity.getValue().getIdOfTheProblem()+";"+entity.getValue().getGradeGivenByTeacher())
                    .iterator());
        } catch (IOException e) {
            throw new FileException("File containing grades could not be found or is poorly formatted!");
        }
    }
}
