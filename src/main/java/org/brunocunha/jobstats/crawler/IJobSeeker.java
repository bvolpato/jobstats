/**
 * Copyright (C) 2015 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
