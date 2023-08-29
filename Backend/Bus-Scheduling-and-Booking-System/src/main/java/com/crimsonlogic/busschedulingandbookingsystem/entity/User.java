package com.crimsonlogic.busschedulingandbookingsystem.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Getter
@Setter
@Table(name = "user_info")
public class User implements UserDetails{
    @Id
    private String userID;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String userRole; // Role can be "User" or "Admin"
    
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}
