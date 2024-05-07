package aua.stf.pom.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public static final String BASE_URL = "https://zangakbookstore.am/";

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BasePage(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }

}