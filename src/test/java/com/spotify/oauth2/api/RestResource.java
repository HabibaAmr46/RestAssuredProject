package com.spotify.oauth2.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.pojo.PlayList;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource {
	
	
	
	public static Response post(String path, String token ,Object requestPlaylist)

	{
		return given().spec(SpecBuilder.getRequestSpec()).body(requestPlaylist)
				.header("Authorization", "Bearer "+token)
				.when().post(path)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().
				response();
	}
	
	
	public static Response postAccount(HashMap<String, String> formParams)
	{
		return given().spec(SpecBuilder.getAccountRequestSpec())
				.formParams(formParams)
				.when().post(Route.API+Route.TOKEN).then().spec(SpecBuilder.getResponseSpec())
				.extract().response();

	}
	public static Response get(String path, String token)
	{
		return given().spec(SpecBuilder.getRequestSpec())
		.header("Authorization", "Bearer "+token)
		.when().get(path)
		.then().spec(SpecBuilder.getResponseSpec())
		.extract().response();
	}
	
	public static Response update(String path, String token,Object requestPlayList)
	{
		return given().spec(SpecBuilder.getRequestSpec())
		.header("Authorization", "Bearer "+token)
		.body(requestPlayList)
		.when().put(path)
		.then().spec(SpecBuilder.getResponseSpec())
		.extract().response();
	}

}
