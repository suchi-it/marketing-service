package com.suchiit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.suchiit.constants.StatusConstants;
import com.suchiit.model.CollegeRecord;
import com.suchiit.model.Comment;
import com.suchiit.model.CreateCollegeRecord;
import com.suchiit.model.Districts;
import com.suchiit.model.UpdateCollegeRecord;
import com.suchiit.service.CollegeService;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class CollegeServiceImpl implements CollegeService {

	private static final Logger logger = LoggerFactory.getLogger(CollegeServiceImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String createCollege(CreateCollegeRecord request) {

		logger.info("College Record is created Successfully in Implimentaion");

		Random rand = new Random();

		Criteria criteria = new Criteria();
		criteria.orOperator(
				Criteria.where("collegeName").regex(
						Pattern.compile(request.getCollegeName(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),

				Criteria.where("collegeCode").regex(
						Pattern.compile(request.getCollegeCode(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));

		Query query = new Query(criteria);
		CollegeRecord collegerecord = this.mongoTemplate.findOne(query, CollegeRecord.class);
		if (collegerecord == null) {

			CollegeRecord newcollegerecord = new CollegeRecord();
			BeanUtils.copyProperties(request, newcollegerecord, "comments");

			List<Comment> finalComments = new ArrayList<>();
			for (Comment comment : request.getComments()) {

				comment.setCreatedAt(new Date());
				finalComments.add(comment);
			}
			newcollegerecord.setComments(finalComments);

			newcollegerecord.setStatus(StatusConstants.PENDING);
			newcollegerecord.setCreatedAt(new Date(System.currentTimeMillis()));
			this.mongoTemplate.insert(newcollegerecord);

			return "College Record successfully created with CollegeCode: " + newcollegerecord.getCollegeCode();
		} else {

			return "College already exist";
		}
	}

	@Override
	public ResponseEntity<?> getAllColleges(String searchInput) {

		logger.info("Getting All Colleges details in Implimentaion");

		Query query = new Query();
		if (StringUtils.isNotEmpty(searchInput)) {
			query = this.getSearchQuery(searchInput);
		}
		List<CollegeRecord> colleges = this.mongoTemplate.find(query, CollegeRecord.class);
		if (!CollectionUtils.isEmpty(colleges)) {
			return new ResponseEntity<>(colleges, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}
	}

	private Query getSearchQuery(String searchInput) {

		logger.info("Executed searchquery in Impl");

		Query query = new Query();
		List<Criteria> criterias = new LinkedList<>();
		Criteria searchCriteria = new Criteria();
		searchCriteria.orOperator(
				Criteria.where("collegeCode")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("collegeName")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("district")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("mandal")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("state")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("pOCName")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("POCPhone")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("POCEmail")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("dateofVisit")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("MOUStatus")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("comments.comments")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("comments.createdBy")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("dueDate")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("createdBy")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		criterias.add(searchCriteria);
		if (!CollectionUtils.isEmpty(criterias)) {
			Criteria criteria = new Criteria();
			criteria.andOperator(criterias.stream().toArray(Criteria[]::new));
			query.addCriteria(criteria);
		}

		return query;
	}

	@Override
	public CollegeRecord getCollege(String collegeCode) {

		logger.info("Getting a single college record in Implimentaion");

		Query query = new Query();
		if (StringUtils.isNotEmpty(collegeCode)) {
			query.addCriteria(Criteria.where("_id").is(collegeCode));
			CollegeRecord collegerecord = this.mongoTemplate.findOne(query, CollegeRecord.class);
			if (collegerecord != null)
				return collegerecord;
			else
				return new CollegeRecord();

		} else {

			return new CollegeRecord();
		}
	}
	
	@Override
	public ResponseEntity<?> updateCollege( UpdateCollegeRecord request) {
		logger.info("Updating a college record in Implimentaion");
		
		 if (StringUtils.isEmpty(request.getCollegeCode())) {
		       
		        return new ResponseEntity<>("CollegeCode con not be empty", HttpStatus.BAD_REQUEST);
		    }
		 
		
		Query query = new Query();
		query.addCriteria(Criteria.where("collegeCode").is(request.getCollegeCode()));
		CollegeRecord collegeRecord = this.mongoTemplate.findOne(query, CollegeRecord.class);
		if (collegeRecord != null) {
			if (StringUtils.isNotEmpty(request.getPocName()))
				collegeRecord.setPocName(request.getPocName());
			if (StringUtils.isNotEmpty(request.getPocPhone()))
				collegeRecord.setPocPhone(request.getPocPhone());
			if (StringUtils.isNotEmpty(request.getPocEmail()))
				collegeRecord.setPocEmail(request.getPocEmail());
			if (StringUtils.isNotEmpty(request.getMouStatus()))
				collegeRecord.setMouStatus(request.getMouStatus());
			
			List<Comment> comments = collegeRecord.getComments();
			if(CollectionUtils.isEmpty(comments)) {
				comments=new ArrayList<>();
			}
              if (request.getComments()!=null) {
            	  Comment comment= request.getComments();
            	  comment.setCreatedAt(new Date());
				comments.add(comment);
			}

			collegeRecord.setLastModified(new Date(System.currentTimeMillis()));
			this.mongoTemplate.save(collegeRecord);
            return new ResponseEntity<>("CollegeRecord " + collegeRecord.getCollegeCode() + " is successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No User found with Id- " + request.getCollegeCode(), HttpStatus.NOT_FOUND);
        }
	}
	
	@Override
	public ResponseEntity<?> deleteCollege(String collegeCode) {

		logger.info("Deleting a college record in Implimentaion");
		

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(collegeCode));
		CollegeRecord collegerecord = this.mongoTemplate.findOne(query, CollegeRecord.class);
		if (collegerecord != null) {
			collegerecord.setCollegeName("INACTIVE");
			this.mongoTemplate.save(collegerecord);
			return new ResponseEntity<>("CollegeRecord " + collegeCode + " is successfully deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No CollegeRecord found with Id-" + collegeCode, HttpStatus.NOT_FOUND);
		}
	}

	
	@Override
	public ResponseEntity<?> getDistricts(String state) {
		logger.info("Getting districts in a state in implimentation");
		Query query = new Query();
		query.addCriteria(Criteria.where("state").is(state));
		Districts district = this.mongoTemplate.findOne(query, Districts.class);
		if (district!=null) {
			return new ResponseEntity<>(district, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No districts found in "+state, HttpStatus.NOT_FOUND);
		}

	}

	/*@Override
	public ResponseEntity<?> getDistricts(String districts) {
		Query query = new Query();
		query.addCriteria(Criteria.where("districts").is(districts));
		Districts district = this.mongoTemplate.findOne(query, Districts.class);
		if (district!=null) {
			return new ResponseEntity<>(district, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No districts found in "+districts, HttpStatus.NOT_FOUND);
		}

	}*/

}
