package com.raghavx.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavx.auth.model.User;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the list
	 */
	User findByEmail(String email);	
	
	
	/**
	 * Find by user name.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);
	
	/**
	 * Find by username and password.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user
	 */
	User findByUsernameAndPassword(String username, String password);
	
}
