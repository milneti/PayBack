package com.example.payback;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class Transaction4Activity extends Activity {
	
	 ListView listView;
	 EditText editing;

	 List<String> data = new ArrayList<String>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction4);
//		
//		 data.add("Hello World");
//		 data.add("Hello World2");
//		 data.add("Hello World3");
//		 data.add("Hello World4");
//		 data.add("Hello World5");
//		 data.add("Hello World6");
//
//		 listView = (ListView) findViewById(R.id.Listofselected);
//		 editing = (EditText) findViewById(R.id.borroweramount);
//
//		 listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction4, menu);
		return true;
	}
	
	public void showTrans3(View view)
    {
		
	    Bundle oldbundle = getIntent().getExtras();
	    
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");

    	Intent intent = new Intent(this, Transaction3Activity.class);
        Bundle Bundle = new Bundle();
        
        Bundle.putInt("Transaction1transCost", transCostInt);
        Bundle.putString("Transaction1transComment", transCommentString);
        Bundle.putParcelableArrayList("Transaction2selected", transselected);
        Bundle.putInt("Transaction3lenderamount", translenderamountInt);
        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
        
        intent.putExtras(Bundle);
        startActivity(intent);
    }
	
	public void showTrans5(View view)
    {
		//NOT FINISHED TODO
		
	    Bundle oldbundle = getIntent().getExtras();
	    
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");

    	Intent intent = new Intent(this, Transaction5Activity.class);
        Bundle Bundle = new Bundle();
        
        Bundle.putInt("Transaction1transCost", transCostInt);
        Bundle.putString("Transaction1transComment", transCommentString);
        Bundle.putParcelableArrayList("Transaction2selected", transselected);
        Bundle.putInt("Transaction3lenderamount", translenderamountInt);
        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
        
        //put in new data here from this transaction page
        
        intent.putExtras(Bundle);
        startActivity(intent);
    }

}
