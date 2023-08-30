package com.assignment.BDIPLUSAssignment.Task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {

	@Id
	@GeneratedValue
	private int id;

	private String username;

	@Size(min = 10, message = "Enter atleast 10 characters")
	private String description;


	public Todo() {

	}

	public Todo(int id, String userame, String description) {
		super();
		this.id = id;
		this.username = userame;
		this.description = description;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserame() {
		return username;
	}

	public void setUserame(String userame) {
		this.username = userame;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "Todo [id=" + id + ", userame=" + username + ", description=" + description + "]";
		}

}

