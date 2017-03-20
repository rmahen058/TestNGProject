package com.rakesh.testng.training.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/*Critical Step Failure: We can use Assertions 
 * Using this assertions will stop the test executions as soon as it encounters assertion error.
 * 
 * Assert.assertEquals(actual, expected);
 * Assert.assertTrue(Boolean Condition, String Message)
 * Assert.assertTrue(Boolean Condition)
 */

/*Non Critical Step Failure: We can user Soft Assertions
 * Using soft assertions will not stop the test executions as soon as it encounters the error, insteal it will allow
 * us to collect the errors.
 * 
 */

public class AssertionsTestNGConcepts {
	
	SoftAssert softAssert;
	@Test
	public void registrationTest() {
		String expectedTitle = "Facebook Login";
		String actualTitle = "Facebook Login";
		
		Assert.assertEquals(actualTitle, expectedTitle);
		
		Assert.assertTrue(2>1, "Test Failed");
		
		Assert.assertTrue(2>11, "Test Failed");		
	}
	
	/*
	 * This method will use the Soft Assertion so that test will not fail upon assertion failure.
	 * But this will not report test failure, instead it will pass
	 */
	
	@Test
	public void loginTest() {
		softAssert = new SoftAssert();
		softAssert.assertEquals("SAP", "SAP");
		System.out.println("I am in the step after assertion success");
		softAssert.assertEquals("SAP", "WIPRO");
		System.out.println("I am in the step after assertion failure");
		
	}
	
	/*
	 * This method will use the Soft Assertion so that test will not fail upon assertion failure.
	 * But this will report test failure since we have implemented softAssert.assertAll();
	 */
	
	@Test
	public void loginTest1() {
		softAssert = new SoftAssert();
		softAssert.assertEquals("SAP", "SAP");
		System.out.println("I am in the step after assertion success");
		softAssert.assertEquals("SAP", "WIPRO");
		System.out.println("I am in the step after assertion failure");
		
		//Below statement reports the test failure, this must be collected/ implement at end of all the methods where we have used
		//Soft Assertions
		softAssert.assertAll();		
	}
	
	@Test
	public void assertFail() {
		Assert.fail(); //This is used to fail the test case.
	}

}
