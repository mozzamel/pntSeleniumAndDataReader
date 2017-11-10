package common;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonApi {

    public static WebDriver driver = null;
    public static ExtentReports extent;
    public static ExtentTest test;

    private String SAUCELABS_USERNAME = "rupon";
    private String SAUCELABS_ACCESSKEY = "855b7b9f-9012-42a5-8e85-b5dcbcee2e99";
    private String browserstack_username = "mdmhossain1";
    private String browserstack_accesskey = "pS2NT6eb2HqKz5xPd56v";
    @BeforeSuite
    public  void startsExtentReports(){
        extent = new ExtentReports(System.getProperty("user.dir")+"/Extent_report/letstKode.html",true);
        //extent.loadConfig(new File(System.getProperty("user.dir")+"/report-config.xml"));
    }

    @Parameters({"useCloudEnv", "cloudEnvName", "platform", "platformVersion", "browserName", "browserVersion", "url",})

    @BeforeMethod
    public void setUp(boolean useCloudEnv, String cloudEnvName, @Optional String platform,
                      @Optional String platformVersion, @Optional String browserName, @Optional String browserVersion, @Optional String url, @Optional Method method) throws IOException {
       test = extent.startTest(method.getName());

        if (useCloudEnv == true) {
            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName, browserstack_username, browserstack_accesskey, platform, platformVersion, browserName, browserVersion);
            } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
                getCloudDriver(cloudEnvName, SAUCELABS_USERNAME, SAUCELABS_ACCESSKEY, platform, platformVersion, browserName, browserVersion);
            }
        } else {
            getLocalDriver(platform, browserName);
        }
        driver.navigate().to(url);
        test.log(LogStatus.INFO,"Maximising Browser");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

    }

    public WebDriver getLocalDriver(String platform, String browserName) {
        platform = platform.toLowerCase();
        if (browserName.equalsIgnoreCase("chrome")) {
            if (platform.contains("win")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/driver/chromedriver.exe");
                driver = new ChromeDriver();
                test.log(LogStatus.INFO, "Executing Chrome Browser For Windows");
            } else if (platform.contains("mac")) {
                System.setProperty("webdriver.chrome.driver", "/Users/mozzamelhossain/IdeaProjects/pntSelenium/Generic/driver/chromedriver");
                driver = new ChromeDriver();
            }

        } else if (browserName.equalsIgnoreCase("fireFox")) {
            if (platform.contains("win")) {
                System.setProperty("webdriver.gecko.drover", "Generic/driver/geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (platform.contains("mac")) {
                System.setProperty("webdriver.gecko.driver", "/Users/mozzamelhossain/IdeaProjects/pntSelenium/Generic/driver/geckodriver");
                driver = new FirefoxDriver();
            }
        }
        return driver;

    }

    public WebDriver getCloudDriver(String cloudEnvName, String envUserName, String envAccessKey,
                                    String platform, String platformVersion, String browserName, String browserVersion) throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserName", browserName);
        cap.setCapability("browserVersion", browserVersion);
        cap.setCapability("platform", platform);
        cap.setCapability("platformVersion", platformVersion);

        if (cloudEnvName.equalsIgnoreCase("browserstack")) {
            driver = new RemoteWebDriver(new URL("https://www.amazon.com/" + envUserName + envAccessKey + "i dont know what will be"), cap);
        } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
            driver = new RemoteWebDriver(new URL("http://" + envUserName + ":" + envAccessKey + "@ondemand.saucelabs.com:80/wd/hub"), cap);
            System.out.println("Getting Saucelab URL");
        }
        return driver;

    }

    @AfterMethod(alwaysRun = true)
    public static void tearDown() {
        driver.quit();
        extent.endTest(test);
        extent.flush();
    }

    public void clickByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void clickById(String locator) {
        driver.findElement(By.id(locator)).click();
    }

    public void clickByCss(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void typeByCss(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public void typeByCssEnter(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
    }

    public void typeByXpath(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void takeEnterKey(String locator) {
        driver.findElement(By.xpath(locator)).sendKeys(Keys.ENTER);
    }


    public void clearInputFields(String locator) {
        driver.findElement(By.cssSelector(locator)).clear();
    }


    public List<WebElement> getListOfWebElementById(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.id(locator));
        return list;
    }


    public List<WebElement> getListOfWebElementBycss(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.cssSelector(locator));
        return list;
    }

    public List<WebElement> getListOfWebElementByXpath(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.xpath(locator));
        return list;
    }

    public String getCurrentPageUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public String getTextByCss(String locator) {
        String text = driver.findElement(By.cssSelector(locator)).getText();
        return text;
    }

    public String getTextById(String locator) {
        String text = driver.findElement(By.id(locator)).getText();
        return text;
    }

    public String getTextByXpath(String locator) {
        String text = driver.findElement(By.xpath(locator)).getText();
        return text;
    }

    public String getTextByName(String locator) {
        String text = driver.findElement(By.name(locator)).getText();
        return text;
    }

    public List<String> getListOfString(List<WebElement> list) {
        List<String> items = new ArrayList<String>();
        for (WebElement element : list) {
            items.add(element.getText());
        }
        return items;
    }


    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

    public void disMissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void goBackHomeWindow() {
        driver.switchTo().defaultContent();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void iFrameHandle(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilClickable(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilSelectabel(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));

    }
    public static void  captureScreenShot(String  pathname){
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyDirectory(source, new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("screen shot taking ");
        }
    }

}

