package aiml.AIResumeanalyzer.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aiml.AIResumeanalyzer.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
