package manageDrivers;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;

import tests.TestBase;

public class FirefoxDriverManager extends DriversManager
{
	private GeckoDriverService service;

	@Override
	protected void startService() 
	{
		service = new GeckoDriverService.Builder()
				.usingDriverExecutable(new File(TestBase.properties.getProperty("FirefoxDriverPath")))
				.usingAnyFreePort()
				.build();
		try {
			service.start();
			System.out.println("Firefox Driver(Gecko) server started...!!");
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void stopService() 
	{
		if(service != null && service.isRunning())
		{
			service.stop();
			System.out.println("Gecko Driver(Firefox) server stopped...!!");
		}
	}

	@Override
	protected void createDriver() 
	{
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = options.getProfile();
		driver = new FirefoxDriver(service, options);
		System.out.println("Driver got initialized to Firefox Driver with profile : "+profile.toString());
	}

}
