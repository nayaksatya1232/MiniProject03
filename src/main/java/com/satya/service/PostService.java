package com.satya.service;

import java.util.List;

import com.satya.dto.PostDto;
import com.satya.entity.BlogPost;
import com.satya.entity.Comment;
import com.satya.util.ServiceMsg;

public interface PostService {
	public List<BlogPost> getAllPost();

	public ServiceMsg addNewPost(PostDto postData);

	public List<Comment> getComments();
}
