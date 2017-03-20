package com.rakesh.testng.training.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParameterizationTestNGConcept {
	/*
	 * @DataProvider - Will help us parameterize the test
	 * We are using Object array since it can store data of any type
	 */
	@DataProvider (parallel = true)
	public Object[][] getData() {
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
	
	@DataProvider
	public Object[][] getData1() {
		Object[][] data = new Object[2][2];
		data[0][0] = "Bentonville";
		data[0][1] = "Arkansas";
		
		data[1][0] = "Dallas";
		data[1][1] = "Texas";
		return data;
	}
	
	/*
	 * This test will utilize the parameterized data in DataProvider and iterate the test
	 */
	@Test (dataProvider = "getData", priority = 1) //This will fetch from getData method
	public void signUp( //All the data which is parameterized will coming as input arguements
		String uName,
		String pWord,
		String browser,
		int zip) {
		System.out.println("Test 1: " + uName + "---" + pWord + "---" + browser + "---" + zip);
	}
	
	@Test (dataProvider = "getData1", priority =2)
	public void formFill(
			String city,
			String state) {
		System.out.println(city + "---" + state);
	}
}
