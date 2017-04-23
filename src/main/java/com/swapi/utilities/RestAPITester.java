package com.swapi.utilities;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * This class consumes RESTAPI and executes
 * 
 * @author rakes
 *
 */
public class RestAPITester {
	public static HttpUriRequest rqst;
	public static HttpResponse resp;

	/**
	 * This method accepts API URL and executes
	 * 
	 * @param restURL
	 * @return Http Status Code
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static int getServiceStatusCode(String restURL) throws ClientProtocolException, IOException {
		rqst = new HttpGet(restURL);
		resp = HttpClientBuilder.create().build().execute(rqst);
		return resp.getStatusLine().getStatusCode();
	}

	/**
	 * This method is used to return the Http Response
	 * 
	 * @return Http Response
	 * @throws ParseException
	 * @throws IOException
	 */
	public static HttpResponse getServiceContent() throws ParseException, IOException {
		return resp;
	}

}
