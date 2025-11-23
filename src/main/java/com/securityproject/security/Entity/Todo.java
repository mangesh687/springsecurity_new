package com.securityproject.security.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String decription;
	@Column(nullable = false)
	private int priority;
	@Column(nullable = false)
	private boolean completed;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id", nullable=false)
	private User owner;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Todo(long id, String title, String decription, int priority, boolean completed, User owner) {
		super();
		this.id = id;
		this.title = title;
		this.decription = decription;
		this.priority = priority;
		this.completed = completed;
		this.owner = owner;
	}
	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
