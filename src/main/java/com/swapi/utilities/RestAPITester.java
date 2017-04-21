package com.swapi.utilities;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

public class RestAPITester {
	public static HttpUriRequest rqst;
	public static HttpResponse resp;
	
	public static int getServiceStatusCode(String restURL) throws ClientProtocolException, IOException {
		rqst = new HttpGet(restURL);
		resp = HttpClientBuilder.create().build().execute(rqst);
		return resp.getStatusLine().getStatusCode();
	}
	
	public static HttpResponse getServiceContent() throws ParseException, IOException {
		return resp;		
	}

}
