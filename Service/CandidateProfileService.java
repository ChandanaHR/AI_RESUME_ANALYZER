package aiml.AIResumeanalyzer.Service;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import aiml.AIResumeanalyzer.DTO.CandidateProfileDTO;
import aiml.AIResumeanalyzer.Repositories.CandidateProfileRepository;
import aiml.AIResumeanalyzer.Repositories.UserRepository;
import aiml.AIResumeanalyzer.entites.CandidateProfile;
import aiml.AIResumeanalyzer.entites.User;
import aiml.AIResumeanalyzer.exception.ResourceNotFoundException;
import aiml.AIResumeanalyzer.mapper.CandidateProfileMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateProfileService {
	private CandidateProfileRepository candidateProfileRepository;
	private UserRepository userRepository;
	private CandidateProfileMapper mapper;
	
	public CandidateProfileDTO getByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));
		CandidateProfile profile = candidateProfileRepository.findByUserId(user.getId()).orElseThrow(()->new ResourceNotFoundException("Profile not found"));
		return mapper.todto(profile);
	}

	@Transactional
	public CandidateProfileDTO update(String email, CandidateProfileDTO dto) {
		User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));
		CandidateProfile profile = candidateProfileRepository.findByUserId(user.getId()).orElseThrow(()->new ResourceNotFoundException("Profile not found"));
		mapper.applyupdates(dto, profile);
		candidateProfileRepository.save(profile);
		return mapper.todto(profile);
	}
	
	
}
