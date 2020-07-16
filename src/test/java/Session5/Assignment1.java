package Session5;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Automate the following test case:
//Scenario: Users will be able to add deposit
//1: Open Browser and go to site http://www.techfios.com/ibilling/?ng=admin/ 
//2. Enter userName: demo@techfios.com 
//3. Enter password: abc123
//4. Click login button
//5. Click on Transactions button
//6. Click on New Deposit button 
//7. Click on Open An Account drop down to expand it
//8. Click on any account name
//9. Type any description
//10. Type any amount
//11. Click on submit button
//Visually check to make sure the deposit posted
//validation


public class Assignment1 {

	@Test
	public void userAbleToMakeDeposit () throws InterruptedException  {
		
	 //Set properties,Set ChromeBrowser as default 
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	
	//Open ChromeBrowser	
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	//go to TechFioswebsite
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");
		
	//user login,
		
		driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
			Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys("abc123");
			Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
			Thread.sleep(2000);
		
	// Elements Library,
		
		driver.findElement(By.xpath("//ul[@id='side-menu']/descendant::span[text()='Transactions']")).click();
		 	Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='New Deposit']")).click();
			Thread.sleep(2000);
		driver.findElement(By.xpath("//select[@id='account']/option[@value='AhmedHamoud']")).click();
			Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='description']")).sendKeys("First Salary");
			Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='amount']")).sendKeys("1,000");
			Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='submit']")).click();
			Thread.sleep(2000);

	//System Validation using assertion,
			
	   String expectedText= "ahmedHamoud";
	   String actualText = driver.findElement(By.xpath("//option [text()='AhmedHamoud']")).getText();
	   Assert.assertEquals("Account Full Name Does Not Match!",expectedText,actualText);
//		 //validation-1
//		//Explicit Wait
//		String depositValidater = 
//		webDriverWait wait = new WebDriverWait(driver,10);
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By Locator));
//		 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addDepositeButtonLocator));
		
	   driver.close();
	   driver.quit();
		
	}
}
