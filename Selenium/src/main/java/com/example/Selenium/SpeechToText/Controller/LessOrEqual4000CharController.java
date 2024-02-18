package com.example.Selenium.SpeechToText.Controller;

import com.example.Selenium.SpeechToText.Model.DataStoreModel;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.CountDownLatch;

public class LessOrEqual4000CharController extends InitializationDriverController {
    public LessOrEqual4000CharController(DataStoreModel dataStoreModel) {
        super(dataStoreModel);
    }

    @Override
    public void configureDriverOnTheWebsite() {


    }

    @Override
    public void configureDriverOnTheLogin() {
        dataStoreModel.getDriver().get("https://ttsfree.com/login");
        ((JavascriptExecutor) dataStoreModel.getDriver()).executeScript("var images = document.getElementsByTagName('img'); for(var i = 0; i < images.length; i++) { images[i].setAttribute('hidden', 'true'); }");

        dataStoreModel.getDriver().findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(dataStoreModel.getUserName());
        dataStoreModel.getDriver().findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys(dataStoreModel.getUserPassword());

        dataStoreModel.setCountDownLatch(new CountDownLatch(2));
    }
}
