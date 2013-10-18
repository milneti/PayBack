package com.example.payback;

import java.util.*;
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
	
	/* Only called when creating a brand new account! */
	User(String fName, String lName, String email) 
	{
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		friends = new ArrayList<Friend>();
		boolean worked = sendNewUserToServer();
		if(!worked)
			throw new IllegalArgumentException();
	}
	static private boolean sendNewUserToServer()
	{
		//TODO: send email, fname, lname, pword to database
		//return success/fail
		return true;
	}
	
	/* Used for the rest of the time, when the user logs in */
	User(String email)
	{
		this.email = email;
		fName = firstNameLookup(email);
		lName = lastNameLookup(email);
		friends = friendsLookup(email);
	}
	private String firstNameLookup(String email)
	{
		//TODO: Pull user's first name from server
		return "John";
	}
	private String lastNameLookup(String email)
	{
		//TODO: Pull user's last name from server
		return "Doe";
	}
	private ArrayList<Friend> friendsLookup(String email) // Used for existing users
	{
		ArrayList<Friend> f = new ArrayList<Friend>();
		//TODO: Pull information about each friend from the server: first name, last name, email. 
		return f;
	}
}

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