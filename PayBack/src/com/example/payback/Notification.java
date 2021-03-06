package com.example.payback;

import java.util.Calendar;

public class Notification implements Comparable<Notification>{
	private String fromEmail;
	private String toEmail;
	private String message;
	private String date; 	//new format: "2013-12-12 12:29:56"
							//old format: "Nov 17 2013, 12:19 AM"
	private boolean read;
	private String notid;
	
	Notification (String from, String to, String message, String dateToParse)
	{
		this.fromEmail = from;
		this.toEmail = to;
		this.message = message;
		this.setRead(false);
		//  0123456789012345
		// "2013-01-08 01:53:36"
	}
	
	
	Notification (String from, String to, String message)
	{
		this.fromEmail = from;
		this.toEmail = to;
		this.message = message;
		//this.date = currDateToString();
		this.date = "2013-12-12 12:29:56";
		this.setRead(false);
		
	}
	Notification(){}
	
	//Used to create a chronological order of dates, like the following:
	//"2012-12-12 12:29:56"
	//"2013-12-12 12:29:56"
	//...
	public int compareTo(Notification other) //chronological order
	{
		String d1 = date;
		String d2 = other.date;
		d1 = d1.replace('-', ' ');
		d1 = d1.replace(':', ' ');
		d2 = d2.replace('-', ' ');
		d2 = d2.replace(':', ' ');
		String[] d1s = d1.split(" ");
		String[] d2s = d2.split(" ");
		for(int i = 0; i < d1s.length; i++)
		{
			int x = Integer.parseInt(d1s[i]);
			int y = Integer.parseInt(d2s[i]);
			if(x > y)
				return 1;
			else if(x < y)
				return -1;
		}
		
		return 0; //equivalent dates
	}
	
	/*
	static String parseDateToString(String input)
	{
		StringBuilder sb = new StringBuilder("");
		String month;
		String moInput = input.substring(5,7);
		switch(Integer.parseInt(moInput))
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
		sb.append(month + " ");
		sb.append(Integer.parseInt(input.substring(8,10)) + " ");
		sb.append(input.substring(0,4) + ", ");
		
		boolean am = true;
		int hour = Integer.parseInt(input.substring(11,13));
		if(hour == 12)
		{
			am = false;
		}
		else if(hour > 12)
		{
			am = false;
			hour -= 12;
		}
		else if(hour == 0)
		{
			hour = 12;
		}
		int min = Integer.parseInt(input.substring(14, 16));
		if(am) 
			sb.append(hour + ":" + min + " " + "AM");
		else
			sb.append(hour + ":" + min + " " + "PM");

		
		
		return sb.toString();
	}
	*/
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
	
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String from) {
		this.fromEmail = from;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String to) {
		this.toEmail = to;
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


	public boolean isRead() {
		return read;
	}


	public void setRead(boolean read) {
		this.read = read;
	}


	public String getNotid() {
		return notid;
	}


	public void setNotid(String notid) {
		this.notid = notid;
	}
}
