package repository;

import domain.Student;
import exception.FileException;
import exception.ServiceException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentDBRepository implements SortingRepository<Long, Student> {

    HashMap<Long,Student> students;
    Connection connection;

    private void loadStudents() throws FileException{
        try {
            String selectStudents="select * from students";
            PreparedStatement selectStudentsStatement=connection.prepareStatement(selectStudents);
            ResultSet studentSet=selectStudentsStatement.executeQuery();
            while(studentSet.next()) {
                Long id=studentSet.getLong("id");
                String name=studentSet.getString("name");
                students.put(id,new Student(name,id));
            }
        } catch (SQLException e) {
            throw new FileException("There was some problem with the database!");
        }
    }

    public StudentDBRepository() throws FileException {
        try {
            students=new HashMap<>();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school?user=postgres&password=1Ppassword2");
        } catch (ClassNotFoundException | SQLException e) {
            throw new FileException("Could not connect to the database!");
        }
        try {
            loadStudents();
        } catch (FileException e) {
            throw e;
        }
    }

    @Override
    public Iterable<Student> findAll(Sort sort) {
        students=new HashMap<>();
        this.loadStudents();
        List<Student> sortedStudents=sort.sort(students.values().stream()
                .map(student -> (Object) student)
                .collect(Collectors.toList()))
                .stream().map(student -> (Student) student)
                .collect(Collectors.toList());
        return sortedStudents;
    }

    @Override
    public Optional<Student> findOne(Long aLong) {
        return Optional.ofNullable(students.get(aLong));
    }

    @Override
    public Iterable<Student> findAll() {
        students=new HashMap<>();
        this.loadStudents();
        Set<Student> allEntities = students.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<Student> save(Student entity) throws ServiceException {
        students=new HashMap<>();
        this.loadStudents();
        Optional<Student> previous=Optional.ofNullable(students.putIfAbsent(entity.getId(), entity));
        previous.ifPresentOrElse(student -> {}, () -> {
            String addStudents = "insert into students (id, name) values(?,?)";
            try {
                PreparedStatement addStudentsStatement = connection.prepareStatement(addStudents);
                addStudentsStatement.setLong(1, entity.getId());
                addStudentsStatement.setString(2, entity.getName());
                addStudentsStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return previous;
    }

    @Override
    public Optional<Student> delete(Long aLong) {
        students=new HashMap<>();
        this.loadStudents();
        Optional<Student> previous=Optional.ofNullable(students.remove(aLong));
        previous.ifPresent(student -> {
            try {
                PreparedStatement deleteStudentStatement=connection.prepareStatement("delete from students where id=?");
                deleteStudentStatement.setLong(1,aLong);
                deleteStudentStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return previous;
    }

    @Override
    public Optional<Student> update(Student entity) throws ServiceException {
        students=new HashMap<>();
        this.loadStudents();
        Optional<Student> savedValue=Optional.ofNullable(students.computeIfPresent(entity.getId(), (k, v) -> entity));
        savedValue.ifPresent( student -> {
            try {
                PreparedStatement studentUpdateStatement=connection.prepareStatement("update students set name=? where id=?");
                studentUpdateStatement.setString(1,entity.getName());
                studentUpdateStatement.setLong(2,entity.getId());
                studentUpdateStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return savedValue;
    }
}
