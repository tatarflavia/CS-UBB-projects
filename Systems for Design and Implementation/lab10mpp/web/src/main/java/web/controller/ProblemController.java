package web.controller;

import core.exception.ServiceException;
import core.service.ProblemServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.ProblemConverter;
import web.dto.ProblemDto;
import web.dto.ProblemsDto;

@RestController
public class ProblemController {
    private static Logger LOGGER = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemServiceInterface problemService;
    
    @Autowired
    private ProblemConverter problemConverter;


    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    ProblemsDto getProblems() {
        return new ProblemsDto(problemConverter.convertModelsToDtos(problemService.getAllProblems()));
    }

    @RequestMapping(value = "/problems", method = RequestMethod.POST)
    ResponseEntity<?> saveProblem(@RequestBody ProblemDto problemDto) {
        try {
            LOGGER.info("addProblem- method entered-server:");
            problemService.addProblem(problemConverter.convertDtoToModel(problemDto));LOGGER.info("addProblem- method finished-server:");
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateProblem(@PathVariable Long id, @RequestBody ProblemDto problemDto) {
        try {
            problemService.updateProblem(problemConverter.convertDtoToModel(problemDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteProblem(@PathVariable Long id) {
        LOGGER.info("deleteProb- method entered-server:");
        problemService.deleteProblem(id);LOGGER.info("deleteProb- method finish-server:");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/problems/filter",method = RequestMethod.GET)
    ProblemsDto filterProblemsByChapter(@RequestParam(value="chapter") String chapter) {
        return new ProblemsDto(problemConverter.convertModelsToDtos(problemService.filterProblemsByChapter(chapter)));
    }
}
