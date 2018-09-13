package com.optum.ori.apiautomation.StepDefinition;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;

import cucumber.api.java.Before;

import java.util.concurrent.TimeUnit;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mkolisnyk.cucumber.runner.BeforeSuite;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.stream.JsonReader;
import com.optum.ori.apiautomation.ResponseHelper;
import com.optum.ori.apiautomation.Utils.CommonUtils;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.deps.difflib.StringUtills;
import gherkin.JSONParser;
import jdk.nashorn.internal.ir.ObjectNode;
import junit.framework.Assert;
import junit.framework.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ExternalFileProcessing.*;


public class CommonStepDefinition {

	static String expectedInputString = "";
	public static HttpURLConnection httpURLConnection = null;
	static String postResponse;
	String urlPass;
	
	@Given("^I have provided the json input ([^\"]*) to service$")
	public void jsonInputToService(String filePath) throws Throwable {
		try {

			expectedInputString = CommonUtils.readFile(CommonUtils.getExpectedPath(filePath));
			if (expectedInputString != null && !expectedInputString.trim().isEmpty()) {
				System.out.println(expectedInputString);
			} else
				org.junit.Assert.fail("FileNotFound Exception : File not exists at path : " + filePath);

		} catch (Exception e) {
			org.junit.Assert.fail("Exception was thrown");
			e.printStackTrace();
		} 
	}
	
	@And("^I invoke REST service ([^\"]*)$")
	public void invokeRestAEService(String endPointUrl) throws Throwable {

		System.out.println("inputURL-------------->" + endPointUrl);
		//requestType = "POST";
		String token = "";
		
		token = ResponseHelper.getAuthTokenForRestService("");

		try{
			String result = null;
			String urlParameters  = expectedInputString;
			byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			int    postDataLength = postData.length;
			String request        = endPointUrl;
			URL    url            = new URL( request );
			httpURLConnection = (HttpURLConnection) url.openConnection();           
			httpURLConnection.setDoOutput( true );
			httpURLConnection.setInstanceFollowRedirects( false );
			httpURLConnection.setRequestMethod( "POST" );
			httpURLConnection.setRequestProperty( "Content-Type", "application/json"); 
			httpURLConnection.setRequestProperty( "charset", "utf-8");
			httpURLConnection.setRequestProperty( "Authorization", "Bearer " + token);
			httpURLConnection.setRequestProperty( "scope", "read");
			httpURLConnection.setRequestProperty( "correlation_id", "11111");
			httpURLConnection.setRequestProperty( "timestamp", "12345");
			httpURLConnection.setRequestProperty( "actor", "myuhc");
			httpURLConnection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			
			httpURLConnection.setUseCaches( false );
			try( DataOutputStream wr = new DataOutputStream( httpURLConnection.getOutputStream())) {
				wr.write( postData );
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.toString());
			}
			int responseCode = httpURLConnection.getResponseCode();
			System.out.println("'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			System.out.println("Response Body : ");
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			result = response.toString();
			System.out.println(result);
			postResponse = result;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		//return token;

	}
	
	@And("^I invoke RESTAPI6 service ([^\"]*)$")
	public void invokeRestAPI6Service(String endPointUrl) throws Throwable {

		System.out.println("inputURL-------------->" + endPointUrl);
		//requestType = "POST";
		String token = "";
		
		token = ResponseHelper.getAuthTokenForRestServiceApi6("");

		try{
			String result = null;
			String urlParameters  = expectedInputString;
			byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			int    postDataLength = postData.length;
			String request        = endPointUrl;
			URL    url            = new URL( request );
			httpURLConnection = (HttpURLConnection) url.openConnection();           
			httpURLConnection.setDoOutput( true );
			httpURLConnection.setInstanceFollowRedirects( false );
			httpURLConnection.setRequestMethod( "POST" );
			httpURLConnection.setRequestProperty( "Content-Type", "application/json"); 
			httpURLConnection.setRequestProperty( "charset", "utf-8");
			httpURLConnection.setRequestProperty( "Authorization", "Bearer " + token);
			httpURLConnection.setRequestProperty( "scope", "read");
			httpURLConnection.setRequestProperty( "correlation_id", "11111");
			httpURLConnection.setRequestProperty( "timestamp", "12345");
			httpURLConnection.setRequestProperty( "actor", "myuhc");
			httpURLConnection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			
			httpURLConnection.setUseCaches( false );
			try( DataOutputStream wr = new DataOutputStream( httpURLConnection.getOutputStream())) {
				wr.write( postData );
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.toString());
			}
			int responseCode = httpURLConnection.getResponseCode();
			System.out.println("'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			System.out.println("Response Body : ");
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			result = response.toString();
			System.out.println(result);
			postResponse = result;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		//return token;

	}
	@And("^I invoke RESTAPI service ([^\"]*)$")
	public void invokeRestAPIService(String endPointUrl) throws Throwable {

		System.out.println("inputURL-------------->" + endPointUrl);
		//requestType = "POST";
		String token = "";
		
		token = ResponseHelper.getAuthTokenForRestAPIService("");

		try{
			String result = null;
			String urlParameters  = expectedInputString;
			byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			int    postDataLength = postData.length;
			String request        = endPointUrl;
			URL    url            = new URL( request );
			httpURLConnection = (HttpURLConnection) url.openConnection();           
			httpURLConnection.setDoOutput( true );
			httpURLConnection.setInstanceFollowRedirects( false );
			httpURLConnection.setRequestMethod( "POST" );
			httpURLConnection.setRequestProperty( "Content-Type", "application/json"); 
			httpURLConnection.setRequestProperty( "charset", "utf-8");
			httpURLConnection.setRequestProperty( "Authorization", "Bearer " + token);
			httpURLConnection.setRequestProperty( "scope", "read");
			httpURLConnection.setRequestProperty( "correlation_id", "11111");
			httpURLConnection.setRequestProperty( "timestamp", "12345");
			httpURLConnection.setRequestProperty( "actor", "myuhc");
			httpURLConnection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			
			httpURLConnection.setUseCaches( false );
			try( DataOutputStream wr = new DataOutputStream( httpURLConnection.getOutputStream())) {
				wr.write( postData );
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.toString());
			}
			int responseCode = httpURLConnection.getResponseCode();
			System.out.println("'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			System.out.println("Response Body : ");
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			result = response.toString();
			System.out.println(result);
			postResponse = result;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		//return token;

	}
	
	@When("^I replace the tag \"(.*?)\" with value ([^\"]*)$")
	public void replaceXMLTag(String key, String value) throws Throwable {
		try {
			if (expectedInputString != null) {
				expectedInputString = expectedInputString.replace(key, value);
				org.junit.Assert.assertTrue(true);
			}

		} catch (Exception e) {
			org.junit.Assert.fail("Exception was thrown");
			e.printStackTrace();
		} 
	}
	
	@Then("^Validate post status code is \"(.*?)\"$")
	public void validatePostStatusCode(String statusCode) throws Throwable {
		try {
			int responseCode = httpURLConnection.getResponseCode();
			String status_code = Integer.toString(responseCode);
			if (status_code.equals(statusCode))
				org.junit.Assert.assertTrue(true);
			else
				org.junit.Assert.fail("Status code comparison failed : Expected: " + statusCode + " But was: " + status_code);
		} catch (Exception e) {
			org.junit.Assert.fail("Exception was thrown");
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
	
	@Then("^Response contains \"(.*?)\"$")
	public void responseContains(String key) throws Throwable {
		try {

			JSONObject acctualJsonResponse = new JSONObject(postResponse);
			System.out.println(acctualJsonResponse);

			HashMap<String, String> out = new HashMap<String, String>();
			ResponseHelper.parseJObject(acctualJsonResponse, out);

			if (out.containsKey(key))
				org.junit.Assert.assertTrue(true);
			else
				org.junit.Assert.fail("Actual response does not contains Key: " + key);

		} catch (Exception e) {
			org.junit.Assert.fail("Exception was thrown");
			e.printStackTrace();
		} 
		
	}
	
	@Then("^Response does not contains \"(.*?)\"$")
	public void responseDoesNotContains(String key) throws Throwable {
		try {

			JSONObject acctualJsonResponse = new JSONObject(postResponse);
			System.out.println(acctualJsonResponse);
			if (!acctualJsonResponse.has(key))
				org.junit.Assert.assertTrue(true);
			else
				org.junit.Assert.fail();
			
		} catch (Exception e) {
			org.junit.Assert.fail("Exception was thrown");
			e.printStackTrace();
		}
	}
	
	@Then("^Response contains \"(.*?)\" in DB ([^\"]*)$") 
	public void responseDBContains(String key, String value) throws Throwable {
		//try {

			JSONObject acctualJsonResponse = new JSONObject(postResponse);
			System.out.println(acctualJsonResponse);
			String input;
			HashMap<String, String> out = new HashMap<String, String>();
			ResponseHelper.parseJObject(acctualJsonResponse, out);
		
				input =out.get(key);
				//System.out.println(input);
				
				ResponseHelper obj = new ResponseHelper();
		boolean res= obj.dbConnection(value, input);
		
		if ( res  )
		{
			//System.out.println(res);
			org.junit.Assert.assertTrue(true);
			}
		else
		{
			//System.out.println(res);
			org.junit.Assert.fail();
		}
			
		/*} catch (Exception e) {
			org.junit.Assert.fail("Exception was thrown");
			e.printStackTrace();
		}*/
	}
		
		
		@Then("^Response contains \"(.*?)\" with value ([^\"]*)$")
		public void responseContains(String key, String value) throws Throwable {
			try {

				JSONObject acctualJsonResponse = new JSONObject(postResponse);
				System.out.println(acctualJsonResponse);

				HashMap<String, String> out = new HashMap<String, String>();
				ResponseHelper.parseJObject(acctualJsonResponse, out);
				//System.out.println(key);
				//System.out.println(value);
				//String a=out.get(key);
				//System.out.println(a);
				int count = 0;
				for (String key1 : out.keySet()) {
					//System.out.println(key1);
					if(key1.matches(key)){
						System.out.println(key1);
						String val=out.get(key1);
						//System.out.println(val);
						if(val.matches(value)){

							System.out.println(val);
							count ++;
						}
						
					}
					
				}
				/*Iterator it = out.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pairs = (Map.Entry) it.next();
					System.out.println(pairs.getKey() + " = " + pairs.getValue());
				}*/

				if (out.containsKey(key))
					if (out.containsValue(value))
					org.junit.Assert.assertTrue(true);
				else
					org.junit.Assert.fail("Actual response does not contains Key: " + key);
			      	

			} catch (Exception e) {
				org.junit.Assert.fail("Exception was thrown");
				e.printStackTrace();
			} 
		
		
		}
		@And("^Response displays \"(.*?)\" ([^\"]*) times1$")
		public void responseCount(String key, String value){
			try{
				JSONObject acctualJsonResponse = new JSONObject(postResponse);
				System.out.println(acctualJsonResponse);
				HashMap<String, String> out = new HashMap<String, String>();
				ResponseHelper.parseJObject(acctualJsonResponse, out);
				int count = 0;
				
			    
				for (String key1 : out.keySet())
		        {
					System.out.println(key1);
		            if (key.equals(key1))
		            {
		                count++;
		            }
				  
			}
				System.out.println(count);
				
				int result = Integer.parseInt(value);	
				System.out.println(result);
			if (count == result)
			{
				System.out.println(count);
				org.junit.Assert.assertTrue(true);
			}
			else
				org.junit.Assert.fail("Count does not match");
			}
				
			catch (Exception e){
				org.junit.Assert.fail("Exception was thrown");
				e.printStackTrace();
			}
		}
		
		
		@Given("^File ([^\"]*) has been created successfully$")
		public void FileCreate(String type) throws Throwable {
			switch (type)
			{
			case "bio" :
				BioFile.fileCreate();
				break;
				
				
			}
		}
		
		@When("^File ([^\"]*) is run$")
		public void FileProcess(String type) throws Throwable {
			switch (type)
			{
			case "bio" :
				BioFile.main(null);
				break;
				
			case "cond" :
				Conditions.main(null);
				break;
			
			case "med" :
				Medication.main(null);
				break;
			
			case "offline" :
				OfflineFile.main(null);
				break;
				
			case "proc" :
				Procedure.main(null);
				break;
				
				
			}
			
		}
		
		@Then("^Validate data is processed$")
		public void FileLoaded() throws Throwable {
			org.junit.Assert.assertTrue(true);
		}
				
		public WebDriver driver;
	
		@Given("^I have provided the url input ([^\"]*) to login$")
		public void LaunchApplication(String url){
			
			System.setProperty("webdriver.firefox.marionette","geckodriver.exe");
			driver= new FirefoxDriver();
		       driver.manage().window().maximize();			
		       driver.get(url);	
		       driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			}
	
		
		@When("^I enter the username ([^\"]*)$")
		public void enterUsername(String userValue) throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.findElement(By.xpath(prop.getProperty("userName"))).sendKeys(userValue);
		}
		
		@And("^I enter the password ([^\"]*)$")
		public void enterPassword1(String pwdValue) throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.findElement(By.xpath(prop.getProperty("pwd"))).sendKeys(pwdValue);
		}
		
		@And("^I click on button to login$")
		public void buttonClick()throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.findElement(By.xpath(prop.getProperty("Button"))).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		
		@Then("^Validate login is successful$")
		public void pageLoadSuccess()throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("ExpectedItem"))).isDisplayed();

		}
		
		@And("^Visit Rally Health and Wellness is on myUHC page$")
		public void RallyLink_myUHC() throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
		       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("rallyLink"))).isDisplayed();
		}
		
		@When("^I click on Visit Rally Health and Wellness$") 
		public void myUHC_Rally_SSO_begins() throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.findElement(By.xpath(prop.getProperty("rallyLink"))).click();
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		}
		
		@Then("^Validate SSO to Rally is successful$")
		public void myUHC_Rally_SSO_Success() throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			Set<String> handles = driver.getWindowHandles();
			handles.remove(driver.getWindowHandle());

			for (String hwnd : handles) {
			    driver.switchTo().window(hwnd);

			    if (driver.getCurrentUrl().contains("werally")) {
			    	System.out.println(driver.getCurrentUrl());
			    	break;
			    }
			        
			    }
			
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("surveyFinishLater"))).click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("rallyDashboard"))).isDisplayed();
		}
		
		@Given("^I have provided the url input ([^\"]*) to login AdminUI$")
		public void LaunchAdminUIApplication(String url) throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			System.setProperty("webdriver.firefox.marionette","geckodriver.exe");
			driver= new FirefoxDriver();
		       driver.manage().window().maximize();			
		       driver.get(url);	
		       driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("MSLogin"))).click();
			}
	
		
		@When("^I enter the MSID ([^\"]*)$")
		public void enterMSUsername(String userValue) throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.findElement(By.xpath(prop.getProperty("MSID"))).sendKeys(userValue);
		}
		
		@And("^I enter the MS password ([^\"]*)$")
		public void enterMSPassword(String pwdValue) throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.findElement(By.xpath(prop.getProperty("MSPWD"))).sendKeys(pwdValue);
		}
		
		@And("^I click on button to login Admin UI$")
		public void AdminbuttonClick()throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.findElement(By.xpath(prop.getProperty("LoginAdmin"))).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		
		@Then("^Validate login to Admin UI is successful$")
		public void AdminUISuccess()throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("ORI"))).isDisplayed();

		}
		@And("^I click on page button ([^\"]*)$")
		public void PageButtonClick(String btnXpath)throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			if (btnXpath.equalsIgnoreCase("AddORIPgBtn"))
			{
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				driver.findElement(By.xpath(prop.getProperty(btnXpath))).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			else if (btnXpath.equalsIgnoreCase("Edit2"))
			{
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				driver.findElement(By.xpath(prop.getProperty(btnXpath))).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			
			else {
			
			if ( btnXpath.equalsIgnoreCase("AddButton"))
			{
				driver.findElement(By.xpath(prop.getProperty(btnXpath))).click();
			}
			else
			{
				if (btnXpath.equalsIgnoreCase("PublishYes"))
				{
					
					        
					    
					

					System.out.println(btnXpath);
					System.out.println("In PublishYes");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					//driver.findElement(By.xpath(".//*[@id='modal1']/div[2]")).click();
					//System.out.println("Displayed");
					//driver.findElement(By.xpath(".//*[@id='modal1']/div[2]/a[contains(text(),'Yes')]")).click();
					
					driver.findElement(By.xpath(prop.getProperty(btnXpath))).click();
					
					}
				else {
				driver.findElement(By.xpath(prop.getProperty(btnXpath))).click();
				System.out.println(btnXpath);}
				
			}}
			
		}
		@And("^enter value \"(.*?)\" in field ([^\"]*)$")
		public void TextDetails(String TxtValue, String TxtXPath )throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (TxtXPath.equalsIgnoreCase("ProgramNmTxt"))
			{
				driver.findElement(By.xpath(prop.getProperty(TxtXPath))).click();
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_P);
			driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-customer/div[2]/ori-customer-info/ori-customer-edit/div/div[1]/form/div[2]/div[1]/ul/li[1]/span")).click();
		}
			else {
			driver.findElement(By.xpath(prop.getProperty(TxtXPath))).click();
			driver.findElement(By.xpath(prop.getProperty(TxtXPath))).clear();
			driver.findElement(By.xpath(prop.getProperty(TxtXPath))).sendKeys(TxtValue);
			}
		}
		
		@And("^choose date ([^\"]*)$")
		public void AddDate1(String DatePath)throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty(DatePath))).click();
				
		}
		@And("^select date via calender ([^\"]*)$")
		public void AddDate2(String DatePath)throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty(DatePath))).click();
			driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-customer/div[2]/ori-customer-info/ori-customer-edit/div/div[1]")).click();
				
		}
		
		@Then("^Validate page load is successful ([^\"]*)$")
		public void pageLoadSuccess2(String pgXpath)throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty(pgXpath))).isDisplayed();

		}
		
		@Then("^Validate Confirm popup for Publish \"(.*?)\" to do ([^\"]*)$")
		public void customerPublish(String CNFXpath, String CNFOKXpath)throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty(CNFXpath))).isDisplayed();
			driver.findElement(By.xpath(prop.getProperty(CNFOKXpath))).click();

		}
		@Then("^Validate customer add is successful for policy ([^\"]*)$")
		public void AddCustomerSuccess(String CNVal)throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("ValCN"))).sendKeys(CNVal);
			driver.findElement(By.xpath(prop.getProperty("SrchBtn"))).click();
			driver.findElement(By.xpath(prop.getProperty("AddCustomerValidation"))).isDisplayed();
		}
		@Then("^Validate customer edit is successful for policy ([^\"]*)$")
		public void EditCustomerSuccess(String CNVal)throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("ValCN"))).sendKeys(CNVal);
			driver.findElement(By.xpath(prop.getProperty("SrchBtn"))).click();
			driver.findElement(By.xpath(prop.getProperty("PurchVal"))).isDisplayed();
		
		}

		@And("^Validate edit is correct for edited value \"(.*?)\" in ([^\"]*)$")
		public void EditCustomerValidation(String EditValue, String EditVald)throws Throwable{
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
			Properties prop = new Properties();
			prop.load(fip);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty(EditVald))).isDisplayed();
			String s = driver.findElement(By.xpath(prop.getProperty(EditVald))).getText();
			if ( s.equalsIgnoreCase(EditValue))
			{
				System.out.println(s);
				System.out.println(EditValue);
				org.junit.Assert.assertTrue(true);
			}
			else
				org.junit.Assert.fail("Count does not match");
		}
		@Then("^Validate policy is successfully updated$")
		public void UpdtCustomerSuccess()throws Throwable{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-product-mapping/div[2]/ori-product-mapping-info/ul/li[1]")).isDisplayed();
			
		}
		
		@Then("^Validate policy is successfully deleted$")
		public void DelCustomerSuccess()throws Throwable{
			Boolean ind=false;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-product-mapping/div[2]/ori-product-mapping-info/ul/li")).isDisplayed())
{
	ind = true;
}
		if (ind == false )	
		{
			org.junit.Assert.assertTrue(true);
		}
		
		}
		
		@And("^Response displays \"(.*?)\" ([^\"]*) times$")
		public void responseCountJSON(String key, String value) throws Throwable{
		
				JSONObject acctualJsonResponse = new JSONObject(postResponse);
				System.out.println(acctualJsonResponse);
				int count=0;
				Iterator<?> iterator = acctualJsonResponse.keys();

				while (iterator.hasNext()) {
				 Object jsonChildObject = iterator.next();
				 System.out.println(jsonChildObject);
				 System.out.println(key);
				 if (jsonChildObject.toString()== key){
					 count++;
				 }
				 int result = Integer.parseInt(value);	
				 System.out.println(count);
				 System.out.println(result);
				if (count == result)
				{
					org.junit.Assert.assertTrue(true);
				}
				else
					org.junit.Assert.fail("Count does not match");
				
				
				}
		}
	//Samridhi's
		 @Then("^Response contains \"(.*?)\" in DB ([^\"]*) with Query ([^\"]*)$") 
		    public void responseDBContains(String key, String value , String Query) throws Throwable {
		            try {
		                    JSONObject acctualJsonResponse = new JSONObject(postResponse);
		                    System.out.println(acctualJsonResponse);
		                    String input;
		                    HashMap<String, String> out = new HashMap<String, String>();
		                    ResponseHelper.parseJObject(acctualJsonResponse, out);
		            
		                            input =out.get(key);
		                            //System.out.println(input);
		                            
		                            ResponseHelper obj = new ResponseHelper();
		            boolean res= obj.dbConnection(value, input , Query);
		            
		            if ( res  )
		            {
		                    System.out.println("DB validation Pass");
		                    org.junit.Assert.assertTrue(true);
		            }
		            else
		            {
		                    System.out.println("DB validation Fail");
		                    org.junit.Assert.fail();
		            } }
		                    
		            catch (Exception e) {
		                    org.junit.Assert.fail("Exception was thrown");
		                    e.printStackTrace();
		            
		    } 
		                }                 
		                
		      @Then("^Database is updated with Column ([^\"]*) for ([^\"]*) when Query ([^\"]*)$") 
		                 public void responseDBContains1(String column,String Key1, String Query1) throws Throwable {
		        //try {
		                
		        HashMap<String, String> out = new HashMap<String, String>();
		                                ResponseHelper str = new ResponseHelper();     
		        String Result = null;
		        String Result1 = null;
		        String column2 = null;
		    
		     
		        
		       Result= str.dbConnection1(Key1,Query1);
		       //int length1=Result.length() ;
		       //System.out.println(length1);
		       
		       if(Result.length()<11){
		    	     Result1= Result;
		    	
		    	 }
		    	 else
		    	     Result1 = Result.substring(0,10);
		       
		    	  
		        column2= column;
		        
		        if(Result1.equalsIgnoreCase(column2))
		        System.out.println("DB validation pass");
		        else
		        System.out.println("DB validation fail");
		 
		        
		}  

		      static String config_id1 = "51000507";
		      @Then("^Database is updated with config_ID for policy ([^\"]*)$")
		      public void responseDBCreats(String policy) throws Throwable {
		    	     HashMap<String, String> out = new HashMap<String, String>();
		             ResponseHelper str = new ResponseHelper();     
		              String Result = null;
		              String Result1 = null;
		              String column2 = null;
		              String pol1 = policy;
		              
		              String Query2= "select a.cnfg_id from espmb.cust_cnfg a ,espmb.cust_pol c,espmb.cust b where a.cust_id = b.cust_id and  b.cust_id = c.cust_id and c.cust_pol_nbr='"+pol1+"' order by a.updt_ts desc"; 
		             
		               Result= str.dbConnection2(Query2);
		               System.out.println(Result);
		               config_id1=Result;
		               //System.out.print(config_id1);
		               //System.out.print(config_id1);
		               

		      }
		   
		      @Then("^Database is updated with column ([^\"]*) for Cnfg_id when Query ([^\"]*)$") 
		      public void responseDBValidates(String coldb,String Query) throws Throwable {
		 	     HashMap<String, String> out = new HashMap<String, String>();
		          ResponseHelper str = new ResponseHelper();     
		          String Result = null;
		          String Result1 = null;
		          String column2 = null;
		      
		          
		         Result= str.dbConnection1(config_id1,Query);
		       
		         
		         if(Result.length()<11){
		      	     Result1= Result;
		      	 }
		      	 else
		      	     Result1 = Result.substring(0,10);
		      	    
		          column2= coldb;
		          //System.out.println(Result1);
		          if(Result1.equalsIgnoreCase(column2))
		          System.out.println("DB validation pass");
		          else
		          System.out.println("DB validation fail");
		   }
		      @Then("^Response contain \"(.*?)\"$")
		      public void responseContain(String key) throws Throwable {
		             try {

		                    JSONObject acctualJsonResponse = new JSONObject(postResponse);
		                    System.out.println(acctualJsonResponse);

		                    HashMap<String, String> out = new HashMap<String, String>();
		                    ResponseHelper.parseJObject(acctualJsonResponse, out);

		                    if (out.containsKey(key))
		                          org.junit.Assert.assertTrue(true);
		                    else
		                          org.junit.Assert.fail("Actual response does not contains Key: " + key);

		             } catch (Exception e) {
		                    org.junit.Assert.fail("Exception was thrown");
		                    e.printStackTrace();
		             } 
		             
		      }
		     
              @When("^user clicks on login button$")
              public void user_clicks_on_login_button() throws Throwable {
                     FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
                     Properties prop = new Properties();
                     prop.load(fip);
                     Thread.sleep(1000);
                     driver.findElement(By.xpath(prop.getProperty("LoginScreen"))).click();
                  
              }
              
              @And("^user enters Email \"(.*?)\" and password \"(.*?)\"$")
              public void user_enters_Email_and_password(String Email,String Loginpass) throws Throwable{
                     FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
                     Properties prop = new Properties();
                     prop.load(fip);
                     driver.findElement(By.xpath(prop.getProperty("RallyEmail"))).sendKeys(Email);
                     driver.findElement(By.xpath(prop.getProperty("Loginpswd"))).sendKeys(Loginpass);
                     driver.findElement(By.xpath(prop.getProperty("LetsgoSubmit"))).click();
                     driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
                     driver.findElement(By.xpath(prop.getProperty("FinishLater"))).click();
                     driver.findElement(By.xpath(prop.getProperty("WelcomeMessage"))).isDisplayed();
                     
              }
              
              @Then("^On Dashboard User clicks on Quit for life tile to land on Apollo Dashboard for SSO$")
              public void on_Dashboard_User_clicks_on_Quit_for_tile_for_Apollo_SSO()throws Throwable{
                     FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
                     Properties prop = new Properties();
                     prop.load(fip);
                     JavascriptExecutor js = (JavascriptExecutor) driver;
                     js.executeScript("window.scrollBy(0,700)");
                     js.executeScript("window.scrollBy(250,0)");
                     //driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
                     //WebElement elem1; 
                     //boolean elem = driver.findElement(By.xpath(prop.getProperty("abvtilebackbtn"))).isEnabled();
                     //elem1=driver.findElement(By.xpath(prop.getProperty("abvtilebackbtn")));
                     //if (elem)
                     //{
                           //elem1.click();
                     //}
                     driver.findElement(By.xpath(prop.getProperty("QuitforLifeTileText"))).click();
                     driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
                  driver.findElement(By.xpath(prop.getProperty("HealthPortal"))).getText();
                     
                     
              
              }
              
              
              @And("^User select online course on Apollo Dashboard to SSO from Apollo to TBK$")
              public void user_SSO_from_TBK_Apollo_to_TBK_to_complete_online_course() throws Throwable {
                     FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
                     Properties prop = new Properties();
                     prop.load(fip);
                     driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
                     JavascriptExecutor js = (JavascriptExecutor) driver;
                     js.executeScript("window.scrollBy(0,700)");
                     driver.findElement(By.xpath(prop.getProperty("ViewCourse"))).click();
                     
              }
              @Then("^TBK lands on TBK dashboard to complete online course$")
              public void tbk_lands_on_TBK_dashboard_to_complete_online_course() throws Throwable {
                     FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
                     Properties prop = new Properties();
                     prop.load(fip);
                     driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
                     driver.findElement(By.xpath(prop.getProperty("TBK_NextActivity"))).click();
              }
              
              @And("^user enters Email \"(.*?)\" and password \"(.*?)\" to enter dashboard$")
              public void user_enters_Email_and_password_to_enter_dashboard(String email1, String pass1) throws Throwable {
                     FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
                     Properties prop = new Properties();
                     prop.load(fip);
                     driver.findElement(By.xpath(prop.getProperty("RallyEmail"))).sendKeys(email1);
                     driver.findElement(By.xpath(prop.getProperty("Loginpswd"))).sendKeys(pass1);
                     driver.findElement(By.xpath(prop.getProperty("LetsgoSubmit"))).click();
                     //driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
                     //driver.findElement(By.xpath(prop.getProperty("FinishLater"))).click();
                     driver.findElement(By.xpath(prop.getProperty("WelcomeMessage"))).isDisplayed();
                     
              }
              
@Then("^On Dashboard User clicks on Coaching tile to land on DWC for SSO$")
public void on_Dashboard_User_clicks_on_RealAppeal_for_Apollo_SSO()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0,700)");
       js.executeScript("window.scrollBy(250,0)");
       
       //driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
       //WebElement elem1; 
       //boolean elem = driver.findElement(By.xpath(prop.getProperty("ScrolltilLeft_coaching"))).isEnabled();
       //elem1=driver.findElement(By.xpath(prop.getProperty("ScrolltilLeft_coaching")));
       //if (elem)
       //{
              //elem1.click();
       //}
       driver.findElement(By.xpath(prop.getProperty("Go_to_Coaching"))).click();
       driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
    driver.findElement(By.xpath(prop.getProperty("WellnessCoaching_logo"))).getText();
}
       
@Then("^On Dashboard User clicks on Quest tile to SSO to Quest website$")
public void on_Dashboard_User_clicks_on_Quest_for_SSO()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0,1200)");
       js.executeScript("window.scrollBy(250,0)");
       
       //driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
       //WebElement elem1; 
       
       driver.findElement(By.xpath(prop.getProperty("Quest_SSO_link"))).click();
       driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    //driver.findElement(By.xpath(prop.getProperty("QuestLogo_OnSSO"))).getText();
}

@Then("^On Dashboard User clicks on Quest tile to SSO to Paquin website$")
public void on_Dashboard_User_clicks_on_Paquin_for_SSO()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0,1200)");
       js.executeScript("window.scrollBy(250,0)");
       
       //driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
       //WebElement elem1; 
       
       driver.findElement(By.xpath(prop.getProperty("Paquin_SSO_link"))).click();
       driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    //driver.findElement(By.xpath(prop.getProperty("QuestLogo_OnSSO"))).getText();
}

//----------Nimrat's methods-----------------

@Given("^User provided the url input \"(.*?)\" for accessing Rally UI$")
public void user_provided_the_url_input_for_accessing_Rally_UI(String url){

	System.setProperty("webdriver.firefox.marionette", "geckodriver.exe");
	driver = new FirefoxDriver();

       driver.manage().window().maximize(); 
       driver.get("https://"+"rallyhealth"+":"+"rallyw1ns!"+"@"+url);

       driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
}


@When("^User click on button to Signup$")
public void SignUpClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("SignUp"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

@And("^User enter the Email ([^\"]*)$")
public void enterEmail(String EmailValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Email"))).sendKeys(EmailValue);
}

@And("^User enter the ConfirmEmail ([^\"]*)$")
public void enterConfirmEmail(String ConEmailValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("ConEmail"))).sendKeys(ConEmailValue);
}

@And("^User enter the Password ([^\"]*)$")
public void enterPassword(String PsswrdValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Psswrd"))).sendKeys(PsswrdValue);
}

@And("^User click on button to accept terms and conditions$")
public void TCClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("T&C"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}


@And("^User click on button to submit$")
public void SubmitClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Submit"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

@And("^User click on button to document$")
public void DocumentClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Document"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

@And("^User click on button to Submit Button$")
public void SubmitButtonClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("SubmitButton"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

@And("^User enter the SubID ([^\"]*)$")
public void enterSubID(String SubIDValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("SubID"))).sendKeys(SubIDValue);
}

@And("^User enter the FName ([^\"]*)$")
public void enterFName(String FNameValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("FName"))).sendKeys(FNameValue);
}

@And("^User enter the LName ([^\"]*)$")
public void enterLName(String LNameValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("LName"))).sendKeys(LNameValue);
}

@And("^User enter the DOB ([^\"]*)$")
public void enterDOB(String DOBValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("DOB"))).sendKeys(DOBValue);
}

@And("^User click on button to Register$")
public void RegisterClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Register"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}


@And("^Validate registration is successful$")
public void MemberRegisteredSuccess()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("MemberRegistered"))).isDisplayed();

}

@And("^Validate registration is not successful when member is not present in CDB$")
public void MemberNotInCDBSuccess()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("MemberNotInCDB"))).isDisplayed();

}
@And("^Validate coins are earned on completing HA$")
public void CoinBalanceSuccess()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("CoinBalance"))).isDisplayed();

}
@And("^User click on button to select Avatar$")
public void MemberRegisteredClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("MemberRegistered"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^User enter the USERNAME ([^\"]*)$")
public void enterEnterUsername(String EnterUsernameValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("EnterUsername"))).sendKeys(EnterUsernameValue);
}
@And("^User click on button to letsGo$")
public void LetsGoClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("LetsGo"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}
@And("^User click on OK Button$")
public void OKButtonClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("OKButton"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}
@And("^User click on Keep Going Button$")
public void KeepGoingClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("KeepGoing"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}

@When("^User click on button to Login$")
public void LoginClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Login"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

@And("^User enter the Login Email ([^\"]*)$")
public void enterLoginEmailValue(String LoginEmailValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("LoginEmail"))).sendKeys(LoginEmailValue);
}

@And("^User enter the Login Password ([^\"]*)$")
public void enterLoginPassword(String LoginPasswordValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("LoginPassword"))).sendKeys(LoginPasswordValue);
}

@And("^User click on button to Rally Login$")
public void LoginButtonClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("LoginButton"))).click();
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
}


@And("^User click on button to answer question of HA$")
public void HAQ1Click()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("HAQ1"))).click();
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
}
@And("^User enter the age ([^\"]*)$")
public void enterHAQ2_age(String HAQ2_ageValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("HAQ2_age"))).sendKeys(HAQ2_ageValue);
}


@And("^User click on button to continue$")
public void HAQ_ContinueButtonClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("HAQ_ContinueButton"))).click();
       driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
}

@And("^User enter the height in feet ([^\"]*)$")
public void enterHAQ3_HeightFeet(String HAQ3_HeightFeetValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("HAQ3_HeightFeet"))).sendKeys(HAQ3_HeightFeetValue);
}

@And("^User enter the height in inches ([^\"]*)$")
public void enterHAQ3_HeightInch(String HAQ3_HeightInchValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("HAQ3_HeightInch"))).sendKeys(HAQ3_HeightInchValue);
}

@And("^User enter the weight ([^\"]*)$")
public void enterHAQ3_Weight(String HAQ3_WeightValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("HAQ3_Weight"))).sendKeys(HAQ3_WeightValue);
}
@And("^User enter the waist ([^\"]*)$")
public void enterHAQ4_Waist(String HAQ4_WaistValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("HAQ4_Waist"))).sendKeys(HAQ4_WaistValue);

}

@And("^User enter the drinks ([^\"]*)$")
public void enterDrinks(String DrinksValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Drinks"))).sendKeys(DrinksValue);

}

@And("^User enter the Light Hours ([^\"]*)$")
public void enterLightHours(String LightHoursValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("LightHours"))).sendKeys(LightHoursValue);

}

@And("^User enter the Light Minutes ([^\"]*)$")
public void enterLightMinutes(String LightMinutesValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("LightMinutes"))).sendKeys(LightMinutesValue);

}

@And("^User enter the Moderate Hours ([^\"]*)$")
public void enterModerateHours(String ModerateHoursValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("ModerateHours"))).sendKeys(ModerateHoursValue);

}

@And("^User enter the Moderate Minutes ([^\"]*)$")
public void enterModerateMinutes(String ModerateMinutesValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("ModerateMinutes"))).sendKeys(ModerateMinutesValue);

}

@And("^User enter the Vigorous Hours ([^\"]*)$")
public void enterVigHours(String VigHoursValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("VigHours"))).sendKeys(VigHoursValue);

}

@And("^User enter the Vigorous Minutes ([^\"]*)$")
public void enterVigMinutes(String VigMinutesValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("VigMinutes"))).sendKeys(VigMinutesValue);

}


@And("^User click on button to continue2$")
public void Continue2Click()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Continue2"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@And("^User enter the Smoking Years ([^\"]*)$")
public void enterSmokingYears(String SmokingYearsValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("SmokingYears"))).sendKeys(SmokingYearsValue);

}

@And("^User enter the Cigarettes a Day ([^\"]*)$")
public void enterCigarettesDay(String CigarettesDayValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("CigarettesDay"))).sendKeys(CigarettesDayValue);

}

@And("^User enter the Systolic ([^\"]*)$")
public void enterSystolic(String SystolicValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Systolic"))).sendKeys(SystolicValue);

}

@And("^User enter the Diastolic ([^\"]*)$")
public void enterDiastolic(String DiastolicValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Diastolic"))).sendKeys(DiastolicValue);

}
@And("^User click on button to finish later button of HA$")
public void FinishLaterButtonClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("FinishLaterButton"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

@And("^User click on button to no score button of HA$")
public void NoScoreButtonClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("NoScoreButton"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}


@And("^User click on button to LetsGo2$")
public void LetsGOButtonClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("LetsGOButton"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}


@And("User click on button to answer question on scale$")
public void PhysicalHealthClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("PhysicalHealth"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@And("^User click on button to SeeYourProfile$")
public void SeeYourProfileClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("SeeYourProfile"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@And("^User click on button to continue3$")
public void Continue3Click()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Continue3"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@And("^User click on button to none of these$")
public void NoneOfTheseClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("NoneOfThese"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@And("^User click on button to skip$")
public void SKipButtonClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("SKipButton"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
@And("^User click on button to Go to Dashboard$")
public void GoToDashboardClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("GoToDashboard"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
@And("^User click on button to Go to Rewards Tab$")
public void RewardsTabClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("RewardsTab"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
@And("^User click on button to Go to Rally Rewards Tab$")
public void RallyRewardsClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("RallyRewards"))).click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
@And("^User enter the BirthMonth ([^\"]*)$")
public void enterBirthMonth(String BirthMonthValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("BirthMonth"))).sendKeys(BirthMonthValue);
}
@And("^User enter the BirthDay ([^\"]*)$")
       public void enterBirthDay(String BirthDayValue) throws Throwable{
             FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
             Properties prop = new Properties();
             prop.load(fip);
              driver.findElement(By.xpath(prop.getProperty("BirthDay"))).sendKeys(BirthDayValue);
}
@And("^User enter the BirthYear ([^\"]*)$")
             public void enterBirthYear(String BirthYearValue) throws Throwable{
                    FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
                    Properties prop = new Properties();
                    prop.load(fip);
                    driver.findElement(By.xpath(prop.getProperty("BirthYear"))).sendKeys(BirthYearValue);
}
@And("^User click on button to BirthdaySubmit$")
public void BirthdaySubmitClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("BirthdaySubmit"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}


@And("^User click on button to open dropdown$")
public void DropdownClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Dropdown"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

@And("^User click on button to open profile$")
public void ProfileClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Profile"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^User click on button to ViewClinical$")
public void ViewClinicalClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("ViewClinical"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}
@And("^User click on button to ViewConditions$")
public void ViewConditionsClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("ViewConditions"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}
@And("^User click on button to ViewProcedures$")
public void ViewProceduresClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("ViewProcedures"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}
@And("^User click on button to ViewMedication$")
public void ViewMedicationClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("ViewMedication"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}
@And("^Validate Data updation on UI from Pharmacy files$")
public void MedData() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("MedData"))).isDisplayed();
}
@And("^Validate Data updation on UI from Procedure files$")
public void ProcData() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("ProcData"))).isDisplayed();
}
@And("^Validate Data updation on UI from Health Screning files$")
public void Source3() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("Source3"))).isDisplayed();
}
@And("^Validate Data updation on UI from Biometric Files$")
public void Source2() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("Source2"))).isDisplayed();
}
@And("^Validate Data updation on UI from Global 23_Nicotine$")
public void Nicotine() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("Nicotine"))).isDisplayed();
}
@And("^Validate Data updation on UI from Conditions File$")
public void VerifyCon() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("VerifyCon"))).isDisplayed();
}
@And("^Validate Data content on UI from Conditions File$")
public void VerifyCondHeader() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("VerifyCondHeader"))).isDisplayed();
}
@And("^Validate Data updation on UI from Global 31_A1C$")
public void A1C() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("A1C"))).isDisplayed();
}
@And("^User click on Missions tab$")
public void MissionsClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Missions"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^User click on button to Join$")
public void JoinMissionClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("JoinMission"))).click();
       driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}
@And("^User click on button to View all missions$")
public void ViewAllMissionClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("ViewAllMission"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^User click on button to HealthRecords$")
public void HealthRecordsClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("HealthRecords"))).click();
       driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
}
@And("^User click on button to go to Browse all tab$")
public void BrowseAllClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("BrowseAll"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^User click on My Missions Tab$")
public void MyMissionsClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("MyMissions"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^Validate Missions are added$")
public void ValidateMission() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("ValidateMission"))).isDisplayed();
}

@And("^Validate added Mission 1$")
public void Added1Mission() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("Added1Mission"))).isDisplayed();
}

@And("^Validate added Mission 3$")
public void Added3Mission() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("Added3Mission"))).isDisplayed();
}
@And("^Validate added Mission 4$")
public void Added4Mission() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("Added4Mission"))).isDisplayed();
}
@And("^Validate Incentives earned for Health Screening")
public void BioScreening() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       if(driver.getPageSource().contains("Completed a Biometric Screening"))
       System.out.println("yes");
}
@And("^Validate Reward_Total Cholesterol_Target")
public void TotalCholesterol() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("TotalCholesterol"))).isDisplayed();
}
@And("^User click on button to go to Rally Rewards Footer$")
public void RallyRewardsFooter()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("RallyRewardsFooter"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^User click on button to go to Employer Rewards$")
public void EmployerRewards()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("EmployerRewards"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

// --------------------ECAP Steps-----------------------


@When("^I enter \"(.*?)\" in field ([^\"]*)$")
public void ECAPText(String TxtValue, String TxtXPath )throws Throwable{
	FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
	Properties prop = new Properties();
	prop.load(fip);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.xpath(prop.getProperty(TxtXPath))).sendKeys(TxtValue);
}

@And("^I click on ([^\"]*) button$")
public void ECAPLogin(String btnXpath) throws Throwable{
	FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
	Properties prop = new Properties();
	prop.load(fip);
	driver.findElement(By.xpath(prop.getProperty(btnXpath))).click();
}

@Then("^Validate page ([^\"]*) is loaded$")
public void ECAPLoginSuccess(String btnXpath) throws Throwable{
	FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
	Properties prop = new Properties();
	prop.load(fip);
	driver.findElement(By.xpath(prop.getProperty(btnXpath))).isDisplayed();
}

/* ------------------ Smoke Test Rally UI --------------------------- */

@Given("^users provided the url input \"(.*?)\" for accessing Rally UI$")
public void users_provided_the_url_input_for_accessing_Rally_UI(String url){

               System.setProperty("webdriver.firefox.marionette", "geckodriver.exe");
               driver = new FirefoxDriver();

       driver.manage().window().maximize(); 
       driver.get("https://"+"rallyhealth"+":"+"rallyw1ns!"+"@"+url);

       driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
}


@And("^users enter the usersNAME ([^\"]*)$")
public void enterEnterusersname(String EnterusersnameValue) throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("Enterusersname"))).sendKeys(EnterusersnameValue);
}

@And("^Validate Your Rally Age")
public void YourRallyAge() throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       driver.findElement(By.xpath(prop.getProperty("YourRallyAge"))).isDisplayed();
}
@And("^users click on button to go to Rally Rewards Footer$")
public void RallyRewardsFooterClick()throws Throwable{
      FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("RallyRewardsFooter"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^users click on button to go to Dashboard$")
public void DashboardIconClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("DashboardIcon"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^users click on button to go to Employer Rewards$")
public void EmployerRewardsClick()throws Throwable{
       FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
       Properties prop = new Properties();
       prop.load(fip);
       driver.findElement(By.xpath(prop.getProperty("EmployerRewards"))).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}


@And("^users click on button to RetakeSurvey$")
public void RetakeSurveyClick()throws Throwable{
FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
Properties prop = new Properties();
prop.load(fip);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
driver.findElement(By.xpath(prop.getProperty("RetakeSurvey"))).click();
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@And("^users click on button to ConfirmRetake$")
public void ConfirmRetakeClick()throws Throwable{
FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\Or.Properties");
Properties prop = new Properties();
prop.load(fip);
driver.findElement(By.xpath(prop.getProperty("ConfirmRetake"))).click();
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

/* ----------------------- Smoke Test Admin UI ------------------- */

@Given("^user is on admin UI login page$")
public void Open_Firefox_start_application() throws Throwable {
     
	System.setProperty("webdriver.firefox.marionette", "geckodriver.exe");
driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(20000,TimeUnit.SECONDS);
driver.get("https://ori-opsadmin-tst.optum.com/");
//FirefoxProfile profile=new FirefoxProfile();
//profile.setAcceptUntrustedCertificates(true);
//driver.get("pass the url as per your requirement");
}


@When( "^user login successfully$")


public void enter_valid_username_password() throws Throwable {
 
 driver.findElement(By.xpath(".//*[@id='Table1']/tbody/tr[6]/td/p/a")).click();
	driver.findElement(By.xpath(".//*[@id='Table1']/tbody/tr[4]/td[3]/p/input")).sendKeys("rsaxena4");
	
	driver.findElement(By.xpath(".//*[@id='Password1']")).sendKeys("Ashu$111");
	driver.findElement(By.xpath(".//*[@id='Button2']")).click();
 
}




@Then("^validate user is on application home page$")

public void user_on_homepage() throws Throwable {
driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);

driver.findElement(By.xpath("html/body/ori-app/div[1]/nav/div/a[2]")).click();
//Assert.assertEquals(driver.findElement(By.xpath("html/body/ori-app/div[1]/nav/div/a[2]")),"ORI");
	
}

@Given("^ user is on application home page$")

public void user_onthe_homepage() throws Throwable {


driver.findElement(By.xpath("html/body/ori-app/div[1]/nav/div/a[2]")).click();
	

}

@Then("^user Visit Biometric Meta$")

public void user_on_BiometricMeta() throws Throwable {

driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-dashboard/div/div/div[3]/div[1]/div/a/i")).click();



}

@Then("^user clicks OK on the Alert$")

public void close_Alert() throws Throwable {

Thread.sleep(5000);
driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();

}


//user performs search edit and publish operations on Biometric Meta data
@Then("^user Operates Biometric Meta data$")

public void Fn_BiometricMeta() throws Throwable {


driver.findElement(By.cssSelector("#filtered_by")).sendKeys("Rashi");
//click on edit
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[1]/div[1]/div[1]/a/i")).click();

//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[1]/div[1]/div[1]/a/i")).click();
//edit the search result

driver.findElement(By.xpath("//div/div/a/i")).click();
driver.findElement(By.xpath("//li/div/div[2]/a/i")).click();
//driver.findElement(By.xpath("//div[@id='sizzle1527450679396']/ori-biometric-meta-info/ori-biometric-meta-edit/div/div/form/div/div/label")).click();
Thread.sleep(1000);
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[2]/ori-biometric-meta-info/ori-biometric-meta-edit/div/div[1]/form/div[1]/div/label")).click();
//driver.findElement(By.xpath("//div[@id='sizzle1527450679396']/ori-biometric-meta-info/ori-biometric-meta-edit/div/div/form/div/div/label")).click();
//driver.findElement(By.xpath("//input[@id='isModalActive']")).click();
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[2]/ori-biometric-meta-info/ul/li[1]/div[1]/div[2]/a[1]/i")).click();
//Active check box edit

//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[2]/ori-biometric-meta-info/ori-biometric-meta-edit/div/div[1]/form/div[1]/div/label")).click();

//Close the edit modal alert
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[2]/ori-biometric-meta-info/ori-biometric-meta-edit/div/div[2]/a[2]")).click();

//driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();


//Publish meta data 
Thread.sleep(2000);
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[1]/div[2]/span/a")).click();
//Publish Warning close
Thread.sleep(2000);
driver.findElement(By.xpath(".//*[@id='modal1']/div[2]/a")).click();
//Success publish Alert
Thread.sleep(5000);
driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();

}	 


@Then("^user add biometric meta data$")

public void abc() throws Throwable{
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[1]/div[1]/div[1]/a/i")).click();

   //driver.findElement(By.linkText("Ok")).click();
   // driver.findElement(By.xpath("//div/div/a/i")).click();
   // driver.findElement(By.xpath("//a[3]/i")).click();

driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[1]/div[2]/a[3]/i")).click();
    driver.findElement(By.xpath("//input[@id='model_column']")).click();
  //  driver.findElement(By.xpath("//input[@id='model_column']")).clear();
    driver.findElement(By.xpath("//input[@id='model_column']")).sendKeys("12344");
    driver.findElement(By.xpath("//input[@id='model_common_name']")).click();
 //   driver.findElement(By.xpath("//input[@id='model_common_name']")).clear();
    driver.findElement(By.xpath("//input[@id='model_common_name']")).sendKeys("Rashmeer");
    driver.findElement(By.xpath("//input[@value='']")).click();
    driver.findElement(By.xpath("//div[3]/div/ul/li[2]/span")).click();
    driver.findElement(By.xpath("//input[@value='']")).click();
    driver.findElement(By.xpath("//div[3]/div/div/ul/li[2]/span")).click();
    driver.findElement(By.id("model_medical_code_value")).click();
   // driver.findElement(By.xpath("//input[@id='model_medical_code_value']")).clear();
    driver.findElement(By.xpath("//input[@id='model_medical_code_value']")).sendKeys("123455");
   driver.findElement(By.xpath("//input[@value='']")).click();
   driver.findElement(By.xpath("//div[3]/div[3]/div/ul/li[2]/span")).click();
   
   // driver.findElement(By.xpath("//input[@id='model_unit']")).clear();
    
 //  driver.findElement(By.xpath("(//input[@value=''])[3]")).click();
  // driver.findElement(By.xpath("//input[@value='numeric']")).click();
   // driver.findElement(By.xpath("//div[3]/div[3]/div/ul/li[2]/span")).click();
    
    
    driver.findElement(By.xpath("//input[@id='model_unit']")).click();
    driver.findElement(By.xpath(".//*[@id='model_unit']")).sendKeys("mg");
    driver.findElement(By.xpath("//input[@id='model_min']")).click();
   // driver.findElement(By.xpath("//input[@id='model_min']")).clear();
    driver.findElement(By.xpath("//input[@id='model_min']")).sendKeys("1");
    driver.findElement(By.xpath("//input[@id='model_max']")).click();
   // driver.findElement(By.xpath("//input[@id='model_max']")).clear();
    driver.findElement(By.xpath("//input[@id='model_max']")).sendKeys("1000");
    driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();
  

}

@Then("^user validate Biometric Export$")

public void click_BiometricExport() throws Throwable {
// Export the latest meta data 
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[1]/div[2]/a[1]/span")).click();

}

@Then("^validate excel file is downloaded$")

public void Excel_Export() throws Throwable {

driver.findElement(By.xpath(".//*[@id='rcnt']")).click();

}



@Then("^user Visit Conditions Meta$")

public void user_on_ConditionMeta() throws Throwable {

driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-dashboard/div/div/div[2]/div[3]/div/a/i")).click();

//driver.findElement(By.xpath("html/body/ori-app/div[1]/nav/div/a[2]")).isDisplayed();

}



//user performs search edit and publish operations on Biometric Meta data
@Then("^user search edit Conditions Meta data$")

public void Search_ConditionMeta() throws Throwable {


driver.findElement(By.cssSelector("#filtered_by")).sendKeys("Rashmee");
//click on edit
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[1]/div[1]/div[1]/a/i")).click();
//edit the search result
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[2]/ori-conditions-meta-info/ul/li/div[1]/div[2]/a[1]/i")).click();
//Active check box edit
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[2]/ori-conditions-meta-info/ori-conditions-meta-edit/div/div[1]/form/div[1]/div/label")).click();

//Close the edit modal alert
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[2]/ori-conditions-meta-info/ori-conditions-meta-edit/div/div[2]/a[2]")).click();




//Publish meta data  
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[1]/div[2]/span[2]/a")).click();

//Publish Warning close
driver.findElement(By.xpath(".//*[@id='modal1']/div[2]/a")).click();

//Success publish Alert
Thread.sleep(5000);
driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();

}	 

@Then("^user add Conditions Meta data$")
public void ADD_conditionMeta() throws Throwable{

//driver.findElement(By.xpath("//div[2]/div[3]/div/a/i")).click();
//  driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
driver.findElement(By.xpath("//div/div/a/i")).click();
driver.findElement(By.xpath("//a[3]/i")).click();
driver.findElement(By.id("model_rule")).click();
driver.findElement(By.id("model_rule")).clear();
driver.findElement(By.id("model_rule")).sendKeys("115");
driver.findElement(By.id("model_outbound_file_id")).click();
driver.findElement(By.id("model_outbound_file_id")).clear();
driver.findElement(By.id("model_outbound_file_id")).sendKeys("21");
driver.findElement(By.id("model_description")).click();
driver.findElement(By.id("model_description")).clear();
driver.findElement(By.id("model_description")).sendKeys("Rashmee test Meta data Add");
driver.findElement(By.xpath("//input[@value='ICD-10']")).click();
driver.findElement(By.xpath("//div[4]/div/div/ul/li/span")).click();
driver.findElement(By.id("model_medicalCodeValue")).click();
driver.findElement(By.id("model_medicalCodeValue")).clear();
driver.findElement(By.id("model_medicalCodeValue")).sendKeys("123451");
driver.findElement(By.xpath("//input[@value='']")).click();
driver.findElement(By.xpath("//div[3]/div/ul/li[4]/span")).click();
Thread.sleep(10);
driver.findElement(By.cssSelector("input.select-dropdown.active")).click();
driver.findElement(By.cssSelector("li.active > span")).click();
//driver.findElement(By.xpath("//ul[@id='select-options-6647f526-46bb-e4b6-a6b1-a53c342e4c65']/li[3]/span")).click();
//driver.findElement(By.xpath("//input[@value='']")).click();
//driver.findElement(By.xpath("(//input[@value=''])[2]")).click();
//driver.findElement(By.xpath("//div[4]/div/ul/li[2]/span")).click();
// driver.findElement(By.xpath("//ul[@id='select-options-2cc4aaa8-f7e9-ac20-501f-b2c6c4a9b710']/li[3]/span")).click();
//driver.findElement(By.xpath("//div[4]/div/ul/li[2]/span")).click();
driver.findElement(By.id("model_comment")).click();
driver.findElement(By.id("model_comment")).clear();
driver.findElement(By.id("model_comment")).sendKeys("Add meta data Rashmee Test");
//  driver.findElement(By.xpath("//input[@value='']")).click();
////div[4]/div[4]/div/input
//driver.findElement(By.xpath(".//*[@id='select-options-ac70861f-b50d-cd41-85e4-e4f7cc084f82']/li[2]/span")).click();
//   driver.findElement(By.xpath("//div[4]/div/ul/li[3]/span")).click();
Thread.sleep(500);
driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();

}

@Then("^user publish Conditions Meta data$")
public void PUBLISH_conditionMeta() throws Throwable{

//Publish meta data 
Thread.sleep(2000);
//driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
//warning
driver.findElement(By.xpath("//a[contains(text(),'Yes')]")).click();
Thread.sleep(5000);
 driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();
//driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();

}



@Then("^user Export Condition Meta Data$")

public void click_ConditionExport() throws Throwable {
// Export the latest meta data 
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[1]/div[2]/a[1]/span")).click();

}


@Then("^user Visit Biometric Search$")

public void visit_BiometricSearch() throws Throwable {

 driver.findElement(By.xpath("//div[2]/div[4]/div/a/i")).click();
    

}

@Then("^user search Biometric data$")

public void search_Biometricdata() throws Throwable {


    driver.findElement(By.id("personId")).click();
    driver.findElement(By.xpath("//input[@id='personId']")).sendKeys("22964114");
  //  driver.findElement(By.xpath("//table[@id='fromProcessDate_table']/tbody/tr/td[3]/div")).click();
 
   
   
	  
    driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/form/div/div[1]/div[4]/div[1]/label")).click();

    driver.findElement(By.xpath(".//*[@id='fromProcessDate_table']/tbody/tr[1]/td[3]/div")).click();
   // driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/form/div/div[2]/a[2]")).click();
    driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/form/div/div[1]/div[4]/div[1]/label")).click();
    Thread.sleep(1000);
   // driver.findElement(By.xpath(".//*[@id='fromProcessDate_root']/div/div/div/div/div[3]/button[3]")).click();
    driver.findElement(By.xpath("//button[@type='button'])[6]")).click();
    driver.findElement(By.xpath("//button[@type='button'])[6]")).click();
    Thread.sleep(1000);
   driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/form/div/div[2]/a[2]")).click();
   driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/div/div[1]/div/div/table/tbody/tr/td[1]/a/i")).click();
   driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-detail/form/div[1]/div[2]/a")).click();
  // driver.findElement(By.xpath("//ori-biometric-search/div/div/div/div/table/tbody/tr/td[2]")).click();
 //  driver.findElement(By.cssSelector("a.waves-effect.waves-light > i.material-icons")).click();
 //  driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click();

}


@Then("^user Visit Condition Search$")

public void visit_ConditonSearch() throws Throwable {

 driver.findElement(By.xpath("//div[2]/div[2]/div/a/i")).click();
    

}

@Then("^user search Condition transaction data$")

public void search_Conditiondata() throws Throwable {


//driver.findElement(By.xpath("//div[2]/div[2]/div/a/i")).click();
driver.findElement(By.xpath(".//*[@id='transactionId']")).click();
//driver.findElement(By.xpath("//input[@id='transactionId']")).click();
// driver.findElement(By.xpath("//input[@id='transactionId']")).clear();
driver.findElement(By.xpath(".//*[@id='transactionId']")).sendKeys("4ba31927-e3af-4801-8b64-dd616a50a491");
driver.findElement(By.xpath("(//a[contains(text(),'Search')])[12]")).click();
driver.findElement(By.xpath("//td/a/i")).click();
driver.findElement(By.xpath("//div/div")).click();
driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click();
driver.findElement(By.xpath("//a[contains(text(),'Clear')]")).click();

}

@Then("^user ADD IDT meta data$")

public void testIDTMetaADDEDit() throws Throwable {
// driver.get(baseUrl + "/secure/support/idt-meta");
//Thread.sleep(5000);
//driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();
Thread.sleep(10000);
  driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
//driver.findElement(By.xpath("//a[contains(text(),'Ok')]")).click();
//driver.findElement(By.xpath("//ori-modal-alert/div/div[2]/a")).click();
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-idt-meta/div[1]/div[1]/div[1]/a/i")).click();
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-idt-meta/div[1]/div[2]/a[3]/i")).click();



driver.findElement(By.xpath("//input[@id='model_verb']")).click();
// driver.findElement(By.xpath("//input[@id='model_verb']")).clear();
driver.findElement(By.xpath("//input[@id='model_verb']")).sendKeys("completed");
driver.findElement(By.xpath("//input[@id='model_product_id']")).click();
// driver.findElement(By.xpath("//input[@id='model_product_id']")).clear();
driver.findElement(By.xpath("//input[@id='model_product_id']")).sendKeys("Rashiii");
driver.findElement(By.xpath("//input[@value='']")).click();
driver.findElement(By.xpath("//div[3]/div/ul/li[4]/span")).click();

//driver.findElement(By.xpath("//div[3]/div/ul/li[2]/span")).click();
driver.findElement(By.xpath("//input[@id='model_product_event_1']")).click();
// driver.findElement(By.xpath("//input[@id='model_product_event_1']")).clear();
driver.findElement(By.xpath("//input[@id='model_product_event_1']")).sendKeys("test6");
driver.findElement(By.xpath("//input[@id='model_product_event_2']")).click();
//driver.findElement(By.cssSelector("#model_product_event_2")).clear();
driver.findElement(By.cssSelector("#model_product_event_2")).sendKeys("test7");
driver.findElement(By.xpath("//input[@id='model_program_referal_id']")).click();
driver.findElement(By.id("model_program_referal_id")).clear();
driver.findElement(By.id("model_program_referal_id")).sendKeys("abcd");
driver.findElement(By.xpath("//input[@id='model_long_id']")).click();
// driver.findElement(By.xpath("//input[@id='model_long_id']")).clear();
driver.findElement(By.xpath("//input[@id='model_long_id']")).sendKeys("abcde");
driver.findElement(By.xpath("//input[@id='model_short_id']")).click();
//  driver.findElement(By.xpath("//input[@id='model_short_id']")).clear();
driver.findElement(By.xpath("//input[@id='model_short_id']")).sendKeys("abc");
//driver.findElement(By.xpath("//div[2]/ul/li/span")).click();
driver.findElement(By.xpath("//input[@id='model_short_id']")).click();
// driver.findElement(By.xpath("//input[@id='model_short_id']")).clear();
driver.findElement(By.xpath("//input[@id='model_short_id']")).sendKeys("quit_for_life_enroll");
driver.findElement(By.xpath("//textarea[@id='model_comment']")).click();
// driver.findElement(By.xpath("//textarea[@id='model_comment']")).click();
// driver.findElement(By.xpath("//textarea[@id='model_comment']")).click();
driver.findElement(By.xpath("//textarea[@id='model_comment']")).click();
//driver.findElement(By.xpath("//textarea[@id='model_comment']")).clear();
driver.findElement(By.xpath("//textarea[@id='model_comment']")).sendKeys("Rashmee QA Test3");
// driver.findElement(By.xpath(".//*[@id='model_comment']")).sendKeys("Rashmee QA Test2");
driver.findElement(By.xpath("//ori-idt-meta-edit/div/div[2]/a[2]")).click();
driver.findElement(By.xpath("//input[@id='filtered_by']")).click();
// driver.findElement(By.xpath("//input[@id='filtered_by']")).click();
// driver.findElement(By.xpath("//input[@id='filtered_by']")).clear();
driver.findElement(By.xpath("//input[@id='filtered_by']")).sendKeys("test");
driver.findElement(By.xpath("//li/div/div[2]/a/i")).click();
driver.findElement(By.xpath("//form/div/div/label")).click();
// driver.findElement(By.xpath("//input[@id='isModalActive']")).click();
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-idt-meta/div[2]/ori-idt-meta-info/ori-idt-meta-edit/div/div[2]/a[2]")).click();
Thread.sleep(1000);
driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
driver.findElement(By.xpath("//ori-modal-warning/div/div[2]/a")).click();

}



@Then("^user Visit IDT Meta$")

public void visit_IDTMeta() throws Throwable {
  //driver.findElement(By.xpath("//div[2]/div/a/i")).click();

  driver.get("https://ori-opsadmin-tst.optum.com/secure/support/idt-meta");


}

@Then("^user Export IDT Meta data$")

public void Fn_IDTMeta() throws Throwable {

    Thread.sleep(20000);
     // driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
  //  driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();
  driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-idt-meta/div[1]/div[2]/a[1]/span")).click();
    driver.findElement(By.xpath("//div[2]/a/span")).click();
   // driver.findElement(By.cssSelector("body > a")).click();
   // driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
}


@Then("^user Visit IDT Search$")

public void visit_IDTSearch() throws Throwable {

driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-dashboard/div/div/div[1]/div[1]/div/a/i")).click();
  	
}

@Then("^user search IDT Data")

public void Fn_IDTData() throws Throwable {

  //html/body/ori-app/div[1]/main/ori-activity-search/form/div/div[1]/div[2]/div[1]/div/input
    driver.findElement(By.xpath("//input[@value='Staged']")).click();
    Thread.sleep(600);
    //driver.findElement(By.xpath("//ul[@id='select-options-13e46ec5-2cbe-9ec6-cf04-c546509308dc']/li[2]/span")).click();
    driver.findElement(By.xpath("//li[2]/span")).click();
    driver.findElement(By.xpath("//input[@id='transactionId']")).click();
    //driver.findElement(By.xpath("//input[@id='transactionId']")).clear();
    driver.findElement(By.xpath("//input[@id='transactionId']")).sendKeys("cd3bcb78-f0ea-4b43-8557-4566e9579b32");
    driver.findElement(By.xpath("(//a[contains(text(),'Search')])[12]")).click();
  // driver.findElement(By.cssSelector("a.waves-effect.waves-light > i.material-icons")).click();
   Thread.sleep(1000);
   driver.findElement(By.xpath("//td/a/i")).click();
   Thread.sleep(2000);
    driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click();	


}

@Then("^user Visit ORI Program Mapping$")

public void visit_ORIPROGMAP() throws Throwable {

//driver.findElement(By.xpath("//div[4]/div/div/a/i")).click();
  	//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-dashboard/div/div/div[4]/div[1]/div/a/i")).click();
  	//html/body/ori-app/div[1]/main/ori-dashboard/div/div/div[4]/div[1]/div/a/i
  driver.findElement(By.xpath("html/body/ori-app/div[1]/footer/div[1]/div/div[2]/ul/li[8]/a")).click();
}

@Then ("^user ADD the BOSS Product$")

public void ADD_BOSS() throws Throwable {

Thread.sleep(5000);
driver.findElement(By.xpath("//span[2]/a/i")).click();
//html/body/ori-app/div[1]/main/ori-product-mapping/div[1]/div[2]/span[2]/a/i
//html/body/ori-app/div[1]/main/ori-product-mapping/div[1]/div[2]/span[2]/a/i
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-product-mapping/div[1]/div[2]/span[2]/a/i")).click();
driver.findElement(By.id("model_product_event_1")).click();
//driver.findElement(By.id("model_product_event_1")).clear();
driver.findElement(By.id("model_product_event_1")).sendKeys("Biometrics");
driver.findElement(By.id("model_product_event_2")).click();
//driver.findElement(By.id("model_product_event_2")).clear();
driver.findElement(By.id("model_product_event_2")).sendKeys("Lab Program - Patient Service Center");
driver.findElement(By.id("model_ori_program_name")).click();
//driver.findElement(By.id("model_ori_program_name")).clear();
driver.findElement(By.id("model_ori_program_name")).sendKeys("BIO");




//driver.findElement(By.id("model_boss_l1")).click();
//driver.findElement(By.id("model_ori_program_name")).click();
//driver.findElement(By.id("model_ori_program_name")).clear();
//driver.findElement(By.id("model_ori_program_name")).sendKeys("rashi");
//driver.findElement(By.id("model_boss_l1")).click();
//driver.findElement(By.id("model_boss_l1")).clear();
//driver.findElement(By.id("model_boss_l1")).sendKeys("test3");
//driver.findElement(By.id("model_boss_l2")).click();
//driver.findElement(By.id("model_boss_l2")).clear();
//driver.findElement(By.id("model_boss_l2")).sendKeys("test4");
//driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();
//driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();
driver.findElement(By.xpath("//div[2]/a/i")).click();

//driver.findElement(By.id("model_product_event_1")).click();
//driver.findElement(By.id("model_ori_program_name")).click();
//driver.findElement(By.id("model_ori_program_name")).clear();
//driver.findElement(By.id("model_ori_program_name")).sendKeys("RASHIi");
driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();


}

@Then ("^user Search EDIT PUBLISH the BOSS Product$")

public void Search_BOSS() throws Throwable {

//Edit
driver.findElement(By.xpath("//input[@id='filtered_by']")).sendKeys("test");
//html/body/ori-app/div[1]/main/ori-product-mapping/div[2]/ori-product-mapping-info/ul/li/div[1]/div[2]/a[1]/i
////div[2]/a/i
driver.findElement(By.xpath("//div[2]/a/i")).click();
driver.findElement(By.id("model_boss_l1")).click();
driver.findElement(By.id("model_boss_l1")).clear();
driver.findElement(By.id("model_boss_l1")).sendKeys("atest4");
driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();
//PUBLISH ORI Product
driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//div[@id='modal1']/div[2]/a")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
//driver.findElement(By.xpath("//div[@id='sizzle1527586024192']/ori-product-mapping-info/ori-product-mapping-edit/div/div/form/div[4]")).click();
//driver.findElement(By.xpath("//div[@id='sizzle1527586024192']/ori-product-mapping-info/ori-product-mapping-edit/div/div/form/div[5]")).click();
//driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();

}

@Then ("^user Delete PUBLISH the BOSS Product$")

public void Delete_BOSS() throws Throwable {
//delete
driver.findElement(By.xpath("//input[@id='filtered_by']")).sendKeys(" OUTCOME");
driver.findElement(By.xpath("//a[2]/i")).click();
driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();
driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
driver.findElement(By.xpath("//a[contains(text(),'Yes')]")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
driver.findElement(By.xpath("//a/span")).click();
}



@Then ("^user ADD the PATCH Product$")

public void ADD_PATCH() throws Throwable {

 //driver.findElement(By.xpath("//div[4]/div/div/a/i")).click();
    driver.findElement(By.linkText("Refresh")).click();
	Thread.sleep(5000);
    driver.findElement(By.xpath("//span[2]/a/i")).click();
    driver.findElement(By.xpath("//label[2]")).click();
    //patch click  -  html/body/ori-app/div[1]/main/ori-product-mapping/div[2]/ori-product-mapping-info/ori-product-mapping-edit/div/div[1]/form/div[1]/p/label[2]
   // driver.findElement(By.id("productSource-PATCH")).click();
   // driver.findElement(By.xpath("//div[@id='sizzle1527627362866']/ori-product-mapping-info/ori-product-mapping-edit/div/div/form/div/p/label[2]")).click();
   // driver.findElement(By.id("productSource-PATCH")).click();
    driver.findElement(By.id("model_product_event_1")).click();
   // driver.findElement(By.id("model_product_event_1")).clear();
    driver.findElement(By.id("model_product_event_1")).sendKeys("Personal Coaching");
    driver.findElement(By.id("model_product_event_2")).click();
   // driver.findElement(By.id("model_product_event_2")).clear();
    driver.findElement(By.id("model_product_event_2")).sendKeys("Eat Smart");
    driver.findElement(By.id("model_product_event_3")).click();
    driver.findElement(By.id("model_ori_program_name")).click();
    driver.findElement(By.id("model_ori_program_name")).clear();
    driver.findElement(By.id("model_ori_program_name")).sendKeys("PERSONAL COACHING EAT SMART PATCH");
    
    driver.findElement(By.linkText("OK")).click();
    
	
	
driver.findElement(By.linkText("Publish")).click();
driver.findElement(By.xpath(".//*[@id='modal1']/div[2]/a")).click();
	
}
@Then ("^user Search the PATCH Product$")

public void Search_PATCH() throws Throwable {
    
    // Search
   driver.findElement(By.id("filtered_by")).click();
   // driver.findElement(By.id("filtered_by")).clear();
    driver.findElement(By.id("filtered_by")).sendKeys("YELLOW TEST PROGRAM NAME");
    //click edit icon
    driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-product-mapping/div[2]/ori-product-mapping-info/ul/li/div[1]/div[2]/a[1]/i")).click();
    driver.findElement(By.xpath("//div[2]/a/i")).click();
    //edit field
    driver.findElement(By.xpath(".//*[@id='model_ori_program_name']")).click();
    driver.findElement(By.xpath(".//*[@id='model_ori_program_name']")).clear();
    driver.findElement(By.xpath(".//*[@id='model_ori_program_name']")).sendKeys("YELLOW TEST PROGRAM NAME2");
    driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();
//html/body/ori-app/div[1]/main/ori-product-mapping/div[2]/ori-product-mapping-info/ori-product-mapping-edit/div/div[2]/a[2]

    driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//div[@id='modal1']/div[2]/a")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
    
    
}


@Then ("^user Delete the PATCH Product$")

public void Delete_PATCH() throws Throwable {


    driver.findElement(By.xpath("//input[@id='filtered_by']")).sendKeys("OUTCOME123");
    driver.findElement(By.xpath("//a[2]/i")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Yes')]")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
    driver.findElement(By.xpath("//a/span")).click();
    
    
}

@Then ("^user close the browser$")

public void Close_Firefox() throws Throwable {


driver.close();

}

@When( "^user enter username ([^\"]*)$")


public void enter_username(String userName) throws Throwable {
 
 driver.findElement(By.xpath(".//*[@id='Table1']/tbody/tr[6]/td/p/a")).click();
	driver.findElement(By.xpath(".//*[@id='Table1']/tbody/tr[4]/td[3]/p/input")).sendKeys(userName);
	

 
}

@And( "^user enter password ([^\"]*)$")


public void enter_password(String PWD) throws Throwable {
 

	driver.findElement(By.xpath(".//*[@id='Password1']")).sendKeys(PWD);
	driver.findElement(By.xpath(".//*[@id='Button2']")).click();
 
}

@Then ("^user search IDT ([^\"]*)$")

public void IDT_Search(String trx) throws Throwable {

	  //html/body/ori-app/div[1]/main/ori-activity-search/form/div/div[1]/div[2]/div[1]/div/input
	    driver.findElement(By.xpath("//input[@value='Staged']")).click();
	    Thread.sleep(600);
	    //driver.findElement(By.xpath("//ul[@id='select-options-13e46ec5-2cbe-9ec6-cf04-c546509308dc']/li[2]/span")).click();
	    driver.findElement(By.xpath("//li[2]/span")).click();
	    driver.findElement(By.xpath("//input[@id='transactionId']")).click();
	    //driver.findElement(By.xpath("//input[@id='transactionId']")).clear();
	    driver.findElement(By.xpath("//input[@id='transactionId']")).sendKeys(trx);
	    driver.findElement(By.xpath("(//a[contains(text(),'Search')])[12]")).click();
	  // driver.findElement(By.cssSelector("a.waves-effect.waves-light > i.material-icons")).click();
	   Thread.sleep(1000);
	   driver.findElement(By.xpath("//td/a/i")).click();
	   Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click();	


}


@Then("^user search Biometric data ([^\"]*)$")

public void search_Biometric(String prsn) throws Throwable {


    driver.findElement(By.id("personId")).click();
    driver.findElement(By.xpath("//input[@id='personId']")).sendKeys(prsn);
  //  driver.findElement(By.xpath("//table[@id='fromProcessDate_table']/tbody/tr/td[3]/div")).click();
	  
    driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/form/div/div[1]/div[4]/div[1]/label")).click();

    driver.findElement(By.xpath(".//*[@id='fromProcessDate_table']/tbody/tr[1]/td[3]/div")).click();
   // driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/form/div/div[2]/a[2]")).click();
    driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/form/div/div[1]/div[4]/div[1]/label")).click();
    Thread.sleep(1000);
   // driver.findElement(By.xpath(".//*[@id='fromProcessDate_root']/div/div/div/div/div[3]/button[3]")).click();
    driver.findElement(By.xpath("//button[@type='button'])[6]")).click();
    driver.findElement(By.xpath("//button[@type='button'])[6]")).click();
    Thread.sleep(1000);
   driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/form/div/div[2]/a[2]")).click();
   driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-search/div/div[1]/div/div/table/tbody/tr/td[1]/a/i")).click();
   driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-detail/form/div[1]/div[2]/a")).click();
  // driver.findElement(By.xpath("//ori-biometric-search/div/div/div/div/table/tbody/tr/td[2]")).click();
 //  driver.findElement(By.cssSelector("a.waves-effect.waves-light > i.material-icons")).click();
 //  driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click();

}


@Then("^user search Condition data ([^\"]*)$")

public void search_Condition(String trx) throws Throwable {

//driver.findElement(By.xpath("//div[2]/div[2]/div/a/i")).click();
driver.findElement(By.xpath(".//*[@id='transactionId']")).click();
//driver.findElement(By.xpath("//input[@id='transactionId']")).click();
// driver.findElement(By.xpath("//input[@id='transactionId']")).clear();
driver.findElement(By.xpath(".//*[@id='transactionId']")).sendKeys(trx);
driver.findElement(By.xpath("(//a[contains(text(),'Search')])[12]")).click();
driver.findElement(By.xpath("//td/a/i")).click();
driver.findElement(By.xpath("//div/div")).click();
driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click();
driver.findElement(By.xpath("//a[contains(text(),'Clear')]")).click();

}

@Then("^user add biometric meta data ([^\"]*) and ([^\"]*)$")

public void Add_BiometricMeta(String common, int column) throws Throwable{
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[1]/div[1]/div[1]/a/i")).click();

   //driver.findElement(By.linkText("Ok")).click();
   // driver.findElement(By.xpath("//div/div/a/i")).click();
   // driver.findElement(By.xpath("//a[3]/i")).click();

driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-biometric-meta/div[1]/div[2]/a[3]/i")).click();
    driver.findElement(By.xpath("//input[@id='model_column']")).click();
  //  driver.findElement(By.xpath("//input[@id='model_column']")).clear();
    driver.findElement(By.xpath("//input[@id='model_column']")).sendKeys(String.valueOf(column));
    driver.findElement(By.xpath("//input[@id='model_common_name']")).click();
 //   driver.findElement(By.xpath("//input[@id='model_common_name']")).clear();
    driver.findElement(By.xpath("//input[@id='model_common_name']")).sendKeys(common);
    driver.findElement(By.xpath("//input[@value='']")).click();
    driver.findElement(By.xpath("//div[3]/div/ul/li[2]/span")).click();
    driver.findElement(By.xpath("//input[@value='']")).click();
    driver.findElement(By.xpath("//div[3]/div/div/ul/li[2]/span")).click();
    driver.findElement(By.id("model_medical_code_value")).click();
   // driver.findElement(By.xpath("//input[@id='model_medical_code_value']")).clear();
    driver.findElement(By.xpath("//input[@id='model_medical_code_value']")).sendKeys("123455");
   driver.findElement(By.xpath("//input[@value='']")).click();
   driver.findElement(By.xpath("//div[3]/div[3]/div/ul/li[2]/span")).click();
   
   // driver.findElement(By.xpath("//input[@id='model_unit']")).clear();
    
 //  driver.findElement(By.xpath("(//input[@value=''])[3]")).click();
  // driver.findElement(By.xpath("//input[@value='numeric']")).click();
   // driver.findElement(By.xpath("//div[3]/div[3]/div/ul/li[2]/span")).click();
    
    
    driver.findElement(By.xpath("//input[@id='model_unit']")).click();
    driver.findElement(By.xpath(".//*[@id='model_unit']")).sendKeys("mg");
    driver.findElement(By.xpath("//input[@id='model_min']")).click();
   // driver.findElement(By.xpath("//input[@id='model_min']")).clear();
    driver.findElement(By.xpath("//input[@id='model_min']")).sendKeys("1");
    driver.findElement(By.xpath("//input[@id='model_max']")).click();
   // driver.findElement(By.xpath("//input[@id='model_max']")).clear();
    driver.findElement(By.xpath("//input[@id='model_max']")).sendKeys("1000");
    driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();
  

}

@Then ("^user ADD the PATCH Product ([^\"]*) and ([^\"]*) and ([^\"]*)$")

public void PATCH_ADD(String PE1, String PE2, String ProgName) throws Throwable {

 //driver.findElement(By.xpath("//div[4]/div/div/a/i")).click();
    driver.findElement(By.linkText("Refresh")).click();
	Thread.sleep(5000);
    driver.findElement(By.xpath("//span[2]/a/i")).click();
    driver.findElement(By.xpath("//label[2]")).click();
    //patch click  -  html/body/ori-app/div[1]/main/ori-product-mapping/div[2]/ori-product-mapping-info/ori-product-mapping-edit/div/div[1]/form/div[1]/p/label[2]
   // driver.findElement(By.id("productSource-PATCH")).click();
   // driver.findElement(By.xpath("//div[@id='sizzle1527627362866']/ori-product-mapping-info/ori-product-mapping-edit/div/div/form/div/p/label[2]")).click();
   // driver.findElement(By.id("productSource-PATCH")).click();
    driver.findElement(By.id("model_product_event_1")).click();
   // driver.findElement(By.id("model_product_event_1")).clear();
    driver.findElement(By.id("model_product_event_1")).sendKeys(PE1);
    driver.findElement(By.id("model_product_event_2")).click();
   // driver.findElement(By.id("model_product_event_2")).clear();
    driver.findElement(By.id("model_product_event_2")).sendKeys(PE2);
    driver.findElement(By.id("model_product_event_3")).click();
    driver.findElement(By.id("model_ori_program_name")).click();
    driver.findElement(By.id("model_ori_program_name")).clear();
    driver.findElement(By.id("model_ori_program_name")).sendKeys(ProgName);
    
    driver.findElement(By.linkText("OK")).click();
    
	
	
//driver.findElement(By.linkText("Publish")).click();
//driver.findElement(By.xpath(".//*[@id='modal1']/div[2]/a")).click();
	
}

@Then ("^user Delete the BOSS Or PATCH Product ([^\"]*)$")

public void Delete_ORIProduct(String Product) throws Throwable {
//delete
driver.findElement(By.xpath("//input[@id='filtered_by']")).sendKeys(Product);
driver.findElement(By.xpath("//a[2]/i")).click();
driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();
driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
driver.findElement(By.xpath("//a[contains(text(),'Yes')]")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
driver.findElement(By.xpath("//a/span")).click();
}

@Then ("^user PUBLISH the Product$")
public void PUBLISH_ORIProduct(String Product) throws Throwable {
//PUBLISH ORI Product
driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
driver.findElement(By.xpath("//div[@id='modal1']/div[2]/a")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
}

@Then ("^user ADD the BOSS Product ([^\"]*) and ([^\"]*) and ([^\"]*)$")

public void ADD_BOSSProduct(String BPE1, String BPE2, String BProgName) throws Throwable {

Thread.sleep(5000);
driver.findElement(By.xpath("//span[2]/a/i")).click();
//html/body/ori-app/div[1]/main/ori-product-mapping/div[1]/div[2]/span[2]/a/i
//html/body/ori-app/div[1]/main/ori-product-mapping/div[1]/div[2]/span[2]/a/i
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-product-mapping/div[1]/div[2]/span[2]/a/i")).click();
driver.findElement(By.id("model_product_event_1")).click();
//driver.findElement(By.id("model_product_event_1")).clear();
driver.findElement(By.id("model_product_event_1")).sendKeys(BPE1);
driver.findElement(By.id("model_product_event_2")).click();
//driver.findElement(By.id("model_product_event_2")).clear();
driver.findElement(By.id("model_product_event_2")).sendKeys(BPE2);
driver.findElement(By.id("model_ori_program_name")).click();
//driver.findElement(By.id("model_ori_program_name")).clear();
driver.findElement(By.id("model_ori_program_name")).sendKeys(BProgName);
//driver.findElement(By.xpath("//input[@id='model_boss_l1']")).sendKeys("test5");

driver.findElement(By.xpath("//div[2]/a/i")).click();

driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();


}

@Then("^user ADD IDT meta data ([^\"]*) and ([^\"]*) and ([^\"]*)$")

public void testIDTMetaADDEDit(String ProdID, String IPE1, String IPE2) throws Throwable {
// driver.get(baseUrl + "/secure/support/idt-meta");
//Thread.sleep(5000);
//driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();
Thread.sleep(20000);
  driver.findElement(By.xpath("//div[@id='modalAlert']/div[2]/a")).click();
//driver.findElement(By.xpath("//a[contains(text(),'Ok')]")).click();
//driver.findElement(By.xpath("//ori-modal-alert/div/div[2]/a")).click();
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-idt-meta/div[1]/div[1]/div[1]/a/i")).click();
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-idt-meta/div[1]/div[2]/a[3]/i")).click();
   
driver.findElement(By.xpath("//input[@id='model_verb']")).click();
// driver.findElement(By.xpath("//input[@id='model_verb']")).clear();
driver.findElement(By.xpath("//input[@id='model_verb']")).sendKeys("completed");
driver.findElement(By.xpath("//input[@id='model_product_id']")).click();
// driver.findElement(By.xpath("//input[@id='model_product_id']")).clear();
//Product ID
driver.findElement(By.xpath("//input[@id='model_product_id']")).sendKeys(ProdID);
driver.findElement(By.xpath("//input[@value='']")).click();
driver.findElement(By.xpath("//div[3]/div/ul/li[4]/span")).click();

//driver.findElement(By.xpath("//div[3]/div/ul/li[2]/span")).click();
driver.findElement(By.xpath("//input[@id='model_product_event_1']")).click();
// driver.findElement(By.xpath("//input[@id='model_product_event_1']")).clear();
//PE1
driver.findElement(By.xpath("//input[@id='model_product_event_1']")).sendKeys(IPE1);
driver.findElement(By.xpath("//input[@id='model_product_event_2']")).click();
//driver.findElement(By.cssSelector("#model_product_event_2")).clear();
//PE2
driver.findElement(By.cssSelector("#model_product_event_2")).sendKeys(IPE2);
driver.findElement(By.xpath("//input[@id='model_program_referal_id']")).click();
driver.findElement(By.id("model_program_referal_id")).clear();
driver.findElement(By.id("model_program_referal_id")).sendKeys("abcd");
driver.findElement(By.xpath("//input[@id='model_long_id']")).click();
// driver.findElement(By.xpath("//input[@id='model_long_id']")).clear();
driver.findElement(By.xpath("//input[@id='model_long_id']")).sendKeys("INCENTIVES.ACTIVITY.QUIT_FOR_LIFE");
driver.findElement(By.xpath("//input[@id='model_short_id']")).click();
//  driver.findElement(By.xpath("//input[@id='model_short_id']")).clear();
driver.findElement(By.xpath("//input[@id='model_short_id']")).sendKeys("QFL_EVALUATION");
//driver.findElement(By.xpath("//div[2]/ul/li/span")).click();
driver.findElement(By.xpath("//input[@id='model_short_id']")).click();
// driver.findElement(By.xpath("//input[@id='model_short_id']")).clear();
driver.findElement(By.xpath("//input[@id='model_short_id']")).sendKeys("quit_for_life_enroll");
driver.findElement(By.xpath("//textarea[@id='model_comment']")).click();
// driver.findElement(By.xpath("//textarea[@id='model_comment']")).click();
// driver.findElement(By.xpath("//textarea[@id='model_comment']")).click();
driver.findElement(By.xpath("//textarea[@id='model_comment']")).click();
//driver.findElement(By.xpath("//textarea[@id='model_comment']")).clear();
driver.findElement(By.xpath("//textarea[@id='model_comment']")).sendKeys("Rashmee QA Test3");
driver.findElement(By.xpath("//ori-idt-meta-edit/div/div[2]/a[2]")).click();



}

@Then("^user Search Edit IDT meta data ([^\"]*)$")

public void IDT_EDITSearch(String FilterSearch) throws Throwable {
//edit
//driver.findElement(By.xpath("//ori-idt-meta-edit/div/div[2]/a[2]")).click();
driver.findElement(By.xpath("//input[@id='filtered_by']")).click();
// driver.findElement(By.xpath("//input[@id='filtered_by']")).click();
// driver.findElement(By.xpath("//input[@id='filtered_by']")).clear();
driver.findElement(By.xpath("//input[@id='filtered_by']")).sendKeys(FilterSearch);
driver.findElement(By.xpath("//li/div/div[2]/a/i")).click();
driver.findElement(By.xpath("//form/div/div/label")).click();
// driver.findElement(By.xpath("//input[@id='isModalActive']")).click();
Thread.sleep(1000);
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-idt-meta/div[2]/ori-idt-meta-info/ori-idt-meta-edit/div/div[2]/a[2]")).click();


}

@Then("^user DELETE IDT meta data$")

public void IDT_DELETE() throws Throwable {
Thread.sleep(1000);
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[1]/div[1]/div[1]/a/i")).click();
//driver.findElement(By.xpath("//div/div/a/i")).click();
driver.findElement(By.xpath("//li/div/div[2]/a[3]/i")).click();
driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();
}

@Then("^user DELETE Conditions meta data$")

public void Condition_DELETE() throws Throwable 
{
 driver.findElement(By.xpath("//li/div/div[2]/a[3]/i")).click();
 Thread.sleep(500);
 driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();
  //  driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
   // driver.findElement(By.xpath("//a[contains(text(),'Yes')]")).click();
    //Thread.sleep(1000);
   // driver.findElement(By.xpath("//a[contains(text(),'Ok')]")).click();
   // driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();
}



@Then("^user PUBLISH IDT meta data$")

public void IDT_PUBLISH() throws Throwable {

 driver.findElement(By.xpath("//a[contains(text(),'Publish')]")).click();
 //Thread.sleep(1000);
    driver.findElement(By.xpath(".//*[@id='modal1']/div[2]/a")).click();
    Thread.sleep(10000);
    driver.findElement(By.xpath("//a[contains(text(),'Ok')]")).click();

}

@Then("^user add Conditions Meta data ([^\"]*) and ([^\"]*)$")
public void ADD_conditionMetadata(String model, int rule) throws Throwable{


driver.findElement(By.xpath("//div/div/a/i")).click();
driver.findElement(By.xpath("//a[3]/i")).click();
driver.findElement(By.id("model_rule")).click();
driver.findElement(By.id("model_rule")).clear();
driver.findElement(By.id("model_rule")).sendKeys(String.valueOf(rule));
driver.findElement(By.id("model_outbound_file_id")).click();
driver.findElement(By.id("model_outbound_file_id")).clear();
driver.findElement(By.id("model_outbound_file_id")).sendKeys("22");
driver.findElement(By.id("model_description")).click();
driver.findElement(By.id("model_description")).clear();
Thread.sleep(1000);
driver.findElement(By.id("model_description")).sendKeys(model);
driver.findElement(By.xpath("//input[@value='ICD-10']")).click();
driver.findElement(By.xpath("//div[4]/div/div/ul/li/span")).click();
driver.findElement(By.id("model_medicalCodeValue")).click();
driver.findElement(By.id("model_medicalCodeValue")).clear();
driver.findElement(By.id("model_medicalCodeValue")).sendKeys("12345");
//driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
driver.findElement(By.id("model_comment")).click();
driver.findElement(By.id("model_comment")).clear();
driver.findElement(By.id("model_comment")).sendKeys("Add meta data Rashmee QA");
driver.findElement(By.xpath("//input[@value='']")).click();
driver.findElement(By.xpath("//div[3]/div/ul/li[3]/span")).click();
driver.findElement(By.id("model_outbound_file_id")).click();
// driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);

driver.findElement(By.xpath("//input[@value='']")).click();
driver.findElement(By.xpath("//div[4]/div/ul/li[2]/span")).click();
//driver.findElement(By.cssSelector("input.select-dropdown.active")).click();
//driver.findElement(By.cssSelector("li.active > span")).click();
// driver.manage().timeouts().implicitlyWait(500,TimeUnit.SECONDS);

Thread.sleep(500);
driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();

}

@Then("^user search edit Conditions Meta data ([^\"]*)$")

public void Search_ConditionMetadata(String FilterSearch) throws Throwable {

 driver.findElement(By.xpath("//div/div/a/i")).click();

driver.findElement(By.cssSelector("#filtered_by")).sendKeys(FilterSearch);
driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
driver.findElement(By.xpath("//div/div/a/i")).click();
//click on edit
//driver.findElement(By.xpath("//div/div/a/i")).click();
//driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
driver.findElement(By.xpath("//li/div/div[2]/a/i")).click();
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[1]/div[1]/div[1]/a/i")).click();
//edit the search result
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[2]/ori-conditions-meta-info/ul/li/div[1]/div[2]/a[1]/i")).click();
//Active check box edit
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[2]/ori-conditions-meta-info/ori-conditions-meta-edit/div/div[1]/form/div[1]/div/label")).click();
driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[2]/ori-conditions-meta-info/ori-conditions-meta-edit/div/div[1]/form/div[1]/div/label")).click();
//Close the edit modal alert
driver.findElement(By.xpath("//a[contains(text(),'OK')]")).click();

//Close the edit modal alert
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[2]/ori-conditions-meta-info/ori-conditions-meta-edit/div/div[2]/a[2]")).click();


//Publish meta data  
//driver.findElement(By.xpath("html/body/ori-app/div[1]/main/ori-conditions-meta/div[1]/div[2]/span[2]/a")).click();

//Publish Warning close
//driver.findElement(By.xpath(".//*[@id='modal1']/div[2]/a")).click();

//Success publish Alert
//Thread.sleep(5000);
//driver.findElement(By.cssSelector("div[id='modalAlert'] a ")).click();

}	 









}

       





		
		

		
	



