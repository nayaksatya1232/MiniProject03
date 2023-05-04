package com.satya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/blog")
	public String blogPost() {
		return "blogpost";
	}
}
