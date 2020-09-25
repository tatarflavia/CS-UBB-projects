package web.converter;

import core.model.BaseEntity;
import web.dto.BaseDto;
import web.dto.GradeDto;
import core.model.Grade;
import core.model.Pair;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradeConverter {

    public List<GradeDto> convertModelsToDtos(Collection<Grade> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    public List<Grade> convertDtosToModels(Collection<GradeDto> dtos) {
        return dtos.stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
    }

    public Grade convertDtoToModel(GradeDto dto) {
        Grade grade=Grade.builder()
                .gradeByTeacher(dto.getGradeByTeacher())
                .studentId(dto.getStudentId()).problemId(dto.getProblemId())
                .build();
        grade.setId(new Pair<>(dto.getStudentId(),dto.getProblemId()));
        return grade;
    }

    public GradeDto convertModelToDto(Grade grade) {
        GradeDto dto= GradeDto.builder()
                .gradeByTeacher(grade.getGradeByTeacher())
                .studentId(grade.getStudentId()).problemId(grade.getProblemId())
                .build();
        return dto;
    }
}
