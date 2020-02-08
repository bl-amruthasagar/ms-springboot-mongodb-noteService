package com.ams.noteapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "note")
public class NoteModel {

	public static final String SEQUENCE_NAME = "note_sequence";

	@Id
	private Long id;

	private String title;

	private String description;

	private boolean isArch;
	
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isArch() {
		return isArch;
	}

	public void setArch(boolean isArch) {
		this.isArch = isArch;
	}
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "NoteModel [id=" + id + ", title=" + title + ", description=" + description + ", isArch=" + isArch + "]";
	}

}
