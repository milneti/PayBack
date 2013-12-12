package com.example.payback;

import java.util.*;

public class Transaction {

	String lenderEmail, borrowerEmail;
	double amount;
	String comment;
	
	Transaction() {
		this.lenderEmail = "";
		this.borrowerEmail = "";
		this.amount = 0;
		this.comment = "";
	}
	Transaction(String lenderEmail, String borrowerEmail, double amount, String comment)
	{
		this.lenderEmail = lenderEmail;
		this.borrowerEmail = borrowerEmail;
		this.amount = amount;
		this.comment = comment;
	}
	public String getLenderEmail() {
		return lenderEmail;
	}
	public void setLenderEmail(String lenderEmail) {
		this.lenderEmail = lenderEmail;
	}
	public String getBorrowerEmail() {
		return borrowerEmail;
	}
	public void setBorrowerEmail(String borrowerEmail) {
		this.borrowerEmail = borrowerEmail;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
}
