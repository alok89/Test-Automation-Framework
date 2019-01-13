package manageDrivers;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;

import tests.TestBase;

public class IEDriverManager extends DriversManager
{
	private InternetExplorerDriverService service;

	@Override
	protected void startService()
	{
		service = new InternetExplorerDriverService.Builder()
				.usingDriverExecutable(new File(TestBase.properties.getProperty("IEDriverPath")))
				.usingAnyFreePort()
				.build();
		try {
			service.start();
			System.out.println("InternetExplorer Driver server started...!!");
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void stopService() 
	{
		if(null != service && service.isRunning())
		{
			service.stop();
			System.out.println("InternetExplorer Driver server stopped...!!");
		}
	}

	@Override
	protected void createDriver() 
	{
		InternetExplorerOptions options = new InternetExplorerOptions();
		driver = new InternetExplorerDriver(service, options);
		System.out.println("Driver got intialized to InternetExplorer driver..!!");
	}

}
