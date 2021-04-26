package web.converter;

import web.dto.ProblemDto;
import core.model.Problem;
import org.springframework.stereotype.Component;

@Component
public class ProblemConverter extends BaseConverter<Long, Problem, ProblemDto> {

    public Problem convertDtoToModel(ProblemDto dto) {
        Problem problem=new Problem(dto.getText(),dto.getNumber(),dto.getChapter());
        problem.setId(dto.getId());
        return problem;
    }

    @Override
    public ProblemDto convertModelToDto(Problem problem) {
        ProblemDto problemDto=new ProblemDto(problem.getText(),problem.getNumber(),problem.getChapter());
        problemDto.setId(problem.getId());
        return problemDto;
    }
}
