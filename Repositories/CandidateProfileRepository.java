package aiml.AIResumeanalyzer.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aiml.AIResumeanalyzer.entites.CandidateProfile;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Long> {
	Optional<CandidateProfile> findByUser_Id(Long userId);
}
