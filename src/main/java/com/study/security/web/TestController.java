package com.study.security.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.security.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	
	private final TestService testService;
	
	@RequestMapping(value = {"/test"}, method = RequestMethod.GET)
	public String fortemCmd(@RequestParam (value = "code", required = false) String code) {
		return testService.getToken(code);
	}
}