package eventHub.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import eventHub.pageComponents.LoginPage;

public class BaseTest {
	
	private static ThreadLocal<LoginPage> lp = new ThreadLocal<>();
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public LoginPage getLoginPage() {
	    return lp.get();
	}
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\eventHub\\resources\\GlobalData.properties");
		prop.load(fis);
		String browser= System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser"); //maven integration of browser
		
		if(browser.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			if(browser.contains("headless")) {
				options.addArguments("--headless=new");
			    options.addArguments("--window-size=1920,1080");
			    options.addArguments("--disable-gpu");
			}
			driver.set(new ChromeDriver(options));
		}
		else if(browser.contains("firefox")) {
			driver.set(new FirefoxDriver());
		}
		else if(browser.contains("edge")) {
			driver.set(new EdgeDriver());
		}
		
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return getDriver();
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchingApplication() throws IOException {
		 initializeDriver();

		    lp.set(new LoginPage(getDriver()));

		    lp.get().goTo(getDriver());

		    return lp.get();
	}

	@AfterMethod(alwaysRun=true)
	public void af() {
		if(getDriver()!=null) {
			getDriver().quit();
			driver.remove();
		}
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jSonContent=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jSonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(/*WebDriver driver, */String testCaseName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)getDriver();
		File src=ts.getScreenshotAs(OutputType.FILE);
		String filePath=System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(src, new File(filePath));
		return filePath;
		
	}
	
}
