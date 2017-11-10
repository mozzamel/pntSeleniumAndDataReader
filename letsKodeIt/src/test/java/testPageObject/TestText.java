package testPageObject;

import common.CommonApi;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageObject.TextOnThisElement;

public class TestText extends CommonApi {
    //@Test
    public void seeText() throws InterruptedException {
        TextOnThisElement text = PageFactory.initElements(driver, TextOnThisElement.class);
        text.getText();
    }

   // @Test
    public void clickBy() throws InterruptedException {
        TextOnThisElement text = PageFactory.initElements(driver, TextOnThisElement.class);
        text.clickByIdLocator();
    }
    @Test
    public  void searchItems() throws Exception {
        TextOnThisElement text = PageFactory.initElements(driver, TextOnThisElement.class);
        text.getSearchItem();
    }
}