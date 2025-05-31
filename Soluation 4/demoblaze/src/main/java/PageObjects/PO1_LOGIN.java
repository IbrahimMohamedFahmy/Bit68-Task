package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO1_LOGIN
{
    // Create constructor to call factory page class to declare web driver
    public WebDriver driver;

    public PO1_LOGIN (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // Data Members
    @FindBy (id = "signin2")
    private WebElement signUpHome;

    @FindBy (id = "sign-username")
    private WebElement usernameSingUP;

    @FindBy (id = "sign-password")
    private WebElement passwordSingUp;

    @FindBy (css = "button[onclick='register()']")
    private WebElement singUpForm;

    @FindBy (xpath = "//div[@id='signInModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement closeSingUp;

    @FindBy (id = "login2")
    private WebElement loginHome;

    @FindBy (id = "loginusername")
    private WebElement usernameSingIn;

    @FindBy (id = "loginpassword")
    private WebElement passwordSingIn;

    @FindBy (css = "button[onclick='logIn()']")
    private WebElement singInForm;

    @FindBy (css = "div[id='logInModal'] div[class='modal-footer'] button:nth-child(1)")
    private WebElement closeSingIn;

    // Methods
    public void openSignUpModal()
    {
        signUpHome.click();
    }

    public void enterSignUpCredentials(String username, String password)
    {
        usernameSingUP.clear();
        usernameSingUP.sendKeys(username);

        passwordSingUp.clear();
        passwordSingUp.sendKeys(password);
    }

    public void submitSignUpForm()
    {
        singUpForm.click();
    }

    public void closeSignUpModal()
    {
        closeSingUp.click();
    }

    public void signUp(String username, String password)
    {
        openSignUpModal();
        enterSignUpCredentials(username, password);
        submitSignUpForm();
    }

    public void openSignInModal()
    {
        loginHome.click();
    }

    public void enterSignInCredentials(String username, String password)
    {
        usernameSingIn.clear();
        usernameSingIn.sendKeys(username);

        passwordSingIn.clear();
        passwordSingIn.sendKeys(password);
    }

    public void submitSignInForm()
    {
        singInForm.click();
    }

    public void closeSignInModal()
    {
        closeSingIn.click();
    }

    public void signIn(String username, String password)
    {
        openSignInModal();
        enterSignInCredentials(username, password);
        submitSignInForm();
    }



}
