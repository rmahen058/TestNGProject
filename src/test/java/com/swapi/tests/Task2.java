package com.swapi.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.swapi.dto.Films;
import com.swapi.dto.Planet;
import com.swapi.dto.Residents;
import com.swapi.utilities.RestAPITester;

public class Task2 extends RestAPITester {

	private static HttpResponse respGETService;

	@Test
	public static void Auto_SWAPI_GET_ALL_Planets()
			throws ClientProtocolException, IOException, ParseException, JSONException {

		String strBaseUrl = "http://swapi.co/api/planets/";
		int iLength = strBaseUrl.length();
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
				String strResult;
				objPlanet = new Planet();

				String strUrl = ja.getJSONObject(i).getString("url");
				int iLastIndex = strUrl.lastIndexOf("/");
				objPlanet.setiPlanetId(Integer.parseInt(strUrl.substring(iLength, iLastIndex)));

				strResult = validate(ja.getJSONObject(i).getString("name"));
				if (strResult != null)
					objPlanet.setStrPlanetName(strResult);

				strResult = validate(ja.getJSONObject(i).getString("diameter"));
				if (strResult != null)
					objPlanet.setlDiameter(Long.parseLong(strResult));

				strResult = validate(ja.getJSONObject(i).getString("population"));
				if (strResult != null)
					objPlanet.setlPopulation(Long.parseLong(strResult));

				JSONArray jaResidents = ja.getJSONObject(i).getJSONArray("residents");
				int iLengthTmp = jaResidents.toString().length();
				String[] arrResidentUrl = jaResidents.toString().substring(1, iLengthTmp - 1).split(",");
				List<Residents> lstRsdnts = new ArrayList<>();
				for (String strResidentUrl : arrResidentUrl) {
					lstRsdnts.add(getResidents(strResidentUrl.replaceAll("\"", "")));
				}
				objPlanet.setObjResidents(lstRsdnts);

				lstPlanets.add(objPlanet);
			}
		} while (!strNextElmt.equalsIgnoreCase("null"));

		for (Planet objPlanetTmp : lstPlanets) {
			System.out.println("Planet Id : " + objPlanetTmp.getiPlanetId());
			System.out.println("Planet Name : " + objPlanetTmp.getStrPlanetName());
			System.out.println("Planet Population : " + objPlanetTmp.getlPopulation());
			System.out.println("Planet Diameter : " + objPlanetTmp.getlDiameter());

			for (Residents objRsdntsTmp : objPlanetTmp.getObjResidents()) {
				if (objRsdntsTmp != null) {
					System.out.println("\t" + "Residents Name = " + objRsdntsTmp.getStrName());
					System.out.println("\t" + "Residents Mass = " + objRsdntsTmp.getiMass());
					System.out.println("\t" + "Residents Height = " + objRsdntsTmp.getiHeigt());

					for (Films objFilmsTmp : objRsdntsTmp.getObjFilm()) {
						if (objFilmsTmp != null) {
							System.out.println("\t" + "\t" + "Film Title = " + objFilmsTmp.getStrTitle());
						}
					}
				}
			}
			System.out.println("\n *************************************** \n");
		}
	}

	private static Residents getResidents(String residentURL)
			throws ClientProtocolException, IOException, ParseException, JSONException {

		if (residentURL.isEmpty())
			return null;

		String strResult;
		Residents objResidents;
		// System.out.println("resident url = "+residentURL);
		getServiceStatusCode(residentURL);
		respGETService = getServiceContent();
		JSONObject jo = new JSONObject(EntityUtils.toString(respGETService.getEntity()));

		objResidents = new Residents();
		strResult = validate(jo.getString("name"));
		if (strResult != null)
			objResidents.setStrName(strResult);

		strResult = validate(jo.getString("height"));
		if (strResult != null)
			objResidents.setiHeigt(Integer.parseInt(strResult));

		strResult = validate(jo.getString("mass"));
		if (strResult != null)
			objResidents.setiMass(strResult);

		JSONArray jaFilms = jo.getJSONArray("films");
		int iLengthTmp = jaFilms.toString().length();
		String[] arrFilmsUrl = jaFilms.toString().substring(1, iLengthTmp - 1).split(",");
		List<Films> lstFilms = new ArrayList<>();
		for (String strFilmsUrl : arrFilmsUrl) {
			// System.out.println("Film names =
			// "+strResidentUrl.replaceAll("\"", ""));
			// objPlanet.setObjResidents(getResidents(strResidentUrl.replaceAll("\"",
			// "")));
			lstFilms.add(getFilms(strFilmsUrl.replaceAll("\"", "")));
		}

		objResidents.setObjFilm(lstFilms);

		return objResidents;
	}

	public static Films getFilms(String filmURL)
			throws ClientProtocolException, IOException, ParseException, JSONException {

		if (filmURL.isEmpty())
			return null;

		String strResult;
		Films objFilms;

		getServiceStatusCode(filmURL);
		respGETService = getServiceContent();
		JSONObject jo = new JSONObject(EntityUtils.toString(respGETService.getEntity()));

		objFilms = new Films();
		strResult = validate(jo.getString("title"));
		if (strResult != null)
			objFilms.setStrTitle(strResult);

		return objFilms;

	}

	private static String validate(String strParam) {
		return strParam.equalsIgnoreCase("unknown") ? null : strParam;
	}
}
