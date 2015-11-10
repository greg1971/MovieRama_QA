package com.movierama;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * 
 * @author kavadias
 * Test Suite for the MovieRama project
 * It includes 2 test cases, TC01 for properly viewing the web application and TC02 for 
 * verifying the appropriate behavior of a fundamental subset of the Search mechanism
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ Tc01.class, Tc02.class, TC02a.class })
public class MovieRamaJUnitTestSuite {

}
