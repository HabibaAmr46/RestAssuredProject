package com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

import org.apache.groovy.parser.antlr4.GroovyParser.ExpressionInParContext;

import com.spotify.oaith2.Utility.ConfigLoader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {

	private static String access_token;
	private static Instant expiry_time;

	public static String getToken()
	{
		try {
			
			if(access_token==null || Instant.now().isAfter(expiry_time)) {
				
				System.out.println("Renewing Tokennn");
				Response reponse=renewToken();
				access_token=reponse.jsonPath().getString("access_token");
				int expiryDurationInSec=reponse.jsonPath().getInt("expires_in");
				expiry_time=Instant.now().plusSeconds(expiryDurationInSec - 300);
			}
			else
			{
				System.out.println("Token is good to use");
			}
		
		
		}catch(Exception e)
		{
			throw new RuntimeException("Faileeddd to get the tokenn");
		}
		
		return access_token;
	}

	public static Response renewToken() {
		HashMap<String, String> formParams = new HashMap<String, String>();
		formParams.put("grant_type", ConfigLoader.getInstance().getProperty("grant_type"));
		formParams.put("refresh_token",ConfigLoader.getInstance().getProperty("refresh_token"));
		formParams.put("client_id", ConfigLoader.getInstance().getProperty("client_id"));
		formParams.put("client_secret", ConfigLoader.getInstance().getProperty("client_secret"));

		Response response = RestResource.postAccount(formParams);
		if (response.getStatusCode() != 200) {
			throw new RuntimeException("ABORTTT!!! Renew Token Failed");
		}

		return response;
	}

}
