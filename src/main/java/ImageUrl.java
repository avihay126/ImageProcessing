


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;



public class ImageUrl {
    private ChromeDriver driver;
    private String profileName;
    private String profileNameLock;
    private Thread thread;


    public ImageUrl(String userName,String password,String profileName){
        this.profileName=changeNameFormat(profileName);
        initialDriver();
        connectFacebook(userName,password);
        getImage(this.profileName);




    }

    private void initialDriver(){
        System.setProperty(Constants.INITIAL_DRIVER, Constants.DRIVER_PATH);
//        ChromeOptions options=new ChromeOptions();
//        options.setHeadless(true);
        this.driver = new ChromeDriver();
    }

    private void connectFacebook(String userName,String password){
        this.driver.get(Constants.FACEBOOK_URL);
        this.driver.findElement(By.id("email")).sendKeys("AIUA1234@WALLA.CO.IL");
        this.driver.findElement(By.id("pass")).sendKeys("AIUA2469");
        this.driver.findElement(By.cssSelector("button[type='submit'][name='login']")).click();
    }
    public void getImage(String profileName){
         this.thread=new Thread(()->{
             try {
                 Thread.sleep(5000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             this.driver.get(Constants.FACEBOOK_URL+profileName);
        });
        this.thread.start();

    }

    private String changeNameFormat(String name) {
        String[] temp = name.split(" ");
        String newName = "";
        for (String s : temp) {
            newName += s;
        }
        return newName;
    }


}
