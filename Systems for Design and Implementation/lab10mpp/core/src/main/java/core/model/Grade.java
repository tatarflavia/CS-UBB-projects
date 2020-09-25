package core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Grade extends BaseEntity<Pair<Long,Long>> {
    private int gradeByTeacher;
    private Long studentId;
    private Long problemId;
}
