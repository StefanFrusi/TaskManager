package Task.manager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    @NotNull(message = "please provide a title")
    private String title;
    @NotNull(message="please provide a description")
    private String description;
    @NotNull(message = "please provide the time due to which the task should be finished")
    private LocalDateTime dueTo;
}
