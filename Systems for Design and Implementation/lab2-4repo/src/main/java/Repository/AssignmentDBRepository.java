package Repository;

import Container.Pair;
import Domain.Grade;
import Exception.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AssignmentDBRepository implements SortingRepository<Pair<Long,Long>, Grade> {
    HashMap<Pair<Long,Long>, Grade> grades;
    Connection connection;

    private void loadGrades() throws FileException{
        try{
            String selectStatement="select * from grades";
            PreparedStatement selectStudentsStatement=connection.prepareStatement(selectStatement);
            ResultSet gradesSet=selectStudentsStatement.executeQuery();
            while(gradesSet.next())
            {
                Long studentID=gradesSet.getLong("student_id");
                Long problemID=gradesSet.getLong("problem_id");
                int grade=gradesSet.getInt("grade");
                grades.put(new Pair<>(studentID,problemID),new Grade(studentID,problemID,grade));
            }
        }
        catch (SQLException e){throw new FileException("There was a problem with the database!");}
    }

    public AssignmentDBRepository() throws FileException {
        try{
            grades=new HashMap<>();
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/school?user=postgres&password=1Ppassword2");
        }
        catch (ClassNotFoundException | SQLException e){
            throw new FileException("Could not connect to the database!");
        }
        try{
            this.loadGrades();
        }
        catch (FileException a)
        {throw new FileException("Could not load the grades from the table in the database!");}

    }


    @Override
    public Iterable<Grade> findAll(Sort sort) {
        grades=new HashMap<>();
        this.loadGrades();
        return sort.sort(grades.values().stream()
                            .map(grade -> (Object)grade)
                            .collect(Collectors.toList()))
                            .stream().map(grade->(Grade)grade)
                            .collect(Collectors.toList());
    }

    @Override
    public Optional<Grade> findOne(Pair<Long, Long> longLongPair) {
        return Optional.ofNullable(grades.get(longLongPair));
    }

    @Override
    public Iterable<Grade> findAll() {
        grades=new HashMap<>();
        this.loadGrades();
        Set<Grade> allEntities=grades.entrySet().stream().map(entry->entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<Grade> save(Grade entity) throws RejectedInputException {
        Optional<Grade> previous=Optional.ofNullable(grades.putIfAbsent(entity.getId(),entity));
        previous.ifPresentOrElse(grade -> {}, ()->{
            String addGrades="insert into grades (student_id,problem_id,grade) values(?,?,?)";
            try{
                PreparedStatement addGradesStatement = connection.prepareStatement(addGrades);
                addGradesStatement.setLong(1,entity.getIdOfTheStudent());
                addGradesStatement.setLong(2,entity.getIdOfTheProblem());
                addGradesStatement.setInt(3,entity.getGradeGivenByTeacher());
                addGradesStatement.executeUpdate();
            }
            catch (SQLException e){throw new RejectedInputException("There was a problem with adding data in the db!");}
        });
        return previous;
    }

    @Override
    public Optional<Grade> delete(Pair<Long, Long> longLongPair) {
        Optional<Grade> previous=Optional.ofNullable(grades.remove(longLongPair));
        previous.ifPresent(grade -> {
            try{
                PreparedStatement deleteGradeStatement=connection.prepareStatement("delete from grades where student_id=? and problem_id=?");
                deleteGradeStatement.setLong(1,longLongPair.first);
                deleteGradeStatement.setLong(2,longLongPair.second);
                deleteGradeStatement.executeUpdate();
            }
            catch (SQLException e){throw new RejectedInputException("There was a problem when deleting data from the database!");}
        });
        return previous;
    }


    @Override
    public Optional<Grade> update(Grade entity) throws RejectedInputException {
        Optional<Grade> savedValue=Optional.ofNullable(grades.computeIfPresent(entity.getId(),(k,v)->entity));
        savedValue.ifPresent(grade -> {
            try{
                PreparedStatement gradeUpdateStatement=connection.prepareStatement("update grades set grade=? where student_id=? and problem_id=?");
                gradeUpdateStatement.setInt(1,entity.getGradeGivenByTeacher());
                gradeUpdateStatement.setLong(2,entity.getIdOfTheStudent());
                gradeUpdateStatement.setLong(3,entity.getIdOfTheProblem());
                gradeUpdateStatement.executeUpdate();
            }
            catch (SQLException e)
            {throw new RejectedInputException("There was a problem when updating the database!");}
        });
        return savedValue;
    }

}
