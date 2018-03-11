package com.example.mvclient.callclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="myrestfeign", url="http://non-secure-fox-api-tst.ose-elr-core.optum.com/")
public interface MDMFeignInterface {
	
	@RequestMapping(value="api/member/claimmemberlookup", method = RequestMethod.POST, consumes="application/json")	
	public void callMDM(@RequestHeader("Authorization") String authorization, @RequestBody String body);
}
