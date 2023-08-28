package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Journey;
import com.crimsonlogic.busschedulingandbookingsystem.service.IJourneyService;

@Controller
@RestController
@RequestMapping("/journey")
public class JourneyController {
	@Autowired
	private IJourneyService jourService;
	
	
	@GetMapping()
	@RequestMapping("/viewalljourneys")
	public List<Journey> viewAllJourneys(){
return jourService.viewAllJourneys();
}
	@PostMapping("/insertjourney")   
    public Journey insertJourney(@RequestBody Journey journey) {
	return jourService.insertJourney(journey);
	}
	  @GetMapping("/getjourneysbyid/{jourid}")
			public Journey  viewJourenyById(@PathVariable("jourid")int journeyId)  {
				return jourService.viewJourneyById(journeyId);
			}
	  @DeleteMapping("/deletejourney/{jourid}")
		public void deleteJourney(@PathVariable("jourid")int journeyid) {
			jourService.deleteJourneyById(journeyid);
		}
	  @PutMapping("/updatejourneysbyid/{jourid}")
	  	public Journey updateStudentById(@PathVariable("jourid")int journeyId,
	  			   @RequestBody Journey newJourney)   {
	  		return jourService.updateJourneyById(journeyId,newJourney);
	  	}

}
