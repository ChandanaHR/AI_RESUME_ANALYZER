package aiml.AIResumeanalyzer.DTO;

import lombok.Data;

@Data
public class CandidateProfileDTO {
	private Long id;
	private String email;
	private String fullName;
	private String phone;
	private String location;
	private String headline;
}