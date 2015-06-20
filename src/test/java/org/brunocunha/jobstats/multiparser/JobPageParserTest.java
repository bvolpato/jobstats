package org.brunocunha.jobstats.multiparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import lombok.extern.log4j.Log4j;

import org.brunocunha.jobstats.model.Position;
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
		JobPageParser.parseJobPage(pos, "http://careers.stackoverflow.com/jobs/91044/senior-software-engineer-boku?a=ux48oTHx1de&searchTerm=java&so=p");
		
		log.info(pos);
		
		assertEquals("Senior Software Engineer", pos.getJobTitle());
		assertEquals("Boku", pos.getCompanyName());
		assertEquals("San Francisco, CA (relocation offered) (visa sponsorship offered)", pos.getLocation());
		assertTrue(pos.getJobDescription().contains("Work hands-on with an agile, highly-skilled team of engineers"));
		assertEquals("java", pos.getTags().get(0));

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
