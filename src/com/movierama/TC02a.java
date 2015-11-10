package com.movierama;

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
 * @author kavadias The current unit test deals with Search for all tiles against “Tom” on all 
 * pages for any combination of the number of items/page, in order to confirm they match the results 
 * (TC02 steps 5-9) 
 * It also performs a Search for all tiles against “Tom Jerry” on all pages for any combination of 
 * the number of items/page, in order to confirm they match the results (TC02 step 27) 
 */
public class TC02a {
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
	 * Method for the positive test of searching for "Tom" among all entries on all pages
	 * for any given possibility of items/page
	 */
	public void TestForMatchingTitleinAllPageEntries_Tom() throws Exception {
		long before = 0, after = 0;
		int[] numbersPerPage = { 5, 10, 20, 30, 50 };

		/**
		 * The test is repeated for all cases of items/page
		 */
		for (int i = 0; i < numbersPerPage.length; i++) {

			try {
				before = System.currentTimeMillis();
				driver.get(MovieRamaUtilities.baseUrl);
				after = System.currentTimeMillis();
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
			assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);

			/**
			 * Change the value of items/page to numbersPerPage
			 */
			before = System.currentTimeMillis();
			new Select(driver.findElement(By
					.xpath("html/body/div[1]/div[1]/div/select")))
					.selectByValue(String.valueOf(numbersPerPage[i]));
			after = System.currentTimeMillis();
			assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
			Thread.sleep(5000);

			try {
				WebElement searchField = driver
						.findElement(By.xpath("//input"));
				searchField.sendKeys("Tom");
				searchField.sendKeys(Keys.RETURN);
				Thread.sleep(5000);

				int numberOfFindings = 0;
				List<WebElement> elements;
				/**
				 * Traverse all pages until there exists no "next" link
				 */
				do {
					/**
					 * traverse all the titles in the current page
					 */
					elements = driver.findElements(By
							.xpath("//*[contains(@class, 'header')]"));

					System.out.println("Number of elements is:"
							+ elements.size());

					for (WebElement ele : elements) {

						try {
							assertTrue(ele.getText().toUpperCase()
									.contains("TOM"));
							System.out.println("Appearence #:"
									+ ++numberOfFindings + " TOM found in:"
									+ ele.getText());
						} catch (Error e) {
							verificationErrors
									.append("error in the string comparison"
											+ e.toString());
						}
					}
					/**
					 * Move to next page or get the results of the final page
					 */
					try {
						if (MovieRamaUtilities.IsTestElementPresent(driver,
								"//a[contains(text(),'next')]"))
							driver.findElement(
									By.xpath("//a[contains(text(),'next')]"))
									.click();
						else {
							elements = driver.findElements(By
									.xpath("//*[contains(@class, 'header')]"));

							System.out.println("Number of elements is:"
									+ elements.size());

							for (WebElement ele : elements) {

								try {
									assertTrue(ele.getText().toUpperCase()
											.contains("TOM"));
									System.out.println("Appearence #:"
											+ ++numberOfFindings
											+ " TOM found in:" + ele.getText());
								} catch (Error e) {
									verificationErrors
											.append("error in the string comparison"
													+ e.toString());
								}

							}
						}
					}

					catch (NoSuchElementException e) {
						e.printStackTrace();
					}

					/**
					 * Keep on traversing as far as there exists the "next" link
					 * or there does not exist the "next", but exists the
					 * "previous"
					 */
				} while (MovieRamaUtilities.IsTestElementPresent(driver,
						"//a[contains(text(),'next')]"));

				if (MovieRamaUtilities.IsTestElementPresent(driver,
						"//a[contains(text(),'previous')]")
						&& !(MovieRamaUtilities.IsTestElementPresent(driver,
								"//a[contains(text(),'next')]"))) {
					elements = driver.findElements(By
							.xpath("//*[contains(@class, 'header')]"));

					System.out.println("Number of elements is:"
							+ elements.size());

					for (WebElement ele : elements) {

						try {
							assertTrue(ele.getText().toUpperCase()
									.contains("TOM"));
							System.out.println("Appearence #:"
									+ ++numberOfFindings + " TOM found in:"
									+ ele.getText());
						} catch (Error e) {
							verificationErrors
									.append("error in the string comparison"
											+ e.toString());
						}
					}
				}

			} catch (NoSuchElementException e) {
				verificationErrors.append(e.toString());
			}
		}
		driver.close();
	}

	@Test
	/**
	 * Method for the positive test of searching for "Tom" among all entries on all pages
	 * for any given possibility of items/page
	 */
	public void TestForMatchingTitleinAllPageEntries_Tom_Jerry() throws Exception {
		long before = 0, after = 0;
		int[] numbersPerPage = { 5, 10, 20, 30, 50 };

		/**
		 * The test is repeated for all cases of items/page
		 */
		for (int i = 0; i < numbersPerPage.length; i++) {

			try {
				before = System.currentTimeMillis();
				driver.get(MovieRamaUtilities.baseUrl);
				after = System.currentTimeMillis();
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
			assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);

			/**
			 * Change the value of items/page to numbersPerPage
			 */
			before = System.currentTimeMillis();
			new Select(driver.findElement(By
					.xpath("html/body/div[1]/div[1]/div/select")))
					.selectByValue(String.valueOf(numbersPerPage[i]));
			after = System.currentTimeMillis();
			assertTrue(MovieRamaUtilities.getResponseTime(before, after) < 3);
			Thread.sleep(5000);

			try {
				WebElement searchField = driver
						.findElement(By.xpath("//input"));
				searchField.sendKeys("Tom Jerry");
				searchField.sendKeys(Keys.RETURN);
				Thread.sleep(5000);

				int numberOfFindings = 0;
				List<WebElement> elements;
				/**
				 * Traverse all pages until there exists no "next" link
				 */
				do {
					/**
					 * traverse all the titles in the current page
					 */
					elements = driver.findElements(By
							.xpath("//*[contains(@class, 'header')]"));

					System.out.println("Number of elements is:"
							+ elements.size());

					for (WebElement ele : elements) {

						try {
							assertTrue((ele.getText().toUpperCase()
									.contains("TOM") && (ele.getText().toUpperCase()
											.contains("JERRY"))));
							System.out.println("Appearence #:"
									+ ++numberOfFindings + " Tom Jerry found in:"
									+ ele.getText());
						} catch (Error e) {
							verificationErrors
									.append("error in the string comparison"
											+ e.toString());
						}
					}
					/**
					 * Move to next page or get the results of the final page
					 */
					try {
						if (MovieRamaUtilities.IsTestElementPresent(driver,
								"//a[contains(text(),'next')]"))
							driver.findElement(
									By.xpath("//a[contains(text(),'next')]"))
									.click();
						else {
							elements = driver.findElements(By
									.xpath("//*[contains(@class, 'header')]"));

							System.out.println("Number of elements is:"
									+ elements.size());

							for (WebElement ele : elements) {

								try {
									assertTrue((ele.getText().toUpperCase()
											.contains("TOM") && (ele.getText().toUpperCase()
													.contains("JERRY"))));
									System.out.println("Appearence #:"
											+ ++numberOfFindings
											+ " TOM found in:" + ele.getText());
								} catch (Error e) {
									verificationErrors
											.append("error in the string comparison"
													+ e.toString());
								}

							}
						}
					}

					catch (NoSuchElementException e) {
						e.printStackTrace();
					}

					/**
					 * Keep on traversing as far as there exists the "next" link
					 * or there does not exist the "next", but exists the
					 * "previous"
					 */
				} while (MovieRamaUtilities.IsTestElementPresent(driver,
						"//a[contains(text(),'next')]"));

				if (MovieRamaUtilities.IsTestElementPresent(driver,
						"//a[contains(text(),'previous')]")
						&& !(MovieRamaUtilities.IsTestElementPresent(driver,
								"//a[contains(text(),'next')]"))) {
					elements = driver.findElements(By
							.xpath("//*[contains(@class, 'header')]"));

					System.out.println("Number of elements is:"
							+ elements.size());

					for (WebElement ele : elements) {

						try {
							assertTrue((ele.getText().toUpperCase()
									.contains("TOM") && (ele.getText().toUpperCase()
											.contains("JERRY"))));
							System.out.println("Appearence #:"
									+ ++numberOfFindings + " Tom Jerry found in:"
									+ ele.getText());
						} catch (Error e) {
							verificationErrors
									.append("error in the string comparison"
											+ e.toString());
						}
					}
				}

			} catch (NoSuchElementException e) {
				verificationErrors.append(e.toString());
			}
		}
		driver.close();
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
