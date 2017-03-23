package com.raghavx.auth.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raghavx.auth.model.PasswordResetToken;
import com.raghavx.auth.model.User;
import com.raghavx.auth.repository.PasswordResetTokenRepository;
import com.raghavx.auth.repository.UserRepository;

/**
 * The Class UserDetailServiceImpl.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService, UserService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;


	/** The password reset token repository. */
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	/** The mail service. */
	@Autowired
	private MailService mailService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		if (userRepository.findByUsername(username) == null) {
			throw new UsernameNotFoundException("Invalid username / password");
		}
		
		return userRepository.findByUsername(username);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendPasswordResetEmail(User user) {
		PasswordResetToken passwordResetToken = new PasswordResetToken();

		String token = UUID.randomUUID().toString();
		passwordResetToken.setToken(token);
		passwordResetToken.setUser(user);
		passwordResetToken.setExpiryDate(System.currentTimeMillis() + 86400000L);

		String url = "https://localhost:8443/forgetpassword?id=" + user.getUserId() + "&token=" + token;
		// use template
		//CustomerPasswordReset customerPasswordReset = new CustomerPasswordReset(user.getUsername(), url);
		//mailService.send(customerPasswordReset, user.getUsername());

		passwordResetTokenRepository.save(passwordResetToken);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByUsername(String username) {
		

		return userRepository.findByUsername(username);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findById(Long id) {
		return userRepository.getOne(id);
	}

	
}
