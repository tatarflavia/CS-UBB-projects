package web.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class ProblemDto extends BaseDto<Long> {
    private String text;
    private int number;
    private String chapter;
}
