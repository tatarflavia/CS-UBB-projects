package web.controller;

import core.exception.ServiceException;
import core.model.Student;
import core.service.StudentServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.StudentConverter;
import web.dto.StudentDto;
import web.dto.StudentsDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    public static final Logger LOGGER= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentServiceInterface studentService;

    @Autowired
    private StudentConverter studentConverter;


    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<StudentDto> getStudents() {
        LOGGER.info("getStudents method entered-server:");
        List<Student> students=studentService.getAllStudents();
        System.out.println(students.size());
        LOGGER.info("getStudents method finished-server");
        return new ArrayList<>(studentConverter.convertModelsToDtos(students));
        }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto) {
        try {
            LOGGER.info("addStudent- method entered-server:");
            studentService.addStudent(studentConverter.convertDtoToModel(studentDto));LOGGER.info("addStudent- method finished-server:");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        try {
            studentService.updateStudent(studentConverter.convertDtoToModel(studentDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteStudent(@PathVariable Long id) {LOGGER.info("deleteStd- method entered-server:");
        studentService.deleteStudent(id);
        LOGGER.info("deleteStd- method finished-server:");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
