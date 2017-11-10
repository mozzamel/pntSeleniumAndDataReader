package testPageObject;

import common.CommonApi;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageObject.HomePage;
public class TestHome extends CommonApi {
   //@Test
    public void letsPractice() throws Exception {
        HomePage obj = PageFactory.initElements(driver, HomePage.class);
        try {
            obj.setSignup("pntselenium@gmail.com","7866dcba","7866dcba");
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(2000);
        }


    }
   // @Test
    public void letsLogIn(){
        HomePage obj = PageFactory.initElements(driver,HomePage.class);
        try {
            obj.setLogin("pntselenium@gmal.com","7866dcba");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}