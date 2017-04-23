package com.swapi.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.swapi.dto.Planet;
import com.swapi.utilities.RestAPITester;

/**
 * This class holds all the test method for Get All Planets
 * 
 * @author rakes
 *
 */
public class Task1_Get_All_Planets extends RestAPITester {
	public static boolean reporterFlag = true;
	public static HttpResponse respGETService;
	public static int respCode;

	/**
	 * This method confirms SWAPI is up and running
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public static void Auto_SWAPI_GET_Base_Test_001() throws ClientProtocolException, IOException {
		Reporter.log("--------------------Start of test: " + getCurrentMethodName() + "--------------------" + "\n",
				reporterFlag);

		respCode = getServiceStatusCode("http://swapi.co/api");
		Reporter.log("HttpResponse Status Code of  BaseTest: " + respCode, reporterFlag); // We can use log4J for logging
		Assert.assertEquals(respCode, HttpStatus.SC_OK);
	}

	/**
	 * This method consumes SWAPI for Planet resources and validates Http Status
	 * Code: 200
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test(priority = 1, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ALL_Test_002() throws ClientProtocolException, IOException, JSONException {
		Reporter.log(
				"\n" + "--------------------Start of test: " + getCurrentMethodName() + "--------------------" + "\n",
				reporterFlag);
		respCode = getServiceStatusCode("http://swapi.co/api/planets");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_OK);
	}

	/**
	 * This method consumes SWAPI for Planet resources and validates attributes
	 * of the planet resources
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 */
	@Test(priority = 2, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ALL_Test_003()
			throws ClientProtocolException, IOException, ParseException, JSONException {
		Reporter.log(
				"\n" + "--------------------Start of test: " + getCurrentMethodName() + "--------------------" + "\n",
				reporterFlag);
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
				Reporter.log("Attribute: " + attr[x], reporterFlag);
			}
		}
		Assert.assertEquals("Content-Type: application/json", respGETService.getEntity().getContentType().toString());
	}

	/**
	 * This method consumes SWAPI for Planet resources and validates search
	 * functionality of the planet resources
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 */
	@Test(priority = 3, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ALL_Test_004()
			throws ClientProtocolException, IOException, ParseException, JSONException {
		Reporter.log(
				"\n" + "--------------------Start of test: " + getCurrentMethodName() + "--------------------" + "\n",
				reporterFlag);

		String searchString = "rd";
		Assert.assertEquals(getServiceStatusCode("http://swapi.co/api/planets/?search=" + searchString),
				HttpStatus.SC_OK);

		respGETService = getServiceContent();
		JSONObject jo = new JSONObject(EntityUtils.toString(respGETService.getEntity()));
		int pCount = jo.getInt("count");
		Reporter.log("Number of Planets containing character \"" + searchString + "\": " + pCount, true);

		JSONArray ja = jo.getJSONArray("results");
		for (int i = 0; i < ja.length(); i++) {
			String pName = ja.getJSONObject(i).getString("name");
			Assert.assertTrue(pName.contains(searchString));
			Reporter.log("Name of the planet: " + pName, reporterFlag);
		}

	}

	/**
	 * This method valiates for duplicate planet names for GET ALL
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 */
	@Test(priority = 4, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ALL_Test_005()
			throws ClientProtocolException, IOException, ParseException, JSONException {
		Reporter.log(
				"\n" + "--------------------Start of test: " + getCurrentMethodName() + "--------------------" + "\n",
				reporterFlag);

		String strBaseUrl = "http://swapi.co/api/planets/";
		String strNextElmt = strBaseUrl;
		List<Planet> lstPlanets = new ArrayList<Planet>();
		Planet objPlanet;

		do {
			getServiceStatusCode(strNextElmt);
			respGETService = getServiceContent();
			JSONObject jo = new JSONObject(EntityUtils.toString(respGETService.getEntity()));

			strNextElmt = jo.getString("next");
			JSONArray ja = jo.getJSONArray("results");
			for (int i = 0; i < ja.length(); i++) {

				objPlanet = new Planet();

				objPlanet.setStrPlanetName(ja.getJSONObject(i).getString("name"));

				lstPlanets.add(objPlanet);
			}
		} while (!strNextElmt.equalsIgnoreCase("null"));

		Set<String> set = new HashSet<String>();
		for (Planet objPlanetTmp : lstPlanets) {
			set.add(objPlanetTmp.getStrPlanetName());
			Reporter.log("Planet Name: " + objPlanetTmp.getStrPlanetName(), reporterFlag);
		}
		Assert.assertEquals(set.size(), lstPlanets.size());
		Reporter.log("No duplicate Planet Name found.");
	}

	/**
	 * This method consumes SWAPI for Planet resources and validates return code
	 * is 404 NOT FOUND for invalid
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test(priority = 4, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ALL_Test_006() throws ClientProtocolException, IOException, JSONException {
		Reporter.log(
				"\n" + "--------------------Start of test: " + getCurrentMethodName() + "--------------------" + "\n",
				reporterFlag);
		respCode = getServiceStatusCode("http://swapi.co/api/plants");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_NOT_FOUND);
	}

	/**
	 * This method is the utility to get the method name
	 * 
	 * @return
	 */
	public static String getCurrentMethodName() {
		return Thread.currentThread().getStackTrace()[2].getClassName() + "."
				+ Thread.currentThread().getStackTrace()[2].getMethodName();
	}
}
