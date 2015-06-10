package org.brunocunha.jobstats.crawler;

import java.io.IOException;
import java.util.List;

import org.brunocunha.jobstats.model.Position;


/**
 * Interface for job seekers
 * @author Bruno Candido Volpato da Cunha
 *
 */
public interface IJobSeeker {

	/**
	 * @return The implementation's seeker name
	 */
	String getSeekerName();
	
	/**
	 * Fetch all jobs
	 * @return All jobs in the website
	 * @throws IOException
	 */
	List<Position> fetch() throws IOException;
	
	/**
	 * Fetch jobs with the keyword 
	 * @param keyword
	 * @return Jobs filtered by keyword
	 * @throws IOException
	 */
	List<Position> fetch(String keyword) throws IOException;
	
	/**
	 * Fetch jobs with the keyword, limiting the results
	 * @param keyword
	 * @param maximum
	 * @return Jobs filtered by keyword according to limit
	 * @throws IOException
	 */
	List<Position> fetch(String keyword, int maximum) throws IOException;
	
}
