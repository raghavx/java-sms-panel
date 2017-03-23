package com.raghavx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Class WebSecurityConfig.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * B crypt password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()

				.antMatchers("/","/upload", "/home", "/signup", "/login", "/resetpassword", "/forgetpassword")

				.permitAll().anyRequest().authenticated()

				.and().logout().permitAll()

				.and().authorizeRequests()

				.anyRequest().hasRole("USER")

				.and().formLogin().defaultSuccessUrl("/filter").loginPage("/login").permitAll();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/resources/static/**");
		web.ignoring().antMatchers("/static/**");
		web.ignoring().antMatchers("/resources/static/images/**");
		web.ignoring().antMatchers("/static/images/**");
		web.ignoring().antMatchers("/resources/static/css/**");
		web.ignoring().antMatchers("/static/css/**");
		web.ignoring().antMatchers("/resources/static/js/**");
		web.ignoring().antMatchers("/static/js/**");

	}

	/**
	 * Configure global.
	 *
	 * @param auth
	 *            the auth
	 * @throws Exception
	 *             the exception
	 */
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		// .passwordEncoder(bCryptPasswordEncoder());
	}

}
