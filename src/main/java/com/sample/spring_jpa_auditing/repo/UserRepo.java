package com.sample.spring_jpa_auditing.repo;

import com.sample.spring_jpa_auditing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
