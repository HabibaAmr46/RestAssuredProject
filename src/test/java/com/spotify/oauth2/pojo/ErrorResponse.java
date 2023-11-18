package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ErrorResponse {
	
	

	
	@JsonProperty("error")
	private InnerError error;

	@JsonProperty("error")
	public InnerError getError() {
	return error;
	}

	@JsonProperty("error")
	public void setError(InnerError error) {
	this.error = error;
	}




}
