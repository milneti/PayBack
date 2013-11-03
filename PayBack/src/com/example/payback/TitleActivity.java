package com.example.payback;


import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

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
	
	
	/*
	public boolean checkAlpha(){
		//check for textbox to only have Letters
	}
	public boolean checkEmail(){
		//check for textbox to have valid email format --> XX@yy.com
	}
	public boolean checkNumber(){
		//check for textbox to only have numbers
	}	
	public boolean checkPassword(){
		//check for textbox to have >6 characters
		//must contain 3 categories of character types: 
		//i.e letter,number,symbol,uppercaseLetter,etc.
	}
	*/
	/*
	public boolean sendServer(final String className, final String parameters, final String urlName){
		Logger CONLOG = Logger.getLogger(className);
		CONLOG.setLevel(Level.INFO);
		final boolean success[] = new boolean[1];
		final CountDownLatch latch = new CountDownLatch(1);
		
		new Thread (new Runnable() {
			@Override
			public void run(){
				//view.invalidate();//uncommenting this will render the button into an app crasher
				Logger CANLOG = Logger.getLogger(className);
				CANLOG.setLevel(Level.INFO);
				URL url = null;
				try {
					url = new URL(urlName);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
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
					} catch (IOException e){
						CANLOG.warning("IOException on HTTP connection send to server: "+connection.getResponseMessage());
						e.printStackTrace();
					}
					
					try{
						String line;
				   		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				   		/*while ((line = reader.readLine()) != null) {
				   		    //System.out.println(line);
				   			CANLOG.info("Data in: "+line);
				   			//Toast.makeText(getApplicationContext(), line, Toast.LENGTH_SHORT).show();
				   		}/
				   		line = reader.readLine();
				   		if(line.equalsIgnoreCase("1"));
				   			success[0] = true;
				   		CANLOG.info("Data in: "+line);
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
			//latch.countDown();
		}).start();	
		return success[0];
	}*/
}
