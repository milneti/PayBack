package com.example.payback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class Transaction2Activity extends TitleActivity  {

    private List<String> listHeader;
    private HashMap<String, List<Friend>> listChild;
    
	static Activity activityInstance;	
	static PageKillReceiver pkr;		//these are the variables
	static IntentFilter filterPKR;		//used for PageKillReceiver.java
	static NoBackingReceiver nbr;		//these are the variables
	static IntentFilter filterNBR;		//used for NoBackingReceiver.java
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
				 
		    super.onCreate(savedInstanceState);
			modifyTitle("Create Transaction",R.layout.activity_transaction2_expandablecontactlist_main);
		    ExpandableListView expListView = (ExpandableListView) findViewById(R.id.expandlistView);

			activityInstance = this;

			pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
			filterPKR = new IntentFilter();
			filterPKR.addAction("com.Payback.Logout_Intent");
			registerReceiver(pkr, filterPKR);
			
			nbr = new NoBackingReceiver(); nbr.setActivityInstance(activityInstance);
			filterNBR = new IntentFilter();
			filterNBR.addAction("com.Payback.MainActivity_Intent");
			registerReceiver(nbr, filterNBR);
		    
		    createData();

		    final Transaction2Activity_expandablecontactlist_adapter listAdapter = new Transaction2Activity_expandablecontactlist_adapter(this, listHeader, listChild);
		    expListView.setAdapter(listAdapter);

	}
	
	public void createData() {
		
        listHeader = new ArrayList<String>();
        listChild = new HashMap<String, List<Friend>>();

        //test friends
    	Friend tests1 = new Friend("Place", "Ment");
    	Friend tests2 = new Friend("For", "Later");
    	Friend tests3 = new Friend("Sprints", "Lname");
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

        
        listHeader.add("Groups");
        List<Friend> child0 = new ArrayList<Friend>();
        
        child0.add(tests1);
        child0.add(tests2);
        child0.add(tests3);
        
        listChild.put(listHeader.get(0), child0);
        
        listHeader.add("Individual");
        List<Friend> child1 = new ArrayList<Friend>();
        
        child1.add(test1);
        child1.add(test2);
        child1.add(test3);
        child1.add(test4);
        child1.add(test5);
        child1.add(test6);
        child1.add(test7);
        child1.add(test8);
        child1.add(test9);
        child1.add(test10);
        child1.add(test11);
        child1.add(test12);
        child1.add(test13);
        child1.add(test14);
        child1.add(test15);
        child1.add(test16);
        child1.add(test17);
        
        listChild.put(listHeader.get(1), child1);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction2, menu);
		return true;
	}
	
	public void showCreateContact(View view)
    {
		
    }
	
	public void checktomoveontotrans3(View view){
		int onetoast = 1;
		for(int i = 0; i < listHeader.size(); i++){
			String currentheader = listHeader.get(i);
			for(int j = 0; j < listChild.get(currentheader).size(); j++){
				if(listChild.get(currentheader).get(j).isSelected()){
					onetoast = 0;
					showTrans3(view);
				}
			}
		}
		if(onetoast == 1){
			Toast.makeText(getApplicationContext(), "No Contact Selected", Toast.LENGTH_SHORT).show();
			onetoast = onetoast - 1;
		}
	}
	
	public void showTrans3(View view)
    {
	    Bundle oldbundle = getIntent().getExtras();
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");

    	Intent intent = new Intent(this, Transaction3Activity.class);
        Bundle Bundle = new Bundle();
        
        Bundle.putInt("Transaction1transCost", transCostInt);
        Bundle.putString("Transaction1transComment", transCommentString);
        
        ArrayList<Friend> selectedContacts = new ArrayList<Friend>();
        for(int i = 0; i < listHeader.size(); i++){
			String currentheader = listHeader.get(i);
			for(int j = 0; j < listChild.get(currentheader).size(); j++){
				if(listChild.get(currentheader).get(j).isSelected()){
					selectedContacts.add(listChild.get(currentheader).get(j));
				}
			}
		}
        
        Bundle.putParcelableArrayList("Transaction2selected", selectedContacts);

        intent.putExtras(Bundle);
        startActivity(intent);
    }

	public void showTrans1(View view)
	{
		Intent intent = new Intent(this, Transaction1Activity.class);
	    startActivity(intent);
	}
	
}
