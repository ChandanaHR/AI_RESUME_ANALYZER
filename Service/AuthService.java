package aiml.AIResumeanalyzer.Service;

import org.apache.coyote.BadRequestException;
import org.jspecify.annotations.Nullable;
//import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import aiml.AIResumeanalyzer.DTO.AuthResponse;
import aiml.AIResumeanalyzer.DTO.LoginRequest;
import aiml.AIResumeanalyzer.DTO.RegisterRequest;
import aiml.AIResumeanalyzer.Repositories.CandidateProfileRepository;
import aiml.AIResumeanalyzer.Repositories.UserRepository;
import aiml.AIResumeanalyzer.entites.CandidateProfile;
import aiml.AIResumeanalyzer.entites.User;
import aiml.AIResumeanalyzer.entites.Role;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private UserRepository userRepository;
	private CandidateProfileRepository candidateprofilerepository;
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private JwtUtil jwtUtil;
	
	@Transactional
	public AuthResponse register(RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail()){
			throw new BadRequestException("An account with this email already exists");
		}
//		Take the "ADMIN" String from the request and convert it into the Java enum constant Role.ADMIN
		Role role = Role.valueOf(request.getRole());
		User user = User.builder()
				   .email(request.getEmail())
				   .passwordHash(passwordEncoder(request.getPassword()))
				   .role(role)
				   .build();
		userRepository.save(user);
		
		if(role == Role.CANDIDATE) {
			CandidateProfile profile = CandidateProfile.builder() 
					                   .user(user)
					                   .fullName(request.getFullName())
					                   .build();
			candidateprofilerepository.save(profile);
		}
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		return new AuthResponse(token, user.getEmail(), user.getRole().name());
	}

	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new BadRequestException("Invalid credentials"));
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		return new AuthResponse(token, user.getEmail(), user.getRole().name());
		
	}

}
