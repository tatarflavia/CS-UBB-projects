package repository;

import domain.Problem;
import exception.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProblemDBRepository implements SortingRepository<Long, Problem> {

    private JdbcOperations jdbcOperations;

    public ProblemDBRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations=jdbcOperations;
    }


    @Override
    public Iterable<Problem> findAll(Sort sort) {
        ArrayList<Problem> problems=new ArrayList<>();
        findAll().forEach(problems::add);
        return sort.sort(problems.stream()
                        .map(problem -> (Object)problem)
                        .collect(Collectors.toList()))
                        .stream().map(problem->(Problem)problem)
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<Problem> findOne(Long aLong) {
        String sql = "select * from problems where id = ?";
        return Optional.ofNullable(jdbcOperations.queryForObject(sql, new Object[]{aLong}, (rs, rowNum) ->
                new Problem(rs.getInt("number"),rs.getString("chapter"),rs.getString("description"),
                        rs.getLong("id")
                )));
    }

    @Override
    public Iterable<Problem> findAll() {
        String selectProblems="select * from problems";
        return jdbcOperations.query(selectProblems,(rs,rowNumber)->{
            Long id=rs.getLong("id");
            String chapter=rs.getString("chapter");
            int number=rs.getInt("number");
            String description=rs.getString("description");
            return new Problem(number,chapter,description,id);
        });
    }


    @Override
    public void save(Problem entity) throws ServiceException {
        String addProblems="insert into problems (id,chapter,number,description) values(?,?,?,?)";
        try{
            jdbcOperations.update(addProblems,entity.getId(),entity.getChapter(),entity.getNumber(),entity.getText());
        }
        catch(DataAccessException e)
        {
            throw new FileException("There was a problem with adding a problem into the database!");
        }
    }


    @Override
    public void delete(Long aLong) throws ServiceException{
        String deleteProblem="delete from problems where id=?";
        try {
            int rows=jdbcOperations.update(deleteProblem,aLong);
            if(rows==0)
                throw new ServiceException("There was a problem when deleting a problem from the database!");
        }
        catch (DataAccessException e) {
            throw new FileException("There was a problem when deleting a problem from the database!");
        }

    }



    @Override
    public void update(Problem entity) throws ServiceException {
        String problemUpdate="update problems set chapter=?,number=?,description=? where id=?";
        try{
            int rows=jdbcOperations.update(problemUpdate,entity.getChapter(),entity.getNumber(),entity.getText(),entity.getId());
            if(rows==0)
                throw new ServiceException("There was a problem when updating a problem in the database!");
            }
        catch (DataAccessException e)
        {
            throw new FileException("There was a problem when updating a problem in the database!");
        }
    }


}
