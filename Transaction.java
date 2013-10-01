package com.example.payback; //This may change

public class Transaction
{
	int lenderID, borrowerID, amount, dt, transactionID;
	String message;
	
	public Transaction(int lenID, int borID, int loanAmount, String comment, int date, int tranID)
	{
		int lenderID = lenID;
		int borrowerID = borID;
		int amount = loanAmount;
		int dt = date;
		String message = comment;
		int transactionID = tranID;
	}
	
	public int getLenderID()
	{
		return this.lenderID;
	}
	public void setLenderID(int lid)
	{
		this.lenderID = lid;
	}
	
	public int getBorrowerID()
	{
		return this.borrowerID;
	}
	public void setBorrowerID(int bid)
	{
		this.borrowerID = bid;
	}
	
	public int getTransactionID()
	{
		return this.transactionID;
	}
	public void setTransactionID(int tid)
	{
		this.transactionID = tid;
	}
	
	public int getDate()
	{
		return this.dt;
	}
	public void setDate(int d)
	{
		this.dt = d;
	}
	
	public int getLoanAmount()
	{
		return this.amount;
	}
	public void setLoanAmount(int la)
	{
		this.amount = la;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	public void setMessage(String m)
	{
		this.message = m;
	}

}
