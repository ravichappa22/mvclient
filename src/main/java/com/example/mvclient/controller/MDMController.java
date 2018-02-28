package com.example.mvclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mvclient.callclient.MDMMediatorClient;

@RestController
public class MDMController {
	
	@Autowired
	private MDMMediatorClient mdmMediatorClient;
	
	@RequestMapping(value="/callmdm", consumes="application/json", produces="application/json")
	public boolean callMDM() {
		return mdmMediatorClient.callMDMInParallel();
	}

}
