package fr.uha.ensisa.ff.mantest_app.it;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloIT{
	
	public static WebDriver driver;
	private static String host, port;
	
	@BeforeEach
	public void setupWebDriver() {
		if(driver != null) return;
		host = System.getProperty("host", "localhost");
		port = System.getProperty("servlet.port", "8080");
		// Looking for marionette in PATH
		String ext = System.getProperty("os.name", "").toLowerCase().startsWith("win") ? ".exe" : "";
		String geckodrivername = "geckodriver" + ext;
		Collection<String> pathes = new ArrayList<>();
		for (String source : new String[] {
				System.getProperty("PATH") /* posix */,
				System.getenv().get("Path") /* win < 10 */,
				System.getenv().get("PATH") /* win >= 10 */ }) {
			if (source != null)
			pathes.addAll(Arrays.asList(source.trim().split(File.pathSeparator)));
		}
		File geckoDriver = null;
		for (String path : pathes) {
			File f = new File(path, geckodrivername);
			if (f.exists() && f.canExecute()) {
				System.setProperty("webdriver.gecko.driver", f.getAbsolutePath());
				geckoDriver = f;
				break;
			}
		}
		if (geckoDriver == null)
			throw new IllegalStateException("Cannot find geckodriver on "
					+ pathes.toString());
		driver = new FirefoxDriver();
	}
	
	@AfterAll
	public static void shutdownWebDriver() {
		if (driver != null) {
			driver.quit();
			try { driver.close();
			} catch (Exception x) {
			}
			driver = null;
		}
	}
	
	public static String getBaseUrl() {
		return "http://" + host + ":" + port + '/';
	}
	
	private void reset() {
		driver.get(getBaseUrl()+"list");
		while(driver.findElements(By.name("delete")).size() != 0) {
			driver.findElements(By.name("delete")).get(0).click();
		}
		driver.get(getBaseUrl()+"reportlist");
		while(driver.findElements(By.name("delete")).size() != 0) {
			driver.findElements(By.name("delete")).get(0).click();
		}
		driver.get(getBaseUrl()+"setup");
	}
	
	@Test
	@DisplayName("Redirect Error")
	public void redictionTest() throws IOException{
		HttpURLConnection co = (HttpURLConnection)new URL(getBaseUrl()).openConnection();
		co.connect();
		assertEquals(200,co.getResponseCode());
		assertEquals((getBaseUrl()+"list"),co.getURL().toString());
		co.connect();
	}
	
	@Test
	@DisplayName("Delete report test")
	public void deleteReportTest() throws IOException{
		reset();
		driver.findElements(By.name("execute")).get(0).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("next")).click();
		driver.get(getBaseUrl()+"reportlist");
		driver.findElements(By.name("delete")).get(0).click();
		assertEquals(driver.findElements(By.className("listname")).size(),0);
	}
	
	@Test
	@DisplayName("Report list size test")
	public void reportTest() throws IOException{
		reset();
		driver.findElements(By.name("execute")).get(0).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("next")).click();
		driver.findElements(By.name("execute")).get(2).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("next")).click();	
		driver.get(getBaseUrl()+"reportlist");
		assertEquals(driver.findElements(By.className("listname")).size(),2);
	}
	
	@Test
	@DisplayName("Report test")
	public void report() throws IOException{
		reset();
		driver.findElements(By.name("execute")).get(0).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.className("btn-danger")).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.className("btn-warning")).click();
		driver.findElement(By.name("next")).click();
		driver.get(getBaseUrl()+"reportlist");
		driver.findElement(By.name("execute")).click();
		assertEquals(driver.findElements(By.className("SUCCESS")).size(),1);
		assertEquals(driver.findElements(By.className("FAILED")).size(),1);
		assertEquals(driver.findElements(By.className("SKIPED")).size(),1);
	}

	
}
