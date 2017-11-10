package pageObject;

import common.CommonApi;
import excelReader.DataReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends CommonApi {
    //    @FindBy(how = How.ID,using = "DrpDwnMn05label")
//     WebElement Practice;
    @FindBy(how = How.CLASS_NAME, using = "style-iiqg2nkxactionTitle")
   static WebElement  login;
    @FindBy(how = How.ID, using = "signUpDialogswitchDialogLink")
    WebElement getLogin;
    @FindBy(how = How.XPATH, using = ".//*[@id='memberLoginDialogswitchDialogLink']")
    WebElement signup;
    @FindBy(how = How.ID, using = "signUpDialogemailInputinput")
    WebElement inputEmail;
    @FindBy(how = How.ID, using = "signUpDialogpasswordInputinput")
    WebElement passWord;
    @FindBy(how = How.ID, using = "signUpDialogretypePasswordInputinput")
    WebElement retypePassWord;
    @FindBy(how = How.ID, using = "memberLoginDialogemailInputinput")
    WebElement inputLogInEmail;
    @FindBy(how = How.ID, using = "memberLoginDialogpasswordInputinput")
    WebElement intputPass;
    @FindBy (how =How.ID,using = "memberLoginDialogoklink")
    WebElement goToClick;

    public  void setSignup(String email, String pass, String retypePass) throws Exception {
        login.click();
        Thread.sleep(5000);
        getLogin.click();
        signup.click();
        inputEmail.sendKeys(email);
        passWord.sendKeys(pass);
        retypePassWord.sendKeys(retypePass);
        Thread.sleep(5000);
    }

    public void setLogin(String logemail, String logpass) throws Exception {
        String [] items = DataReader.getDataFromExecelFile("/src/test/resources/selenium.xlsx",0);
            for (int I = 1; I<items.length; I++) {
                login.click();
                Thread.sleep(5000);
                getLogin.click();
                Thread.sleep(5000);
                inputLogInEmail.sendKeys(logemail);
                Thread.sleep(5000);
                intputPass.sendKeys(logpass);
                goToClick.click();
                Thread.sleep(6000);
            }
    }

}