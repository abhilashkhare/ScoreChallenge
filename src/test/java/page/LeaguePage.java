package page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorUtils;

import java.util.List;

public class LeaguePage {
    private AppiumDriver<MobileElement> driver;

    private static WebDriverWait wait;

    public LeaguePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /*
    Navigates to "Leagues" bottom tab and selects the parameterized League name
     */
    public String selectLeagueAndGetSelectedLeagueText(String leagueName) {
        MobileElement leaguesBottomTab = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator("leaguePage.leaguesTab")));
        leaguesBottomTab.click();

        //Scrolls to the required League Name if it's not displayed on the screen
        scrollToElementByText(leagueName).click();

        return getLeagueTitle();
    }

    public static String getLeagueTitle() {
        MobileElement leagueTitle = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator("leaguePage.leagueTitle")));
        return leagueTitle.getText();
    }

    public Boolean selectLeadersTab() {
        By leaderView = LocatorUtils.getLocator("leaguePage.leadersTab");
        driver.findElement(leaderView).click();
        return Boolean.valueOf(driver.findElement(leaderView).getAttribute("enabled"));
    }

    public List<MobileElement> getLeadershipHeaders() {
        return driver.findElements(LocatorUtils.getLocator("leaguePage.leadershipHeader"));
    }

    public List<MobileElement> getSecondaryLeadershipEntries() {
        return driver.findElements(LocatorUtils.getLocator("leaguePage.leadershipEntry"));
    }

    public List<MobileElement> getLeadingLeadershipEntries() {
        return driver.findElements(LocatorUtils.getLocator("leaguePage.leadingLeadershipEntry"));
    }

    public MobileElement scrollToElementByText(String text) {
        String uiScrollables = String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"%s\"))", text);
        return driver.findElement(MobileBy.AndroidUIAutomator(uiScrollables));
    }

    public Boolean navigateBackAndVerifyLeagueMenuIsDisplayed() {
        driver.findElement(LocatorUtils.getLocator("leaguePage.navigateBackButton")).click();

        MobileElement leaguesView = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator("leaguePage.leaguesView")));
        return leaguesView.isDisplayed();
    }
}