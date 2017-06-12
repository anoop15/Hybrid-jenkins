package Com.vivino.test;


import static Com.vivino.test.DriverScript.CONFIG;
import static Com.vivino.test.GetOSName.OsUtils.isWindows;
import static Com.vivino.test.DriverScript.APP_LOGS;
import static Com.vivino.test.DriverScript.OR;


import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import Com.vivino.test.Constants;

public class Keywords {

	public WebDriver driver; 
	
	public String openBrowser(String object,String data){

        // Chrome Driver Path

            System.setProperty("webdriver.chrome.driver", "Driver/chromedriver");
        // Internet Explorer Path
        if (isWindows()) {
            File file = new File("IEDriver/IEDriver.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        }

        APP_LOGS.debug("Opening browser");
        if(data.equals("Mozilla"))
            driver=new FirefoxDriver();
        else if(data.equals("IE"))
            driver=new InternetExplorerDriver();
        else if(data.equals("Chrome"))
            driver=new ChromeDriver();

        long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitwait"));
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
        return Constants.KEYWORD_PASS;

    }
	
	
	//This function navigate browser to the URL
	 public String navigate(String object,String data){
	        APP_LOGS.debug("Navigating to URL");
	        try{
	            driver.navigate().to(data);
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" -- Not able to navigate";
	        }
	        return Constants.KEYWORD_PASS;
	    }
	 
	 
	 //This function write input in Text field
	 public  String writeInInput(String object,String data){
	        APP_LOGS.debug("Writing in text box");

	        try{
	            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

	        }
	        return Constants.KEYWORD_PASS;

	    }
	 
	 public  String writeInInputId(String object,String data){
	        APP_LOGS.debug("Writing in text box");

	        try{
	            driver.findElement(By.id(OR.getProperty(object))).sendKeys(data);
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

	        }
	        return Constants.KEYWORD_PASS;

	    }
	 
	 public String clickLink(String object,String data){
	        APP_LOGS.debug("Clicking on link ");
	        try{
	            driver.findElement(By.xpath(OR.getProperty(object))).click();
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
	        }

	        return Constants.KEYWORD_PASS;
	    }
	  
	    	

	    public  String clickButton(String object,String data){
	        APP_LOGS.debug("Clicking on Button");
	        try{
	            driver.findElement(By.xpath(OR.getProperty(object))).click();
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
	        }
	        return Constants.KEYWORD_PASS;
	    }


	    public String pause(String object, String data) throws NumberFormatException, InterruptedException{
	        long time = (long)Double.parseDouble(object);
	        Thread.sleep(time*1000L);
	        return Constants.KEYWORD_PASS;
	    }

	    
	    

	    public String verifyText(String object, String data){
	        APP_LOGS.debug("Verifying the text");
	        try{
	            String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
	            String expected=data;

	            if(actual.equals(expected))
	                return Constants.KEYWORD_PASS;
	            else
	                return Constants.KEYWORD_FAIL+" -- text not verified "+actual+" -- "+expected;
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
	        }

	    }
	    
	    
	    public  String closeBrowser(String object, String data){
	        APP_LOGS.debug("Closing the browser");
	        try{
	            driver.close();
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+"Unable to close browser. Check if its open"+e.getMessage();
	        }
	        return Constants.KEYWORD_PASS;

	    }
	    
	    public  String Addprofileimage(String object,String data){
	        APP_LOGS.debug("Adding/change image ");

	        try{
	            driver.findElement(By.id(OR.getProperty(object))).sendKeys(data);
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

	        }
	        return Constants.KEYWORD_PASS;

	    }
	    
	    public String SelectFromDropdown(String object,String data){
	    	 APP_LOGS.debug("Select value form dropdown menu");
	    	try{
	    		Select value= new Select(driver.findElement(By.id(OR.getProperty(object))));
	    		value.selectByVisibleText(data);
	    	}catch(Exception e){
	    		return Constants.KEYWORD_FAIL+" Unable to select value form dropdown "+e.getMessage();
	    	}
	    	return Constants.KEYWORD_PASS;
	    }
	    
	    
	    
	  
		
	    
	    
	    
	    
	    
	    
	    

}
