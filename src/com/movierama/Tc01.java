package com.movierama;

import static org.junit.Assert.assertEquals;

import com.movierama.MovieRamaUtilities;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author kavadias Junit Test case aiming to verify that there's no problem
 *         with accessing the MovieRama website
 */
public class Tc01 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	/**
	 * before and after values are kept in order to be calculated later on with
	 * the getResponseTime method of the parent class
	 */
	long before = 0, after = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	/**
	 * Some essential regression tests take place to make sure that the page has been loaded:
	 * 1. The existence of the "MovieRama" title
	 * 2. The existence of the "movies in theaters this week" text
	 * 3. Change the items/page and confirm that the total number of movies is always the same
	 */
	public void essentialRegressionTest() throws InterruptedException {
		try {
			before = System.currentTimeMillis();
			driver.get(MovieRamaUtilities.baseUrl);
			after = System.currentTimeMillis();
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);

		/**
		 * Assertion for the existence of title "MovieRama" in the retrieved web
		 * page In this respect, it is verified that the site is not down, or
		 * frozen etc
		 */
		try {
			assertEquals("MovieRama", driver.getTitle());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		/**
		 * Assertion for the existence of the text:*
		 * "movies in theaters this week" In this respect, it is verified that
		 * the site has retrieved a number of movies played in theatres
		 */
		try {
			assertThat(
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText(),
					containsString("movies in theaters this week"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		/**
		 * Assertion that the number of retrieved values is not changed when
		 * modifying the number of items/page
		 */

		/**
		 * Change the value of items/page to 5
		 */
		before = System.currentTimeMillis();
		new Select(driver.findElement(By
				.xpath("html/body/div[1]/div[1]/div/select")))
				.selectByValue("5");
		after = System.currentTimeMillis();
		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);

		Thread.sleep(5000);
		/**
		 * At this point the number of movies is stored in the variable
		 * stringInitial and from that moment on verifications are done against
		 * this parameter after changing the number of items/page
		 */
		String stringInitial = driver.findElement(
				By.xpath("html/body/div[1]/div[3]/div[1]/div")).getText();

		/**
		 * Change the value of items/page to 10
		 */
		before = System.currentTimeMillis();
		new Select(driver.findElement(By
				.xpath("html/body/div[1]/div[1]/div/select")))
				.selectByValue("10");
		after = System.currentTimeMillis();
		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
		Thread.sleep(5000);

		try {
			assertEquals(
					stringInitial,
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText());
		} catch (Error e) {
			verificationErrors.append("\n 10 items/page:" + e.toString());
		}

		/**
		 * Change the value of items/page to 20
		 */
		before = System.currentTimeMillis();
		new Select(driver.findElement(By
				.xpath("html/body/div[1]/div[1]/div/select")))
				.selectByValue("20");
		after = System.currentTimeMillis();
		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
		Thread.sleep(5000);

		try {
			assertEquals(
					stringInitial,
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText());
		} catch (Error e) {
			verificationErrors.append("\n 20 items/page:" + e.toString());
		}

		/**
		 * Change the value of items/page to 30
		 */
		before = System.currentTimeMillis();
		new Select(driver.findElement(By
				.xpath("html/body/div[1]/div[1]/div/select")))
				.selectByValue("30");
		after = System.currentTimeMillis();
		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
		Thread.sleep(5000);

		try {
			assertEquals(
					stringInitial,
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText());
		} catch (Error e) {
			verificationErrors.append("\n 30 items/page:" + e.toString());
		}

		/**
		 * Change the value of items/page to 50
		 */
		before = System.currentTimeMillis();
		new Select(driver.findElement(By
				.xpath("html/body/div[1]/div[1]/div/select")))
				.selectByValue("50");
		after = System.currentTimeMillis();
		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
		Thread.sleep(3000);

		try {
			assertEquals(
					stringInitial,
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText());
		} catch (Error e) {
			verificationErrors.append("\n 50 items/page:" + e.toString());
		}

		/**
		 * Assertion for the malformed URL including 99999999999
		 */
		try {
			driver.get("http://workable-movierama.herokuapp.com/page/99999999999");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		try {
			assertEquals(
					"null movies in theaters this week ",
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		/**
		 * Assertion for the malformed URL including xxxxxx
		 */
		try {
			driver.get("http://workable-movierama.herokuapp.com/page/xxxxxx");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		try {
			assertEquals(
					"null movies in theaters this week ",
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
	}
	
	@Test
	/**
	 * Additional tests on: navigation with next and previous buttons
	 */
	public void navigationTest() throws InterruptedException {

		try {
			before = System.currentTimeMillis();
			driver.get(MovieRamaUtilities.baseUrl);
			after = System.currentTimeMillis();
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
		Thread.sleep(5000);

		/**
		 * Change the value of items/page to 5
		 */
		before = System.currentTimeMillis();
		new Select(driver.findElement(By
				.xpath("html/body/div[1]/div[1]/div/select")))
				.selectByValue("5");
		after = System.currentTimeMillis();
		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
		Thread.sleep(5000);

		/**
		 * start navigating among the 5 items/page with the next button (move
		 * forward)
		 */

		driver.findElement(By.xpath("html/body/div[1]/div[3]/div[8]/a"))
				.click(); // only the first time the Xpath is different
		int i = 2;
		while (MovieRamaUtilities.IsTestElementPresent(driver,
				"html/body/div[1]/div[3]/div[8]/a[2]")) {
			System.out.print("page # " + i++ + " I will click on:");
			System.out.println(driver.findElement(
					By.xpath("html/body/div[1]/div[3]/div[8]/a[2]")).getText());
			before = System.currentTimeMillis();
			driver.findElement(By.xpath("html/body/div[1]/div[3]/div[8]/a[2]"))
					.click();
			after = System.currentTimeMillis();
			assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
	//		Thread.sleep(1000);
			try {
				assertThat(
						driver.findElement(
								By.xpath("html/body/div[1]/div[3]/div[1]/div"))
								.getText(),
						containsString("movies in theaters this week"));
			} catch (NoSuchElementException e) {
				verificationErrors.append(e.toString());
			}
		}
		
		/**
		 * start navigating among the 5 items/page with the previous button (move
		 * backwards)
		 */

		driver.findElement(By.xpath("html/body/div[1]/div[3]/div[6]/a"))
				.click(); // only the first time the Xpath is different
		i--;
		while (MovieRamaUtilities.IsTestElementPresent(driver,
				"html/body/div[1]/div[3]/div[8]/a[1]")
				&&
				MovieRamaUtilities.IsTestElementPresent(driver,
						"html/body/div[1]/div[3]/div[8]/a[2]")) {
			System.out.print("page # " + i-- + " I will click on:");
			System.out.println(driver.findElement(
					By.xpath("html/body/div[1]/div[3]/div[8]/a[1]")).getText());
			before = System.currentTimeMillis();
			driver.findElement(By.xpath("html/body/div[1]/div[3]/div[8]/a[1]"))
					.click();
			after = System.currentTimeMillis();
			assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
	//		Thread.sleep(1000);
			try {
				assertThat(
						driver.findElement(
								By.xpath("html/body/div[1]/div[3]/div[1]/div"))
								.getText(),
						containsString("movies in theaters this week"));
			} catch (NoSuchElementException e) {
				verificationErrors.append(e.toString());
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		/**
		 * Any potential errors are logged into a file
		 */
		String verificationErrorString = verificationErrors.toString();
		MovieRamaUtilities.writeMsgToFile(verificationErrorString,
				"C:\\temp\\TC01.txt");
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}