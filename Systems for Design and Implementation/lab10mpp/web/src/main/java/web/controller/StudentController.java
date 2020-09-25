package web.controller;

import core.exception.ServiceException;
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

@RestController
public class StudentController {
    public static final Logger LOGGER= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentServiceInterface studentService;

    @Autowired
    private StudentConverter studentConverter;


    @RequestMapping(value = "/students", method = RequestMethod.GET)
    StudentsDto getStudents() {
        return new StudentsDto(studentConverter.convertModelsToDtos(studentService.getAllStudents()));
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
