package com.satya.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satya.dao.CommentDao;
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
	private CommentDao commentDao;
	@Autowired
	private HttpSession session;

	@Override
	public List<BlogPost> getAllPosts() {
		List<BlogPost> posts = this.postDao.findAllBlogPosts();
		return posts;
	}

	@Override
	public List<BlogPost> getMyPosts() {
		UserEntity user = (UserEntity) session.getAttribute("user");
		List<BlogPost> posts = this.postDao.getPostByUserId(user.getUserId());
		return posts;
	}

	@Override
	public BlogPost getPost(Integer postId) {
		BlogPost post = this.postDao.findById(postId).get();
		return post;
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
	public ServiceMsg updatePost(Integer id, PostDto postData) {
		BlogPost post = this.postDao.findById(id).get();
		post.setTitle(postData.getTitle());
		post.setDescription(postData.getDescription());
		post.setContent(postData.getContent());
		this.postDao.save(post);
		return ServiceMsg.POST_UPDATED;
	}

	@Override
	public List<Comment> getCommentsByUser() {
		UserEntity user = (UserEntity) this.session.getAttribute("user");
		List<BlogPost> posts = this.postDao.getPostByUserId(user.getUserId());
		List<Comment> comments = new ArrayList<Comment>();
		for (BlogPost post : posts) {
			comments.addAll(post.getComments());
		}
		return comments;
	}

	@Override
	public ServiceMsg addComment(Integer pid, Comment data) {
		BlogPost post = this.postDao.findById(pid).get();
		data.setPost(post);
		this.commentDao.save(data);
		return ServiceMsg.COMMENTED;
	}

	@Override
	public ServiceMsg deletePost(Integer id) {
		this.postDao.deleteById(id);
		return ServiceMsg.POST_DELETED;
	}

	@Override
	public void deleteComment(Integer commentId) {
		this.commentDao.deleteById(commentId);
	}

}
