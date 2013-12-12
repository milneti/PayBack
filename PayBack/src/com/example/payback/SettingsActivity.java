package com.example.payback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Intent;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends TitleActivity
{
	static Activity activityInstance;	//these are variables
	static PageKillReceiver pkr;		//used for PageKillReceiver.java
	static IntentFilter filter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Settings",R.layout.activity_settings);
		
		activityInstance = this;
		pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
		filter = new IntentFilter();
		filter.addAction("com.Payback.Logout_Intent");
		registerReceiver(pkr, filter);
	}

	public void showEditPassword(View view) {
		LayoutInflater inflater = this.getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View dialoglayout = inflater.inflate(R.layout.dialog_settings_password, null);
		builder.setTitle("Edit Password")
			   .setView(dialoglayout)
			   .setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   //Toast.makeText(getApplicationContext(),"Back", Toast.LENGTH_SHORT).show();				        	   
		        	   dialog.dismiss();
				   }
			   })
			   .setNegativeButton(R.string.save_changes, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   //Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_SHORT).show();
					   editPassword(dialoglayout);
				   }
			   });
		
		TextView o = (TextView) dialoglayout.findViewById(R.id.oldPassView);
			o.setText("Old Password:");
		TextView n = (TextView) dialoglayout.findViewById(R.id.newPassView);
			n.setText("New Password:");
		TextView c = (TextView) dialoglayout.findViewById(R.id.confirmPassView);
			c.setText("Confirm New Password:");
		
		Dialog dialog = builder.create();
		dialog.show();
	}
	
	public void editPassword(View layout) {
		EditText oldview = (EditText) layout.findViewById(R.id.oldPass);
		String oldPassText = oldview.getText().toString();
		
		EditText newview = (EditText) layout.findViewById(R.id.newPass);
		String newPassText = newview.getText().toString();
		
		EditText confirmview = (EditText) layout.findViewById(R.id.confirmPass);
		String confirmPassText = confirmview.getText().toString();
		
		if(newPassText.length() >= 8) {
			if(newPassText.equals(confirmPassText)) {
				try {
					boolean success = AccessNet.modifyUserPassword(user.getEmail(), oldPassText, newPassText);
					if(success) {
						Toast.makeText(getApplicationContext(), "Password changed!", Toast.LENGTH_SHORT).show();
						user.setPassword(newPassText);
					} else {
						Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_SHORT).show();
					}
				} catch (InterruptedException e) {
					Toast.makeText(getApplicationContext(), "Problem with server connection", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getApplicationContext(), "New password must be at least 8 characters!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void showEditEmail(View view) {
		LayoutInflater inflater = this.getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		final View dialogLayout = inflater.inflate(R.layout.dialog_settings_password, null);
		
		builder.setTitle("Edit Email")
			   .setView(dialogLayout)
			   .setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   //Toast.makeText(getApplicationContext(),"Back", Toast.LENGTH_SHORT).show();				        	   
		        	   dialog.dismiss();
				   }
			   })
			   .setNegativeButton(R.string.save_changes, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   //Toast.makeText(getApplicationContext(), "Email Changed", Toast.LENGTH_SHORT).show();
					   editEmail(dialogLayout);
				   }
			   });
		
		TextView o = (TextView) dialogLayout.findViewById(R.id.oldPassView);
			o.setText("Old Email:");
		TextView n = (TextView) dialogLayout.findViewById(R.id.newPassView);
			n.setText("New Email:");
		TextView c = (TextView) dialogLayout.findViewById(R.id.confirmPassView);
			c.setText("Confirm New Email:");
		
		EditText o2 = (EditText) dialogLayout.findViewById(R.id.oldPass);
			o2.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		EditText n2= (EditText) dialogLayout.findViewById(R.id.newPass);
			n2.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		EditText c2 = (EditText) dialogLayout.findViewById(R.id.confirmPass);
			c2.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		
		Dialog dialog = builder.create();
		dialog.show();
	}
	
	public void editEmail(View layout) {
		EditText oldEmail = (EditText) layout.findViewById(R.id.oldPass);
		String oldEmailText = oldEmail.getText().toString();
		
		EditText newEmail = (EditText) layout.findViewById(R.id.newPass);
		String newEmailText = newEmail.getText().toString();
		
		EditText confirmEmail = (EditText) layout.findViewById(R.id.confirmPass);
		String confirmEmailText = confirmEmail.getText().toString();
		
		if(oldEmailText.equals(user.getEmail())) {
			if(newEmailText.equals(confirmEmailText)) {
				if(verifyEmail(newEmailText)) {
					try {
						boolean success = AccessNet.modifyUserEmail(oldEmailText, user.getPassword(), newEmailText);
						if(success) {
							Toast.makeText(getApplicationContext(), "Email changed!", Toast.LENGTH_SHORT).show();
							user.setEmail(newEmailText);
						} else {
							//this should never happen
							Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
						}
					} catch (InterruptedException e) {
						Toast.makeText(getApplicationContext(), "Error connecting to server", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "Emails don't match!", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getApplicationContext(), "Incorrect email!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public boolean verifyEmail(String email) {
		final String EMAIL_PATTERN = 
				"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
		final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		final Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
	
	public void showEditDisplayName(View view) {
		LayoutInflater inflater = this.getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("Edit Name")
			   .setView(inflater.inflate(R.layout.dialog_settings_name, null))
			   .setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   Toast.makeText(getApplicationContext(),"Back", Toast.LENGTH_SHORT).show();				        	   
		        	   dialog.dismiss();
				   }
			   })
			   .setNegativeButton(R.string.save_changes, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   Toast.makeText(getApplicationContext(), "Name Changed", Toast.LENGTH_SHORT).show();
					   editName();
				   }
			   });
		Dialog dialog = builder.create();
		dialog.show();
	}
	
	public void editName() {
		//server call to edit name
	}
	
	public void showMainMenu(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}
}
