package com.example.payback;

import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
abstract class Account
{
	String fName;
	String lName;
	String email;
	String displayName;
	
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}

public class User extends Account{
	private ArrayList<Friend> friends; //updated when the User logs in
	private ArrayList<Notification> notifications;
	private ArrayList<ResolveTransaction> transactions;

	private String password;
	/* Only called when creating a brand new account! */
	User(String fName, String lName, String email) 
	{
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.friends = new ArrayList<Friend>();
		this.notifications = new ArrayList<Notification>();
		this.transactions = new ArrayList<ResolveTransaction>();
		boolean worked = sendNewUserToServer();
		if(!worked)
			throw new IllegalArgumentException("Error creating a new account.");
	}
	static private boolean sendNewUserToServer()
	{
		//TODO: send email, fname, lname, pword to database
		//return success/fail
		return true;
	}
	
	/* Used for the rest of the time, when the user logs in */
	User(String email, String password)
	{
		this.email = email;
		this.password = password;
		this.updateName(email);
		this.friends = Friend.updateFriends(email);
		this.notifications = Notification.updateNotifications(email);
		this.transactions = ResolveTransaction.updateTransactions(email);
	}
	public ArrayList<Friend> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<Friend> friends) {
		this.friends = friends;
	}
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}
	public ArrayList<ResolveTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(ArrayList<ResolveTransaction> transactions) {
		this.transactions = transactions;
	}
	
	public void updateName(String email){
		this.fName ="john";
		this.lName ="doe";
	}
	public String getPassword(){
		return password;
	}
	
	public void setnoneselected(){
		for(int i = 0; i < friends.size(); i++){
			friends.get(i).setSelected(false);
		}
	}	
		
}
class Friend extends Account implements Parcelable {
	boolean selected;
	int amounttosel;
	
	//default constructor
	Friend(){
		this.fName = null;
		this.lName = null;
		this.email = null;
		this.selected = false;
		this.amounttosel = 0;
	}
	
	Friend(String fName, String lName, String email){
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.displayName = fName + " " + lName;
		this.selected = false;
		
		if(!sendNewFriendToServer())
			throw new IllegalArgumentException();		
	}
	
	Friend(String fName, String lName, String email, String displayName){
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.displayName = displayName;
		this.selected = false;
		
		if(!sendNewFriendToServer())
			throw new IllegalArgumentException();		
	}
	
	//blank friend. FOR TESTING ONLY
	Friend(String fName, String lName){
		this.fName = fName;
		this.lName = lName;
		this.email = "dummydata@youdidntremoveme.yet";
		this.selected = false;
		this.amounttosel = 0;
		
		if(!sendNewFriendToServer())
			throw new IllegalArgumentException();
	}
	
	//Getters and Setters
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public int getamounttosel() {
	    return amounttosel;
	}

	public void setamounttosel(int amounttosel) {
		this.amounttosel = amounttosel;
	}
	
	static ArrayList<Friend> updateFriends(String email) // Used for existing users
	{
	    Friend test1 = new Friend("Price", "Gutierrez");
	    Friend test2 = new Friend("Vanna", "Mccullough");
	    Friend test3 = new Friend("Wyatt", "Paul");
	    Friend test4 = new Friend("Thaddeus", "Robbins");
	    Friend test5 = new Friend("Rooney", "Dejesus");
	    Friend test6 = new Friend("Xavier", "Wolfe");
	    Friend test7 = new Friend("Byron", "Raymond");
	    Friend test8 = new Friend("Quinn", "Whitfield");
	    Friend test9 = new Friend("Farrah", "Moon");
	    Friend test10 = new Friend("Ainsley", "Whitehead");
	    Friend test11 = new Friend("Josephine", "Patton");
	    Friend test12 = new Friend("Mariko", "Patton");
	    Friend test13 = new Friend("Raphael", "Fitzgerald");
	    Friend test14 = new Friend("Deacon", "Daniels");
	    Friend test15 = new Friend("Delilah", "Fletcher");
	    Friend test16 = new Friend("Robin", "Andrews");
	    Friend test17 = new Friend("Melvin", "Price");
		
		ArrayList<Friend> f = new ArrayList<Friend>();
		
		f.add(test1);
		f.add(test2);
		f.add(test3);
		f.add(test4);
		f.add(test5);
		f.add(test6);
		f.add(test7);
		f.add(test8);
		f.add(test9);
		f.add(test10);
		f.add(test11);
		f.add(test12);
		f.add(test13);
		f.add(test14);
		f.add(test15);
		f.add(test16);
		f.add(test17);

		//TODO: Pull information about each friend from the server: first name, last name, email. 
		return f;
	}
	  
	//Methods
	static boolean sendNewFriendToServer()
	{
		//TODO: send email, fname, lname, pword to database
		//return success/fail
		return true;
	}

	public String extractEmail(String friend){
		int startID = friend.indexOf("(");
		int endID = friend.indexOf(")");
		return friend.substring(startID+1, endID);
	}
	
	public String toString() {
		return  getfName() + " " + getlName() + " (" + getEmail() + ")";
	}

	public String getDisplayName(){
		return displayName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(fName);
		dest.writeString(lName);
		dest.writeString(email);
	
	}
	
	public Friend(Parcel source){
		 this.fName = source.readString();
		 this.lName = source.readString();
		 this.email = source.readString();
	}
	
    public static final Parcelable.Creator<Friend> CREATOR = new Parcelable.Creator<Friend>() {
    	 
        @Override
        public Friend createFromParcel(Parcel source) {
            return new Friend(source);
        }
 
        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
}