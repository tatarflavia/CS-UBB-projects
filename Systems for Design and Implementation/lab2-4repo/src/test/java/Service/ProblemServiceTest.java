package Service;

import Domain.Problem;
import Domain.Validators.ProblemValidator;
import Domain.Validators.Validator;
import Repository.InMemoryRepository;
import Repository.Repository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class ProblemServiceTest {

    @Test
    public void addProblem() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        Problem prob3=new Problem(3,"bbjj","uuuj",4);
        problemService.addProblem(prob3);
        Assert.assertEquals(1,problemService.getAllProblems().size());
        Problem prob2=new Problem(2,"AIjj","bbjj",2);
        problemService.addProblem(prob2);
        Assert.assertEquals(2,problemService.getAllProblems().size());
    }

    @Test
    public void getAllProblems() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        Problem prob3=new Problem(3,"bbhh","uuuhh",4);
        problemService.addProblem(prob3);
        Assert.assertEquals(1,problemService.getAllProblems().size());
        Problem prob2=new Problem(2,"AIhh","bbhh",2);
        problemService.addProblem(prob2);
        Assert.assertEquals(2,problemService.getAllProblems().size());
    }

    @Test
    public void deleteProblem() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        Problem prob3=new Problem(3,"bbhh","uuuhh",4);
        problemService.addProblem(prob3);
        Problem prob2=new Problem(2,"AIhh","bbhh",2);
        problemService.addProblem(prob2);
        Assert.assertEquals(2,problemService.getAllProblems().size());
        problemService.deleteProblem((long)4);
        Assert.assertEquals(1,problemService.getAllProblems().size());
        problemService.deleteProblem((long)2);
        Assert.assertEquals(0,problemService.getAllProblems().size());
    }

    @Test
    public void updateProblem() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        problemService.updateProblem(new Problem(11,"cccc","tttt",1));

        List<Problem> problems=problemService.getAllProblems();
        Assert.assertEquals(3,problems.size());
        Problem updatedProblem=problemRepository.findOne(1L).get();
        Assert.assertEquals(updatedProblem.getChapter(),"cccc");
        Assert.assertEquals(updatedProblem.getText(),"tttt");
        Assert.assertEquals(updatedProblem.getNumber(),11);
    }

    @Test
    public void filterProblemsByChapter() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        problemService.addProblem(new Problem(12,"chapter","problem text",1));
        problemService.addProblem(new Problem(7,"chapter","text",2));
        problemService.addProblem(new Problem(3,"another chapter","text",3));
        Set<Problem> filteredProblems=problemService.filterProblemsByChapter("chapter");
        Assert.assertEquals(filteredProblems.size(),3);
        filteredProblems=problemService.filterProblemsByChapter("another");
        Assert.assertEquals(filteredProblems.size(),1);
    }
}