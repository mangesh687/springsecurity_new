package com.securityproject.security.Entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
@Entity
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private long id;
	@Column(nullable = false)
	private String firstname;
	@Column(nullable = false)
	private String lastname;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@CreatedBy
	@Column(updatable=false, name = "created_at" )
	private Date createdAt;
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updateAt;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="user_authorities", joinColumns = @JoinColumn(name="user_id") )
	private List<Authority> authorities;
	@OneToMany(mappedBy = "owner",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Todo> todo;



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id, String firstname, String lastname, String email, String password, List<Authority> authorities,
			List<Todo> todo) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.todo = todo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Todo> getTodo() {
		return todo;
	}

	public void setTodo(List<Todo> todo) {
		this.todo = todo;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

}
