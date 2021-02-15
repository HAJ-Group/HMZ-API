package com.hmz.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfigLoader implements ConfigLoader {
	
	public static final String DEFAULT_TARGET_FILE = "application.properties";
	private final String target_file;
	private final Properties properties;
	
	public PropertiesConfigLoader() {
		this(DEFAULT_TARGET_FILE);
	}
	
	public PropertiesConfigLoader(String target_file) {
		this.properties = new Properties();
		this.target_file = target_file;
		load();
	}
	
	private void load() {
		InputStream in = getClass().getClassLoader().getResourceAsStream(target_file);
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getProperty(String name) {
		return properties.getProperty(name).toLowerCase();
	}

}
