package com.example.mvclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mvclient.callclient.MvClient;

@RestController
public class MVController {
	
	@Autowired
	private MvClient mvClient;
	
	@RequestMapping(value="/callmv", consumes="application/json", produces="application/json")
	public boolean sendCallsToMv() {
		
		return mvClient.callMvInParallel();
	}

}
