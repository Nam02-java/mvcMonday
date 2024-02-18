package com.example.Selenium.SpeechToText.Controller;

import com.example.Selenium.Package02.CheckESC;
import com.example.Selenium.Package02.CheckHandAD;
import com.example.Selenium.Package02.Selenium;
import com.example.Selenium.SpeechToText.Model.DataStoreModel;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

import static com.example.Selenium.Package02.ReadTextCSV.flag_TextCSV_LessOrEqual4000Char;

public abstract class InitializationDriverController implements Runnable {

    DataStoreModel dataStoreModel;

    public InitializationDriverController(DataStoreModel dataStoreModel) {
        this.dataStoreModel = dataStoreModel;
    }

    public void initializationDriver() {
//        System.out.println("Params text : " + dataStoreModel.getParams().get("Text") + "\n"
//                + "Params voice : " + dataStoreModel.getParams().get("Voice") + "\n"
//                + "Params file name : " + dataStoreModel.getParams().get("FileName"));

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "D:\\New folder\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension", false); // Disable chrome running as automation
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // Disable chrome running as automation

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // The number of seconds that a driver waits to load an element without the wait setting

        driver.manage().window().maximize();

    }


    public  void configureDriverOnTheLogin(){

        dataStoreModel.getDriver().get("https://ttsfree.com/login");

        ((JavascriptExecutor) dataStoreModel.getDriver()).executeScript("var images = document.getElementsByTagName('img'); for(var i = 0; i < images.length; i++) { images[i].setAttribute('hidden', 'true'); }");

        dataStoreModel.getDriver().findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(dataStoreModel.getUserName());
        dataStoreModel.getDriver().findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys(dataStoreModel.getUserPassword());

        dataStoreModel.setCountDownLatch(new CountDownLatch(2));
        Thread threadCheckESC = new Thread(new CheckESC(d, latch, null));
        Thread threadCheckHandAD = new Thread(new CheckHandAD(driver, latch, null));
        threadCheckESC.start();
        threadCheckHandAD.start();
        latch.await();

        driver.findElement(By.xpath("//ins[@class='iCheck-helper']")).click();
        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("laptopaz.com"))).click();
        } catch (Exception exception) {
            if (flag_TextCSV_LessOrEqual4000Char == true) {
                Driver_Work(params, driver, js, webElement, threadCheckESC, threadCheckHandAD, wait, element_solve);
            } else if (flag_TextCSV_LessOrEqual4000Char == false) {
                for (int i = 0; i <= arrayList_Char.size() + 1; i++) {
                    System.out.println("size of arrayList_Char : " + arrayList_Char.size());
                    System.out.println("value of arraylist[index] : " + arrayList_Char.get(0));
                    Driver_Work(params, driver, js, webElement, threadCheckESC, threadCheckHandAD, wait, element_solve);
                    count += 1;
                    arrayList_Char.remove(0);
                }
                driver.close();
            }
        }
    }

    public abstract void configureDriverOnTheWebsite();

    @Override
    public void run() {

        initializationDriver();
        configureDriverOnTheLogin();
        configureDriverOnTheWebsite();
    }
}

class beetween extends InitializationDriverController {
    public beetween(DataStoreModel dataStoreModel) {
        super(dataStoreModel);
    }

    @Override
    public void configureDriverOnTheWebsite() {
        System.out.println("oke");
    }

    @Override
    public void configureDriverOnTheLogin() {

    }
}


class testToRun {
    public static void main(String[] args) {
        DataStoreModel dataStoreModel = new DataStoreModel();
//
//        Thread thread1 = new Thread(new InitializationDriverController(dataStoreModel) {
//            @Override
//            public void configureDriverOnTheWebsite() {
//                System.out.println("oke");
//            }
//        });
//        thread1.start();

//        Thread thread2 = new Thread(new InitializationDriverController(dataStoreModel) {
//            @Override
//            public void configureDriverOnTheWebsite() {
//                System.out.println("|oke");
//            }
//        });




      //  thread2.start();

//        Thread thread1 = new Thread(new beetween(dataStoreModel));
//        thread1.start();
    }
}