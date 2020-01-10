package com.thida.fcm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;

@Service
public class PushNotificationService {
	private static final String FIREBASE_SERVER_KEY = "AIzaSyClz8XIDzSlsgeL4tJ-e01J41d9TLYx2pM";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity){
		RestTemplate restTemplate = new RestTemplate();
		/**
	    https://fcm.googleapis.com/fcm/sendAIzaSyCjpjJdQDOPsVJGZtcV1MJYoh6AZ4VVEiA
	    Content-Type:application/json
	    Authorization:key=FIREBASE_SERVER_KEY*/
	 
	    ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
	    interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
	    restTemplate.setInterceptors(interceptors);
	 
	    String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
	 
	    return CompletableFuture.completedFuture(firebaseResponse);
	}
	


}
