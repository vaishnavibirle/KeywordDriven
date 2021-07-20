package com.blz.selenium.engine;

import com.blz.selenium.base.BaseClass;
import jdk.jfr.Description;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


@Description("Created class keyword engine applied exception handling" +
        "created method of start execution where it will take all credentials")

public class KeywordEngine {
    public static WebDriver driver;
    public static Properties prop;

    public static Workbook workbook;
    public static Sheet sheet;

    public  static WebElement element;
    public static BaseClass baseClass;

    public final static String SheetPath = "C:\\Users\\irg\\IdeaProjects\\KeyWord_Driven_Framework\\src\\test\\resources\\new_data.xlsx";

    public static void startExecution(String sheetName) {
        String locatorName = null;
        String locatorValue = null;
        FileInputStream file = null;
        try {
            file = new FileInputStream(SheetPath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook = WorkbookFactory.create(file);
        }catch (InvalidFormatException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheet(sheetName);

        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {
                String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();//id=sessionkey
                if (!locatorColValue.equalsIgnoreCase("NA")) {
                    locatorName = locatorColValue.split("=")[0].trim();  //id
                    locatorValue = locatorColValue.split("=")[1].trim(); //sessionkey
                }
                String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

                switch (action) {
                    case "open browser":
                        baseClass = new BaseClass();
                        prop = baseClass.init_properties();
                        if (value.isEmpty() || value.equals("NA")) {
                            driver = baseClass.init_driver(prop.getProperty("browser"));
                        } else {
                            driver = baseClass.init_driver(value);
                        }
                        break;

                    case "enter url":
                        if (value.isEmpty() || value.equals("NA"))
                        {
                            driver.get(prop.getProperty("url"));
                        } else {
                            driver.get(value);
                        }
                        break;
//                    case "quit":
//                        driver.quit();
//                        break;
                    default:
                        break;
                }

                switch (locatorName) {
                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        if (action.equalsIgnoreCase("sendKeys")) {
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        locatorName = null;
                        break;

                    case "name":
                        element = driver.findElement(By.name(locatorValue));
                        if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        locatorName = null;
                        break;

                    case "xpath":
                        element = driver.findElement(By.xpath(locatorValue));
                        if(action.equalsIgnoreCase("click")){
                            element.click();
                        }

                    case "className":
                        element = driver.findElement(By.className(locatorValue));
                        if(action.equalsIgnoreCase("click")){
                            element.click();
                        }

                    case "quit":
                        driver.quit();
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
