package Task.manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    @Email(message = "please enter a valid email address")
    @NotNull(message = "Email field cannot be null")
    private String email;
    @NotNull(message = "The password field cannot be null")
    private String pwd;
}
