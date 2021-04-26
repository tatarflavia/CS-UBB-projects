package web.converter;


import core.model.Student;
import org.springframework.stereotype.Component;
import web.dto.StudentDto;

@Component
public class StudentConverter extends BaseConverter<Long, Student, StudentDto> {

    public Student convertDtoToModel(StudentDto dto) {
        Student student=new Student(dto.getName());
        student.setId(dto.getId());
        return student;
    }

    public StudentDto convertModelToDto(Student student) {
        StudentDto dto = new StudentDto(student.getName());
        dto.setId(student.getId());
        return dto;
    }

}