package testPageObject;

import common.CommonApi;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageObject.DisableAndEnableClass;

import java.sql.Driver;

public class DropdownAndDisableCase extends CommonApi {
   // @Test
    public void testDisableAndEnagle() throws InterruptedException {


        DisableAndEnableClass obj3 = PageFactory.initElements(driver, DisableAndEnableClass.class);
        obj3.setGoogleSearchBox("http://www.prothom-alo.com/");
    }
   // @Test
    public void selectClass() throws InterruptedException {
        DisableAndEnableClass obj3 = PageFactory.initElements(driver, DisableAndEnableClass.class);
         obj3.setDropdowBox();
    }

}