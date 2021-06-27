package com.spring.test.spb.repositories;

import com.spring.test.spb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
