package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO2_ADD_TO_CARD
{
    // Create constructor to call factory page class to declare web driver
    public WebDriver driver;

    public PO2_ADD_TO_CARD (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Data Members
    @FindBy(xpath = "//a[normalize-space()='Nokia lumia 1520']")
    private WebElement nokiaPhone;

    @FindBy(xpath = "//a[@class='btn btn-success btn-lg']")
    private WebElement addToCartButton;

    @FindBy(css = "a[href='cart.html']")
    private WebElement cartPageLink;

    @FindBy(id = "tbodyid")
    private WebElement cartTable;

    // Methods
    public void clickOnNokiaPhone()
    {
        nokiaPhone.click();
    }

    public void clickOnAddToCart()
    {
        addToCartButton.click();
    }

    public void goToCartPage()
    {
        cartPageLink.click();
    }

    public String getCartTableText()
    {
        return cartTable.getText();
    }

}
