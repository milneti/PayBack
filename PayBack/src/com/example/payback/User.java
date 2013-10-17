package com.example.payback;

import java.util.*;
<<<<<<< HEAD

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
=======
abstract class Account 
{
	String fName;
	String lName;
	String email;
	
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
}
public class User extends Account{
	private ArrayList<Friend> friends; //updated when the User logs in
	User(String fName, String lName, String email)
	{
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		boolean worked = sendNewUserToServer();
		if(!worked)
			throw new IllegalArgumentException();
	}
	static boolean sendNewUserToServer()
>>>>>>> master
	{
		//TODO: send email, fname, lname, pword to database
		//return success/fail
		return true;
	}
	ArrayList<String> friendEmailsLookup()
	{
		//TODO: look up email addresses of friends, given the friends' user ids
		return new ArrayList<String>();
	}
}

<<<<<<< HEAD
<<<<<<< HEAD

=======
class Friend extends Account {}
>>>>>>> master
=======
class Friend extends Account {
	boolean selected;
	
	Friend(String fName, String lName, String email)
	{
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.selected = false;
		boolean worked = sendNewFriendToServer();
		if(!worked)
			throw new IllegalArgumentException();
		
	}
	
	//Getters and Setters
	  public boolean isSelected() {
		    return selected;
		  }

		  public void setSelected(boolean selected) {
		    this.selected = selected;
		  }
	
	//Methods
	static boolean sendNewFriendToServer()
	{
		//TODO: send email, fname, lname, pword to database
		//return success/fail
		return true;
	}
	
	
	
}
>>>>>>> master
