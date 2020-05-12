package com.sampleweb;

import javax.xml.ws.Endpoint;



public class CallWSTestApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("object created");
Endpoint.publish("http://localhost:8080/testApp", new TestWebApp());
System.out.println("Url enabled");
	}

}
