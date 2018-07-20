package testscripts;

import java.net.MalformedURLException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pom.GoogleSearchPagePOM;

public class GoogleTest extends BaseTest {
	
	
	/**
	 * This is Test scripts class and it calls all the method from POM class
	 * 
	 * 
	 * @author Dhirajkumar.pandey
	 */
	

  
  @Test(priority = 1)
  public void verifyPageTitle() {
	  
	  String expected = driver.getTitle();
	  Assert.assertEquals(expected, "Google");
	  Reporter.log("Page Title Verified successfully", true);
  }
  @Test(priority = 2)
  public void verifyGoogleSearch() {
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);

	  pomObj.fnVerifyGoogleSearch(driver);
  }
  @Test(priority = 3)
  public void verifyGoogleSearchNotFoundValidation() {
	  
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);
	  pomObj.fnVerifyGoogleSearchValidationMessage(driver);
  }
  @Test(priority = 4)
  public void verifyDirectOpeningSearchResult() throws MalformedURLException, InterruptedException {
	  
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);
	  pomObj.fnVerifyDirectOpeningSearchResult(driver);
  }
  @Test(priority = 5)
  public void verifyWrongEmailValidationMessage() {
	  
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);
	  pomObj.fnVerifyWrongEmailValidation(driver);
  }
  @Test(priority = 6)
  public void verifyForgotEmailFunctionality(){
	  
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);
	  pomObj.fnForgotEmail(driver);
  }
  @Test(priority = 7)
  public void verifyExistingGoogleAccountValidation() {
	  
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);
	  pomObj.fnCreateAccountWithExistingGmailId(driver);
  }
  @Test(priority = 8)
  public void verifySuccessfulGmailLogin()  {
	  
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);
	  pomObj.fnVerifySuccessfulLoginGmailAccount(driver);
  }
  @Test(priority = 9)
  public void verifySuccessfulGmailLogout() {
	  
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);
	  pomObj.fnVerifyLogoutGmailAccount(driver);
  }
  @Test(priority = 10)
  public void verifyGoogleSearchThroughImageURL() {
	  
	  GoogleSearchPagePOM pomObj = new GoogleSearchPagePOM(driver);
	  pomObj.fnVerifySearchThroughImageURL(driver);
  }
  

  
 
  
}
