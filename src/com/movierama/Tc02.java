package com.movierama;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author kavadias The current unit test deals with 1 negative and 1 positive
 *         search test More precisely in the context of the negative test, a
 *         search for the word "jijikos" is performed whereas in the positive
 *         test, the word "Tom' is searched
 */
public class Tc02 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

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
	 * Method for the negative test of searching for "jijikos"
	 */
	public void TestForNonMatchingTitle() throws Exception {
		/**
		 * before and after values are kept in order to be calculated later on
		 * with the getResponseTime method of the parent class
		 */
		long before = 0, after = 0;
		try {
			before = System.currentTimeMillis();
			driver.get(MovieRamaUtilities.baseUrl);
			after = System.currentTimeMillis();
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);

		/**
		 * Negative test for the term "jijikos" which is expected to have no
		 * matches
		 */
		try {
			WebElement searchField = driver
					.findElement(By
							.xpath("html/body/div[1]/div[2]/form/div/search-input/input"));
			searchField.sendKeys("jijikos");
			searchField.sendKeys(Keys.RETURN);
			driver.findElement(By.xpath("html/body/div[1]")).click();
			Thread.sleep(5000); // Delay for allowing to see the operation on
								// the browser

		} catch (NoSuchElementException e) {
			verificationErrors.append(e.toString());
		}

		/**
		 * Assertion for the existence of the text: "0 results found"
		 * corresponding to search for "jijikos"
		 */
		try {
			assertThat(
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText(), containsString("0 results found"));
		} catch (NoSuchElementException e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	/**
	 * Method for the negative test of searching for "jijikos"
	 */
	public void TestForIllegalCharacters() throws Exception {
		/**
		 * before and after values are kept in order to be calculated later on
		 * with the getResponseTime method of the parent class
		 */
		long before = 0, after = 0;
		try {
			before = System.currentTimeMillis();
			driver.get(MovieRamaUtilities.baseUrl);
			after = System.currentTimeMillis();
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);

		/**
		 * Negative test for the term ""@#$%^%"" which is expected to have no
		 * matches
		 */
		try {
			WebElement searchField = driver
					.findElement(By
							.xpath("html/body/div[1]/div[2]/form/div/search-input/input"));
			searchField.sendKeys("@#$%^%");
			searchField.sendKeys(Keys.RETURN);
			driver.findElement(By.xpath("html/body/div[1]")).click();
			Thread.sleep(5000); // Delay for allowing to see the operation on
								// the browser

		} catch (NoSuchElementException e) {
			verificationErrors.append(e.toString());
		}

		/**
		 * Assertion for the existence of the text: "0 results found"
		 * corresponding to search for "@#$%^%"
		 */
		try {
			assertThat(
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText(), containsString("0 results found"));
		} catch (NoSuchElementException e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	/**
	 * Method for the positive test of searching for "Tom"
	 */
	public void TestForMatchingTitle() throws Exception {
		/**
		 * before and after values are kept in order to be calculated later on
		 * with the getResponseTime method of the parent class
		 */
		long before = 0, after = 0;
		try {
			before = System.currentTimeMillis();
			driver.get(MovieRamaUtilities.baseUrl);
			after = System.currentTimeMillis();
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);

		/**
		 * Positive test for the term "Tom"
		 */
		try {
			WebElement searchField = driver
					.findElement(By
							.xpath("html/body/div[1]/div[2]/form/div/search-input/input"));
			searchField.sendKeys("Tom");
			searchField.sendKeys(Keys.RETURN);
			Thread.sleep(5000);

		} catch (NoSuchElementException e) {
			verificationErrors.append(e.toString());
		}

		/**
		 * retrieve the String of the results found
		 */
		String resultsFound = driver.findElement(
				By.xpath("html/body/div[1]/div[3]/div[1]/div")).getText();

		/**
		 * Assert that the results found are not 0, i.e. the given search text
		 * has matches
		 */
		assertTrue(resultsFound.compareTo("0 results found") != 0);

		/**
		 * Next step is to verify that the number of matches does not change in
		 * case we change the number of items per page
		 */

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
			verificationErrors.append("\n 10 items/page for search:"
					+ e.toString());
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
			verificationErrors.append("\n 20 items/page for search:"
					+ e.toString());
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
			verificationErrors.append("\n 30 items/page for search:"
					+ e.toString());
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
		Thread.sleep(5000);

		try {
			assertEquals(
					stringInitial,
					driver.findElement(
							By.xpath("html/body/div[1]/div[3]/div[1]/div"))
							.getText());
		} catch (Error e) {
			verificationErrors.append("\n 50 items/page for search:"
					+ e.toString());
		}
	}

	@Test
	/**
	 * Method for the positive test of searching for "Tom" among all entries on current page
	 */
	public void TestForMatchingTitleinAllPageEntries() throws Exception {
		long before = 0, after = 0;
		try {
			before = System.currentTimeMillis();
			driver.get(MovieRamaUtilities.baseUrl);
			after = System.currentTimeMillis();
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);

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
			WebElement searchField = driver.findElement(By.xpath("//input"));
			searchField.sendKeys("Tom");
			searchField.sendKeys(Keys.RETURN);
			Thread.sleep(5000);

			int numberOfFindings = 0;
			/**
			 * Traverse all pages until there exists no "next" link
			 */
			do {
				/**
				 * traverse all the titles in the current page
				 */
				List<WebElement> elements = driver.findElements(By
						.xpath("//*[contains(@class, 'header')]"));
				
				System.out.println("Number of elements is:" + elements.size());

				for (WebElement ele : elements) {

					try {
						assertTrue(ele.getText().toUpperCase().contains("TOM"));
						System.out.println("Appearence #:" + ++numberOfFindings
								+ " TOM found in:" + ele.getText());
					} catch (Error e) {
						verificationErrors
								.append("error in the string comparison"
										+ e.toString());
					}
				}
				/**
				 * Move to next page
				 */		
				driver.findElement(By.xpath("//a[contains(text(),'next')]")).click();
				
				/**
				 * Keep on traversing as far as there exists the "next" link 
				 * or
				 * there does not exist the "next", but exists the "previous"
				 */
			} while (MovieRamaUtilities.IsTestElementPresent(driver,
					"//a[contains(text(),'next')]")
					||
					( (!(MovieRamaUtilities.IsTestElementPresent(driver,
							"//a[contains(text(),'next')]")) && (MovieRamaUtilities.IsTestElementPresent(driver,
									"//a[contains(text(),'previous')]"))))
					);

		} catch (NoSuchElementException e) {
			verificationErrors.append(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		MovieRamaUtilities.writeMsgToFile(verificationErrorString,
				"C:\\temp\\TC02.txt");
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
