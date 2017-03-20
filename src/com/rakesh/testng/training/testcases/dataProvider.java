package com.rakesh.testng.training.testcases;

import org.testng.annotations.DataProvider;

public class dataProvider {
	
	@DataProvider (name = "userRegistration", parallel = true)
	public static Object[][] getData() {
		Object[][] data = new Object[3][4];
		data[0][0] = "uName1";
		data[0][1] = "pWord1";
		data[0][2] = "Chrome";
		data[0][3] = 12345;
		
		data[1][0] = "uName2";
		data[1][1] = "pWord2";
		data[1][2] = "Mozilla";
		data[1][3] = 23456;
		
		data[2][0] = "uName3";
		data[2][1] = "pWord3";
		data[2][2] = "Safari";
		data[2][3] = 34567;
		
		return data;
	}
	
	@DataProvider (name = "locData")
	public Object[][] getData1() {
		Object[][] data = new Object[2][2];
		data[0][0] = "Bentonville";
		data[0][1] = "Arkansas";
		
		data[1][0] = "Dallas";
		data[1][1] = "Texas";
		return data;
	}

}
