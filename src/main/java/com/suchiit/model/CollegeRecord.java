package com.suchiit.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.suchiit.constants.CollectionConstants;

@Document(collection=CollectionConstants.COLLEGEDETAILS)
public class CollegeRecord {
	private String district;
	private String collegeName;  
	@Id
	@NotBlank(message="collegeCode should not be blank")
    private String collegeCode;
    private String mandal;
    private String state;
	private String pocName; 
	private String pocPhone;
	private String pocEmail;
	private Date dateofVisit;
	private String mouStatus; 
	private List<Comment> comments;
	private Date dueDate;
	private String createdBy;
	private String status;
	@CreatedDate
	private Date createdAt;
	@LastModifiedBy
	private Date lastModified;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public CollegeRecord(String district, String collegeName,
			@NotBlank(message = "collegeCode should not be blank") String collegeCode, String mandal, String state,
			String pocName, String pocPhone, String pocEmail, Date dateofVisit, String mouStatus,
			List<Comment> comments, Date dueDate, String createdBy, String status, Date createdAt, Date lastModified) {
		super();
		this.district = district;
		this.collegeName = collegeName;
		this.collegeCode = collegeCode;
		this.mandal = mandal;
		this.state = state;
		this.pocName = pocName;
		this.pocPhone = pocPhone;
		this.pocEmail = pocEmail;
		this.dateofVisit = dateofVisit;
		this.mouStatus = mouStatus;
		this.comments = comments;
		this.dueDate = dueDate;
		this.createdBy = createdBy;
		this.status = status;
		this.createdAt = createdAt;
		this.lastModified = lastModified;
	}
	public CollegeRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
