package AppiumTrainerFramework.pageobjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageObject
public class WikipediaPage {

  private static final String URL =  "https://en.wikipedia.org";

  @Inject
  private WebDriver webDriver;

  @FindBy(id = "p-search")
  private SearchComponent searchComponent;

  public SearchComponent getSearchComponent() {
    return searchComponent;
  }

  public WikipediaPage open() {
    webDriver.get(URL);
    return this;
  }

}
