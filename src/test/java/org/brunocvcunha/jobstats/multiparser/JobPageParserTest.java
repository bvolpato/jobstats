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
package org.brunocvcunha.jobstats.multiparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import lombok.extern.log4j.Log4j;

import org.brunocvcunha.jobstats.model.Position;
import org.brunocvcunha.jobstats.multiparser.JobPageParser;
import org.junit.Test;

@Log4j
public class JobPageParserTest {

	/**
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testStackoverflow() throws URISyntaxException, IOException {
		Position pos = new Position();
		JobPageParser.parseJobPage(pos, "http://careers.stackoverflow.com/jobs/91044/rails-developer-software-engineer-ror-connexity?searchTerm=java");
		
		log.info(pos);
		
		assertEquals("Rails Developer / Software Engineer (ROR)", pos.getJobTitle());
		assertEquals("Connexity", pos.getCompanyName());
		assertEquals("Camarillo, CA (allows remote)", pos.getLocation());
		assertTrue(pos.getJobDescription().contains("Building Ruby/Rails interfaces"));
		assertEquals("ruby", pos.getTags().get(0));

	}
	
	/**
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testCatsone() throws URISyntaxException, IOException {
		Position pos = new Position();
		JobPageParser.parseJobPage(pos, "https://energypioneersolutions.catsone.com/careers/index.php?m=portal&a=details&jobOrderID=5608313&ref=indeed");
		
		log.info(pos);
		
		assertEquals("JAVA Programmer/JAVA Developer", pos.getJobTitle());
		assertEquals("Hastings, NE", pos.getLocation());
		assertTrue(pos.getJobDescription().contains("Growing Nebraska company"));

	}
	
	/**
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testBrassRing() throws URISyntaxException, IOException {
		Position pos = new Position();
		JobPageParser.parseJobPage(pos, "https://xjobs.brassring.com/tgwebhost/jobdetails.aspx?partnerid=54&siteid=5346&jobid=1224760");
		
		log.info(pos);
		
		assertEquals("Mobile Web Developer", pos.getJobTitle());
		assertEquals("GE Capital", pos.getCompanyName());
		assertEquals("Stamford", pos.getLocation());
		
		log.info("Job Description: " + pos.getJobDescription());
		assertTrue(pos.getJobDescription().contains("degree and a minimum of 2 years"));

	}
}
