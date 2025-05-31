package TestCases;

import SetUp.SuperClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class T02_ADD_TO_CARD extends SuperClass
{
    @Test(dataProvider="Data")
    public void testAddProductToCartAndVerifyInCart(HashMap<String, String> input) {
        // Part one
        // Step 1. login with valid data
        authPage.openSignInModal();
        authPage.enterSignInCredentials(input.get("ValidName"), input.get("ValidPassword"));
        authPage.submitSignInForm();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

       // Closing the sign-in popup even though login will fail on purpose.
       // This helps to keep the test going so we can check the next steps.
        authPage.closeSignInModal();

        // Part two
        // 1. Click Nokia Lumia 1520
        productPage.clickOnNokiaPhone();

        // 2.  Add to Cart
        productPage.clickOnAddToCart();

        // 3. Wait the Alert
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert message: " + alertText);

        // 4. check the alert contain
        Assert.assertTrue(alertText.contains("Product added") || alertText.contains("added to cart"));

        // 5. accept the Alert
        alert.accept();

        // 6. go to the Cart page
        productPage.goToCartPage();

        // 7.get table data
        String cartContent = productPage.getCartTableText();

        // 8. check if the product is existed in the table
        Assert.assertTrue(cartContent.contains("Nokia lumia 1520"),
                "Error: Product not found in cart table!");
    }

    @DataProvider
    public Object[][] Data() throws IOException
    {
        String jsonFile = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//main//resources//Data.json"), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> DataList = objectMapper.readValue(jsonFile, new TypeReference<List<HashMap<String, String>>>() {});

        Object[][] Data = new Object[DataList.size()][1];
        for(int i = 0 ; i < DataList.size() ; i++)
        {
            Data[i][0] = DataList.get(i);
        }
        return Data;

    }
}
