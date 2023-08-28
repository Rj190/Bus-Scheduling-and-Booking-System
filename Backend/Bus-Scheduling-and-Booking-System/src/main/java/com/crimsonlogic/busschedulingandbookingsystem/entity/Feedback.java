package com.crimsonlogic.busschedulingandbookingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;
    
    @Column(name="feedbacktxt")
	@NotNull
    private String feedbackText;
    
    @Column(name = "rating")
    private int rating;
    
    
    //default constructors
	public Feedback() {
		super();
	}
	
	//Parameterized constructors
	public Feedback(Integer feedbackId, @NotNull String feedbackText, int rating) {
		super();
		this.feedbackId = feedbackId;
		this.feedbackText = feedbackText;
		this.rating = rating;
	}
	
	
	//getters and setters
	public Integer getId() {
		return feedbackId;
	}

	public void setId(Integer id) {
		this.feedbackId = id;
	}

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	//to String()
	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", feedbackText=" + feedbackText + ", rating=" + rating + "]";
	}

    
}

