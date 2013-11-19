package com.example.payback;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends TitleActivity {

	  private ListView contactlistview;	  
	  private ArrayList<String> friendList;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Contact List",R.layout.activity_contact);

		contactlistview = (ListView) findViewById(R.id.listofselected);
		friendList = buildFriendList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_contact_iteminlist, friendList);
		contactlistview.setAdapter(adapter);
		registerForContextMenu(contactlistview);
		/*
		contactlistview.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parentList, View view,
					int position, long rowId) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		  */  
	}

	private ArrayList<String> buildFriendList() {
	    ArrayList<String> list = new ArrayList<String>();
	    
	    //dummy friends
	    Friend test1 = new Friend("Price", "Gutierrez","test@yahoo.com");
	    Friend test2 = new Friend("Vanna", "Mccullough");
	    Friend test3 = new Friend("Wyatt", "Paul");
	    Friend test4 = new Friend("Thaddeus", "Robbins");
	    Friend test5 = new Friend("Rooney", "Dejesus");
	    Friend test6 = new Friend("Xavier", "Wolfe");
	    Friend test7 = new Friend("Byron", "Raymond");
	    Friend test8 = new Friend("Quinn", "Whitfield","test2@yahoo.com");
	    Friend test9 = new Friend("Farrah", "Moon");
	    Friend test10 = new Friend("Ainsley", "Whitehead");
	    Friend test11 = new Friend("Josephine", "Patton");
	    Friend test12 = new Friend("Mariko", "Patton");
	    Friend test13 = new Friend("Raphael", "Fitzgerald");
	    Friend test14 = new Friend("Deacon", "Daniels");
	    Friend test15 = new Friend("Delilah", "Fletcher");
	    Friend test16 = new Friend("Robin", "Andrews");
	    Friend test17 = new Friend("Melvin", "Price");
	    
	    for(int x = 0; x < 2;x++){
		    list.add(test1.toString());
		    list.add(test2.toString());
		    list.add(test3.toString());
		    list.add(test4.toString());
		    list.add(test5.toString());
		    list.add(test6.toString());
		    list.add(test7.toString());
		    list.add(test8.toString());
		    list.add(test9.toString());
		    list.add(test10.toString());
		    list.add(test11.toString());
		    list.add(test12.toString());
		    list.add(test13.toString());
		    list.add(test14.toString());
		    list.add(test15.toString());
		    list.add(test16.toString());
		    list.add(test17.toString());
	    }

	    return list;
	  }

	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		if (view.getId() == R.id.listofselected) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			menu.setHeaderTitle(friendList.get(info.position));
			String[] menuItems = getResources().getStringArray(R.array.ContactsMenu);
			for (int i = 0; i<menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
			Toast.makeText(getApplicationContext(),"menu up", Toast.LENGTH_SHORT).show();
		}
		
	}
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int menuItemIndex = item.getItemId();
		
		Friend user = new Friend(null,null,null);
		final String toDelete = user.extractEmail(friendList.get(info.position));

		switch (menuItemIndex)
		{
			case 0:
				LayoutInflater inflater = this.getLayoutInflater();
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				
				Toast.makeText(getApplicationContext(),"Opened Show Contact", Toast.LENGTH_SHORT).show();
				//TextView one = (TextView)findViewById(R.id.emailConfirmView);
				//one.setText("hh");
				builder.setTitle("User Information")
				       .setView(inflater.inflate(R.layout.dialog_user_info, null))
				       .setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				        	   Toast.makeText(getApplicationContext(),"Back", Toast.LENGTH_SHORT).show();				        	   
				        	   dialog.dismiss();
				           }
				       })
				       .setNegativeButton(R.string.DeleteContact, new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				        	   deleteContact(toDelete);
				        	   Toast.makeText(getApplicationContext(),"ToDelete", Toast.LENGTH_SHORT).show();
				        	   dialog.cancel();
				           }
				       });
				Dialog dialog = builder.create();
				dialog.show();
				return false;
			case 1:
				AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
		 	    builder2.setTitle("Confirm Delete?")
				       .setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
				    	   public void onClick(DialogInterface dialog, int id) {
				        	   dialog.dismiss();	     
				        	   confirmDelete(toDelete);		        	   
				           }
				       })
				       .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				        	   dialog.cancel();
				           }
				       });
				Dialog dialog2 = builder2.create();
				dialog2.show();
				return false;
			case 2:
				return false;
		}
		return true;
	}
	
	public void showCreateContact(View view)
    {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		final EditText emailinput = new EditText(this);
		
 	    builder.setTitle("Add new Contact")
		       .setView(emailinput)
		       .setPositiveButton(R.string.Add, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   //confirmContact(emailinput.getText().toString());	
		        	    try {
		        		   sendContact(emailinput.getText().toString());
						} catch (InterruptedException e) {
							
						}
						
		        	   Toast.makeText(getApplicationContext(),"sent email", Toast.LENGTH_SHORT).show();
		        	   dialog.dismiss();	        	   
		           }
		       })
		       .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		           }
		       });
		Dialog dialog = builder.create();
		dialog.show();
    }
/*
	public void confirmContact(final String email){
		LayoutInflater inflater = this.getLayoutInflater();
		AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
		
		builder2.setTitle("Confirm Add Contact?")
		       .setView(inflater.inflate(R.layout.dialog_user_info, null))
		       .setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.dismiss();
		        	   try {
		        		   sendContact(email);
						} catch (InterruptedException e) {
							
						}
		           }
		       })
		       .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               dialog.cancel();
		           }
		       });
		Dialog dialog = builder2.create();
		dialog.show();

		//send email to server. return first and last name of this email account.
		
		if (name != NULL)
			((TextView)findViewById(R.id.firstView)).setText(first);
			((TextView)findViewById(R.id.lastView)).setText(last);		
		
		
		//((TextView)findViewById(R.id.emailConfirmView)).setText(email);
	}
*/
	public void sendContact(String email) throws InterruptedException{
		
		 Toast.makeText(getApplicationContext(),"In Send", Toast.LENGTH_SHORT).show();
		 
		final String EMAIL_PATTERN = 
				"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
		final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		final Matcher matcher;
		matcher = pattern.matcher(email);
		
		if(!matcher.matches()){
			Toast.makeText(getApplicationContext(), "Email: \""+email+"\" is not a valid email address!", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getApplicationContext(),"calling add", Toast.LENGTH_SHORT).show();
			String status = "fail";
			AccessNet caller = new AccessNet();
			String params = "userEmail="+user.getEmail()+"&password="+user.getPassword()+"&newFriendEmail="+email;
			String urlstub = "db_friendof_create.php";
			status = caller.simpleServerCall(urlstub, params);

			if(status.equalsIgnoreCase("success")){
				Toast.makeText(getApplicationContext(),email + " Added as a Friend!", Toast.LENGTH_SHORT).show();
				
			}else{
				Toast.makeText(getApplicationContext(),"Error adding friend", Toast.LENGTH_SHORT).show();
			}
		}
	
	}
	
	public void deleteContact(final String email){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
 	    builder.setTitle("Confirm Delete?")
		       .setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
		    	   public void onClick(DialogInterface dialog, int id) {
		        	   dialog.dismiss();	     
		        	   confirmDelete(email);		        	   
		           }
		       })
		       .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		           }
		       });
		Dialog dialog = builder.create();
		dialog.show();
	}
	
	public void confirmDelete(String email){
		//server call to delete friend
		String msg = email + " succesfully deleted";
		Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
	}
	
	public void showMainMenu(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
