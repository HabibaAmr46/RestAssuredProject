package com.spotify.oaith2.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class PropertyUtils {
	
	
	public static Properties propertyLoader(String filePath)
	{
		Properties prop= new Properties();
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			try {
				prop.load(reader);
				reader.close();
			}catch(IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load the properties file");
			}
		}catch(FileNotFoundException e)
		
		{
			e.printStackTrace();
			throw new RuntimeException("Properties file not found at: "+filePath);
		}
		
		return prop;
	}


}
