package com.crimsonlogic.busschedulingandbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Feedback;
import com.crimsonlogic.busschedulingandbookingsystem.repository.IFeedbackRepository;

import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
	
	@Autowired
    private IFeedbackRepository feedbackRepo;
	
	@Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }
	
	@Override
    public Feedback getFeedbackById(Integer id) {
        return feedbackRepo.findById(id).get();
    }

	@Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }
	
	@Override
    public Feedback updateFeedbackbyId(Integer feedbackId, Feedback feedback) {
        getFeedbackById(feedbackId);
        feedback.setId(feedbackId);
        return feedbackRepo.save(feedback);
    }

	@Override
    public void deleteFeedbackbyId(Integer id) {
    	feedbackRepo.deleteById(id);
    }
}
