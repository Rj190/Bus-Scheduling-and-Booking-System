package com.crimsonlogic.busschedulingandbookingsystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.crimsonlogic.busschedulingandbookingsystem.security.JwtAuthenticationEntryPoint;
import com.crimsonlogic.busschedulingandbookingsystem.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception {
	 * http.csrf().disable().authorizeRequests().antMatchers("/auth/login").
	 * permitAll() .antMatchers("/api/users/create-user").permitAll().antMatchers(
	 * "/api/users/**").hasRole("Admin")
	 * .anyRequest().authenticated().and().exceptionHandling().
	 * authenticationEntryPoint(point).and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
	 * and() .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	 * return http.build(); }
	 */
	
	
	
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests()
//	            .antMatchers("/api/users/list").authenticated()
	            .antMatchers("/auth/login").permitAll().antMatchers("/api/users/create-user")
	            .permitAll()//.antMatchers("http://localhost:8080/swagger-ui/").permitAll()
					.anyRequest()
					/* .authenticated() */.permitAll()
	            .and().exceptionHandling().authenticationEntryPoint(point)
	            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	        
	        
	        //  .antMatchers("/api/users/list").hasRole("Admin").anyRequest().authenticated()

	        return http.build();
	    }
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}
}
