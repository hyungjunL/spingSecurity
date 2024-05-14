package com.study.security.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class TestService {
	
	/*
	 * 클라이언트가 로그인을 성공하면 얻느 code값을 이용하여 token을 얻는 메소드 얻어진 code를 /oauth2/token API에 얹어서 다시 인증서버에 요청하여 token, refresh_token 등 데이터를 얻음 
	 */
	public String getToken(String code) {
		
		try {
			log.debug(" ## CODE : {}",code);
			
			HttpClient client = HttpClient.newBuilder().build();
			
			Map<String, String> requestMap = new HashMap<>();
			requestMap.put("grant_type", "authorization_code");
			requestMap.put("redirect_uri", "http://localhost:9000/test");
			requestMap.put("code", code);
			requestMap.put("scope", "openid");

	        // request를 보낼 때, header의 "Authorization"에 인증을 위한 정보를 암호화해서 넣는다. 
	        HttpRequest request = HttpRequest.newBuilder()
	        		.POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&redirect_uri=http://localhost:9000/test&code="+ code +"&scope=openid"))
	                .uri(new URI("http://localhost:9000/oauth2/token"))
	                .header("Authorization", getBasicAuthenticationHeader("test", "1234"))
	                .header("Content-Type", "application/x-www-form-urlencoded")
	                .build();
	
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        log.debug("## token, refresh_token ... {}", response.body());
	        
	        return response.body();
	        
		}catch(Exception e) {
			e.printStackTrace();
			log.error(" ## fail to get token : [{}], [{}] ", e, e.getMessage());
			return "로그인 실패";
		}

	}
	
	private static final String getBasicAuthenticationHeader(String username, String password) {
	    String valueToEncode = username + ":" + password; 
	    // credential 만들기: 이름이랑 비밀번호를 ':'로 연결하면 됨
	    return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
	    // 위에서 만든 credential을 Base64로 인코딩하고, 앞에 'Basic'을 붙이면 끝!
	}
	
}
