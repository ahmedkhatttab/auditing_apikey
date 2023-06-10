package com.sample.spring_jpa_auditing;

import com.sample.spring_jpa_auditing.entities.User;
import com.sample.spring_jpa_auditing.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SpringJpaAuditingApplication implements CommandLineRunner {

	private final UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaAuditingApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("eng.ahmedkhatab@yahoo.com");
		user.setName("migration");
		user.setPhone("+966568221388");
		user.setTempCreatedOn(LocalDateTime.now().minusDays(10));
		user.setTempUpdatedOn(LocalDateTime.now().minusDays(5));
		userRepo.save(user);
		log.info("#########", user);
	}
}
