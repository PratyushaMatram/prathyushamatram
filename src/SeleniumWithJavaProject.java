import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class SeleniumWithJavaProject {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		//starting the screenRecord
		ScreenRecorderUtil.startRecord("main");
		
//invoking Browser
		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver","C:\\Users\\zeprats\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver");
		Thread.sleep(1000);
		
//Maximizing the window
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
//1.Navigate to the FitPeo Homepage:
		//Open the web browser and navigate to FitPeo Homepage.
		driver.get("https://www.fitpeo.com/");
		Thread.sleep(1000);
		
//2.Navigate to the Revenue Calculator Page:
		//From the homepage, navigate to the Revenue Calculator Page.
		driver.navigate().to("https://www.fitpeo.com/revenue-calculator");
		
//3.Scroll Down to the Slider section:
		//Scroll down the page until the revenue calculator slider is visible by 350 pixels Down
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(500); 
		
//4.Adjust the Slider:
		//Adjust the slider to set its value to 820. we’ve highlighted the slider in red color, Once the slider is moved the bottom text field value should be updated to 820
		
		//locating the slider point using xpath
		WebElement sliderPoint = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-root')]/span[3]/input"));
		//our desired value which we want on the slider
		int targetValue = 820;
		Thread.sleep(1000);
		//current value which is default-ly present on the slider
		int currentValue = Integer.parseInt(sliderPoint.getAttribute("value"));
		Thread.sleep(1000);
		System.out.println("Current value of the slider: " + currentValue);
		//updated value is the one, which we need to update from current to desired values
		int UpdatedValue = targetValue - currentValue;	
		Thread.sleep(1000);
		//for loop to perform the operation to move the slider until our desired value visible on the slider
		for (int i = 1; i <= UpdatedValue ; i++) {
            sliderPoint.sendKeys(Keys.ARROW_RIGHT);
        }
		//wait for sometime 5 seconds
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
	    Thread.sleep(1000);
	    //convert the updated slider value to the integer number
		int UpdatedValueAfterSliding = Integer.parseInt(sliderPoint.getAttribute("value"));
		//print the updated value on the console
		System.out.println("Updated value after sliding: " + UpdatedValueAfterSliding);	
		Thread.sleep(1000);	
		//get the TextBox value after sliding operation performed
		String TextBoxValue = driver.findElement(By.xpath("//input[contains(@class, 'MuiInputBase-input')]")).getAttribute("value");
	    //print the updated TextBox value to the console
		System.out.println("Updated TextBox value from Slider: " + TextBoxValue);
		Thread.sleep(1000);
		//verify the updated TextBox value is present to the our desired value on the slider which is '820'	
		if(TextBoxValue.equalsIgnoreCase("820")) {
			//if true, give a console print of PASS and textBox value
			System.out.println("PASS: " + TextBoxValue);	
		} else {
			//if false, give a console print of FAIL and textBox value
			System.out.println("FAIL: " + TextBoxValue);	
		}
		//Take Screenshot of the webPage at slider point and textBox value of 820 then saving to the project
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot, new File("slider_value.png"));
		System.out.println("screenshot saved Successfully");
		Thread.sleep(3000); 
		
//5.Update the Text Field:
			//Click on the text field associated with the slider
			//Enter the value 560 in the text field. Now the slider also should change accordingly 	
		
		//locating the textBox using xpath
		WebElement TextBox = driver.findElement(By.xpath("//input[contains(@class, 'MuiInputBase-input')]"));
		//executing javaScript to enter the value '560' to the textBox
		((JavascriptExecutor)driver).executeScript("arguments[0].value='560'", TextBox);
		//erasing the default value presenting in the 
		TextBox.sendKeys(Keys.ENTER);
		TextBox.sendKeys(Keys.BACK_SPACE);
		TextBox.sendKeys(Keys.BACK_SPACE);
		TextBox.sendKeys(Keys.BACK_SPACE);
		//entering our desired value '560'
		TextBox.sendKeys("560");
		
//6.Validate Slider Value:
		//Ensure that when the value 560 is entered in the text field, the slider's position is updated to reflect the value 560.
		
		//get the updated slider value after the textBox number Update in integer 
		int UpdatedSliderValue = Integer.parseInt(sliderPoint.getAttribute("value"));
		//give a console print of the updated slider value
		System.out.println("Updated Slider valuefrom TextBox: " + UpdatedSliderValue);
		Thread.sleep(1000);
		//get the TextBox value after entering 560 value operation performed
		String UpdatedTextboxValue = driver.findElement(By.xpath("//input[contains(@class, 'MuiInputBase-input')]")).getAttribute("value");
	    //print the updated TextBox value to the console
		System.out.println("Updated TextBox value :" + UpdatedTextboxValue);
		Thread.sleep(1000);
		 //verify the updated slider value is present to the our desired value on the TextBox which is '560'
		if(UpdatedTextboxValue.equalsIgnoreCase("560")) {
			//if true, give a console print of PASS and textBox value
			System.out.println("PASS: " + UpdatedSliderValue);	
		} else {
			//if false, give a console print of FAIL and textBox value
			System.out.println("FAIL: " + UpdatedSliderValue);	
		}
		Thread.sleep(1000);	
		
		
//7.Select CPT Codes:
		//Scroll down further and select the checkBoxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474.
		
		//Selecting CPT-99091 check box value
		driver.findElement(By.xpath("//label[contains(@class,'MuiFormControlLabel-root')]//span[text()='57']")).click();
		//Selecting CPT-99453 check box value
		driver.findElement(By.xpath("//label[contains(@class,'MuiFormControlLabel-root')]//span[text()='19.19']")).click();
		Thread.sleep(1000);
		//Selecting CPT-99454 check box value
		driver.findElement(By.xpath("//label[contains(@class,'MuiFormControlLabel-root')]//span[text()='63']")).click();
		//Selecting CPT-99474 check box value
		driver.findElement(By.xpath("//label[contains(@class,'MuiFormControlLabel-root')]//span[text()='15']")).click();
		//Take Screenshot of the webPage at checkBoxes Selection then saving to the project
		File CheckBoxscreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(CheckBoxscreenshot, new File("CheckBoxscreenshot.png"));
		System.out.println("screenshot saved Successfully");
		Thread.sleep(3000); 
		// scrolling to top of the page till Total Recurring Reimbursement value is visible by 500 pixels Up
		js.executeScript("window.scrollBy(0,-500)", "");
		Thread.sleep(1000);
		
//8.Validate Total Recurring Reimbursement:
	//set the Total Individual Patient/Month to 820 number
		
		//executing javaScript to enter the value '820' to the textBox
		((JavascriptExecutor)driver).executeScript("arguments[0].value='820'", TextBox);
		//erasing the default value presenting in the 
		TextBox.sendKeys(Keys.ENTER);
		TextBox.sendKeys(Keys.BACK_SPACE);
		TextBox.sendKeys(Keys.BACK_SPACE);
		TextBox.sendKeys(Keys.BACK_SPACE);
		//entering our desired value '820'
		TextBox.sendKeys("820");
		
//9.Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.
		
		//get the text of of the element Total recurring Reimbursement and store in form of string
		String Str = driver.findElement(By.xpath("//div[contains(@class,'MuiBox-root css-m1khva')]")).getText();
		//give a console print of the text captured
		System.out.println("" + Str);
		//verify the stored text from captured text equal to our desired value which is $110700
		if(Str.contains("$110700")) {
			//if true, give a console print of PASS and textBox value
			System.out.println("PASS: "+ Str);
		} else {
			//if false, give a console print of FAIL and textBox value
			System.out.println("FAIL: "+ Str);
		}
		//Take Screenshot of the webPage at Total Recurring Reimbursement then saving to the project
		File screenshot1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot1, new File("Totalrecurring_Reimbursement.png"));
		System.out.println("screenshot saved Successfully");
		Thread.sleep(5000); 
		
		//closing the browser
		driver.close(); 
		Thread.sleep(5000);
		
		//stop the screen Record
		ScreenRecorderUtil.stopRecord();

	}

	

}
