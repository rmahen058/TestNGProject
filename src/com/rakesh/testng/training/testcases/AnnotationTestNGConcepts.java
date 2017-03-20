package com.rakesh.testng.training.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AnnotationTestNGConcepts {
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println(" I am in Before Suite Method");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println(" I am in Before Test Method");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println(" I am in Before Method");
	}
	
	@Test (priority = 1)
	public void loginTest(){
		System.out.println("I am in loginTest method");
		throw new SkipException("I am testing Skipping the Test.");
	}
	
	@Test (priority = 2, dependsOnMethods = {"loginTest"})
	public void changePasswordTest() {
		System.out.println("I am in changePasswordTest method");
	}
	
	@Test (priority = 3, dependsOnMethods = {"loginTest", "changePasswordTest"})
	public void logoutTest() {
		System.out.println(" I am in Logout Method");
	}
		
	@AfterMethod
	public void afterMethod() {		
		System.out.println("I am in After Method");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println(" I am in After Test method");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println(" I am in Afer Suite Method");
	}

}
