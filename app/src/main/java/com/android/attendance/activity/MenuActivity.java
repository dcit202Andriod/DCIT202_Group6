package com.android.attendance.activity;

import java.util.ArrayList;

import com.android.attendance.bean.AttendanceBean;
import com.android.attendance.bean.StudentBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
import android.telephony.SmsManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MenuActivity extends Activity {

	Button addStudent;
	Button addFaculty;
	Button viewStudent;
	Button viewFaculty;
	Button logout;
	Button attendancePerStudent;

	private ListView listView ;
	private ArrayAdapter<String> listAdapter;



	public void sendMessage(View view)
	{
		DBAdapter dbAdapter = new DBAdapter(this);
		final ArrayList<String> studentList = new ArrayList<String>();
		 ArrayList<AttendanceBean> attendanceBeanList;

		ArrayList<StudentBean> studentBeanList;
		studentBeanList=dbAdapter.getAllStudentmobile();
		attendanceBeanList=((ApplicationContext)MenuActivity.this.getApplicationContext()).getAttendanceBeanList();

		try {
			ArrayList<String> arr = new ArrayList<String>();


			//Toast.makeText(MenuActivity.this,"Button SMS sent",Toast.LENGTH_SHORT).show();


			//ArrayList attendanceBean = attendanceBeanList;
	//int val=0;
	//while (attendanceBean.size()>val)
		for(AttendanceBean attendanceBean : attendanceBeanList)
			{
				String att= ""+ attendanceBean.getAttendance_session_id();
				att=att.trim();
				arr.add(att);
				//Log.d(TAG, "Attendence"+att);



			}


			int i =0;


			for(StudentBean studentBean : studentBeanList)
			{
				String users = studentBean.getStudent_mobilenumber();

				studentList.add(users);
				String temp = arr.get(i);
				int flag=0;
				int number = Integer.parseInt(temp);
				if(number<5)
				{
					flag=1;
				}
				if(flag==1)
				{
					SmsManager smgr = SmsManager.getDefault();
					smgr.sendTextMessage(users,null,"Your attendance is "+temp+" You are a DEFAULTER",null,null);
					Log.d(TAG, "sendMessage: "+"Your attendance is " + temp + " You are a DEFAULTER");
				}
				else
				{
					SmsManager smgr = SmsManager.getDefault();
					smgr.sendTextMessage(users,null,"Your attendance is "+temp,null,null);
					Log.d(TAG, "sendMessage: "+"Your attendance is " + temp);

				}


				i++;


			}
			Toast.makeText(MenuActivity.this,"Message sent to all Students Successfully",Toast.LENGTH_SHORT).show();



		}
		catch (Exception e)
		{
			Toast.makeText(MenuActivity.this,"SMS Failed",Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		addStudent =(Button)findViewById(R.id.buttonaddstudent);
		addFaculty =(Button)findViewById(R.id.buttonaddfaculty);
		viewStudent =(Button)findViewById(R.id.buttonViewstudent);
		viewFaculty =(Button)findViewById(R.id.buttonviewfaculty);

		logout =(Button)findViewById(R.id.buttonlogout);
		
		addStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,AddStudentActivity.class);
				startActivity(intent);
			}
		});
		
		addFaculty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,AddFacultyActivity.class);
				startActivity(intent);
			}
		});
		
		viewFaculty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,ViewFacultyActivity.class);
				startActivity(intent);
			}
		});


		viewStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,ViewStudentActivity.class);
				startActivity(intent);
			}
		});
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		attendancePerStudent=(Button)findViewById(R.id.attendancePerStudentButton);
		attendancePerStudent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				DBAdapter dbAdapter = new DBAdapter(MenuActivity.this);
				ArrayList<AttendanceBean> attendanceBeanList=dbAdapter.getAllAttendanceByStudent();
				((ApplicationContext)MenuActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
				
				Intent intent = new Intent(MenuActivity.this,ViewAttendancePerStudentActivity.class);
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
