package com.example.payback;

import java.util.*;

public class User {
	String fName;
	String lName;
	String email;
	int userId;
	static int nextIdToUse = 0; //currently it resets every time the program is run; will change with server implementation
	ArrayList<Integer> friends; //populated whenever user inputs an email address to create a transaction with
	User(String fName, String lName, String email)
	{
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		
		//get last used userid from server
		
		userId = nextIdToUse++;
		
		
		//send updated last used userid back to server
	}
	
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
