package feature;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class logins {
	 WebDriver driver;
	 Object[][] testData;
	 
	 @Given("User is on Home Page")
	 public void open_chrome_and_open_application() {
		 System.setProperty("webdriver.chrome.driver","G:\\worldline\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://practice.expandtesting.com/");
		 System.out.println("Open Home Page Executed");
	 }
	 
	 @When("User navigate to Login Page")
	 public void login_page() {
		 WebElement login = driver.findElement(By.xpath("(//a[normalize-space()='Login Form'])[1]"));
		 login.click();
		 String expectedURL = "https://practice.expandtesting.com/login";
		 String actualURL = driver.getCurrentUrl();
		 Assert.assertEquals(expectedURL,actualURL);
		 System.out.println("Navigate to Login Page Executed");
	 }
	 
	 @Then("User enters username and password {int}")
	 public void enter_username_and_password(int userIndex) {
		 testData = testingData();
		 String usernames = (String) testData[userIndex - 1][0];
	     String passwords = (String) testData[userIndex - 1][1];
		 WebElement user = driver.findElement(By.name("username"));
		 user.sendKeys(usernames);
		 WebElement pass = driver.findElement(By.name("password"));
		 pass.sendKeys(passwords);
		 System.out.println("Enter Username and Password Executed");
	 }
	 

	 @And("User should get logged in")
	 public void login_with_credentials()  {
		 WebElement l = driver.findElement(By.xpath("(//button[normalize-space()='Login'])[1]"));
		 l.click();
		 System.out.println("User clicked the login button");
	 }
	 
	 @Then("Message displayed Login Successfully or not")
	 public void login_success() throws InterruptedException {
		 Thread.sleep(2000);
		 String expectedURL = "https://practice.expandtesting.com/secure";
		 String actualURL = driver.getCurrentUrl();
		 if (expectedURL.equals(actualURL)) {
			    System.out.println("Login Successful");
			} else {
			    System.out.println("Login Failed");
			}
		 driver.quit();
	 }
	 
	 public Object[][] testingData(){
		 ReadExcelFile config = new ReadExcelFile("G:\\worldline\\taskSample2.xlsx");
			
		 int rows = config.getRowCount(0);
		 int col = config.getColCount(0);
			
			Object[][] credentials = new Object[rows][col];
			
			for(int i=0;i<rows;i++) {
				for(int j=0;j<col;j++) {
					credentials[i][j] = config.getData(0, i+1, j);
				}
			}
			
			return credentials;
		}
}
