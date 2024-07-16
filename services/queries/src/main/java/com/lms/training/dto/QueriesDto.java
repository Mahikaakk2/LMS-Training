package com.lms.training.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Schema(name = "Queries",
        description = "Schema to hold Queries"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueriesDto {

    @Schema(
            description = "Query ID of Query", example = "1"
    )
    private Long queryId;

    @NotNull(message = "NewJoiner ID can not be a null or empty")
//    @PositiveOrZero(message = "Positive or Zero values required")
    @Schema(
            description = "NewJoiner ID of Query", example = "1"
    )
    private Long newJoinerId;

    @NotNull(message = "Mentor ID can not be a null or empty")
    @Schema(
            description = "Mentor ID of Query", example = "1"
    )
    private Long mentorId;
    @NotNull(message = "Course ID can not be a null or empty")
    @Schema(
            description = "Course ID of Query", example = "1"
    )
    private Long courseId;

    @NotEmpty(message = "Query Text can not be a null or empty")
    @Schema(
            description = "Query text of Query", example = "I have a doubt"
    )
    private String queryText;
    @Schema(
            description = "Response Text of Query", example = "This is the answer"
    )

    private String ResponseText;
    @Schema(
            description = "Status of Query", example = "false"
    )

    private boolean status;

}
