package com.spotify.oaith2.Utility;

import java.util.Properties;

public class DataLoader {
	
	private final Properties prop;
	private static DataLoader dataLoader;
	
	private DataLoader()
	{
		prop= PropertyUtils.propertyLoader("src/test/resources/data.properties");
	}
	

	public static DataLoader getInstance()
	{
		if (dataLoader==null)
		{
			dataLoader = new DataLoader();
		}
		
		return dataLoader;
	}
	
	
	public String getProperty(String propertyName)
	{
	
		return prop.getProperty(propertyName);
	}
}
