package ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Frames {
	private Frames()
	{ }
	
	public static int numberOfFramesPresent(WebDriver driver) {
		int totalFrames = 0;
		By locatorValue = By.tagName("iframe");
			List<WebElement> frames = driver.findElements(locatorValue);
			if(frames.size()>0)
				totalFrames = frames.size();
		return totalFrames;
	}
	
	public static WebElement switchToFrameToFindElement(WebDriver driver, By locatorValueOfFrame, By locatorValueOfElement) {
		WebElement element = null;
		try {
			driver = WaitsForWebElement
					.findElementUsingWebDriverWait(driver, 10, ExpectedConditions.frameToBeAvailableAndSwitchToIt(locatorValueOfFrame));
			element = retrieveStaledElement(driver, locatorValueOfElement);
		}catch(NoSuchFrameException ex) {
			System.out.println("Frame not found. "+ex.getMessage());
		}
		return element;
	}
	
	public static WebElement switchToFrameToFindElement(WebDriver driver, String idOrNameOfFrame, By locatorValueOfElement) {
		WebElement element = null;
		try {
			driver = WaitsForWebElement.
					findElementUsingWebDriverWait(driver, 10, ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrNameOfFrame));
				element = retrieveStaledElement(driver, locatorValueOfElement);
		}catch(NoSuchFrameException ex) {
			System.out.println("Frame not found. "+ex.getMessage());
		}
		return element;
	}
	
	public static WebElement findElementInFrames(WebDriver driver, int numberOfFrames, By locatorValueOfElement) {
		WebElement element = null;
		try {
			for(int i=0; i<numberOfFrames; i++) {
				driver = WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, ExpectedConditions.frameToBeAvailableAndSwitchToIt(i));
				try {
					element = retrieveStaledElement(driver, locatorValueOfElement);
					if(element != null)
						break;
				}catch(TimeoutException e) {
					System.out.println("Element not found. Hence, trying again");
				}
				driver.switchTo().parentFrame();
			}
		}catch(NoSuchFrameException ex) {
			System.out.println("Frame not found. "+ex.getMessage());
		}
		return element;
	}
	
	private static WebElement retrieveStaledElement(WebDriver driver, By locatorValueOfElement) {
		WebElement elementFound = null;
		for(int i=1; i<=4; i++) {
			try{
				elementFound = WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, locatorValueOfElement);
				if(elementFound!=null)
					break;
			}catch (StaleElementReferenceException e) {
				System.out.println("Element got staled. Hence, trying again..");
			}
		}
		return elementFound;
	}
}
