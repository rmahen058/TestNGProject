package com.swapi.tests;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import com.swapi.utilities.RestAPITester;

public class Task1_Get_All_Planets extends RestAPITester {
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
	public static void Auto_SWAPI_GET_ALL_Test_002() throws ClientProtocolException, IOException, JSONException {
		respCode = getServiceStatusCode("http://swapi.co/api/planets");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag); 
		Assert.assertEquals(respCode, HttpStatus.SC_OK);
	}

	@Test(priority = 2, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ALL_Test_003()
			throws ClientProtocolException, IOException, ParseException, JSONException {
		String[] expAttributes = { "films", "edited", "created", "climate", "rotation_period", "url", "population",
				"orbital_period", "surface_water", "diameter", "gravity", "name", "residents", "terrain" };

		Assert.assertEquals(getServiceStatusCode("http://swapi.co/api/planets"), HttpStatus.SC_OK);

		respGETService = getServiceContent();
		JSONObject jo = new JSONObject(EntityUtils.toString(respGETService.getEntity()));
		JSONArray ja = jo.getJSONArray("results");

		for (int j = 0; j < 1; j++) {
			JSONObject po = ja.getJSONObject(j);
			String[] attr = JSONObject.getNames(po);
			ArrayAsserts.assertArrayEquals(expAttributes, attr);
			Reporter.log("Attributes for the Resource Plannets: ", reporterFlag);
			for (int x = 0; x < attr.length; x++) {
				Reporter.log(attr[x], reporterFlag);
			}
		}
		Assert.assertEquals("Content-Type: application/json", respGETService.getEntity().getContentType().toString());

	}

	//@Test(priority = 3, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ALL_Test_004()
			throws ClientProtocolException, IOException, ParseException, JSONException {
		String[] expAttributes = { "films", "edited", "created", "climate", "rotation_period", "url", "population",
				"orbital_period", "surface_water", "diameter", "gravity", "name", "residents", "terrain" };

		Assert.assertEquals(getServiceStatusCode("http://swapi.co/api/planets/?search=al"), HttpStatus.SC_OK);

		respGETService = getServiceContent();
		JSONObject jo = new JSONObject(EntityUtils.toString(respGETService.getEntity()));
		int count = jo.getInt("count");
		Reporter.log("Count for Search: " + count, true);
		JSONArray ja = jo.getJSONArray("results");
		for (int i = 0; i < ja.length(); i++) {
			String pName = ja.getJSONObject(i).getString("name");
			System.out.println(pName);
		}
		System.out.println(ja.length());

		for (int j = 0; j < 1; j++) {
			JSONObject po = ja.getJSONObject(j);
			String[] attr = JSONObject.getNames(po);
			ArrayAsserts.assertArrayEquals(expAttributes, attr);
			for (int x = 0; x < attr.length; x++) {
				System.out.println("Attributes: " + attr[x]);
			}
		}

		Assert.assertEquals("Content-Type: application/json", respGETService.getEntity().getContentType().toString());

	}
	
	@Test(priority = 4, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ALL_Test_006() throws ClientProtocolException, IOException, JSONException {
		respCode = getServiceStatusCode("http://swapi.co/api/plants");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag); 
		Assert.assertEquals(respCode, HttpStatus.SC_NOT_FOUND);
		
	}
}
