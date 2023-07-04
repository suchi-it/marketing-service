package com.suchiit.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.suchiit.constants.CollectionConstants;

@Document(collection = CollectionConstants.DISTRICTS)
public class Districts {
	@Id
	private String id;
	private List<String> districts;
	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getDistricts() {
		return districts;
	}

	public void setDistricts(List<String> districts) {
		this.districts = districts;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Districts(String id, List<String> districts, String state) {
		super();
		this.id = id;
		this.districts = districts;
		this.state = state;
	}

}
