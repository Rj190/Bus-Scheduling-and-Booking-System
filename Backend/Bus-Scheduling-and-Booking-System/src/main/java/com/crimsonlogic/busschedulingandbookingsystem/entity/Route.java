package com.crimsonlogic.busschedulingandbookingsystem.entity;



import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "routedetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Route {
 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
	@NotBlank(message = "Departure city is required")
    @Size(min = 2, max = 100, message = "Departure city must be between 2 and 100 characters")
    private String departureCity;

    @NotBlank(message = "Arrival city is required")
    @Size(min = 2, max = 100, message = "Arrival city must be between 2 and 100 characters")
    private String arrivalCity;

    @Min(value = 1, message = "Distance must be at least 1")
    private Integer distance;

    @Min(value = 1, message = "Duration must be at least 1")
    private Integer duration;
    
    
    @OneToMany(mappedBy = "route")
    @JsonIgnoreProperties("route")
    private List<Journey> journeys ;
    

}
