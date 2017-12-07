package com.qzn.log;

import org.apache.logging.log4j.Level;

public class LogLevel {

	// OFF: 0
	// FATAL: 100
	// ERROR: 200
	// WARN: 300
	// INFO: 400
	// DEBUG: 500
	// TRACE: 600

	public static final Level AUDIT = Level.forName("AUDIT", 50);

	public static final Level TRACK = Level.forName("TRACK", 60);

}
