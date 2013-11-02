package com.example.payback;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.os.Bundle;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TitleActivity extends Activity {

	private static final int MY_REQUEST_CODE = 0;
    public final boolean success = false;
	public void modifyTitle(String name, int layout) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		setContentView(layout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_bar); 
		TextView header = (TextView) getWindow().findViewById(R.id.titleNameView); 
        header.setText(name);
	}
	
	public void ShowShortcuts(View view)
	{
		Intent intent = new Intent(this, ShortcutsActivity.class);
		startActivityForResult(intent, MY_REQUEST_CODE);
        this.finish();
	}
	
	public void hideSoftKeyboard(View view) {
		((InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	
	public boolean callServer(final String className, final String parameters, final URL url, final boolean success){
		Logger CONLOG = Logger.getLogger(className);
		CONLOG.setLevel(Level.INFO);
		
		new Thread (new Runnable() {
			@Override
			public void run(){
				//view.invalidate();//uncommenting this will render the button into an app crasher
				Logger CANLOG = Logger.getLogger(className);
				CANLOG.setLevel(Level.INFO);
				CANLOG.info("Able to set url and params: "+parameters);
				HttpURLConnection connection;
				try {
					connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setDoInput(true);
					connection.setInstanceFollowRedirects(false); 
					connection.setRequestMethod("POST"); 
					connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
					connection.setRequestProperty("charset", "utf-8");
					connection.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));
					connection.setUseCaches (false);
					CANLOG.info("Able to set HTTP: "+connection);
					
					try{
						DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
						wr.writeBytes(parameters);
						wr.flush();
						wr.close();
						CANLOG.info("Data send success!");
						success = true;
					} catch (IOException e){
						CANLOG.warning("IOException on HTTP connection send to server: "+connection.getResponseMessage());
						e.printStackTrace();
					}
					
					try{
						String line;
				   		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				   		while ((line = reader.readLine()) != null) {
				   		    //System.out.println(line);
				   			CANLOG.info("Data in: "+line);
				   			//Toast.makeText(getApplicationContext(), line, Toast.LENGTH_SHORT).show();
				   		}
				   		reader.close();
				   		CANLOG.info("Data receive success!");
					} catch (IOException e){
						CANLOG.warning("IOException on HTTP connection receive from server: "+connection.getResponseMessage());
						e.printStackTrace();
					}
					connection.disconnect();	
				} catch (IOException e) {
					CANLOG.warning("IOException on HTTP connection creation");
					e.printStackTrace();
				}	   		    
			}
		}).start();	
	}
}
