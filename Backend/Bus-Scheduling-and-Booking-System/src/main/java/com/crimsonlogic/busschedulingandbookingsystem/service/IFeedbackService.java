package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Feedback;

public interface IFeedbackService {
	
	public List<Feedback> getAllFeedbacks();
	public Feedback createFeedback(Feedback feedback);
	public Feedback updateFeedbackbyId(Integer id, Feedback feedback);
	public void deleteFeedbackbyId(Integer id);
	Feedback getFeedbackById(Integer id);

}
