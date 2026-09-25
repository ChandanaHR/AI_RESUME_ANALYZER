package aiml.AIResumeanalyzer.entites;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames="email"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(nullable = false)
	 private String email;
	 
	 @Column(nullable = false)
	 private String passwordHash;
	 
	 @Enumerated(EnumType.STRING)
	 @Column(nullable = false)
	 private Role role;
	 
	 @Column(nullable = false, updatable = false)
	 private LocalDateTime createdAt;
	 
	 @PrePersist
	 void onCreate() {
		 this.createdAt = LocalDateTime.now();
	 }
	 
}
