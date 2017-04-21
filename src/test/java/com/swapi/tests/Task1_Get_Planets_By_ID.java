package com.swapi.tests;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.swapi.utilities.RestAPITester;

public class Task1_Get_Planets_By_ID extends RestAPITester {
	public static boolean reporterFlag = true;
	public static HttpResponse respGETService;
	public static int respCode;

	@Test
	public static void Auto_SWAPI_GET_Base_Test_001() throws ClientProtocolException, IOException {
		respCode = getServiceStatusCode("http://swapi.co/api");
		Reporter.log("HttpResponse Status Code of  BaseTest: " + respCode, reporterFlag); // We can use log4J for logging
		Assert.assertEquals(respCode, HttpStatus.SC_OK);
	}

	@Test(priority = 1, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_Swapi_GET_ID_TEST_OO2() throws ClientProtocolException, IOException, JSONException {
		respCode = getServiceStatusCode("http://swapi.co/api/planets/99/");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag); 
		Assert.assertEquals(respCode, HttpStatus.SC_NOT_FOUND);
	}
}
