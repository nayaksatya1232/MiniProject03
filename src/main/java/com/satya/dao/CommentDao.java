package com.satya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>{

}
