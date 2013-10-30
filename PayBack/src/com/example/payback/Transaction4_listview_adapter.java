package com.example.payback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;

public class Transaction4_listview_adapter extends SimpleAdapter{
	List<Map<String, String>> data;
	
	public Transaction4_listview_adapter(Context context,
			List<Map<String, String>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		this.data = data;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		final EditText text = (EditText) view.findViewById(R.id.borroweramount);  
        DecimalFormat dec = new DecimalFormat("0.00");
        String userInput= ""+ text.getText().toString().replaceAll("[^\\d]", "");
        if (userInput.length() > 0) {
        	Float in=Float.parseFloat(userInput);
            float percen = in/100;
            text.setText("$"+dec.format(percen));
            text.setSelection(text.getText().length());
         }
		
	    text.setRawInputType(Configuration.KEYBOARD_12KEY);    

	    text.addTextChangedListener(new TextWatcher(){
	        DecimalFormat dec = new DecimalFormat("0.00");
	        public void afterTextChanged(Editable arg0) {
//	        	data
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	        }
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	            if(!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
	            {
	                String userInput= ""+ s.toString().replaceAll("[^\\d]", "");
	                if (userInput.length() > 0) {
	                    Float in=Float.parseFloat(userInput);
	                    float percen = in/100;
	                    text.setText("$"+dec.format(percen));
	                    text.setSelection(text.getText().length());
	                }
	            }
	        }
	    });
	
	    
	    
		return view;
	}
}
