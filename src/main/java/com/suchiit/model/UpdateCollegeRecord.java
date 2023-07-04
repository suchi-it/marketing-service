package com.suchiit.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class UpdateCollegeRecord {
	@NotBlank(message="collegeCode Should not be blank")
	private String collegeCode;
	private String pocName;
	private String pocPhone;
	private String pocEmail;
	private Date dateofVisit;
	private String mouStatus;  
	private Comment comments;
	public String getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}
	public String getPocName() {
		return pocName;
	}
	public void setPocName(String pocName) {
		this.pocName = pocName;
	}
	public String getPocPhone() {
		return pocPhone;
	}
	public void setPocPhone(String pocPhone) {
		this.pocPhone = pocPhone;
	}
	public String getPocEmail() {
		return pocEmail;
	}
	public void setPocEmail(String pocEmail) {
		this.pocEmail = pocEmail;
	}
	public Date getDateofVisit() {
		return dateofVisit;
	}
	public void setDateofVisit(Date dateofVisit) {
		this.dateofVisit = dateofVisit;
	}
	public String getMouStatus() {
		return mouStatus;
	}
	public void setMouStatus(String mouStatus) {
		this.mouStatus = mouStatus;
	}
	
	public Comment getComments() {
		return comments;
	}
	public void setComments(Comment comments) {
		this.comments = comments;
	}
	
	public UpdateCollegeRecord(String collegeCode, String pocName, String pocPhone, String pocEmail, Date dateofVisit,
			String mouStatus, Comment comments) {
		super();
		this.collegeCode = collegeCode;
		this.pocName = pocName;
		this.pocPhone = pocPhone;
		this.pocEmail = pocEmail;
		this.dateofVisit = dateofVisit;
		this.mouStatus = mouStatus;
		this.comments = comments;
	}
	public UpdateCollegeRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
