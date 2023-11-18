package com.spotify.oauth2.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oaith2.Utility.DataLoader;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.PlayListApi.PlayListAPI;
import com.spotify.oauth2.pojo.ErrorResponse;
import com.spotify.oauth2.pojo.PlayList;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class PlayListTests {
	
	@Step("Creating a playlist Body")
	public PlayList playListBuilder(String name , String description , boolean Public)
	{
		return new PlayList().setName(name).
				setDescription(description)
				.setPublic(Public);
	}

	@Test(description="User should be able to create a PlayList")
	public void ShouldBeAbleToCreateAPlayList()
	{
			
		PlayList requestplayList=playListBuilder("New Playlist", "New playlist description", false);
		
		Response response=PlayListAPI.post(requestplayList);
		Assert.assertEquals(response.getStatusCode(), 201);
		
		PlayList responsePlaylist=response.as(PlayList.class);
		
		
		Assert.assertEquals(responsePlaylist.getName(), "New Playlist");
		Assert.assertFalse(responsePlaylist.getPublic());
		
		 
	}


	@Test(description="User should be able to get a PlayList")
	public void ShouldBeAbleToGetAPlayList()
	{
		
		Response response= PlayListAPI.get(DataLoader.getInstance().getProperty("get_playlist_id"));
		
		Assert.assertEquals(response.getStatusCode(), 200);
		PlayList responsePlayList=response.as(PlayList.class);
		Assert.assertEquals(responsePlayList.getName(), "Updated Playlist");
		
		
	}



	@Test(description="User should be able to update a PlayList")
	public void ShouldBeAbleToUpdatePlayList()
	{
		
		PlayList updateplayList=playListBuilder("Updated Playlist", "Updated playlist description", false);
	
		
		Response response = PlayListAPI.update(DataLoader.getInstance().getProperty("update_playlist_id"),updateplayList);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(description="User shouldn't be able to create a playlist without name")
	public void ShouldNotBeAbleToCreateAPlayListWithoutName()
	{
		
		
	
		
		PlayList requestplayList=new PlayList().setName("")
				.setDescription("New playlist description")
				.setPublic(false);
		
		
		Response response=PlayListAPI.post(requestplayList);
		ErrorResponse errorResponse=response.as(ErrorResponse.class);
		
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(errorResponse.getError().getMessage(),"Missing required field: name" );
		
		
		
	}
	
	@Test(description="User shouldn't be able to create a playlist with expired token")
	public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken()
	{
			
		
		PlayList requestplayList=new PlayList().setName("New Playlist").
				setDescription("New playlist description")
				.setPublic(false);
		
		
		Response response=PlayListAPI.post("12345", requestplayList);
		ErrorResponse errorResponse=response.as(ErrorResponse.class);
		
		Assert.assertEquals(response.getStatusCode(), 401);
		Assert.assertEquals(errorResponse.getError().getMessage(),"Invalid access token" );
		
		
		
	}

	
	
	
}

