package com.leo.demo.service;

import com.leo.demo.model.JRTTAPI;

public class APIFactory {
	
	private static APIFactory instance = null;
	
	private APIFactory() {
		
	}
	
	public static synchronized APIFactory create(){
		
		if (instance == null) {
			instance = new APIFactory();
		}
		return instance;
		
	}
	
	public JRTTAPI createJRTTAPI() {
		return JRTTAPI.getInstance();
	}
	
	/**
	 * 其他api的拓展在工厂模式中创建
	 */
	/*public OrderAPI createAPI() {
		return OrderAPI.getInstance();
	}*/
	
}
