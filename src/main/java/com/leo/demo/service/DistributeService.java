package com.leo.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cizito.habee.core.HttpAnswer;
import com.cizito.habee.core.ProxyIP;
import com.cizito.habee.http.downloader.Downloader;
import com.cizito.habee.http.downloader.PostMethodDownloader;
import com.leo.demo.model.JRTTAPI;

import net.sf.json.JSONObject;

@Component
public class DistributeService {

	private final static String appId = "1671832616695815";
	
	private final static String secret = "351775628cf0a30319feb1a15c5e3ead0e7aaae4";
	
//	private Singleton singleton = Singleton.getInstance();
	
	private JRTTAPI api = APIFactory.create().createJRTTAPI();
	
	@Scheduled(cron = "0 0/1 16 * * ? ")
	public void task1() {
		
		refreshToken();
		System.out.println("access-token:" + api.getAccessToken());
		System.out.println("refresh-token:" + api.getRefreshToken());
		System.out.println("==========================================");
		
	}
	
	private void refreshToken() {
		
		ProxyIP proxyIP = null;
		String url = "https://ad.oceanengine.com/open_api/oauth2/refresh_token/";
		Downloader downloader = new PostMethodDownloader(proxyIP, "utf-8");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("app_id", appId);
		param.put("secret", secret);
		param.put("grant_type", "refresh_token");
		param.put("refresh_token", api.getRefreshToken() == null ? "7207f236e27c0718c57708d1c28d97c86eb4cc86" : api.getRefreshToken());
		downloader.setParam(param);
		
		HttpAnswer httpAnswer = null;
		try {
			httpAnswer = downloader.download(url, null);
			
			String content = (String) httpAnswer.getPollen().getEntity();
			JSONObject jo = JSONObject.fromObject(content);
			String accessToken = jo.getJSONObject("data").getString("access_token");
			String refreshToken = jo.getJSONObject("data").getString("refresh_token");
			api.setAccessToken(accessToken);
			api.setRefreshToken(refreshToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
