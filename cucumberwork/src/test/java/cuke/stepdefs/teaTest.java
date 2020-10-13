package cuke.stepdefs;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class teaTest {

	private static WebDriver driver;
	private static WebElement targ;

	@Before
	public void init(){
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
	}
	
	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {
		driver.get("http://www.practiceselenium.com/welcome.html");
		Thread.sleep(1500);
		System.out.println("tea website accessed");
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_menu_page() throws Throwable {
		 targ = driver.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a"));
		 Thread.sleep(1500);
         targ.click();
         System.out.println("Menu clicked");
         Thread.sleep(1500);
         targ = driver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000450914921\"]/div/h1"));
         assertEquals("Menu", targ.getText());
         System.out.println("Menu found");
	}
	
	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000453230000\"]/div/p/span/span/strong"));
		Thread.sleep(1500);
	    assertEquals("Green Tea", targ.getText());
	    
	    
		System.out.println("green tea is located");
	
	}
	
	@Then("^I can see red tea$")
	public void i_can_see_red_tea() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000453231072\"]/div/p/span/span/strong"));
		Thread.sleep(1500);
	    assertEquals("Red Tea", targ.getText());
	    System.out.println("red tea is located");
	}

	@Then("^I can see Oolong tea$")
	public void i_can_see_Oolong_tea() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000453231735\"]/div/p/span/span/strong"));
		Thread.sleep(1500);
	    assertEquals("Oolong Tea", targ.getText());
	    System.out.println("oolong tea is located");
	}
	
	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
		targ = driver.findElement(By.id("wsb-button-00000000-0000-0000-0000-000451955160"));
		Thread.sleep(1500);
        targ.click();
        System.out.println("Checkout clicked");
		
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000451990420\"]/div/pre/strong/span"));
		Thread.sleep(1500);
        assertEquals("Enter your billing information", targ.getText());
        System.out.println("Checkout reached");

	}
	
	@After
    public void tearDown() {
        driver.close();
    }
}
