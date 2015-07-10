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
package org.brunocunha.jobstats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.brunocunha.jobstats.crawler.IJobSeeker;
import org.brunocunha.jobstats.crawler.IndeedCrawler;
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
			List<Position> found = seeker.fetch("java", 1000);
			for (Position job : found) {
				log.info("Job --> " + job);
			}

			//ElasticSearchHelper.index(found);
		}

	}
}
