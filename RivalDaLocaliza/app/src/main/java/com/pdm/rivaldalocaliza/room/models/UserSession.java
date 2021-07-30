package com.pdm.rivaldalocaliza.room.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(tableName = "user_session")
public class UserSession {
	@PrimaryKey(autoGenerate = true)
	private Long id;
	private String token;
	private String refresh_token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
}
