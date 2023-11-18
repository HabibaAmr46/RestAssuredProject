package com.spotify.oaith2.Utility;

import java.util.Properties;

public class ConfigLoader {
	
	private final Properties prop;
	private static ConfigLoader configLoader;
	
	private ConfigLoader()
	{
		prop= PropertyUtils.propertyLoader("src/test/resources/config.properties");
	}
	

	public static ConfigLoader getInstance()
	{
		if (configLoader==null)
		{
			configLoader = new ConfigLoader();
		}
		
		return configLoader;
	}
	
	
	public String getProperty(String propertyName)
	{
	
		return prop.getProperty(propertyName);
	}
}
