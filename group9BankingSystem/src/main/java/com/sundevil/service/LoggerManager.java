package com.sundevil.service;

import java.io.File;
import java.io.FileFilter;

import org.springframework.stereotype.Service;

/**
 * 
 */

/**
 * @author satyaswaroop
 *
 */
@Service
public class LoggerManager implements ILoggerManager {
	
	/* (non-Javadoc)
	 * @see ILoggerManager#getLatestLogs()
	 */
	@Override
	public File getLatestLogs()
	{
		return lastFileModified(System.getProperty("catalina.base")+"/applogs/bank9/");
	}
	private File lastFileModified(String dir) {
		File fl = new File(dir);
		File[] files = fl.listFiles(new FileFilter() {			
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		long lastMod = Long.MIN_VALUE;
		File choise = null;
		for (File file : files) {
			if (file.lastModified() > lastMod) {
				choise = file;
				lastMod = file.lastModified();
			}
		}
		return choise;
	}

}
