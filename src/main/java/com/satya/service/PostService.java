package com.satya.service;

import java.util.List;

import com.satya.dto.PostDto;
import com.satya.entity.BlogPost;
import com.satya.entity.Comment;
import com.satya.util.ServiceMsg;

public interface PostService {
	public List<BlogPost> getAllPosts();

	public List<BlogPost> getMyPosts();

	public BlogPost getPost(Integer postId);

	public ServiceMsg addNewPost(PostDto postData);
	
	public ServiceMsg updatePost(Integer id,PostDto postData);
	
	public ServiceMsg deletePost(Integer id);

	public ServiceMsg addComment(Integer pid,Comment data);
	
	public void deleteComment(Integer commentId);

	public List<Comment> getCommentsByUser();
}
