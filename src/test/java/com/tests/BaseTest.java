package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    // Browser -> chrome/ff
    // HUB_HOST -> localhost/10.0.1.3

    @BeforeTest
    public void setUp(ITestContext iTestContext) throws MalformedURLException {

        //Default Value
        String host="localhost";
        DesiredCapabilities dc= new DesiredCapabilities();


        //Value set based on provided
        if(System.getProperty("BROWSER")!=null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc.setCapability("browserName","firefox");
        } else{
            dc.setCapability("browserName","chrome");
        }

        if(System.getProperty("HUB_HOST")!=null){
            host=System.getProperty("HUB_HOST");
        }

        String testName = iTestContext.getCurrentXmlTest().getName();

        String completeUrl = "http://" + host + ":4444/wd/hub";

        dc.setCapability("name",testName);
        this.driver=new RemoteWebDriver(new URL(completeUrl),dc);

    }

    @AfterTest
    public void quitBrowser(){
        this.driver.quit();
    }
}
