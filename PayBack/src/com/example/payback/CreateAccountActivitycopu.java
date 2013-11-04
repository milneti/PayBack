//package com.example.payback;
//
//import android.os.Bundle;
//import android.content.Intent;
//import android.view.Menu;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//
//public class CreateAccountActivity extends TitleActivity {
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_create_account);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.create_account, menu);
//		return true;
//	}
//	
//	public void Register(final View view) throws InterruptedException {
//		//*
//		Logger CONLOG = Logger.getLogger(CreateAccountActivity.class .getName());
//		//is email check taken from: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
//		final String EMAIL_PATTERN = 
//				"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
//		final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//		final Matcher matcher;
//		
//		CONLOG.setLevel(Level.INFO);
//		String fName = ((EditText)findViewById(R.id.fName)).getText().toString();
//		String lName = ((EditText)findViewById(R.id.lName)).getText().toString();
//		String email = ((EditText)findViewById(R.id.email)).getText().toString();
//		String password = ((EditText)findViewById(R.id.password)).getText().toString();
//
//		CONLOG.info("Register loaded data <"+fName+", "+lName+", "+email+", "+password+">");
//		matcher = pattern.matcher(email);
//		/*
//		* <SRC of snippet: http://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily >
//		* Altered, modified, adulterated, outfitted, genetically modified by Hohyun@11/01/2013
//		* Source of exception explained here: http://www.androiddesignpatterns.com/2012/06/app-force-close-honeycomb-ics.html
//		* corrected to start under a new thread using tutorial:
//		* http://developer.samsung.com/android/technical-docs/Basics-of-multi-threading-in-Android
//		*/
//		if(fName.isEmpty()){
//			Toast.makeText(getApplicationContext(), "First name cannot be empty!", Toast.LENGTH_SHORT).show();
//		}else if(lName.isEmpty()){
//			Toast.makeText(getApplicationContext(), "Last name cannot be empty!", Toast.LENGTH_SHORT).show();
//		}else if(email.isEmpty()){
//			Toast.makeText(getApplicationContext(), "Email cannot be empty!", Toast.LENGTH_SHORT).show();
//		}else if(password.isEmpty()){
//			Toast.makeText(getApplicationContext(), "Password cannot be empty!", Toast.LENGTH_SHORT).show();
//		}else if(password.length() < 8){
//			Toast.makeText(getApplicationContext(), "Password must be at least 8 characters long!", Toast.LENGTH_SHORT).show();
//		}else if(!matcher.matches()){
//			Toast.makeText(getApplicationContext(), "Email: \""+email+"\" is not a valid email address!", Toast.LENGTH_SHORT).show();
//		}else{
//			//all transferred data must be finals.
//			final String fNameF = fName;
//			final String lNameF = lName;
//			final String emailF = email;
//			final String passwordF = password;
//			
//			//threading synch for return values taken from: http://stackoverflow.com/questions/9148899/returning-value-from-thread
//			final boolean[] success = new boolean[1];
//			final CountDownLatch latch = new CountDownLatch(1);
//			
//			new Thread (new Runnable() {
//				@Override
//				public synchronized void run(){
//					//view.invalidate();//uncommenting this will render the button into an app crasher
//					Logger CANLOG = Logger.getLogger(CreateAccountActivity.class .getName());
//					CANLOG.setLevel(Level.INFO);
//					try {
//						URL url = new URL("http://chase.mamatey.com/PayBack/AccountCreation.php");
//						String sendParams = "fname="+fNameF+"&lname="+lNameF+"&email="+emailF+"&password="+passwordF;
//						CANLOG.info("Able to set url and params: "+sendParams);
//						HttpURLConnection connection;
//						try {
//							connection = (HttpURLConnection) url.openConnection();
//							connection.setDoOutput(true);
//							connection.setDoInput(true);
//							connection.setInstanceFollowRedirects(false); 
//							connection.setRequestMethod("POST"); 
//							connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
//							connection.setRequestProperty("charset", "utf-8");
//							connection.setRequestProperty("Content-Length", "" + Integer.toString(sendParams.getBytes().length));
//							connection.setUseCaches (false);
//							CANLOG.info("Able to set HTTP: "+connection);
//							
//							try{
//								DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
//								wr.writeBytes(sendParams);
//								wr.flush();
//								wr.close();
//								CANLOG.info("Data send success!");
//							} catch (IOException e){
//								CANLOG.warning("IOException on HTTP connection send to server: "+connection.getResponseMessage());
//								e.printStackTrace();
//							}
//							
//							try{
//								String line;
//						   		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//						   		//while ((line = reader.readLine()) != null) {
//						   		    //System.out.println(line);
//						   		line = reader.readLine();
//						   		
//						   			
//						   		if(line.equalsIgnoreCase("1"));
//						   			success[0] = true;
//						   		CANLOG.info("Data in: "+line);
//						   			//Toast.makeText(getApplicationContext(), line, Toast.LENGTH_SHORT).show();
//						   		//}
//						   		reader.close();
//						   		CANLOG.info("Data receive success!");
//							} catch (IOException e){
//								CANLOG.warning("IOException on HTTP connection receive from server: "+connection.getResponseMessage());
//								e.printStackTrace();
//							}
//							
//							connection.disconnect();
//							CANLOG.info("Created new account in system: "+sendParams);
//							//Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT).show();
//							
//						} catch (IOException e) {
//							CANLOG.warning("IOException on HTTP connection creation");
//							e.printStackTrace();
//						}           
//
//					} catch (MalformedURLException e) {
//						CANLOG.warning("MalformedURLException on URL");
//						e.printStackTrace();
//					}
//		   		    
//					latch.countDown();
//				}
//			}).start();
//			
//			latch.await();
//			//To be tested.
//			if(success[0]){
//				CONLOG.info("Created new account in system: ");
//				Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//				startActivity(intent);
//			}else{
//				CONLOG.warning("Failed to create account in system. Server response error.");
//				Toast.makeText(getApplicationContext(), "Error account could not be created", Toast.LENGTH_SHORT).show();
//			}
//		}
//	}
//	
//	public void Login(View view) {
//		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivity(intent);
//	}
//
//}

