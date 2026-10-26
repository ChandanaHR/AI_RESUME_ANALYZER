package aiml.AIResumeanalyzer.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {
	private final SecretKey key;
	private final long expirationMs;
	
	public JwtUtil(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration-ms}") long expirationMs) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.expirationMs = expirationMs;
	}
	
	public String generateToken(String email, String role) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + expirationMs);
		return Jwts.builder()
				.subject(email)
				.claim("role", role)
				.issuedAt(now)
				.expiration(expiry)
				.signWith(key)
				.compact();
	}
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public String extractRole(String token) {
		return extractClaim(token, claims->claims.get("role",String.class));
	}
	
	public boolean isTokenValid(String token, String email) {
		return extractEmail(token).equals(email) && isExpired(token);
	}
	
	public boolean isExpired(String token) {
		return extractClaim(token,Claims::getExpiration).before(new Date());
	}
	
	private <T> T extractClaim(String token, Function<Claims,T> resolver) {
		Claims claims = Jwts.parser()
				.verifyWith(key)
				.build().parseSignedClaims(token).getPayload();
		return resolver.apply(claims);
	}
}
