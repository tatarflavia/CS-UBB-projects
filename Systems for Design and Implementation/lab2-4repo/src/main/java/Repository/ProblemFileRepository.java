package Repository;

import Domain.Problem;
import Exception.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ProblemFileRepository extends FileRepository<Long, Problem>{
    public ProblemFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    Map<Long, Problem> readFile() throws FileException {
        HashMap<Long,Problem> map=new HashMap<>();
        try (Stream<String> lines = Files.lines(path))
        {
            lines.map(line->line.split(";"))
                    .forEach(lineSplit -> map.put(Long.parseLong(lineSplit[3]),
                            new Problem(Integer.parseInt(lineSplit[0]),lineSplit[1],lineSplit[2],Long.parseLong(lineSplit[3]))));
            return map;
        } catch (Exception e) {
            throw new FileException("File containing problems could not be found or is poorly formatted!");
        }
    }

    @Override
    void writeFile() throws FileException {
        try {
            Files.write(path,()-> entities.entrySet().stream()
                    .<CharSequence>map(entity -> entity.getValue().getNumber()+";"+ entity.getValue().getChapter()+";"+entity.getValue().getText()+";"+entity.getKey())
                    .iterator());
        } catch (IOException e) {
            throw new FileException("File containing problems could not be found or is poorly formatted!");
        }
    }
}
