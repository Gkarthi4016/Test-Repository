/**
 * 
 */
package com.karthi.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author GKarthi4016
 *
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication()
			.withUser("gkarthi")
			.password("abcd1234")
			.authorities("ROLE_USER");
	}

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/homepage").access("hasRole('ROLE_USER')")
		.and()
			.formLogin().loginPage("/loginpage")
			.defaultSuccessUrl("/homepage")
			.failureUrl("/loginpage?error")
			.usernameParameter("username").passwordParameter("password")				
		.and()
			.logout().logoutSuccessUrl("/loginpage?logout"); 

	}

	
}
