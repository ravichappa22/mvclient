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
		String fileName = "/Users/rchappa1/Documents/Claims-Modernization/PocWorld/mvclient/src/main/resources/MDMMATCHMOCK";
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			
			while ((line = br.readLine()) != null) {
				String finalString=null;
				finalString = line.replace("ReplceClainTrackingId", newTrackingId);
				//finalString = line;
				System.out.println("current Thread= "+Thread.currentThread() + finalString);
				mdmFeignInterface.callMDM("bearer 8e790074-1181-4f2e-96c3-7c4ce7a8886b",finalString);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
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
