package com.android.attendance.activity;

import com.android.attendance.bean.FacultyBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddFacultyActivity extends Activity {

	Button registerButton;
	Button cancelButton;
	EditText textFirstName;
	EditText textLastName;
	EditText textemail;
	EditText textcontact;
	EditText textaddress;
	EditText textusername;
	EditText textpassword;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addfaculty);


		textFirstName=(EditText)findViewById(R.id.editTextFirstName);
		textLastName=(EditText)findViewById(R.id.editTextLastName);
		textcontact=(EditText)findViewById(R.id.editTextPhone);
		textaddress=(EditText)findViewById(R.id.editTextaddr);
		textusername=(EditText)findViewById(R.id.editTextUserName);
		textpassword=(EditText)findViewById(R.id.editTextPassword);
		registerButton=(Button)findViewById(R.id.RegisterButton);
		cancelButton=(Button)findViewById(R.id.Cancel_Button);
		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String first_name = textFirstName.getText().toString();
				String last_name = textLastName.getText().toString();
				String phone_no = textcontact.getText().toString();
				String address = textaddress.getText().toString();
				String userName = textusername.getText().toString();
				String passWord = textpassword.getText().toString();

				if (TextUtils.isEmpty(first_name)) {
					textFirstName.setError("Please Enter Firstname");
				}
				else if (TextUtils.isEmpty(last_name)) {
					textLastName.setError("Please Enter Lastname");
				}
				else if (TextUtils.isEmpty(phone_no)) {
					textcontact.setError("please Enter Mobile Number");
				}

				else if (TextUtils.isEmpty(address)) {
					textaddress.setError("Enter Address");
				}	
				else if (TextUtils.isEmpty(userName)) {
					textcontact.setError("Please Enter Username");
				}
				else if (TextUtils.isEmpty(passWord)) {
					textaddress.setError("Enter Password");
				}				
				else { 
					
					FacultyBean facultyBean = new FacultyBean();
					facultyBean.setFaculty_firstname(first_name);
					facultyBean.setFaculty_lastname(last_name);
					facultyBean.setFaculty_mobilenumber(phone_no);
					facultyBean.setFaculty_address(address);
					facultyBean.setFaculty_username(userName);
					facultyBean.setFaculty_password(passWord);
					
					DBAdapter dbAdapter = new DBAdapter(AddFacultyActivity.this);
					dbAdapter.addFaculty(facultyBean);
					
					Intent intent =new Intent(AddFacultyActivity.this,MenuActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "Faculty Added Successfully", Toast.LENGTH_SHORT).show();

				}

			}
		});

		cancelButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent =new Intent(AddFacultyActivity.this,MenuActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
