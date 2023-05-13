package com.satya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.satya.dto.PostDto;
import com.satya.entity.BlogPost;
import com.satya.entity.Comment;
import com.satya.service.PostService;
import com.satya.service.UserService;
import com.satya.util.ServiceMsg;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	
	private static final String LOGGED = "logged";
	private static final String POST = "postData";
	private static final String ADDPOST = "addpost";



	@GetMapping("/myposts")
	public String myposts(Model model) {
		boolean logged = userService.checkUser();
		if (logged) {
			List<BlogPost> posts = this.postService.getMyPosts();
			model.addAttribute("posts", posts);
		}
		model.addAttribute(LOGGED, logged);
		return "myposts";
	}

	@GetMapping("/newpost")
	public String addpost(Model model) {
		boolean logged = userService.checkUser();
		model.addAttribute(LOGGED, logged);
		model.addAttribute("op", "Create Post");
		model.addAttribute(POST, new PostDto());
		return ADDPOST;
	}

	@GetMapping("/editpost/{id}")
	public String editpost(@PathVariable("id") Integer id, Model model) {
		boolean logged = userService.checkUser();
		BlogPost post = new BlogPost();
		if (logged) {
			post = this.postService.getPost(id);
		}
		model.addAttribute(LOGGED, logged);
		model.addAttribute("op", "Edit Post");
		model.addAttribute("postId", id);
		model.addAttribute(POST, post);
		return ADDPOST;
	}

	@PostMapping("/addpost")
	public String addPostHandler(@ModelAttribute("postData") PostDto postData, HttpServletRequest req, Model model) {
		ServiceMsg status = null;
		String id = req.getParameter("postId");
		if (!id.isEmpty()) {
			int postId = Integer.parseInt(id);
			status = this.postService.updatePost(postId, postData);
		} else {
			status = this.postService.addNewPost(postData);
		}

		if (status == ServiceMsg.POST_ADDED || status == ServiceMsg.POST_UPDATED) {
			model.addAttribute("success", status.getMsg());
		} else
			model.addAttribute("failed", status.getMsg());
		model.addAttribute(LOGGED, true);
		model.addAttribute("op", "Edit Post");
		model.addAttribute(POST, new PostDto());
		return ADDPOST;
	}

	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") Integer id) {
		boolean logged = userService.checkUser();
		if (logged)
			this.postService.deletePost(id);
		return "redirect:/myposts";
	}

	@GetMapping("/comments")
	public String comments(Model model) {
		boolean logged = userService.checkUser();
		if (logged) {
			List<Comment> comments = this.postService.getCommentsByUser();
			model.addAttribute("comments", comments);
		}
		model.addAttribute(LOGGED, logged);
		return "comments";
	}

	@PostMapping("/addcomment")
	public String addComment(@ModelAttribute("commentData") Comment data, HttpServletRequest req, Model model) {
		int pid = Integer.parseInt(req.getParameter("pid"));
		this.postService.addComment(pid, data);
		return "redirect:/blog/" + pid;
	}
	@GetMapping("/dltcomment/{id}")
	public String deleteComment(@PathVariable("id")Integer id) {
		this.postService.deleteComment(id);
		return "redirect:/comments";
	}
}
