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
	@Test
	public void testName() {
		String testName = "testname";
		driver.get(getBaseUrl() + "hello?name=" + testName);
		assertTrue(driver.getPageSource().contains(testName),
				"Sent name not found in page");
	}

	@Test
	public void hello() throws IOException {
		String testName = "testname";
		HttpURLConnection connection = (HttpURLConnection)new URL("http://localhost:" + port +"/hello?name="+testName).openConnection();
		{
			connection.connect();
			assertEquals(200, connection.getResponseCode());
			
			try (InputStream in = connection.getInputStream()) {
				String output = IOUtils.toString(in, StandardCharsets.UTF_8);
				assertTrue(output.contains(testName),"Sent name not found in page  with source \n" + output);
			}
		}
	}
	
	@Test
	@DisplayName("Redirect Error")
	public void redictionTest() throws IOException{
		HttpURLConnection co = (HttpURLConnection)new URL(getBaseUrl()).openConnection();
		co.connect();
		assertEquals(200,co.getResponseCode());
		assertEquals((getBaseUrl()+"hello"),co.getURL().toString());
		co.connect();
	}
	
}
