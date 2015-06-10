package org.brunocunha.jobstats.crawler;

import java.io.IOException;
import java.util.List;

import org.brunocunha.jobstats.model.Position;


public interface IJobSeeker {

	String getSeekerName();
	List<Position> fetch() throws IOException;
	List<Position> fetch(String keyword) throws IOException;
	List<Position> fetch(String keyword, int maximum) throws IOException;
	
}
