package Repository;

import Domain.Problem;
import Domain.Student;
import Domain.Validators.ProblemValidator;
import Domain.Validators.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class InMemoryRepositoryTest {

    @Test
    public void findOne() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Problem prob2=new Problem(2,"AIuyuy","bbdsfsf",2);
        problemRepository.save(prob2);
        Assert.assertEquals(prob2, problemRepository.findOne(2l).get());

    }

    @Test
    public void findAll() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Problem prob2=new Problem(2,"AIdd","bbdd",2);
        problemRepository.save(prob2);
        Problem prob3=new Problem(3,"bbgg","uuujjj",4);
        problemRepository.save(prob3);
        Assert.assertEquals(2, StreamSupport.stream(problemRepository.findAll().spliterator(), false).count());

    }

    @Test
    public void save() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long, Problem>();
        Problem prob2=new Problem(2,"AIjj","bbkk",2);
        problemRepository.save(prob2);
        Problem prob3=new Problem(3,"bbjj","uuukk",4);
        problemRepository.save(prob3);
        Assert.assertEquals(2, StreamSupport.stream(problemRepository.findAll().spliterator(), false).count());

    }

    @Test
    public void delete() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Problem prob2=new Problem(2,"AggI","bbmm",2);
        problemRepository.save(prob2);
        Problem prob3=new Problem(3,"bbnn","uuumm",4);
        problemRepository.save(prob3);
        Assert.assertEquals(2, StreamSupport.stream(problemRepository.findAll().spliterator(), false).count());
        problemRepository.delete((long)2);
        problemRepository.delete((long)4);
        Assert.assertEquals(0, StreamSupport.stream(problemRepository.findAll().spliterator(), false).count());
    }

    @Test
    public void update() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Problem prob3=new Problem(3,"bbjj","uuujj",4);
        problemRepository.save(prob3);
        problemRepository.update(new Problem(9,"uuukk","uuukk",4));
        Assert.assertEquals(9, problemRepository.findOne(4l).get().getNumber());
        Assert.assertEquals("uuukk", problemRepository.findOne(4l).get().getChapter());
        Assert.assertEquals("uuukk", problemRepository.findOne(4l).get().getText());

    }
}