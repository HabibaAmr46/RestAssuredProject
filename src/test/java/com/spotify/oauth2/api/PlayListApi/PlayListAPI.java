package com.spotify.oauth2.api.PlayListApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.spotify.oaith2.Utility.ConfigLoader;
import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Route;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.PlayList;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlayListAPI {
	
	@Step("Post request is created including the request PlayList body")
	public static Response post(PlayList requestPlaylist)

	{
		return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().getProperty("user_id")+Route.PLAYISTS, TokenManager.getToken(), requestPlaylist);
		
	}
	
	public static Response post(String token,PlayList requestPlaylist)

	{
		return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().getProperty("user_id")+Route.PLAYISTS, token, requestPlaylist);
		
	}
	
	
	public static Response get(String playListID)
	{
		return RestResource.get(Route.PLAYISTS+"/"+playListID, TokenManager.getToken());
		
	}
	
	public static Response update(String playListID,PlayList requestPlayList)
	{
		
		return RestResource.update(Route.PLAYISTS+"/"+playListID, TokenManager.getToken(), requestPlayList);
		
	}

}
