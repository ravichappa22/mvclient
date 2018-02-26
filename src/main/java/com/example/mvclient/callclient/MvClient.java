package com.example.mvclient.callclient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MvClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private FeignInterface feignInterface;
	
	public boolean sendClaimsToMV(String newTrackingId) {
		//File file = new File("classpath:input");
		String fileName = "/Users/rchappa1/Documents/Claims-Modernization/PocWorld/mvclient/src/main/resources/MemberMatch";
		String finalString=null;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			
			while ((line = br.readLine()) != null) {
				finalString = line.replace("ReplceClainTrackingId", newTrackingId);
				//finalString = line;
				System.out.println("current Thread= "+Thread.currentThread() + finalString);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		feignInterface.callMvLocal("bearer c36e9f96-6652-4258-b93f-8044ec53cc1a",finalString);
		
		return true;
	}
	
	
	class CallMvInParallel implements Runnable{

		@Override
		public void run() {
			sendClaimsToMV(UUID.randomUUID().toString());
			
		}
		
	}
	
	public boolean callMvInParallel() {
		for(int i=0; i<50;i++) {
		Thread thread = new Thread(new CallMvInParallel());
		thread.start();
			//sendClaimsToMV(UUID.randomUUID().toString());
		}
		return true;
	}

}
