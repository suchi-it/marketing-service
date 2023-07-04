package com.suchiit.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.suchiit.model.CollegeRecord;
import com.suchiit.model.CreateCollegeRecord;
import com.suchiit.model.UpdateCollegeRecord;


public interface CollegeService {
	String createCollege(CreateCollegeRecord request);

	ResponseEntity<?> getAllColleges(String searchInput);
	public CollegeRecord getCollege(String collegeCode);
	ResponseEntity<?> updateCollege( UpdateCollegeRecord request);
	ResponseEntity<?> deleteCollege(String collegeCode);
	
	ResponseEntity<?> getDistricts(String state);
	//ResponseEntity<?> getDistricts(String districts);

	
}
