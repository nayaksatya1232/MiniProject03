package com.satya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.satya.entity.BlogPost;
import com.satya.service.PostService;

@Controller
public class HomeController {
	@Autowired
	private PostService postService;

	@GetMapping("/")
	public String home(Model model) {
		List<BlogPost> posts = this.postService.getAllPost();
		model.addAttribute("posts",posts);
		return "home";
	}

	@GetMapping("/blog")
	public String blogPost() {
		return "blogpost";
	}
}
