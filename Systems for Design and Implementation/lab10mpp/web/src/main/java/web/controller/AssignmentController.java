package web.controller;

import core.exception.NoGradeException;
import core.exception.ServiceException;
import core.model.Pair;
import core.model.Problem;
import core.model.Student;
import core.service.AssignmentService;
import core.service.AssignmentServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.GradeConverter;
import web.converter.ProblemConverter;
import web.converter.StudentConverter;
import web.dto.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AssignmentController {
    private static Logger LOGGER = LoggerFactory.getLogger(AssignmentController.class);

    @Autowired
    private AssignmentServiceInterface gradeService;

    @Autowired
    private GradeConverter gradeConverter;
    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private ProblemConverter problemConverter;


    @RequestMapping(value = "/grades", method = RequestMethod.POST)
    ResponseEntity<?> saveGrade(@RequestBody GradeDto gradeDto) {
        LOGGER.info("addAssignment- method entered-server:");
        try {
            gradeService.assignProblemToStudent(gradeConverter.convertDtoToModel(gradeDto));
            LOGGER.info("addAssignment- method finish-server:");
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/grades/ungraded", method = RequestMethod.GET)
    GradesDto getUngraded() {
        return new GradesDto(gradeConverter.convertModelsToDtos(gradeService.showAllUngraded()));
    }

    @RequestMapping(value = "/grades/graded", method = RequestMethod.GET)
    GradesDto showAllGraded(){
        return new GradesDto(gradeConverter.convertModelsToDtos(gradeService.showAllGraded()));
    }

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    GradesDto getGrades(){
        return new GradesDto(gradeConverter.convertModelsToDtos(gradeService.showAll()));
    }

    @RequestMapping(value = "/grades/{studentID}/{problemID}",method = RequestMethod.PUT)
    ResponseEntity<?> giveGradeToStudent(@PathVariable long studentID, @PathVariable long problemID,@RequestBody GradeDto gradeDto){
        try{
            LOGGER.info("updateAssignment- method entered-server:");
            gradeService.giveGradeToStudent(gradeConverter.convertDtoToModel(gradeDto));
            LOGGER.info("updateAssignment- method finish-server");
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (ServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/grades/{studentID}/{problemID}", method= RequestMethod.DELETE)
    ResponseEntity<?> deleteGrade(@PathVariable Long studentID,@PathVariable Long problemID){
        try{
            LOGGER.info("deleteAssignment- method entered-server:");
            gradeService.deleteGrade(studentID,problemID);LOGGER.info("deleteAssignment- method finished-server:");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/grades/deleteAllGradesStudent/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAllGradesStudent(@PathVariable  long id){
        gradeService.deleteAllGradesStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/grades/deleteAllGradesProblem/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAllGradesProblem(@PathVariable long id){
        gradeService.deleteAllGradesProblem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value="/grades/studentHighest",method = RequestMethod.GET)
    StudentDto studentHighestGPA(){
        try{Student result=gradeService.studentHighestGPA();
            return studentConverter.convertModelToDto(result);}
        catch (ServiceException e)
        {return new StudentDto("null");}

    }

    @RequestMapping(value = "/grades/studentMaxGrade",method = RequestMethod.GET)
    StudentsDto topStudentsWithMaxGradeOnAChapter(@RequestParam(value="chapter") String chapter,@RequestParam(value="number") int number){
        return new StudentsDto(studentConverter.convertModelsToDtos(gradeService.topStudentsWithMaxGradeOnAChapter(chapter,number)));
    }

    @RequestMapping(value = "/grades/gradingInProgress",method = RequestMethod.GET)
    ProblemsDto gradingInProgressProblem(){
        try{
            return new ProblemsDto(problemConverter.convertModelsToDtos(gradeService.gradingInProgressProblem()));
        }
        catch (ServiceException e)
        {

            List<Problem> problems=new ArrayList<>();
            return new ProblemsDto(problemConverter.convertModelsToDtos(problems));
        }

    }
}
