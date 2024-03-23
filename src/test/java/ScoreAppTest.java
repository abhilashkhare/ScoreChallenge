import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import page.LeaguePage;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ScoreAppTest {
    private AppiumDriver<MobileElement> driver;
    private LeaguePage leaguePage;


    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 6");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
        desiredCapabilities.setCapability("appPackage", "com.fivemobile.thescore");
        desiredCapabilities.setCapability("appActivity", "com.fivemobile.thescore.ui.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        leaguePage = new LeaguePage(driver);
    }

    @Test
    public void verifyLeagueFunctionality() {
        //Tested with parameters "NFL","NBA"
        final String selectedLeague = "NFL";

        // Step 1 : Open a league, team, or player page of your choice (bonus points for using a data-driven or parameterized approach)
        String actualSelectedLeagueName = leaguePage.selectLeagueAndGetSelectedLeagueText(selectedLeague);
        // Step 2: Verify that the expected page opens correctly.
        Assert.assertEquals(actualSelectedLeagueName, selectedLeague, " Selected League not displayed");

        // Step 3: Tap on a sub-tab of your choice, eg: league table / standings / leaders, or stats tab of the league, team, or player.
        leaguePage.selectLeadersTab();

        // Step 4 : Verify that you are on the correct tab and that the data is displayed correctly and corresponds to the league, team, or player from step 1
        Assert.assertTrue(leaguePage.selectLeadersTab(), "Leaders view not selected");

        List<MobileElement> leadershipHeaders = leaguePage.getLeadershipHeaders();
        Assert.assertTrue(leadershipHeaders.size() > 0, "Leadership header data not available");

        // Verify headers are displayed
        for (MobileElement header : leadershipHeaders) {
            Assert.assertNotNull(header.getText(), "Title is not found.");
        }

        /*
           Verify leadership entries are displayed.
           This is not inclusive of all entries. As a regression, verifies the entries displayed on the screen without scrolling
           Leading - Position 1
           Secondary - 2 and beyond
         */
        List<MobileElement> leadershipLeadingEntries = leaguePage.getLeadingLeadershipEntries();
        Assert.assertTrue(leadershipLeadingEntries.size() > 0, "Leading Leadership data not available");
        List<MobileElement> leadershipSecondaryEntries = leaguePage.getSecondaryLeadershipEntries();
        Assert.assertTrue(leadershipSecondaryEntries.size() > 0, "Secondary Leadership data not available");

        // Verify that selecting Leadership did not change the selected League from Step 1
        Assert.assertEquals(actualSelectedLeagueName, leaguePage.getLeagueTitle(), " Selected League not displayed");

        // Step 5 : Verify that back navigation returns you to the previous page correctly.
        Assert.assertTrue(leaguePage.navigateBackAndVerifyLeagueMenuIsDisplayed(), "League Page after Back navigation not displayed");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
