package pageObject;

import com.relevantcodes.extentreports.LogStatus;
import pageObject.HomePage;
import common.CommonApi;
import excelReader.DataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.IOException;

public class TextOnThisElement extends CommonApi {
    @FindBy(how = How.ID, using = "opentab")
    public static WebElement textBox;
    @FindBy (how = How.XPATH, using =".//*[@id='isp_icon_id']")
    WebElement searchBox;
//    @FindBy(how = How.XPATH, using = ".//*[@id='checkbox-example']/fieldset/label[1]")
//    public static WebElement element;

    public void getText() throws InterruptedException {
        String element;
        element = textBox.getText();
        System.out.println("&&&&&");
        Thread.sleep(5000);
    }

    public void clickByIdLocator() throws InterruptedException {
        clickByXpath(".//*[@id='checkbox-example']/fieldset/label[1]");
        test.log(LogStatus.INFO, "Clicking on XPATH Checkbox Example");
        Thread.sleep(5000);
        clickByXpath(".//*[@id='opentab']");
        test.log(LogStatus.INFO, "Clicking on XPATH Opentab");
        Thread.sleep(6000);
        goBackHomeWindow();
        Thread.sleep(4000);
       clickByXpath(".//*[@id='openwindow']");
       test.log(LogStatus.INFO, "Clicking on XPATH Openwindow");
        Thread.sleep(5000);
        test.log(LogStatus.PASS, "Passed Test Case");
        //System.out.println(driver.getTitle());
    }
    public void getSearchItem() throws Exception {
        String [] items = DataReader.getDataFromExecelFile("/src/test/resources/selenium.xlsx",0);
        for (int I = 1; I<items.length; I++){
            Thread.sleep(5000);
            searchBox.sendKeys(items[I]);
            Thread.sleep(5000);
            searchBox.clear();
            Thread.sleep(5000);
        }
     }

}
