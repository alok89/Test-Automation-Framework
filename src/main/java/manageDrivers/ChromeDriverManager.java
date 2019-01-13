package manageDrivers;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import tests.TestBase;

public class ChromeDriverManager extends DriversManager {

	private ChromeDriverService service;

	protected void startService() {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(TestBase.properties.getProperty("ChromeDriverPath")))
				.usingAnyFreePort()
				.build();
		try {
			service.start();
			System.out.println("Chrome Driver server started...!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void stopService() {
		if(service != null && service.isRunning()) {
			service.stop();
			System.out.println("Chrome Driver server stopped...!!");
		}
	}

	protected void createDriver() {
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(service, options);
		System.out.println("Driver got initialized to Chrome Driver..!!");
	}

}
