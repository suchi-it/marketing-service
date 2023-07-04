package com.suchiit.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suchiit.model.CollegeRecord;
import com.suchiit.model.CreateCollegeRecord;
import com.suchiit.model.UpdateCollegeRecord;
import com.suchiit.service.CollegeService;

@RestController
@Validated
@RequestMapping("/api/college")
public class CollegeResource {

	private static final Logger logger = LoggerFactory.getLogger(CollegeResource.class);
	@Autowired
	CollegeService collegeservice;
	

	@CrossOrigin(value = "http://localhost:3000")
	@PostMapping("/createcollegerecord")
	public ResponseEntity<?> createCollegeRecord(@RequestBody CreateCollegeRecord request) {

		logger.info("College record is created in resource");
		return new ResponseEntity<String>(collegeservice.createCollege(request), HttpStatus.OK);
	}

	@CrossOrigin(value = "http://localhost:3000")
	@GetMapping("/getallcollegesrecords")
	public ResponseEntity<?> getCollegesRecords(@RequestParam(required = false) String searchInput) {

		logger.info(" Getting All College records in resource");
		return new ResponseEntity<>(this.collegeservice.getAllColleges(searchInput), HttpStatus.OK);
	}

	@CrossOrigin(value = "http://localhost:3000")
	@GetMapping("/getcollegerecord")
	public ResponseEntity<?> getCollegeRecord(@RequestParam String collegeCode) {
		logger.info(" Getting a college record in resource");
		return new ResponseEntity<CollegeRecord>(this.collegeservice.getCollege(collegeCode), HttpStatus.OK);
	}

	@CrossOrigin(value = "http://localhost:3000")
	@PutMapping("/updatecollegerecord")
	public ResponseEntity<?> updateCollegeRecord(@RequestBody UpdateCollegeRecord request) {
		logger.info("update a college record in resource");
		return this.collegeservice.updateCollege(request);
		//return collegeservice.updateCollege(request, bindingResult);
	}

	@CrossOrigin(value = "http://localhost:3000")
	@DeleteMapping("/deletecollegerecord")
	public ResponseEntity<?> deleteCollegeRecord(@RequestParam String collegeCode) {
		logger.info(" Delete a college record in resource");

		return this.collegeservice.deleteCollege(collegeCode);
	}
	
	
	@GetMapping("/districts")
	public ResponseEntity<?> getDistricts(@RequestParam String state) {
	logger.info("Getting districts in a state in resource");
		return new ResponseEntity<>(this.collegeservice.getDistricts(state), HttpStatus.OK);
	}

	/*	@GetMapping("/districts")
	public ResponseEntity<?> getDistricts(@RequestParam String districts) {
		return new ResponseEntity<>(this.collegeservice.getDistricts(districts), HttpStatus.OK);
	}*/
}
