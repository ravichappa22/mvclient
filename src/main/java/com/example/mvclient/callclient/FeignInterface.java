package com.example.mvclient.callclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="myrestfeignmv", url="http://localhost:8093/")
public interface FeignInterface {
	
	@RequestMapping(value="api/membervalidation/claim/", method = RequestMethod.POST, consumes="application/xml")
	public String callMvLocal(@RequestHeader("Authorization") String authorization, @RequestBody String body);
}