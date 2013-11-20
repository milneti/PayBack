//CREDIT TO Will Macfarlane for the template used in Notification.
package com.example.payback;

import java.util.ArrayList;
import java.util.Calendar;

public class ResolveTransaction {
	private String amount;
	private String message;
	private String date; //format: "Nov 17 2013, 12:19 AM"
	
	
	ResolveTransaction (String amount, String message)
	{
		this.amount = amount;
		this.message = message;
		this.date = currDateToString();
	}
	ResolveTransaction(){}
	
	static String currDateToString()
	{
		Calendar c = Calendar.getInstance();
		String min = c.get(Calendar.MINUTE) + "";
		if(min.length() == 1) //changes 3 into 03
			min = "0" + min;
		int hour = c.get(Calendar.HOUR);
		if (hour == 0)
			hour = 12;
		String am_pm = (c.get(Calendar.AM_PM) == 0) ? "AM" : "PM";
		String month;
		switch(c.get(Calendar.MONTH))
		{
			case 0: month = "Jan"; break;
			case 1: month = "Feb"; break;
			case 2: month = "Mar"; break;
			case 3: month = "Apr"; break;
			case 4: month = "May"; break;
			case 5: month = "Jun"; break;
			case 6: month = "Jul"; break;
			case 7: month = "Aug"; break;
			case 8: month = "Sep"; break;
			case 9: month = "Oct"; break;
			case 10: month = "Nov"; break;
			case 11: month = "Dec"; break;
			default: throw new IllegalArgumentException("Month broken");
		}
		String s = (month + " " + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.YEAR) + ", " + hour + ":" + min + " " +  am_pm);
		//Should return a string that looks like "Nov 17 2013, 12:19 AM"
		return s;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	//TODO:
	public void sendToServer() //send this transaction to server. Server needs to grab the "to" field and send it to them
	{
		
	}
	//TODO:
	public static ArrayList<ResolveTransaction> updateTransactions(String email)
	{
		ArrayList<ResolveTransaction> trans = new ArrayList<ResolveTransaction> ();
		//populates this user's notifications arraylist
		return trans;
		
	}

}