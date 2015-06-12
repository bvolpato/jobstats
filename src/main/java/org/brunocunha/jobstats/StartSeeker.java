package org.brunocunha.jobstats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.brunocunha.jobstats.crawler.IJobSeeker;
import org.brunocunha.jobstats.crawler.IndeedCrawler;
import org.brunocunha.jobstats.elasticsearch.ElasticSearchHelper;
import org.brunocunha.jobstats.model.Position;

/**
 * Launcher class, that executes the search and indexes on ElasticSearch
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
public class StartSeeker {

	public static void main(String[] args) throws IOException {

		List<IJobSeeker> seekers = new ArrayList<IJobSeeker>();
		//seekers.add(new StackOverflowCrawler());
		seekers.add(new IndeedCrawler());

		for (IJobSeeker seeker : seekers) {
			List<Position> found = seeker.fetch("flex", 10);
			for (Position job : found) {
				log.info("Job --> " + job);
			}

			//ElasticSearchHelper.index(found);
		}

	}
}
