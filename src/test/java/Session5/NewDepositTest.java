package Session5;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewDepositTest {

	WebDriver driver;
	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");
	}
	
//	@Test(priority=0)
//	public void userShouldBeAbleAddDeposite() throws InterruptedException {     //prefered to have same test case topect as method name
//		driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
//		Thread.sleep(2000);
//		driver.findElement(By.id("password")).sendKeys("abc123");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
//		Thread.sleep(3000);		
//	}   
	
	
	@Test(priority=1)
	public void userShouldBeAbleAddDeposite2() throws InterruptedException {     //prefered to have same test case topect as method name
		driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys("abc123");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		Thread.sleep(3000);	
		String expectedTitle = "Dashboard- iBilling";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "You fucked up");
		
		By Transactions_MENU_LOCATOR= By.xpath("//ul[@id='side-menu']/descendant::span[text()='Transactions']");
		By NEW_DEPOSIT_LOCATOR= By.linkText("New Deposit");
		
		driver.findElement(Transactions_MENU_LOCATOR).click();
		//apply wait
		waitForElement(NEW_DEPOSIT_LOCATOR,driver,20);
		driver.findElement(NEW_DEPOSIT_LOCATOR).click();
	
		//SELECT AN ACCOUNT from DropDown
		Select select = new Select(driver.findElement(By.cssSelector("select#account")));
		select.selectByVisibleText("AhmedHamoud");
		
	
		
		//generet random number
//		Random rnd = new Random();
//		//rnd.nextInt(bound)
//		rnd.nextInt(999);
//		=new Random().nextInt(999); //need to be store to be use later
		Thread.sleep(2000);
		String expectedDescription = "AutomationTest"+ new Random().nextInt(999);
		driver.findElement(By.id("description")).sendKeys(expectedDescription);
		driver.findElement(By.id("amount")).sendKeys("100,000");
		driver.findElement(By.id("submit")).click();
		Thread.sleep(2000);
		// validation with explicit wait and assertion
		waitForElement(By.linkText(expectedDescription),driver,60);
		//import list=java.util
		//since we use List of webelemnts than whenever we try to use driver after = add s to elements
		 List<WebElement> descriptionElements =  driver.findElements(By.xpath("//table/descendant::a"));
		//scroll down
		 JavascriptExecutor js  = (JavascriptExecutor)driver;
		 
		 js.executeScript("scroll(0,400)");
		 
		 //Assert.assertEquals(actual, expected, message);
	     //next we will try assert true and false
	    // Assert.assertTrue(condition, message);
	     //if you write a method that gaves boolen always start with is
	     Assert.assertTrue(isDescriptionMatch(expectedDescription,descriptionElements), "Deposti unsucessfull");
	     Thread.sleep(2000);
	}
	
	private boolean isDescriptionMatch(String expectedDescription, List<WebElement> descriptionElements) {
	//using for loop for the 20 item in description 
		for (int i=0; i< descriptionElements.size();i++) {
		//comperssing always must have java statment call if 
		if(expectedDescription.equalsIgnoreCase(descriptionElements.get(i).getText())) {
		return true;
			
		}
		
	}

		return false;
}

	//private void waitForElement(By nEW_DEPOSIT_LOCATOR, WebDriver driver2, int i) {
	private void waitForElement(By locator, WebDriver driver1, int time) {
	new WebDriverWait(driver1,time).until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}

	@AfterMethod
	public void closingEverything() {
		driver.close();
		driver.quit();

	
 }
	
}
