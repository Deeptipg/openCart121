package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression", "Master"})
	public void verify_account_registration ()
	{
		logger.info("**** Starting TC001_AccountRegistrationTest **** ");
		logger.debug("This is a debug log message");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link.. ");
		
		hp.clickRegister();
		logger.info("Clicked on Register link..");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
	
		logger.info("Providing Customer Details...");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message..");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("Test failed");
			logger.debug("debug logs..");
			Assert.assertTrue(false);
		}
       // Assert.assertEquals(confmsg, "Your Account Has Been Created!!!", "Confirmation message mismatched");
        
        logger.info("Test Passed");
		}
		catch(Exception e)
		{
			Assert.fail("Test failed");
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest **** ");
        
		
	}
}
