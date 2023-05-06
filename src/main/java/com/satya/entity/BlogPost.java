package com.satya.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	private String title;
	private String description;
	@Lob
	@Column(columnDefinition = "longtext")
	private String content;
	@CreationTimestamp
	private LocalDate creationDate;
	@UpdateTimestamp
	private LocalDate updateDate;
	@ManyToOne
	private UserEntity user;
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
	private List<Comment> comments;
}
