package com.satya.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satya.dao.PostDao;
import com.satya.dto.PostDto;
import com.satya.entity.BlogPost;
import com.satya.entity.Comment;
import com.satya.entity.UserEntity;
import com.satya.util.ServiceMsg;

import jakarta.servlet.http.HttpSession;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDao postDao;
	@Autowired
	private HttpSession session;

	@Override
	public List<BlogPost> getAllPost() {
		UserEntity user = (UserEntity) this.session.getAttribute("user");
		List<BlogPost> posts = this.postDao.getPostByUserId(user.getUserId());
		return posts;
	}

	@Override
	public ServiceMsg addNewPost(PostDto postData) {
		if (postData.getTitle().isBlank() || postData.getDescription().isBlank() || postData.getContent().isBlank()) {
			return ServiceMsg.EMPTY_FIELD;
		}
		BlogPost post = new BlogPost();
		BeanUtils.copyProperties(postData, post);
		UserEntity u = (UserEntity) this.session.getAttribute("user");
		post.setUser(u);

		this.postDao.save(post);

		return ServiceMsg.POST_ADDED;
	}

	@Override
	public List<Comment> getComments() {
		UserEntity user = (UserEntity) this.session.getAttribute("user");
		List<BlogPost> posts= this.postDao.getPostByUserId(user.getUserId());
		List<Comment> comments = new ArrayList<Comment>();
		for(BlogPost post:posts) {
			comments.addAll(post.getComments());
		}
		return comments;
	}

}
