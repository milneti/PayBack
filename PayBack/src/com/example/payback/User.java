package com.example.payback;

import java.util.*;

public class User {
	private String fName;
	private String lName;
	private String email;
	private int userId;
	private boolean selected;	
	static int nextIdToUse = 0; //currently it resets every time the program is run; will change with server implementation
	ArrayList<Integer> friends; //populated whenever user inputs an email address to create a transaction with

	User(String fName, String lName, String email)
	{
		this.setfName(fName);
		this.setlName(lName);
		this.setEmail(email);
		
		//get last used userid from server
		
		setUserId(nextIdToUse++);
		
		
		//send updated last used userid back to server
	}


	// Getters and Setters

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	// Methods
	static String idToEmail(int id)
	{
		//server stuff
		return "a@b.com";
	}
	static int emailToId(String email)
	{
		//server stuff
		return -1;
	}
}


