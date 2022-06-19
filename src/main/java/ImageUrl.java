


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ImageUrl {
    private ChromeDriver driver;
    private String profileName;
    private String profileNameLock;
    private Thread thread;


    public ImageUrl(String userName, String password, String profileName) {
        this.profileName = changeNameFormat(profileName);
        initialDriver();
        connectFacebook();
    }

    private void initialDriver() {
        System.setProperty(Constants.INITIAL_DRIVER, Constants.DRIVER_PATH);
        ChromeOptions options=new ChromeOptions();
        options.setHeadless(true);
        this.driver = new ChromeDriver();
    }

    private void connectFacebook() {
        this.driver.get(Constants.FACEBOOK_URL);
        this.driver.findElement(By.id("email")).sendKeys(Constants.USER_NAME);
        this.driver.findElement(By.id("pass")).sendKeys(Constants.PASSWORD);
        this.driver.findElement(By.cssSelector("button[type='submit'][name='login']")).click();
        this.driver.get(Constants.FACEBOOK_URL + profileName);
    }

    public String getImage(String profileName) {
        try {
            this.driver.findElement(By.cssSelector("a[aria-label='דף הבית'][role='link']"));
            this.driver.get(Constants.FACEBOOK_URL + profileName);
        } catch (Exception e) {
            getImage(profileName);
        }
        temp();

        return "dftgyhuj";

    }
    private void temp(){
        try {
            List<WebElement> imageElements = this.driver.findElements(By.cssSelector("image[preserveAspectRatio='xMidYMid slice']"));
            WebElement profileImageElement = imageElements.get(1);
            profileImageElement.click();
            WebElement element =this.driver.findElement(By.cssSelector("img[data-visualcompletion='media-vc-image'][referrerpolicy='origin-when-cross-origin'][class='ji94ytn4 d2edcug0 r9f5tntg r0294ipz']"));
            String s=element.getAttribute("src");
            System.out.println(s);


        } catch (Exception e) {
            System.out.println("ghj");
            temp();
        }
    }

    public String getProfileName() {
        return this.profileName;
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
