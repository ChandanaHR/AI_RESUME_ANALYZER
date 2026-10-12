package aiml.AIResumeanalyzer.mapper;

import aiml.AIResumeanalyzer.DTO.CandidateProfileDTO;
import aiml.AIResumeanalyzer.entites.CandidateProfile;

public class CandidateProfileMapper {
	public CandidateProfileDTO todto(CandidateProfile profile) {
		CandidateProfileDTO dto = new CandidateProfileDTO();
		dto.setId(profile.getId());
		dto.setEmail(profile.getUser().getEmail());
		dto.setFullName(profile.getFullName());
		dto.setLocation(profile.getLocation());
		dto.setPhone(profile.getPhone());
		dto.setHeadline(profile.getHeadline());
		return dto;
	}
	
	public void applyupdates(CandidateProfileDTO dto, CandidateProfile profile) {
		profile.setFullName(dto.getFullName());
		profile.setLocation(dto.getLocation());
		profile.setPhone(dto.getPhone());
		profile.setHeadline(dto.getHeadline());
	}
}
