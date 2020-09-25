package web.converter;

import web.dto.ProblemDto;
import core.model.Problem;
import org.springframework.stereotype.Component;

@Component
public class ProblemConverter extends BaseConverter<Long, Problem, ProblemDto> {

    public Problem convertDtoToModel(ProblemDto dto) {
        Problem problem = Problem.builder()
                .chapter(dto.getChapter())
                .text(dto.getText())
                .number(dto.getNumber())
                .build();
        problem.setId(dto.getId());
        return problem;
    }

    @Override
    public ProblemDto convertModelToDto(Problem problem) {
        ProblemDto dto=ProblemDto.builder()
                .chapter(problem.getChapter())
                .text(problem.getText())
                .number(problem.getNumber())
                .build();
        dto.setId(problem.getId());
        return dto;
    }
}
