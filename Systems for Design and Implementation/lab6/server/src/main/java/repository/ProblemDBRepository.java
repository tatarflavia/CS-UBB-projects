package repository;

import domain.Problem;
import exception.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProblemDBRepository implements SortingRepository<Long, Problem> {

    private HashMap<Long, Problem> problems;
    private Connection connection;

    private void loadProblems() throws FileException{
        try
        {
            String selectProblems="select * from problems";
            PreparedStatement selectProblemsStatement=connection.prepareStatement(selectProblems);
            ResultSet problemsSet=selectProblemsStatement.executeQuery();
            while(problemsSet.next())
            {
                Long id=problemsSet.getLong("id");
                String chapter=problemsSet.getString("chapter");
                int number=problemsSet.getInt("number");
                String description=problemsSet.getString("description");
                problems.put(id,new Problem(number,chapter,description,id));
            }
        }
        catch (SQLException e) {
            throw new FileException("There is a problem with the problem table from the db!");
        }


    }


    public ProblemDBRepository() throws FileException {
        try{this.problems = new HashMap<>();
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school?user=postgres&password=1Ppassword2");}
        catch (ClassNotFoundException | SQLException a){
            throw new FileException("Could not connect to the database!");}
        try {
            this.loadProblems();
        } catch (FileException e) {
            throw e;
        }
    }



    @Override
    public Iterable<Problem> findAll(Sort sort) {
        problems=new HashMap<>();
        this.loadProblems();
        return sort.sort(problems.values().stream()
                        .map(problem -> (Object)problem)
                        .collect(Collectors.toList()))
                        .stream().map(problem->(Problem)problem)
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<Problem> findOne(Long aLong) {
        return Optional.ofNullable(problems.get(aLong));
    }

    @Override
    public Iterable<Problem> findAll() {
        problems=new HashMap<>();
        this.loadProblems();
        Set<Problem> allEntities = problems.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<Problem> save(Problem entity) throws ServiceException {
        problems=new HashMap<>();
        this.loadProblems();
        Optional<Problem> previous=Optional.ofNullable(problems.putIfAbsent(entity.getId(),entity));
        previous.ifPresentOrElse(problem -> {},()->{
            String addProblems="insert into problems (id,chapter,number,description) values(?,?,?,?)";
            try{
                PreparedStatement addProblemStatement=connection.prepareStatement(addProblems);
                addProblemStatement.setLong(1,entity.getId());
                addProblemStatement.setString(2,entity.getChapter());
                addProblemStatement.setInt(3,entity.getNumber());
                addProblemStatement.setString(4,entity.getText());
                addProblemStatement.executeUpdate();
            }
            catch(SQLException e)
            {
                throw new FileException("There was a problem with adding in the db!");
            }
        });
        return previous;
    }


    @Override
    public Optional<Problem> delete(Long aLong) {
        problems=new HashMap<>();
        this.loadProblems();
        Optional<Problem> previous=Optional.ofNullable(problems.remove(aLong));
        previous.ifPresent(problem -> {
            try {
                PreparedStatement deleteProblemStatement=connection.prepareStatement("delete from problems where id=?");
                deleteProblemStatement.setLong(1,aLong);
                deleteProblemStatement.executeUpdate();
            }
            catch (SQLException e) {
                throw new FileException("There was a problem when deleting from the db!");
            }
        });
        return previous;
    }



    @Override
    public Optional<Problem> update(Problem entity) throws ServiceException {
        problems=new HashMap<>();
        this.loadProblems();
        Optional<Problem> savedValue=Optional.ofNullable(problems.computeIfPresent(entity.getId(),(k,v)->entity));
        savedValue.ifPresent(problem -> {
            try{
                PreparedStatement problemUpdateStatement=connection.prepareStatement("update problems set chapter=?,number=?,description=? where id=?");
                problemUpdateStatement.setString(1,entity.getChapter());
                problemUpdateStatement.setInt(2,entity.getNumber());
                problemUpdateStatement.setString(3,entity.getText());
                problemUpdateStatement.setLong(4,entity.getId());
                problemUpdateStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new FileException("There was a problem when updating the db!");
            }
        });
        return savedValue;
    }


}
