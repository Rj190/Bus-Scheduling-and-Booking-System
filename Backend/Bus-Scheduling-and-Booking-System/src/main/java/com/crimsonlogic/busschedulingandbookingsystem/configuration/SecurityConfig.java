package com.crimsonlogic.busschedulingandbookingsystem.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/users").permitAll() // Allow all users to access user list
                .antMatchers("/api/users/**").hasRole("ADMIN") // Require ADMIN role for user-related endpoints
                .anyRequest().authenticated() // Require authentication for other endpoints
            .and()
            .httpBasic() // Use Basic Authentication
            .and()
            .csrf().disable(); // Disable CSRF for simplicity (you might want to enable it in a real app)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
            .withUser("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
            .and()
            .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN", "USER");
    }
}
