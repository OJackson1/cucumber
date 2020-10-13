package cuke.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class googleTest {
	
	private static WebDriver driver;
	private static WebElement targ;
	
	private static String searchTerm = "kittens";

	@Before
	public void init(){
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver(chromeCfg());
	}
	
	@Given("^we can open google$")
	public void we_can_open_google() throws Throwable {

		driver.get("https://google.com");
		Thread.sleep(1500);
		assertEquals("Google", driver.getTitle());
		System.out.println("Google opened");
	}

	@When("^we search for kittens$")
	public void we_search_for_kittens() throws Throwable {
		targ = driver.findElement(By.name("q"));
		Thread.sleep(1500);
		assertNotNull(targ);
		targ.sendKeys(searchTerm);
		System.out.println("What is in search bar: " + targ.getAttribute("value"));
		targ.submit();

	}
	
	@Then("^google will return us images of kitten$")
	public void google_will_return_us_images_of_kitten() throws Throwable {
	    targ = driver.findElement(By.xpath("//*[@id=\"hdtb-msb-vis\"]/div[2]/a"));
	    Thread.sleep(1500);
		targ.click();
		
		targ = driver.findElement(By.xpath("//*[@id=\"islrg\"]/div[1]/div[1]/a[1]/div[1]/img"));
		Thread.sleep(1500);
		Boolean hasImage = targ.getAttribute("src").contains("image");
		assertTrue(hasImage);
	}
	
	@After
    public void tearDown() {
        driver.close();
    }
	
	public static ChromeOptions chromeCfg() {
	 Map<String, Object> prefs = new HashMap<String, Object>();
	 ChromeOptions chromeOptions = new ChromeOptions();
	  
	 prefs.put("profile.default_content_setting_values.cookies", 2);
	 prefs.put("network.cookie.cookieBehavior", 2);
	 prefs.put("profile.block_third_party_cookies", true);

	 chromeOptions.setExperimentalOption("prefs", prefs);

	 return chromeOptions;
	 }
}
