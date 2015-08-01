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
package org.brunocvcunha.jobstats.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Position POJO
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Data
public class Position {

	private String jobTitle;
	private String companyName;
	private String location;
	private Date postedDate;
	
	private String shortDescription;
	private String jobDescription;
	private String jobSkills;
	
	private List<String> tags;
	
	private String origin;
	private String originId;
	private String originUrl;
	
}
