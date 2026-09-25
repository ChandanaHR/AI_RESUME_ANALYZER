package aiml.AIResumeanalyzer.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min=8, message = "Password must atleast 8 characters")
	private String password;
	
	@NotBlank
	private String fullName;
	
	@NotBlank
	@Pattern(regexp = "CANDIDATE|RECRUITER", message="Role must be CANDIDATE or RECRUITER")
	private String role;
}
