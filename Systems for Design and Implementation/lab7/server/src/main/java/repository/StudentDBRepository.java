package repository;

import domain.Student;
import exception.FileException;
import exception.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import java.util.*;
import java.util.stream.Collectors;


public class StudentDBRepository implements SortingRepository<Long, Student> {
    private JdbcOperations jdbcOperations;

    public StudentDBRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations=jdbcOperations;
    }

    @Override
    public Iterable<Student> findAll(Sort sort) {
        ArrayList<Student> students=new ArrayList<>();
        findAll().forEach(students::add);
        return sort.sort(students.stream()
                .map(student -> (Object) student)
                .collect(Collectors.toList()))
                .stream().map(student -> (Student) student)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Student> findOne(Long aLong) {
        String sql = "select * from students where id = ?";
        return Optional.ofNullable(jdbcOperations.queryForObject(sql, new Object[]{aLong}, (rs, rowNum) ->
                new Student(rs.getString("name"),
                        rs.getLong("id")
                )));
    }

    @Override
    public Iterable<Student> findAll() {
        String selectStudents="select * from students";
        return jdbcOperations.query(selectStudents,(rs,rowNumber)->{
            Long id=rs.getLong("id");
            String name=rs.getString("name");
            return new Student(name,id);
        });
    }

    @Override
    public void save(Student entity) throws ServiceException {
        String addStudents = "insert into students (id, name) values(?,?)";
        try{
            jdbcOperations.update(addStudents,entity.getId(),entity.getName());
        }
        catch (DataAccessException e)
        {throw new FileException("There was a problem when saving in the database!"); }

    }



    @Override
    public void delete(Long aLong) throws ServiceException{
        String deleteStudent="delete from students where id=?";
        try {
            int rows=jdbcOperations.update(deleteStudent,aLong);
            if (rows==0)
                throw new ServiceException("There was some problem when deleting a student in the database!");
        } catch (DataAccessException e) {
            throw new FileException("There was some problem when deleting a student in the database!");
        }

    }

    @Override
    public void update(Student entity) throws ServiceException {
        String studentUpdate="update students set name=? where id=?";
        try {
            int rows=jdbcOperations.update(studentUpdate,entity.getName(),entity.getId());
            if (rows==0)
                throw new ServiceException("There was some problem when updating a student in the database!");
        } catch (DataAccessException e) {
            throw new FileException("There was some problem when updating the a student in the database!");
        }
    }
}
