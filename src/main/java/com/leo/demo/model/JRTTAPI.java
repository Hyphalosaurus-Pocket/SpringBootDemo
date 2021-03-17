package com.leo.demo.model;

public class JRTTAPI {

	private static JRTTAPI instance = null;
	
	private String accessToken;
	 
	private String refreshToken;
	
	private JRTTAPI() {
		
	}

	public static synchronized JRTTAPI getInstance(){
		
		if (instance == null) {
			instance = new JRTTAPI();
		}
		return instance;
		
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
