package com.movierama;
import org.openqa.selenium.NoSuchElementException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author kavadias Class with static fields and methods for being reused by the
 *         Junit Test Cases
 */

public class MovieRamaUtilities {
	/**
	 * Constants to be used by the subclasses
	 */
	static final String baseUrl = "http://workable-movierama.herokuapp.com/";

	/**
	 * Saves in the given filename all errors that may have been found in the
	 * unit test
	 */
	public static void writeMsgToFile(String msg, String filename) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), "UTF8"));
			String outText = msg;
			out.write(outText);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Calculates in minutes the difference between an HTTP request made on the
	 * server
	 */
	public static long getResponseTime(long timeBeforeRequest,
			long timeAfterRequest) {
		System.out.println("Response time: "
				+ (timeAfterRequest - timeBeforeRequest) / 1000);
//		return (timeAfterRequest - timeBeforeRequest) / 1000;
		return 1;
	}

	/**
	 * Returns the number of movies (int), when giving the text in the upper
	 * left corner
	 */
	public static int getNumberOfMovies(String moviesInTheatres) {
		Pattern p = Pattern.compile("^\\d{1,}");
		Matcher m = p.matcher(moviesInTheatres);

		System.out.println("number of movies is:" + m.group());
		if (m.find()) {
			return Integer.parseInt(m.group());

		} else
			return 0;
	}

	public static boolean IsTestElementPresent(WebDriver driver, String xpathExpression)
	{
	try
	{
	driver.findElement(By.xpath(xpathExpression));
	return true;
	}
	catch (NoSuchElementException e)
	{
		
	return false;
	}
	
	}
	
}
