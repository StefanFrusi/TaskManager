package Task.manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "email cannot be empty")
    @Email(message = "please enter a valid email address")
    private String email;
    @NotNull(message = "password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=])\\S+$",message = " Make sure that the password contains: at least 1 upper case letter , at least 1 special character, at least 1 digit.Make sure it doesnt contain spaces ")
    @Size(min =8,max = 20,message = "The password must be between 8 to 20 characters")
    private String pwd;

}
