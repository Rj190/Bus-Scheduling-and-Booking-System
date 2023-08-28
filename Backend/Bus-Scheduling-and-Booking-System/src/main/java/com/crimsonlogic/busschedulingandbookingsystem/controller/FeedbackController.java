package com.crimsonlogic.busschedulingandbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Feedback;
import com.crimsonlogic.busschedulingandbookingsystem.service.IFeedbackService;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
	
	@Autowired
    private IFeedbackService feedbackServc;


    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackServc.getAllFeedbacks();
    }

    @GetMapping("/{getfeedbackbyid}/{id}")
    public Feedback getFeedbackById(@PathVariable ("id") Integer id) {
        return feedbackServc.getFeedbackById(id);
    }

    @PostMapping("/createfeedback")
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackServc.createFeedback(feedback);
    }

    @PutMapping("/{updatefeedbackbyid}/{id}")
    public Feedback updateFeedback(@PathVariable ("id") Integer id, @RequestBody Feedback updatedFeedback) {
        return feedbackServc.updateFeedbackbyId(id, updatedFeedback);
    }

    @DeleteMapping("/{deletefeedbackbyid}/{id}")
    public void deleteFeedback(@PathVariable ("id") Integer id) {
    	feedbackServc.deleteFeedbackbyId(id);
    }
}

