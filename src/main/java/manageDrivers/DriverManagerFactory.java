package manageDrivers;

public class DriverManagerFactory {
	
	public static DriversManager getDriverManager(DriverType type) {
		DriversManager manager = null;

		switch(type) {
		case CHROME: manager = new ChromeDriverManager();
			break;

		case FIREFOX: manager = new FirefoxDriverManager();
			break;

		case IE : manager = new IEDriverManager();
			break;

		case EDGE : manager = new EdgeDriverManager();
			break;
		}
		return manager;
	}
}
