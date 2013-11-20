//CREDIT TO Will Macfarlane for the template used in Notification.
package com.example.payback;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ResolveTransactionActivity extends TitleActivity
{
	static final boolean DEMO = true;
	
	static Activity activityInstance;	//these are variables
	static PageKillReceiver pkr;		//used for PageKillReceiver.java
	static IntentFilter filter;
	
	@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				modifyTitle("Resolve Transactions",R.layout.activity_resolve_transaction1);
		
				activityInstance = this;
				pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
				filter = new IntentFilter();
				filter.addAction("com.Payback.Logout_Intent");
				registerReceiver(pkr, filter);
				
				user.setTransactions(ResolveTransaction.updateTransactions(user.getEmail())); //transactions updated every time this page is loaded
				
				ArrayList<ResolveTransaction> trans = user.getTransactions();
				
				final ListView listview = (ListView) findViewById(R.id.listview);
				
				if(DEMO)
				{
					trans.add(new ResolveTransaction("$4.50", "Paid for the milk this week."));
					trans.add(new ResolveTransaction("$20.50", "Paid for Brian's pizza."));
					trans.add(new ResolveTransaction("$5.00", "Spotted Kristin a 5"));
					trans.add(new ResolveTransaction("$2.50", "Paid for the eggs this week"));
					trans.add(new ResolveTransaction("$18.50", "Paid for Brian's pizza AGAIN"));
					trans.add(new ResolveTransaction("$17.00", "Filled the fridge with beer"));

				}
					//final StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, al);
				TransAdapter ta = new TransAdapter(this, android.R.layout.simple_list_item_1, trans);
				listview.setAdapter(ta);
				setContentView(listview);	
			}

			@Override
			public boolean onCreateOptionsMenu(Menu menu) 
			{
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.resolve_transaction, menu);
				return true;
			}
		}
		class TransAdapter extends ArrayAdapter<ResolveTransaction>
		{
			private final Context context;
			private final ArrayList<ResolveTransaction> al;
			public TransAdapter (Context context, int textViewResourceId, ArrayList<ResolveTransaction> al)
			{
				super(context, R.layout.activity_resolve_transaction1, al);
				this.context = context;
				this.al = al;
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rowView = inflater.inflate(R.layout.activity_noti_row_layout, parent, false);
			    TextView from = (TextView) rowView.findViewById(R.id.from);
			    TextView date = (TextView) rowView.findViewById(R.id.date);
			    TextView message = (TextView) rowView.findViewById(R.id.secondLine);
			    
			    //TODO: set the following text to the person's first name and last name with a server call, instead of just their email
			    from.setText(al.get(position).getAmount());
			    
			    date.setText(al.get(position).getDate());
			    message.setText(al.get(position).getMessage());
			    return rowView;
			}
		}