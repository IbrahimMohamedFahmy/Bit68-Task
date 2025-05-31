package TestCases;

import SetUp.SuperClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class T01_LOGIN extends SuperClass
{
    @Test(dataProvider="Data")
    public void SingUpWithValidData(HashMap<String, String> input)
    {
        authPage.signUp(input.get("ValidName"), input.get("ValidPassword"));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Sign up successful"));
        driver.switchTo().alert().accept();
    }

    @Test(dataProvider="Data")
    public void SingUpWithInValidData(HashMap<String, String> input)
    {
        authPage.signUp(input.get("InValidName"), input.get("InValidPassword"));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("This user already exist.") || alertText.contains("Invalid"));
        driver.switchTo().alert().accept();
    }

    @Test
    public void SingUpWithEmptyFields()
    {
        authPage.openSignUpModal();
        authPage.submitSignUpForm();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Please fill out Username and Password.") ||
                alertText.toLowerCase().contains("empty"));
        driver.switchTo().alert().accept();
    }

    @Test(dataProvider="Data")
    public void SingUpWithJustUsername(HashMap<String, String> input)
    {
        authPage.openSignUpModal();
        authPage.enterSignUpCredentials(input.get("ValidName"), "");
        authPage.submitSignUpForm();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Password is required") ||
                alertText.contains("Please fill out"));
        driver.switchTo().alert().accept();
    }

    @Test(dataProvider="Data")
    public void SingUpWithJustPassword(HashMap<String, String> input)
    {
        authPage.openSignUpModal();
        authPage.enterSignUpCredentials("", input.get("ValidPassword"));
        authPage.submitSignUpForm();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Username is required") ||
                alertText.contains("Please fill out"));
        driver.switchTo().alert().accept();
    }

    @Test(dataProvider="Data")
    public void SignInWithValidData(HashMap<String, String> input)
    {
        authPage.signIn(input.get("ValidName"), input.get("ValidPassword"));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Login successful"));
        driver.switchTo().alert().accept();
    }

    @Test(dataProvider="Data")
    public void SignInWithInValidData(HashMap<String, String> input)
    {
        authPage.signIn(input.get("InValidName"), input.get("InValidPassword"));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Invalid username or password") ||
                alertText.contains("Wrong credentials"));
        driver.switchTo().alert().accept();
    }

    @Test
    public void SignInWithEmptyFields()
    {
        authPage.openSignInModal();
        authPage.submitSignInForm();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Please fill out Username and Password") ||
                alertText.toLowerCase().contains("empty"));
        driver.switchTo().alert().accept();
    }

    @Test(dataProvider="Data")
    public void SignInWithJustUsername(HashMap<String, String> input)
    {
        authPage.openSignInModal();
        authPage.enterSignInCredentials(input.get("ValidName"), "");
        authPage.submitSignInForm();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Password is required") ||
                alertText.contains("Please fill out"));
        driver.switchTo().alert().accept();
    }

    @Test(dataProvider="Data")
    public void SignInWithJustPassword(HashMap<String, String> input)
    {
        authPage.openSignInModal();
        authPage.enterSignInCredentials("", input.get("ValidPassword"));
        authPage.submitSignInForm();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Username is required") ||
                alertText.contains("Please fill out"));
        driver.switchTo().alert().accept();
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
