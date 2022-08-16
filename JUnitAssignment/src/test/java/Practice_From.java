import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Practice_From {
    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headed");
        driver = new ChromeDriver(ops);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void getTitle() {
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains("ToolsQA"));
    }

    @Test
    public void regFrom() throws InterruptedException, IOException, ParseException {
        driver.get("https://demoqa.com/automation-practice-form");
        //String fileName = "./src/main/resources/students.json";

        //UserName
        WebElement txtFirstName = driver.findElement(By.id("firstName"));
        txtFirstName.sendKeys("Abir");
        WebElement txtLastName = driver.findElement(By.id("lastName"));
        txtLastName.sendKeys("Hossen");

        //Email
        WebElement txtEmail = driver.findElement(By.id("userEmail"));
        txtEmail.sendKeys("hossenabir422@gmail.com");

        //Gender
        List<WebElement> radio = driver.findElements(By.tagName("div"));
        radio.get(72).click();

        //MobileNumber
        WebElement mobile = driver.findElement(By.id("userNumber"));
        mobile.sendKeys("0168617171");

        //Date of Birth
        Actions actions = new Actions(driver);
        WebElement txtDate = driver.findElement(By.id("dateOfBirthInput"));
        actions.moveToElement(txtDate).
                doubleClick().click().
                perform();
        txtDate.sendKeys("20 Dec 1996");
        txtDate.sendKeys(Keys.ENTER);

        //Subject
        WebElement searchElement = driver.findElement(By.id("subjectsInput"));
        searchElement.sendKeys("Maths Chemistry");

        //Hobbies Problem Issue
        /*Actions actions =new Actions(driver);
        WebElement h1= driver.findElement(By.xpath("//label[contains(text(),'Sports')]"));
        WebElement h2= driver.findElement(By.xpath("//label[contains(text(),'Reading')]"));
        WebElement h3= driver.findElement(By.xpath("//label[contains(text(),'Music')]"));
        actions.sendKeys(h2).click().perform();
        actions.sendKeys(h3).click().perform();*/

        //UploadPicture
        driver.findElement(By.id("uploadPicture")).sendKeys("D:\\JOB\\JOB\\73kb.jpg");
        sleep(2000);

        //currentAddress
        WebElement txtAddress = driver.findElement(By.id("currentAddress"));
        txtAddress.sendKeys("Dhanmondi 15,Dhaka");

        //State
        WebElement getState = driver.findElement(By.id("react-select-3-input"));
        getState.sendKeys("Uttar Pradesh");
        getState.sendKeys(Keys.ENTER);

        //City
        WebElement getCity = driver.findElement(By.id("react-select-4-input"));
        getCity.sendKeys("Lucknow");
        getCity.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Submit
        WebElement txtButton1 = driver.findElement(By.id("submit"));
        txtButton1.sendKeys(Keys.ENTER);
        String text= driver.findElement(By.className("modal-body")).getText();
        driver.findElement(By.id("closeLargeModal")).sendKeys(Keys.ENTER);
        System.out.println(text);

        //String txt = driver.findElement(By.className("modal-title h4")).getText();
        //Assert.assertTrue(txt.contains("Thanks for submitting the form"));

        //student.json add
        String fileName = "./src/main/resources/students.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONObject studentObj = new JSONObject();
        JSONArray studentArray = (JSONArray) obj;
        //--scraping Data From Web Table

        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> allRows = table.findElements(By.className("tr"));
        int i = 0;
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.className("td"));
            for (WebElement cell : cells) {
                i++;
                studentObj.put(cells.get(0).getText(), cell.getText());

            }

        }
        studentArray.add(studentObj);
        System.out.println(studentArray);
        FileWriter file = new FileWriter(fileName);
        file.write(studentArray.toJSONString());
        file.flush();
        file.close();


    }
    @Test
    public void modal(){
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("closeLargeModal")).click();
        String text= driver.findElement(By.className("modal-body")).getText();
        System.out.println(text);
       //driver.findElement(By.id("closeLargeModal")).click();
    }

    @After
    public void finishTest() {
        //driver.close();
    }
}
