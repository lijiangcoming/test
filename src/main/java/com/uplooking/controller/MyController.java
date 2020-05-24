package com.uplooking.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {

	@GetMapping(value = "/my/hello")
	public String index(HttpServletRequest request) {
		System.out.println("hello Spring-boot");
		request.setAttribute("hello", "大家好");
		Random random = new Random();
		request.setAttribute("op", random.nextInt(2));
		return "my/hello";
	}
	
	@GetMapping(value = "/my/login")
	public String login() {
		return "my/login";
		
	}
	@PostMapping(value = "/my/login")
	public String loginform(String name,String pwd) {
		System.out.println(name+"-"+pwd);
		return "my/login";
	}
	
	
}
