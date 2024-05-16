package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Enter;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().browserVersion("125.0.6422.61").setup();
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
        driver.close();
        driver.quit();

    }

    public void testCase01()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        System.out.println("Automating Google Form");
        
        try {
            //Open the google form URL and verify 
            navigateTo(driver,"https://forms.gle/wjPkzeSEk1CM7KgGA");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='text'])[1]")));
            
            //Fill the name in Name text box
            text(driver, By.xpath("(//input[@type='text'])[1]"), "Sumeet");

            //Why do you want to practice automation with Epoch time
            text(driver, By.xpath("//textarea[@class='KHxj8b tL9Q4c']"), "I want to be the best QA Engineer! "+epochTime());
            
            //Enter your Automation Testing experience in the next radio button
            radioButton(driver, By.xpath("//span[@class='aDTYNe snByac OvPDhc OIC90c']"));
            js.executeScript("window.scrollBy(0,700);");
            
            //Which of the following have you learned in Crio.Do for Automation Testing?
            checkBox(driver, By.xpath("//span[@class='aDTYNe snByac n5vBHf OIC90c']"));

            //Provide how you would like to be addressed in the next dropdown
            dropDown(driver);
         
            //Select Date before 7 days
            setDate(driver, By.xpath("//input[@type='date']"));
            
            //Add Time
            setTime(driver);

            //New URL
            newURL(driver);

            //Submit Form
            submitForm(driver);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Failure!");
            return;
        }
    }

    
   
    private static void navigateTo(ChromeDriver driver,String url) throws Exception
    {
       
        try {
            if(!(driver.getCurrentUrl().equals(url)))
            {
                driver.get(url);
                System.out.println("Google form URL:"+url);
                
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while navigating"+e.getMessage());
        }
    }

    private static void text(ChromeDriver driver,By selector,String texttosend)
    {
        try {
            WebElement textbox = driver.findElement(selector);
            //textbox.click();
            textbox.sendKeys(texttosend);
            System.out.println("Text send successfully to Input box");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while Adding text to Input field"+e.getMessage());
        }
    }

    private static String epochTime()
    {
        long epoch = System.currentTimeMillis() / 1000;
        
        return String.valueOf(epoch);
    }

    private static void radioButton(ChromeDriver driver,By selector)
    {
        try {
           List<WebElement> Experience = driver.findElements(selector);

           for (WebElement element : Experience) 
           {
                String year = element.getText();
                if(year.contains("> 10"))
                {
                    element.click();
                    System.out.println("Clicked on Radio Button Successfully");
                }
           }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while clicking on Radio button"+e.getMessage());
        }
    }

    private static void checkBox(ChromeDriver driver,By selector)
    {
        try {
            List<WebElement> crioSubjectList = driver.findElements(selector);
            
            for (WebElement subject : crioSubjectList) 
            {
                String subjectLearned = subject.getText();

                if(subjectLearned.contains("Java") || subjectLearned.contains("Selenium") || subjectLearned.contains("TestNG"))
                {
                    subject.click();
                    System.out.println("Checked successfully"+subjectLearned);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while selecting checkbox"+e.getMessage());
        }
    }
    private static void dropDown(ChromeDriver driver) throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement dropdown = driver.findElement(By.xpath("(//div[@jsname='LgbsSe']//div/div[1]/span)[1]"));
        dropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']/div")));
       List<WebElement> clickAddress = driver.findElements(By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']/div"));

       for (WebElement address : clickAddress) 
       {
            String selectAddress = address.getText();
            if(selectAddress.contains("Mr"))
            {
                address.click();
                System.out.println("Clicked on Mr Successfully");
                break;
            }
       }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while selecting Address(Mr)"+e.getMessage());
        }

        
    }
    private static void setDate(ChromeDriver driver,By selector) throws InterruptedException
    {
        
        try 
        {
            WebElement date = driver.findElement(selector);
            LocalDate currentDate = LocalDate.now();
            LocalDate before_7days = currentDate.minusDays(7);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String final_Date_1 = myFormatObj.format(before_7days);
            System.out.println(final_Date_1);
            
            Thread.sleep(2000);
            date.sendKeys(final_Date_1);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while Adding Date"+e.getMessage());
        }
       
    }

    private static void setTime(ChromeDriver driver)
    {
        try 
        {
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

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while Adding Time"+e.getMessage());
        }
    }

    private static void newURL(ChromeDriver driver)
    {
       
        try 
        {
            driver.get("https://www.amazon.in/");
            Thread.sleep(2000);
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while opening New URL"+e.getMessage());
        }

    }

    private static void submitForm(ChromeDriver driver)
    {
        try 
        {
            WebElement subbtn = driver.findElement(By.xpath("//span[text()='Submit']"));
            subbtn.click();
            
            WebElement thankstext = driver.findElement(By.xpath("//div[@class='vHW8K']"));

            String textMatch = thankstext.getText();
            if(textMatch.contains("Thanks"))
            {
                System.out.println(textMatch);
                System.out.println("Automated Succesfully Google Form");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception occured while submitting google form"+e.getMessage());
        }
    }


}
