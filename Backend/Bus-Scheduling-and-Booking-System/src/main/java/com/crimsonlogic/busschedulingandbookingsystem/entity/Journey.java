package com.crimsonlogic.busschedulingandbookingsystem.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "journey_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer journeyId;


    @NotNull(message = "Journey date is required")
    private LocalDate journeyDate;
    
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull(message = "Journey time is required")
    private LocalTime journeyTime;
    

    @NotNull(message = "arrival Date  is required")
    private LocalDate arrivalDate;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull(message = "arrival time is required")
    private LocalTime arrivalTime;
    
    
    @NotNull(message = "fare is required")
    private Double fare;


	@ManyToOne
	@JoinColumn(name = "busId_fk")
	@JsonIgnoreProperties("journeys")
	private Bus bus;

    @ManyToOne
    @JoinColumn(name = "routeID_fk")
    @JsonIgnoreProperties("journeys")
    private Route route;
    
    
    @OneToMany(mappedBy = "journey")
    private List<Booking> bookings ;
    

}
