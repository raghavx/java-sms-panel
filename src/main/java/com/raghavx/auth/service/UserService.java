package com.raghavx.auth.service;

import java.util.List;

import com.raghavx.auth.model.User;

/**
 * The Interface UserService.
 */
public interface UserService {
    
    /**
     * Save.
     *
     * @param user the user
     * @return the user
     */
    User save(User user);
    
    /**
     * Find by username.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

	/**
	 * Send password reset email.
	 *
	 * @param user the user
	 */
	void sendPasswordResetEmail(User user);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<User> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	User findById(Long id);


}
