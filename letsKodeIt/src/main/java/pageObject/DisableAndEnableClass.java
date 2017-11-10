package pageObject;

import common.CommonApi;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class DisableAndEnableClass extends CommonApi {
    @FindBy(how = How.ID, using = "lst-ib")
    WebElement googleSearchBox;
    @FindBy(how = How.ID, using = "gs_taif0")
    WebElement GoogleSearchBox2;
    @FindBy(how=How.XPATH,using = ".//*[@id='carselect']")
    static WebElement dropdownBox;

    public void setGoogleSearchBox(String address) throws InterruptedException {
        if (GoogleSearchBox2.isEnabled()) {
            GoogleSearchBox2.sendKeys(address);
            Thread.sleep(5000);
        } else if (googleSearchBox.isEnabled()) {
            googleSearchBox.sendKeys(address);
            Thread.sleep(4000);
        }else {
            System.out.println("didnot find element");
        }
    }
    public void setDropdowBox()  {

        Select sel = new Select(dropdownBox);
        sel.selectByValue("bmw");
        List<WebElement>selectedOption =sel.getAllSelectedOptions();
        System.out.println("_______");
        for(WebElement fruit: selectedOption){
            System.out.println(fruit.getText());
        }
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sel.selectByVisibleText("Honda");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      sel.selectByIndex(inputIndex);
//        sel.selectByVisibleText(inputVisible);



    }



}
