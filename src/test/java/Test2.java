import org.apache.maven.surefire.shared.lang3.time.StopWatch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Hashtable;

public class Test2
{
    RemoteWebDriver driver = null;
    public static String status = "passed";
    String username = Test1.username;
    String access_key = Test1.access_key;


    /*
    @Parameters(value={"browser","version","platform", "resolution"})
    public void testSetUp(String browser, String version, String platform, String resolution) throws Exception
    */
    @BeforeMethod
    public void testSetUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "VR on Hyperexecute");


        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("tunnel",false);
        capabilities.setCapability("network",true);
        capabilities.setCapability("console",true);
        capabilities.setCapability("visual",true);

        // SMART UI OPTIONS  :

//        Hashtable<String, Integer> errorColor= new Hashtable<>();
//        errorColor.put("red",200);
//        errorColor.put("green",0);
//        errorColor.put("blue",0);
//
//        HashMap<String,Object> output= new HashMap<String, Object>();
//        output.put("errorColor",errorColor);//Output Difference error color
//        output.put("errorType","movement");//Flat Differences/Movement
//        output.put("transparency",0.1);// Set transparency of Output
//        output.put("largeImageThreshold",200);// the granularity to which the comparison happens(the scale or level of detail in a set of data.)Range-100-1200
//        output.put("useCrossOrigin",false);//source -localmachine
//        output.put("outputDiff",true);//don't want to comparison set as false
//
//        HashMap<String, Object> sm=new HashMap<String, Object>();
//        sm.put("output",output);
//        sm.put("scaleToSameSize",false);//scale to same size
//        sm.put("ignore","antialiasing");//remove picture grouping


//        capabilities.setCapability("smartUI.options",sm);

        // To set as a baseline
        capabilities.setCapability("smartUI.baseline",true);



        // Smart UI project name
        capabilities.setCapability("smartUI.project","Sample_HE_VR_Project");




        try
        {
            StopWatch driverStart = new StopWatch();
            driverStart.start();
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);

            SessionId session = ((RemoteWebDriver) driver).getSessionId();
            System.out.println("--------2----------" + session + "----------2----------");
            driverStart.stop();

            float timeElapsed = driverStart.getTime() / 1000f;
            System.out.println("Driver setup time " + "   " + timeElapsed);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        }
        System.out.println("Started session");
    }

    @Test(description="Visiting Lambdatest", groups= {"lt","lambdatest"} )
    public void lambdatest() throws InterruptedException
    {

        driver.get("https://lambdatest.com");
        Thread.sleep(5000);
        driver.executeScript("smartui.takeScreenshot");
        System.out.println("Screenshot captured! ");


    }
    @Test(description="Visiting Tesla", groups= {"lt","tesla"} )
    public void tesla() throws InterruptedException
    {

        driver.get("https://tesla.com");
        Thread.sleep(5000);
        driver.executeScript("smartui.takeScreenshot");
        System.out.println("Screenshot captured! ");
    }

    @Test(description="Visiting google", groups= {"lt","google"} )
    public void google() throws InterruptedException
    {

        driver.get("https://google.com");
        Thread.sleep(5000);
        driver.executeScript("smartui.takeScreenshot");
        System.out.println("Screenshot captured! ");
    }

    @Test(description="Visiting Bing", groups= {"lt","bing"} )
    public void bing() throws InterruptedException
    {

        driver.get("https://bing.com");
        Thread.sleep(5000);
        driver.executeScript("smartui.takeScreenshot");
        System.out.println("Screenshot captured! ");
    }



    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
        {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
