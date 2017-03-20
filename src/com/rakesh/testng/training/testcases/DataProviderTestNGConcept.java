package com.rakesh.testng.training.testcases;

import org.testng.annotations.Test;

public class DataProviderTestNGConcept {
	
	/*
	 * This test will utilize the parameterized data in DataProvider and iterate the test
	 */
	@Test ( dataProviderClass = dataProvider.class, dataProvider = "userRegistration", priority = 1) 
	public void signUp( //All the data which is parameterized will coming as input arguements
		String uName,
		String pWord,
		String browser,
		int zip) {
		System.out.println("Test 1: " + uName + "---" + pWord + "---" + browser + "---" + zip);
	}
	
	@Test (dataProviderClass = dataProvider.class, dataProvider = "locData", priority =2)
	public void formFill(
			String city,
			String state) {
		System.out.println(city + "---" + state);
	}
}
