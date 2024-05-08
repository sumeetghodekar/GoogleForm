package demo;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.Select;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().browserVersion("123.0.6312.59").setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        //driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        System.out.println("Verify that Google Form URL is Open");
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");

        String url = driver.getCurrentUrl();
        if(url.contains("forms"))
        {
            System.out.println(url);
            System.out.println("end Test case: testCase01");
        }
        else
        {
            System.out.println("Does not contain URL");
            System.out.println("end Test case: testCase01");
        }
        
    }

    public void testCase02() throws InterruptedException
    {
        System.out.println("Start Test case: testCase02");
        System.out.println("Verify Fill in your name in the 1st text box");

        WebElement firstname = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        Thread.sleep(2000);
        firstname.click();
        firstname.sendKeys("Sumeet");

        System.out.println("end Test case: testCase02");
    }

    public void testCase03() throws InterruptedException
    {
        System.out.println("Start Test case: testCase03");
        System.out.println("Verify Why are you practicing Automation");

        WebElement auto = driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        Thread.sleep(2000);
        auto.click();
        auto.sendKeys("I want to be the best QA Engineer! 1710572021");

        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException
    {
        System.out.println("Start Test case: testCase04");
        System.out.println("Verify Enter your Automation Testing experience in the next radio button");

        WebElement exp = driver.findElement(By.xpath("//span[text()='3 - 5']"));
        Thread.sleep(2000);
        exp.click();

        System.out.println("end Test case: testCase04");

    }

    public void testCase05() throws InterruptedException
    {
        System.out.println("Start Test case: testCase05");
        System.out.println("Verify Which of the following have you learned in Crio.Do for Automation Testing?");

        WebElement java = driver.findElement(By.xpath("//span[text()='Java']"));
        Thread.sleep(2000);
        java.click();

        WebElement selenium = driver.findElement(By.xpath("//span[text()='Selenium']"));
        Thread.sleep(2000);
        selenium.click();

        Thread.sleep(3000);
        WebElement testng = driver.findElement(By.xpath("//label[@for='i39']"));
        
        testng.click();

        System.out.println("end Test case: testCase05");

    }
    public void testCase06() throws InterruptedException
    {
        System.out.println("Start Test case: testCase06");
        System.out.println("Verify Provide how you would like to be addressed in the next dropdown");

        Thread.sleep(4000);
        // Select title = new Select(driver.findElement(By.xpath("")));
        // title.selectByVisibleText("Mr");

        WebElement dropdown = driver.findElement(By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb DEh1R']"));
        dropdown.click();

        Thread.sleep(2000);
        WebElement title = driver.findElement(By.xpath("(//span[text()='Mr'])[2]"));
        title.click();
        System.out.println("end Test case: testCase06");


    }

    public void testCase07() throws InterruptedException
    {
        System.out.println("Start Test case: testCase07");
        System.out.println("Verify What was the date 7 days ago?");

        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);

        LocalDate dateMinus7 = currentDate.minusDays(7);
        System.out.println(dateMinus7);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateMinus7.format(formatter);
        System.out.println(formattedDate);

        int day = dateMinus7.getDayOfMonth();
        int month = dateMinus7.getMonthValue();
        int year = dateMinus7.getYear();


        Thread.sleep(3000);
        WebElement date = driver.findElement(By.xpath("//input[@type='date']"));
        date.click();
        date.sendKeys(Keys.ARROW_LEFT); 
        date.sendKeys(Keys.ARROW_LEFT);
        date.sendKeys(String.valueOf(day));
        date.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(2000);
        date.sendKeys(String.valueOf(month));
        date.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(2000);
        date.sendKeys(String.valueOf(year));
        Thread.sleep(5000);
        System.out.println("end Test case: testCase07");
    }

    public void testCase08()throws InterruptedException
    {
        System.out.println("Start Test case: testCase08");
        System.out.println("Verify What is the time right now?");

        LocalTime currentTime = LocalTime.now(); 
        int hour = currentTime.getHour(); 
        int twelveHourFormat = (hour % 12 == 0) ? 12 : hour % 12;
        int minutes = currentTime.getMinute(); 

       
        WebElement timeField = driver.findElement(By.xpath("(//input[@type='text'])[2]")); 
        timeField.click(); 
        Thread.sleep(1000); 
        timeField.sendKeys(String.valueOf(twelveHourFormat)); 
        timeField.sendKeys(Keys.TAB);

        
        WebElement timeField1 = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
        timeField1.click(); 
        Thread.sleep(1000); 
        timeField1.sendKeys(String.valueOf(minutes));
        System.out.println("end Test case: testCase08");
    }

    public void testCase09() throws InterruptedException
    {
        System.out.println("Start Test case: testCase09");
        System.out.println("Verify Change the URL of the tab (amazon.in) and you will find the pop up as follows. Click on cancel.");

        driver.get("https://www.amazon.in/");

        Thread.sleep(5000);

            try {
                Alert alert = driver.switchTo().alert(); 
                alert.dismiss(); 
            } catch (Exception ex) {
                System.out.println("No pop-up appeared.");
            }
            Thread.sleep(3000);
            System.out.println("end Test case: testCase09");
    }

    public void testCase10()throws InterruptedException
    {
        System.out.println("Start Test case: testCase10");
        System.out.println("Verify Submit the form");

        WebElement submitbtn = driver.findElement(By.xpath("//span[text()='Submit']"));
        submitbtn.click();

        Thread.sleep(3000);

        WebElement thankstext = driver.findElement(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"));

        String textsubmission = thankstext.getText();

        Thread.sleep(2000);
        if(textsubmission.contains("Thanks"))
        {
            System.out.println(textsubmission);
            System.out.println("end Test case: testCase10");
        }
        else
        {
            System.out.println("Message does not contain");
            System.out.println("end Test case: testCase10");
        }
        
    }



}
