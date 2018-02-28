package com.example.mvclient.callclient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MDMMediatorClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MDMFeignInterface mdmFeignInterface;
	
	public boolean sendClaimsToMDM(String newTrackingId) {
		//File file = new File("classpath:input");
		String fileName = "/Users/rchappa1/Documents/Claims-Modernization/PocWorld/mvclient/src/main/resources/MDM";
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
	
		mdmFeignInterface.callMDM("bearer 5041f029-a58a-4a4f-9d72-02358b5c96f2",finalString);
		
		return true;
	}
	
	
	class CallMvInParallel implements Runnable{

		@Override
		public void run() {
			sendClaimsToMDM(UUID.randomUUID().toString());
			
		}
		
	}
	
	public boolean callMDMInParallel() {
		for(int i=0; i<50;i++) {
		Thread thread = new Thread(new CallMvInParallel());
		thread.start();
			//sendClaimsToMV(UUID.randomUUID().toString());
		}
		return true;
	}

}
