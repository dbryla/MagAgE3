package org.age.mag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Props {
	private static final Logger log = LoggerFactory.getLogger(Props.class);
	private final static String fileName = "mag.properties";
	private final static HashMap<String, String> props = new HashMap<String, String>();

	private Props() {
	}

	public static void loadProperties() {
		props.clear();
		File propsFile = new File(fileName);
		Path propsPath = propsFile.toPath();
		List<String> readAllLines;
		try {
			readAllLines = Files.readAllLines(propsPath);
			readAllLines.forEach(line -> setProps(line));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private static void setProps(String line) {
		String[] strings = line.split("=");
		props.put(strings[0], strings[1]);
	}
	
	public static String getAgeHome() {
		return props.get("org.age.home");
	}

}
