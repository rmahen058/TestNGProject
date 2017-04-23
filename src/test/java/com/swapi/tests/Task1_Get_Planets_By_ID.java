package com.swapi.tests;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
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

/**
 * This class holds all the test cases for GET By ID- for Planet resources
 * 
 * @author rakes
 *
 */
public class Task1_Get_Planets_By_ID extends RestAPITester {
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
		Reporter.log("\n" + "--------------------Start of test: " + Task1_Get_All_Planets.getCurrentMethodName()
				+ "--------------------" + "\n", reporterFlag);

		respCode = getServiceStatusCode("http://swapi.co/api");
		Reporter.log("HttpResponse Status Code of  BaseTest: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_OK);
	}

	/**
	 * 
	 * This method consumes SWAPI for Planet resources and validates Http Status
	 * Code: 200
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */

	@Test(priority = 1, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_Swapi_GET_ID_TEST_OO1() throws ClientProtocolException, IOException, JSONException {
		Reporter.log("\n" + "--------------------Start of test: " + Task1_Get_All_Planets.getCurrentMethodName()
				+ "--------------------" + "\n", reporterFlag);

		respCode = getServiceStatusCode("http://swapi.co/api/planets/1/");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_OK);
	}

	/**
	 * This method consumes for invalid planet id and validates Http Status
	 * Code: 404
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test(priority = 2, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_Swapi_GET_ID_TEST_OO2() throws ClientProtocolException, IOException, JSONException {
		Reporter.log("\n" + "--------------------Start of test: " + Task1_Get_All_Planets.getCurrentMethodName()
				+ "--------------------" + "\n", reporterFlag);

		respCode = getServiceStatusCode("http://swapi.co/api/planets/99/");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_NOT_FOUND);
	}

	/**
	 * This method validates planets attributes and content type of Http
	 * Response
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test(priority = 3, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_Swapi_GET_ID_TEST_OO3() throws ClientProtocolException, IOException, JSONException {

		Reporter.log("\n" + "--------------------Start of test: " + Task1_Get_All_Planets.getCurrentMethodName()
				+ "--------------------" + "\n", reporterFlag);

		String[] expAttributes = { "films", "edited", "created", "climate", "rotation_period", "url", "population",
				"orbital_period", "surface_water", "diameter", "gravity", "name", "residents", "terrain" };
		respCode = getServiceStatusCode("http://swapi.co/api/planets/1/");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_OK);

		respGETService = getServiceContent();
		JSONObject jo = new JSONObject(EntityUtils.toString(respGETService.getEntity()));
		String[] pAttribute = JSONObject.getNames(jo);
		Reporter.log("Attributes are as below:", reporterFlag);
		for (int i = 0; i < pAttribute.length; i++) {
			Reporter.log("Attribute: " + pAttribute[i], reporterFlag);
		}

		ArrayAsserts.assertArrayEquals(expAttributes, pAttribute);
		Assert.assertEquals("Content-Type: application/json", respGETService.getEntity().getContentType().toString());
	}

	/**
	 * This method validates the contents of Http Response
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test(priority = 4, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_Swapi_GET_ID_TEST_OO4() throws ClientProtocolException, IOException, JSONException {

		Reporter.log("\n" + "--------------------Start of test: " + Task1_Get_All_Planets.getCurrentMethodName()
				+ "--------------------" + "\n", reporterFlag);

		respCode = getServiceStatusCode("http://swapi.co/api/planets/1/");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_OK);

		respGETService = getServiceContent();
		String strResp = EntityUtils.toString(respGETService.getEntity());
		JSONObject jo = new JSONObject(strResp);

		Assert.assertEquals(jo.getString("name"), "Tatooine");
		Assert.assertEquals(jo.getString("rotation_period"), "23");
		Assert.assertEquals(jo.getString("orbital_period"), "304");
		Assert.assertEquals(jo.getString("diameter"), "10465");
		Assert.assertEquals(jo.getString("climate"), "arid");
		Assert.assertEquals(jo.getString("gravity"), "1 standard");
		Assert.assertEquals(jo.getString("terrain"), "desert");
		Assert.assertEquals(jo.getString("surface_water"), "1");
		Assert.assertEquals(jo.getString("population"), "200000");
		Assert.assertEquals(jo.getString("created"), "2014-12-09T13:50:49.641000Z");
		Assert.assertEquals(jo.getString("edited"), "2014-12-21T20:48:04.175778Z");
		Assert.assertEquals(jo.getString("url"), "http://swapi.co/api/planets/1/");

		String[] expRes = { "http://swapi.co/api/people/1/", "http://swapi.co/api/people/2/",
				"http://swapi.co/api/people/4/", "http://swapi.co/api/people/6/", "http://swapi.co/api/people/7/",
				"http://swapi.co/api/people/8/", "http://swapi.co/api/people/9/", "http://swapi.co/api/people/11/",
				"http://swapi.co/api/people/43/", "http://swapi.co/api/people/62/" };

		JSONArray jaRes = jo.getJSONArray("residents");
		int iLengthTmp = jaRes.toString().length();
		String[] strRes = jaRes.toString().toString().substring(1, iLengthTmp - 1).split(",");

		for (int i = 0; i < strRes.length; i++) {
			Assert.assertEquals(strRes[i].replaceAll("\"", ""), expRes[i]);
		}
		;

		String[] expFilms = { "http://swapi.co/api/films/5/", "http://swapi.co/api/films/4/",
				"http://swapi.co/api/films/6/", "http://swapi.co/api/films/3/", "http://swapi.co/api/films/1/" };

		JSONArray jaFilms = jo.getJSONArray("films");
		iLengthTmp = jaFilms.toString().length();
		String[] strFilms = jaFilms.toString().toString().substring(1, iLengthTmp - 1).split(",");
		for (int i = 0; i < strFilms.length; i++) {
			Assert.assertEquals(strFilms[i].replaceAll("\"", ""), expFilms[i]);
		}
	}

	/**
	 * This method tests the search field functionality for a valid search
	 * parameter
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test(priority = 5, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ID_Test_005() throws ClientProtocolException, IOException {
		Reporter.log("\n" + "--------------------Start of test: " + Task1_Get_All_Planets.getCurrentMethodName()
				+ "--------------------" + "\n", reporterFlag);

		respCode = getServiceStatusCode("http://swapi.co/api/planets/1/?search=too");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_OK);

	}

	/**
	 * This method tests the search field functionality for invalid search
	 * parameter
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test(priority = 6, dependsOnMethods = "Auto_SWAPI_GET_Base_Test_001")
	public static void Auto_SWAPI_GET_ID_Test_006() throws ClientProtocolException, IOException {
		Reporter.log("\n" + "--------------------Start of test: " + Task1_Get_All_Planets.getCurrentMethodName()
				+ "--------------------" + "\n", reporterFlag);

		respCode = getServiceStatusCode("http://swapi.co/api/planets/1/?search=toy");
		Reporter.log("HttpResponse Status Code: " + respCode, reporterFlag);
		Assert.assertEquals(respCode, HttpStatus.SC_NOT_FOUND);

	}
}
