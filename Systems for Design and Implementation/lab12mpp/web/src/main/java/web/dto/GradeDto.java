package web.dto;

import core.model.Pair;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class GradeDto {
    private int gradeByTeacher;
    private Long studentId;
    private Long problemId;
}

