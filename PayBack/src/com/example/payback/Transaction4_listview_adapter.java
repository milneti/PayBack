package com.example.payback;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Transaction4_listview_adapter extends SimpleAdapter
{
	List<Map<String, String>> data;
	Context context;
	int maxSeekBar = 9999;		//the maximum amount any borrower could borrow
	int progressLocation = 0;
	
	public Transaction4_listview_adapter(Context context,
			List<Map<String, String>> data, int resource, String[] from, int[] to, int maxForBorrower, int thumbLocation)
	{
		super(context, data, resource, from, to);
		this.data = data;
		this.context = context;
		this.maxSeekBar = maxForBorrower;	//max amount used for seek bar progress
		this.progressLocation = thumbLocation;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		View view = super.getView(position, convertView, parent);
		EditText text = (EditText) view.findViewById(R.id.borroweramount);  
	    text.setRawInputType(Configuration.KEYBOARD_12KEY);    

		if(!(((Map<String, String>)getItem(position)).get("data").startsWith("$")))
		{
	        DecimalFormat dec = new DecimalFormat("0.00");
	        String userInput= ""+ text.getText().toString().replaceAll("[^\\d]", "");
	        if (userInput.length() > 0)
	        {
	        	Float in = Float.parseFloat(userInput);
	            float percen = in/100;
	            text.setText("$"+dec.format(percen));
	            text.setSelection(text.getText().length());
	        }
		}

		
		final View view2 = view;
	    text.addTextChangedListener(new TextWatcher()
	    {
	        DecimalFormat dec = new DecimalFormat("0.00");
	        
	        public void afterTextChanged(Editable arg0)
	        {
	        	
	        }
	        
	        public void beforeTextChanged(CharSequence s, int start, int count, int after)
	        {
	        	
	        }
	        
	        public void onTextChanged(CharSequence s, int start, int before, int count)			//when text is changed, this is what happens
	        {
	            if(!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
	            {
	                String userInput= "" + s.toString().replaceAll("[^\\d]", "");
	                if (userInput.length() > 0)
	                {
	        			EditText text = (EditText) view2.findViewById(R.id.borroweramount);  	        			
	                    Float in = Float.parseFloat(userInput);
	                    float percen = in/100;
	                    text.setText("$" + dec.format(percen));
	                    text.setSelection(text.getText().length());
	                    
	                    int index = -1;
	        			TextView text2 = (TextView) view2.findViewById(R.id.borrowername);
	        			for(int i = 0; i < data.size(); i++)									//iterate through ArrayList data to find a
	        			{																		//friend whose entered value is not the same
	        				if(data.get(i).get("friendname") == text2.getText().toString())		//as the default value
	        				{
	        					index = i;														//Set index to that friend's location in data
	        				}
	        			}
	                    
	                    data.get(index).put("data", userInput);									//update data with new borrowerShare
	                }
	            }
	        }
	    });
	
		
	    SeekBar sb = (SeekBar) view.findViewById(R.id.seekBar1);	//the seek bar
        sb.setMax(maxSeekBar);										//sets the max as the max amount anyone can borrow
        sb.setProgress(progressLocation);							//starts off at default
        
        sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener()	//listens for changes in seek bar
        {
        	//when progress changes
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {	
                EditText text = (EditText) view2.findViewById(R.id.borroweramount);
                //gets the EditText box which holds a borrower's amount
                text.setText(Integer.toString(progress));
                //sets the progress amount as the EditText value
            }

			@Override
			public void onStartTrackingTouch(SeekBar seekBar){}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar){}
        }
        );
	    
		return view;
	}
}
