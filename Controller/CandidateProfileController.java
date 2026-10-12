package aiml.AIResumeanalyzer.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiml.AIResumeanalyzer.DTO.CandidateProfileDTO;
import aiml.AIResumeanalyzer.Service.CandidateProfileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/candidate/profile")
@RequiredArgsConstructor
public class CandidateProfileController {
	private CandidateProfileService profileService;
	
//	Method to get the user details of currently logged in user
	@GetMapping("/me")
	public ResponseEntity<CandidateProfileDTO> getMyProfile(Authentication auth) {
		return ResponseEntity.ok(profileService.getByEmail(auth.getName()));
	}
	
	@PutMapping("/me")
	public ResponseEntity<CandidateProfileDTO> updateMyProfile(Authentication auth, @RequestBody CandidateProfileDTO dto) {
		return ResponseEntity.ok(profileService.update(auth.getName(),dto));
	}
}
