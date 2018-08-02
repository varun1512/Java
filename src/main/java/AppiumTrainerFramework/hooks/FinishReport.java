package AppiumTrainerFramework.hooks;

import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cognifide.qa.bb.logging.BrowserLogEntryCollector;
import com.cognifide.qa.bb.logging.entries.BrowserLogEntry;
import com.cognifide.qa.bb.logging.entries.LogEntry;

import com.google.inject.Inject;

import cucumber.api.Scenario;
import cucumber.api.java.After;

/**
 * A helper class that can create a screenshot file and gather additional data.
 */
public class FinishReport {

  @Inject
  private WebDriver webDriver;

  @Inject
  private BrowserLogEntryCollector browserLogEntryCollector;

  /**
   * Creates screenshot and gathers additional information, embeds it in the scenario and closes webDriver.
   */
  @After
  public void addDataAndClose(Scenario scenario) {
    if (scenario.isFailed()) {
      if(webDriver instanceof TakesScreenshot){
        addScreenshot(scenario);
      }
      addPageLink(scenario);
      addJSConsoleErrors(scenario);
    }
    webDriver.quit();
  }

  private void addJSConsoleErrors(Scenario scenario) {
    List<LogEntry> browserLogEntries = browserLogEntryCollector.getBrowserLogEntries();
    for (LogEntry browserLogEntry : browserLogEntries) {
      scenario.write("Console Error: " + ((BrowserLogEntry) browserLogEntry).getMessage());
    }
  }

  private void addPageLink(Scenario scenario) {
    scenario.write("Test page: " + "<a href=" + webDriver.getCurrentUrl() + ">link</a>");
  }

  private void addScreenshot(Scenario scenario) {
    byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    scenario.embed(screenshot, "image/png");
  }

}
