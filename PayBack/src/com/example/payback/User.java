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

class Friend extends Account {}