package com.caldeira.repository;

import com.caldeira.model.User;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

	public Optional<User> findActiveUserByEmail(String username);

	public Optional<User> findByEmail(String email);

}
