package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import java.sql.*;

public class CreateAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account, menu);
		return true;
	}
	
	public void Register(View view) {
//		String url = "jdbc:mysql://70.171.40.125:3306/";
//		String dbName = "mydb";
//		String driver = "com.mysql.jbdc.Driver";
//		String userName = "Admin";
//		String password = "AdminPassword1";
//		try {
//			Class.forName(driver).newInstance();
//			Connection conn = DriverManager.getConnection(url+dbName, userName, password);
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void Login(View view) {
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
	}

}
