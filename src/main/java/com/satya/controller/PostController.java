package com.satya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.satya.dto.PostDto;
import com.satya.entity.BlogPost;
import com.satya.entity.Comment;
import com.satya.service.PostService;
import com.satya.service.UserService;
import com.satya.util.ServiceMsg;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;

	@GetMapping("/myposts")
	public String myposts(Model model) {
		boolean logged = userService.checkUser();
		if (logged) {
			List<BlogPost> posts = this.postService.getAllPost();
			model.addAttribute("posts", posts);
		}
		model.addAttribute("logged", logged);
		return "myposts";
	}

	@GetMapping("/newpost")
	public String addpost(Model model) {
		boolean logged = userService.checkUser();
		model.addAttribute("logged", logged);
		model.addAttribute("postData", new PostDto());
		return "addpost";
	}

	@PostMapping("/addpost")
	public String addpostHandler(@ModelAttribute("postData") PostDto postData, Model model) {
		ServiceMsg status = this.postService.addNewPost(postData);
		if (status == ServiceMsg.POST_ADDED) {
			model.addAttribute("success", status.getMsg());
		} else
			model.addAttribute("failed", status.getMsg());
		model.addAttribute("postData", new BlogPost());
		return "addpost";
	}

	@GetMapping("/comments")
	public String comments(Model model) {
		boolean logged = userService.checkUser();
		if (logged) {
			List<Comment> comments = this.postService.getComments();
			model.addAttribute("comments", comments);
		}
		model.addAttribute("logged", logged);
		return "comments";
	}
}
