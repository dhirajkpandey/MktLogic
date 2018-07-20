package pom;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import generic.WaitStatement;
import generic.ConstantsData;
import generic.Excel;

public class GoogleSearchPagePOM {
	
	/**
	 * This is Web Element Repository class
	 * 
	 * According to Page Object Model  design, Page Object should be kept separate from test scripts.
	 * 
	 * All the test data are called from Excel sheet(GoogleData.xlsx)
	 * 
	 * @author Dhirajkumar.pandey
	 */
	
	//----------------------Web Element Repository--------------------------------//
	@FindBy(name = "q")	
	private WebElement googleSearch_txtbox;
	
	@FindBy(name = "btnk")	
	private WebElement googleSearch_btn; 
	
	@FindBy(name = "btnI")	
	private WebElement googleSearch_btnIamFeelingLucky;
	
	@FindBy(id = "hplogo")	
	private WebElement googleSearchPage_logo;
	
	@FindBy(tagName = "a")	
	private WebElement googleSearchedPage_linktxt;
	
	@FindBy(xpath ="//div[@class='med card-section']/p")	
	private WebElement googleSearchNotFoundValidationMessage_lbl;
	
	@FindBy(linkText = "Gmail")	
	private WebElement gmail_linktxt;
	
	@FindBy(xpath = "//a[text() = 'Sign In']")	
	private WebElement gmailSignIn_linktxt;
	
	@FindBy(id = "identifierId")	
	private WebElement gmailEmailOrPhone_inputtxt;
	
	@FindBy(xpath = "//span[text() = 'Next']")	
	private WebElement gmailSignInIdentifier_nextbtn;
		
	@FindBy(name = "password")	
	private WebElement gmailPassword_inputtxt;
	
	@FindBy(xpath = "//div[@id= 'passwordNext']/content")	
	private WebElement gmailSignPassword_nextbtn;
	
	//---------------------------Forgot email account--------------------------------//
	
	@FindBy(xpath = "//span[text() = 'Forgot email?']")	
	private WebElement gmailSignInForgotEmail_linktxt;
	
	@FindBy(id = "recoveryIdentifierId")	
	private WebElement gmailSignInForgotEmailRecovery_linktxt; 
		
	@FindBy(xpath = "//div[@id= 'queryPhoneNext']/content")	
	private WebElement gmailSignInForgotEmailRecovery_nextbtn;
	
	@FindBy(id = "firstName")	
	private WebElement googleAccountFirstName_txt;
	
	@FindBy(id = "lastName")	
	private WebElement googleAccountlastName_txt;

	@FindBy(xpath = "//span[text() = 'Send']")	
	private WebElement gmailRecovery_sendbtn;
	
	@FindBy(xpath = "//p[text() = 'Google will send a verification code to ']")	
	private WebElement gmailRecoveryCodeSentMessage_lbl;

		
	//-------------------------To log out Gmail Account-------------------------------//
	
	@FindBy(xpath = "//span[@class = 'gb_8a gbii']")	
	private WebElement gmailSignOutIcon_btn;
	
	@FindBy(xpath = "//a[text() = 'Sign out']")	
	private WebElement gmailSignOut_btn;
	
			
	@FindBy(xpath = "//div[text()='Enter a valid email or phone number']")	
	private WebElement gmailwrongEmailvalidationMessage_lbl;
	
	
	
	//--------------Repository for Creating Account-----------------------------------//
	
	@FindBy(xpath = "//span[text() = 'Create account']")	
	private WebElement gmailCreateAccount_linktxt;
	
	@FindBy(id = "username")	
	private WebElement googleAccountUserName_txt;
	
	@FindBy(name = "Passwd")	
	private WebElement googleAccountPassword_txt;
	
	@FindBy(name = "ConfirmPasswd")	
	private WebElement googleAccountConfirmPassword_txt;
	
	@FindBy(xpath = "//div[@id= 'accountDetailsNext']/content")	
	private WebElement gmailCreateAccount_nextbtn;
	
	
	@FindBy(xpath = "//div[text() = 'That username is taken. Try another.']")	
	private WebElement googleAccountExistingUsernameValidationMessage_lbl;
	
	//-------------------WebElement for search though image----------------------------------//
	
	@FindBy(xpath = "//div[@class='gb_Q gb_R'][2]/a")	
	private WebElement googleSearchPage_imagelink;
	
	@FindBy(id = "qbi")	
	private WebElement googleSearchPageImageIcom_img;
	
	@FindBy(id = "qbui")	
	private WebElement googleSearchPageInputImageUrl_txtbox;
	
	@FindBy(xpath = "//input[@class='gbqfb kpbb']")	
	private WebElement googleSearchPageSearchByImage_btn;
	
	@FindBy(xpath = "//div[@class='r5a77d']")	
	private WebElement googleSearchedPageForImage_lbl;
	
	
	
	//------------------Web Element Repository ended-------------------------------------//

	
	public GoogleSearchPagePOM(WebDriver driver){
	PageFactory.initElements(driver, this);
	}
	
	static String  filePath = ".\\src\\main\\resources\\GoogleData.xlsx";
	
	String validSearchText = Excel.fnGetData(filePath,"GoogleData", 1, 0);
	String invalidSearchText = Excel.fnGetData(filePath,"GoogleData", 1, 1);
	String validgmail = Excel.fnGetData(filePath,"GoogleData", 1, 2);
	String invalidgmail = Excel.fnGetData(filePath,"GoogleData", 1, 3);
	String firstName = Excel.fnGetData(filePath,"GoogleData", 1, 4);
	String lastName = Excel.fnGetData(filePath,"GoogleData", 1, 5);
	String userName = Excel.fnGetData(filePath,"GoogleData", 1, 6);
	String password = Excel.fnGetData(filePath,"GoogleData", 1, 7);
	String confirmPassword = Excel.fnGetData(filePath,"GoogleData", 1, 8);
	String recoveryEmail = Excel.fnGetData(filePath,"GoogleData", 1, 9);
	
	


	//To verify searching a text or link
	public void fnVerifyGoogleSearch(WebDriver driver){
		googleSearch_txtbox.clear();
		googleSearch_txtbox.sendKeys(validSearchText);
		googleSearch_txtbox.submit();
		WaitStatement.fnImplicitWait(driver, 5);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement loop : links) {
				       
	        String filtering = loop.getText();
	        
	        if(filtering.contains(validSearchText)){
			Reporter.log("Search found", true);
			break;
	        }
		}
		
		driver.navigate().back();
		
	}
	//To verify validation message when google could not find searched text
	public void fnVerifyGoogleSearchValidationMessage(WebDriver driver){
		googleSearch_txtbox.clear();
		googleSearch_txtbox.sendKeys(invalidSearchText);
		googleSearch_txtbox.submit();
		WaitStatement.fnImplicitWait(driver, 5);
		
		if(googleSearchNotFoundValidationMessage_lbl.getText().contains("Es wurden keine mit deiner Suchanfrage")){
			
			Reporter.log("Validation message 'did not match any documents' is verified");
		}
		

	}
	//To Verify the functionality of "I'm feeling Lucky" search button - the top most search result should get directly returned
		public void fnVerifyDirectOpeningSearchResult(WebDriver driver) throws MalformedURLException, InterruptedException{
			driver.navigate().to(ConstantsData.URL);
			googleSearch_txtbox.clear();
			googleSearch_txtbox.sendKeys(validSearchText);
			googleSearchPage_logo.click();
			//Thread.sleep(3000);
			googleSearch_btnIamFeelingLucky.click();
			URL currentUrl = new URL(driver.getCurrentUrl());
			String splitedUrl = currentUrl.getHost(); 
			String group1 = splitedUrl.substring(4);
			
			if(group1.substring(0, 2).equalsIgnoreCase(validSearchText.substring(0,  2))){
				
				Reporter.log("Searched text results in direct opening of the site");
			}
			driver.navigate().back();

		}
		
	//To Verify the validation message when user enters wrong email
		public void fnVerifyWrongEmailValidation(WebDriver driver){
			gmail_linktxt.click();
			WaitStatement.fnImplicitWait(driver, 10);
			gmailSignIn_linktxt.click();
			WaitStatement.fnExplicitWait(driver, 10, gmailEmailOrPhone_inputtxt);
			gmailEmailOrPhone_inputtxt.sendKeys(invalidgmail);
			gmailSignInIdentifier_nextbtn.click();
			
			if(gmailwrongEmailvalidationMessage_lbl.getText().equalsIgnoreCase("Enter a valid email or phone number")){
				Reporter.log("Wrong email validation message is verified.");
			}
	
		}
		//To Verify Forgot email recovery functionality is working
		public void fnForgotEmail(WebDriver driver) {
			gmailSignInForgotEmail_linktxt.click();
			gmailSignInForgotEmailRecovery_linktxt.sendKeys(recoveryEmail);
			gmailSignInForgotEmailRecovery_nextbtn.click();
			WaitStatement.fnImplicitWait(driver, 5);
			googleAccountFirstName_txt.sendKeys(firstName);
			googleAccountlastName_txt.sendKeys(lastName);
			gmailSignInIdentifier_nextbtn.click();
			WaitStatement.fnImplicitWait(driver, 5);
			if(gmailRecovery_sendbtn.isDisplayed()==true){
				gmailRecovery_sendbtn.click();
			}
			if(gmailRecoveryCodeSentMessage_lbl.getText().contains("Google will send a verification code to ")){
				Reporter.log("Email recovery functionality is verified.");
			}
			
		}
		//To Verify validation for existing gmail id while creating new account
				public void fnCreateAccountWithExistingGmailId(WebDriver driver) {
					driver.navigate().to(ConstantsData.URL);
					gmail_linktxt.click();
					gmailSignIn_linktxt.click();
					
					WaitStatement.fnImplicitWait(driver, 5);
					gmailCreateAccount_linktxt.click();
					googleAccountFirstName_txt.sendKeys(firstName);
					googleAccountlastName_txt.sendKeys(lastName);
					googleAccountUserName_txt.sendKeys(userName);
					googleAccountPassword_txt.sendKeys(password);
					googleAccountConfirmPassword_txt.sendKeys(confirmPassword);
					gmailCreateAccount_nextbtn.click();
					if(googleAccountExistingUsernameValidationMessage_lbl.getText().contains("That username is taken. Try another.")){
						Reporter.log("Google existing user name validation message is verified.");
					}
					driver.navigate().back();

				}


		
		//To Verify the user logs in his gmail account successfully 
				public void fnVerifySuccessfulLoginGmailAccount(WebDriver driver){
					WaitStatement.fnExplicitWait(driver, 10, gmailEmailOrPhone_inputtxt);
					gmailEmailOrPhone_inputtxt.clear();
					gmailEmailOrPhone_inputtxt.sendKeys(validgmail);
					gmailSignInIdentifier_nextbtn.click();
					gmailPassword_inputtxt.sendKeys(password);
					WaitStatement.fnExplicitWait(driver, 10, gmailSignPassword_nextbtn);
					gmailSignPassword_nextbtn.click();
		
				}
		//To Verify the user logs in his gmail account successfully 
				public void fnVerifyLogoutGmailAccount(WebDriver driver){
					WaitStatement.fnExplicitWait(driver, 10, gmailSignOutIcon_btn);
					gmailSignOutIcon_btn.click();
					gmailSignOut_btn.click();
					
					driver.navigate().back();
					
				}
				
				//To Verify google calculator 
				public void fnVerifySearchThroughImageURL(WebDriver driver) throws NumberFormatException{
					WaitStatement.fnImplicitWait(driver, 10);
					driver.navigate().to(ConstantsData.URL);
					googleSearchPage_imagelink.click();
					googleSearchPageImageIcom_img.click();
					googleSearchPageInputImageUrl_txtbox.sendKeys(ConstantsData.IMAGEURL);
					googleSearchPageSearchByImage_btn.click();
					if(googleSearchedPageForImage_lbl.getText().contains("Vermutung für dieses Bild: ")){
						
						Reporter.log("Google image search is verified.");
					}
					
				}
		
				
		
		
			
								



}
