package com.crimsonlogic.busschedulingandbookingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {
	
	 private String username;
	
	 private String jwtToken;

	 private String userRole;
	 


}
