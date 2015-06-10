package org.brunocunha.jobstats.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.brunocunha.jobstats.crawler.IJobSeeker;
import org.brunocunha.jobstats.crawler.StackOverflowCrawler;
import org.brunocunha.jobstats.model.Position;
import org.junit.Test;

public class StackOverflowCrawlerTest {

	@Test
	public void testJava() throws IOException {
		IJobSeeker seeker = new StackOverflowCrawler();
		
		List<Position> found = seeker.fetch("java", 1);
		assertEquals(1, found.size());
		
	}
}
