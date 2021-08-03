package com.blz.selenium.test;

import com.blz.selenium.engine.KeywordEngine;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Keyword_Driven_Test extends BaseClass {

    public KeywordEngine keywordEngine;

    @Description("Created Login test class" +
            "Used validations for proper execution")
    @Test
    public void loginTest() {
        keywordEngine = new KeywordEngine();
        keywordEngine.startExecution("Sheet1");
        String actual_Title = driver.getTitle();
        String expected_Title = "Facebook";
        Assert.assertEquals(actual_Title,expected_Title);
        System.out.println("you are logged in successfully.");
    }
}
