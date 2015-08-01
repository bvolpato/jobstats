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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.extern.log4j.Log4j;

import org.brunocvcunha.inutils4j.MyStringUtils;
import org.brunocvcunha.jobstats.model.Position;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Log4j
public class JobPageParser {

	
	private static Properties props;
	
	public static void parseJobPage(Position pos, String url) throws URISyntaxException, IOException {
		log.info("Obtaining page and Parsing job for url: " + url);
		URI uri = new URI(url);
		String host = uri.getHost();
		log.info("URI Host: " + host);
		
		String content = MyStringUtils.getContent(url);
		Document doc = Jsoup.parse(content);

		parseJobPage(pos, url, doc);
	}
	
	public static void parseJobPage(Position pos, String url, Document doc) throws URISyntaxException, IOException {
		log.info("Parsing job for url: " + url);
		URI uri = new URI(url);
		String host = uri.getHost();
		log.info("URI Host: " + host);
		
		String jobDescription = selectString(doc, findProperty(host, "description"));
		if (jobDescription != null) 
			pos.setJobDescription(jobDescription);
		
		String jobTitle = selectString(doc, findProperty(host, "title"));
		if (jobTitle != null) 
			pos.setJobTitle(jobTitle);
		
		
		String company = selectString(doc, findProperty(host, "company"));
		if (company != null) 
			pos.setCompanyName(company);
		
		String location = selectString(doc, findProperty(host, "location"));
		if (location != null) 
			pos.setLocation(location);
		
		List<String> tags = selectList(doc, findProperty(host, "tags"));
		if (location != null) 
			pos.setTags(tags);
		
		
		
	}
	
	public static String findProperty(String host, String property) throws IOException {
		
		while (host.contains(".")) {
			log.debug("Find property \"" + property + "\" in host \""+ host + "\"");
			
			String initial = getProps().getProperty(host + "." + property);
			if (initial != null) {
				return initial;
			}
			
			host = host.substring(host.indexOf(".") + 1);
			
		}
		
		return null;
	}
	
	public static String selectString(Document doc, String selector) {
		log.info("Handling String selector " + selector);
		
		if (selector == null) {
			return null;
		}
		
		
		String[] selectArray = selector.split("::"); 
		String selectPart = selectArray[0];
		String valuePart = selectArray[1];
		
		log.debug("selectPart -> " + selectPart);
		log.debug("valuePart -> " + valuePart);
		
		Elements currentEl = parseSelector(doc, selectPart);
		
		if (valuePart.equalsIgnoreCase("text")) {
			log.debug("Selecting string \"" + currentEl.text() + "\" for selector \"" + selector + "\"");
			return currentEl.text();
		} else {
			log.debug("Selecting string \"" + currentEl.html() + "\" for selector \"" + selector + "\"");
			return currentEl.html();
		}
	}
	
	public static List<String> selectList(Document doc, String selector) {
		log.info("Handling List selector " + selector);
		
		if (selector == null) {
			return null;
		}
		
		
		String[] selectArray = selector.split("::"); 
		String selectPart = selectArray[0];
		String valuePart = selectArray[1];
		
		log.debug("selectPart -> " + selectPart);
		log.debug("valuePart -> " + valuePart);
		
		Elements currentEl = parseSelector(doc, selectPart);
		
		List<String> returnList = new ArrayList<String>();
		for (Element el : currentEl) {
			if (valuePart.equalsIgnoreCase("text")) {
				returnList.add(el.text());
			} else {
				returnList.add(el.html());
			}
		}
		
		log.debug("Selecting list \"" + returnList + "\" for selector \"" + selector + "\"");
		
		return returnList;
	}
	
	public static Elements parseSelector(Document doc, String selectPart) {
		
		
		log.debug("selectPart -> " + selectPart);
		
		
		Elements currentEl = doc.getAllElements();
		
		String[] selectIterate = selectPart.split("\\|\\|");
		for (String iteration : selectIterate) {
			log.debug("iterate at -> " + iteration);
			currentEl = currentEl.select(iteration);
			log.debug("size of elements: " + currentEl.size());
		}
		
		return currentEl;
	}

	public static Properties getProps() throws IOException {
		if (props == null) {
			props = new Properties();
			props.load(JobPageParser.class.getResourceAsStream("/multiparser-rules.properties"));
		}
		
		return props;
	}
}
