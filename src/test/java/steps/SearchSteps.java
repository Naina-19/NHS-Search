package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;
import utils.DriverFactory;

public class SearchSteps {
    WebDriver driver = DriverFactory.getDriver();
    SearchPage searchPage = new SearchPage(driver);

    @Given("I am on the NHS Jobs search page")
    public void openSearchPage() throws InterruptedException {
        driver.get("https://www.jobs.nhs.uk/candidate/search");
        Thread.sleep(2000);
        driver.manage().window().maximize();
    }

    @When("I enter {string} in the keyword field")
    public void enterKeyword(String keyword) throws InterruptedException {
        System.out.println("typing keyword");
        searchPage.enterKeyword(keyword);

    }

    @When("I enter {string} in the location field")
    public void enterLocation(String location) throws InterruptedException {
        searchPage.enterLocation(location);
    }

    @When("I leave the keyword and location fields empty")
    public void iLeaveTheKeywordAndLocationFieldsEmpty() throws InterruptedException {
        searchPage.enterKeyword("");
        searchPage.enterLocation("");
    }

    @When("I click the search button")
    public void clickSearch() {
        System.out.println("Clicking on the search button");
        searchPage.clickSearch();
    }

    @When("I sort the results by {string}")
    public void iSortTheResultsBy(String criteria) {
        searchPage.sortByNewest(criteria);
        System.out.println("Sorted results by: " + criteria);
    }

    @Then("I should see job results related to {string} in {string}")
    public void verifyResults(String keyword, String location) {
        searchPage.verifyResults(keyword, location);
    }

    @Then("I should see an error message or no results")
    public void verifyNoResults() {
        searchPage.verifyNoResults();
    }


    @Then("I should see no results or a validation message")
    public void iShouldSeeNoResultsOrAValidationMessage() {
        // Write code here that turns the phrase above into concrete actions
        searchPage.verifyNoResults();
    }


    @Then("I should see job results related to keyword {string}")
    public void iShouldSeeJobResultsRelatedToKeyword(String keyword) {
        searchPage.verifyResultsWithKeywordOnly(keyword);   }

    @Then("I should see job results related to location {string}")
    public void iShouldSeeJobResultsRelatedToLocation(String location) {
        searchPage.verifyResultWithLocationOnly(location);   }

    @Then("I should see all results")
    public void iShouldSeeAllResults() {
        searchPage.totalResults();
    }
}
