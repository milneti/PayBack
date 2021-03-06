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
		    expListView.expandGroup(0);		    
	}
	
	public void createData() {
		
        listHeader = new ArrayList<String>();
        listChild = new HashMap<String, List<Friend>>();
        
        listHeader.add("Individual");
        List<Friend> child1 = user.getFriends();
        
        listChild.put(listHeader.get(0), child1);
        
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
	
	public void showTrans1(View view)
	{
    	Bundle oldbundle = getIntent().getExtras();
		
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");	    
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    ArrayList<Integer> lendsharelist = oldbundle.getIntegerArrayList("Transaction3borroweramountlist");
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");
    	  
		Intent intent = new Intent(this, Transaction1Activity.class);
        Bundle Bundle = new Bundle();
        
        Bundle.putInt("Transaction1transCost", transCostInt);
        Bundle.putString("Transaction1transComment", transCommentString);
        Bundle.putParcelableArrayList("Transaction2selected", transselected);
        Bundle.putInt("Transaction3lenderamount", translenderamountInt);
        Bundle.putIntegerArrayList("Transaction3borroweramountlist", lendsharelist);
        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
        
        intent.putExtras(Bundle);
	    startActivity(intent);
	    finish();
	}
	
	public void showTrans3(View view)
    {
	    Bundle oldbundle = getIntent().getExtras();
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Integer> lendsharelist = new  ArrayList<Integer>();
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");
        ArrayList<Friend> selectedContacts = new ArrayList<Friend>();
        for(int i = 0; i < listHeader.size(); i++){
			String currentheader = listHeader.get(i);
			for(int j = 0; j < listChild.get(currentheader).size(); j++){
				if(listChild.get(currentheader).get(j).isSelected()){
					selectedContacts.add(listChild.get(currentheader).get(j));
				}
			}
		}
		int numContacts = selectedContacts.size();
		int lenderShare = transCostInt/(numContacts+1);
		int totaltrans = lenderShare;
		for(int i = 0; i < numContacts; i ++){
			lendsharelist.add(lenderShare);
			totaltrans = totaltrans + lenderShare;
		}
		if(totaltrans != transCostInt){
			lenderShare = lenderShare + (transCostInt - totaltrans);
		}
	    
	    
	    
	    if(button1Selected){
	    	Intent intent = new Intent(this, Transaction5Activity.class);
	        Bundle Bundle = new Bundle();
	        
	        Bundle.putInt("Transaction1transCost", transCostInt);
	        Bundle.putString("Transaction1transComment", transCommentString);
	        Bundle.putParcelableArrayList("Transaction2selected", selectedContacts);
	        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
	        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
			Bundle.putIntegerArrayList("Transaction3borroweramountlist", lendsharelist);
			Bundle.putInt("Transaction3lenderamount", lenderShare);

	        intent.putExtras(Bundle);
	        startActivity(intent);
	    }
	    else if(button2Selected)
	    {
	    	Intent intent = new Intent(this, Transaction4Activity.class);
	        Bundle Bundle = new Bundle();
	        
	        Bundle.putInt("Transaction1transCost", transCostInt);
	        Bundle.putString("Transaction1transComment", transCommentString);
	        Bundle.putParcelableArrayList("Transaction2selected", selectedContacts);
	        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
	        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
			Bundle.putIntegerArrayList("Transaction3borroweramountlist", lendsharelist);
			Bundle.putInt("Transaction3lenderamount", lenderShare);

	        intent.putExtras(Bundle);
	        startActivity(intent);
	    }
	    finish();
    }


	
}
