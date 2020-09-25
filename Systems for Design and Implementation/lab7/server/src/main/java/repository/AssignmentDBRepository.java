package repository;

import container.Pair;
import domain.Grade;
import exception.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class AssignmentDBRepository implements SortingRepository<Pair<Long,Long>, Grade> {
    private JdbcOperations jdbcOperations;


    public AssignmentDBRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations=jdbcOperations;
    }


    @Override
    public Iterable<Grade> findAll(Sort sort) {
        ArrayList<Grade> grades=new ArrayList<>();
        findAll().forEach(grades::add);
        return sort.sort(grades.stream()
                            .map(grade -> (Object)grade)
                            .collect(Collectors.toList()))
                            .stream().map(grade->(Grade)grade)
                            .collect(Collectors.toList());
    }

    @Override
    public Optional<Grade> findOne(Pair<Long, Long> longLongPair) {
        String sql = "select * from grades where student_id=? and problem_id=?";
        return Optional.ofNullable(jdbcOperations.queryForObject(sql, new Object[]{longLongPair.first,longLongPair.second}, (rs, rowNum) ->
                new Grade(rs.getLong("student_id"),rs.getLong("problem_id"),rs.getInt("grade"))
                ));
    }

    @Override
    public Iterable<Grade> findAll() {
        String selectStatement="select * from grades";
        return jdbcOperations.query(selectStatement,(rs,rowNumber)->{
            Long studentID=rs.getLong("student_id");
            Long problemID=rs.getLong("problem_id");
            int grade=rs.getInt("grade");
            return new Grade(studentID,problemID,grade);
        });
    }

    @Override
    public void save(Grade entity) throws ServiceException {
        String addGrades="insert into grades (student_id,problem_id,grade) values(?,?,?)";
        try{
            jdbcOperations.update(addGrades,entity.getIdOfTheStudent(),entity.getIdOfTheProblem(),entity.getGradeGivenByTeacher());
        }
        catch (DataAccessException e){throw new ServiceException("There was a problem with adding a grade in the database!");}
    }

    @Override
    public void delete(Pair<Long, Long> longLongPair) throws ServiceException{
        String deleteGrade="delete from grades where student_id=? and problem_id=?";
        try{
            int rows=jdbcOperations.update(deleteGrade,longLongPair.first,longLongPair.second);
            if(rows==0)
                throw new ServiceException("There was a problem when deleting a grade from the database!");
        }
        catch (DataAccessException e){throw new ServiceException("There was a problem when deleting a grade from the database!");}
    }


    @Override
    public void update(Grade entity) throws ServiceException {
        String updateGrade="update grades set grade=? where student_id=? and problem_id=?";
        try{
            int rows=jdbcOperations.update(updateGrade,entity.getGradeGivenByTeacher(),entity.getIdOfTheStudent(),entity.getIdOfTheProblem());
            if(rows==0)
                throw new ServiceException("There was a problem when updating a grade in the database!");
        }
        catch (DataAccessException e)
        {throw new ServiceException("There was a problem when updating a grade in the database!");}

    }

}
