package com.sampleweb;

import java.text.SimpleDateFormat;
import java.util.Date;


import javax.jws.WebService;

@WebService(endpointInterface = "com.sampleweb.TestApp")
public class TestWebApp implements TestApp {
	
	

	
	public String getTimeAsString() {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		return sm.format(new Date());
	}

	public String GetTutorialService() {
		// TODO Auto-generated method stub
		String TutorialName = "Web Services";
		return TutorialName;
	}
	
}
