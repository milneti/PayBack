package com.example.payback;

import java.util.*;

public class User {
	String fName;
	String lName;
	String email;
	ArrayList<Integer> friends; //populated whenever user inputs an email address to create a transaction with
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
		return false;
	}
	ArrayList<String> friendEmailsLookup()
	{
		//TODO: look up email addresses of friends, given the friends' user ids
		return new ArrayList<String>();
	}
}
