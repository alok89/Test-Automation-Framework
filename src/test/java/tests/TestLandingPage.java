package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LandingPage;
import pages.SignInPage;
import pages.SignUpPage;

public class TestLandingPage extends TestBase {
	
	private LandingPage landingPage;
	private SignInPage signInPage;
	
	@BeforeClass
	public void initializePage() {
		landingPage = new LandingPage(driver);
	}
	
	@Test(priority = 1)
	public void testNavigateToSignUpPage() {
		System.out.println("============== Starting Test : TestNavigateToSignUpPage =============");
		landingPage.goTo();
		Assert.assertTrue(landingPage.isAt());
		SignUpPage signUpPage = landingPage.goToSignUpPage();
		Assert.assertTrue(signUpPage.isAt());
		signUpPage.navigateBack();
		System.out.println("============== Executed Test : TestNavigateToSignUpPage =============");
	}
	
	@Test(priority = 2)
	public void testNavigateToSignInPage() {
		System.out.println("============== Starting Test : TestSignInPage =============");
		landingPage.refreshPage();
		Assert.assertTrue(landingPage.isAt());
		signInPage = landingPage.goToSignInPage();
		Assert.assertTrue(signInPage.isAt());
		System.out.println("============== Executed Test : TestSignInPage =============");
	}
	
	@Test(priority = 3, dataProvider = "FeaturesNameProvider")
	public void testFeaturesDetailsPages(String featureName) {
		System.out.println("============== Starting Test : testFeaturesDetailsPages =============");
		signInPage.refreshPage();
		signInPage.navigateBack();
		Assert.assertTrue(landingPage.isAt());
		landingPage.openFeaturesDetailsPage(featureName);
		System.out.println("============== Executed Test : testFeaturesDetailsPages =============");
	}
	
	@DataProvider(name = "FeaturesNameProvider", indices = {0,1,2})
	public Object[] dataProvider() {
		Object[] featuresName = new Object[]{"Bug Tracker", "In-app bug reporting", "Test Cases"};
		return featuresName;
	}
}
