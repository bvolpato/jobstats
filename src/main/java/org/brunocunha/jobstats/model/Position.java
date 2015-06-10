package org.brunocunha.jobstats.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Data
public class Position {

	private String jobTitle;
	private String companyName;
	private String location;
	private Date postedDate;
	
	private String jobDescription;
	private String jobSkills;
	
	private List<String> tags;
	
	private String origin;
	private String originId;
	private String originUrl;
	
}
