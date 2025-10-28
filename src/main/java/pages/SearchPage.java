package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;


    private By keywordField = By.id("keyword");
    private By locationField = By.id("location");
    private By searchButton = By.id("search");
    private By resultsContainer = By.id("search-results-heading");
    private By sortDropdown= By.id("sort");
    private By errorField= By.xpath("//*[@id=\"maincontent\"]/div/h2");
    private By moreSearchOptionField= By.id("searchOptionsBtn");
    private By employerField= By.id("employer");
    private By distanceDropdown = By.id("distance");


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(2000));
    }


    public void enterKeyword(String keyword)  {

        wait.until(ExpectedConditions.visibilityOfElementLocated(keywordField)).sendKeys(keyword);
    }

    public void enterLocation(String location)  {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locationField)).sendKeys(location);

    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

    }

    public void verifyResults(String keyword, String location) {

        String result=wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer)).getText();
        assert result.contains(keyword);
        assert result.contains(location);
    }

    public void verifyNoResults() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(errorField));
        assert driver.findElement(errorField).getText().contains("not found");
    }

    public void sortByNewest(String visibleText) {


        try {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
            Select select = new Select(dropdown);

            // Try selecting by exact visible text
            select.selectByVisibleText(visibleText);
            System.out.println("✅ Sorted by: " + visibleText);

        } catch (NoSuchElementException e) {
            System.out.println("⚠️ Exact option not found. Trying tolerant match...");

            // Fallback logic: tolerant matching
            WebElement dropdown = driver.findElement(sortDropdown);
            Select select = new Select(dropdown);
            String normalizedWanted = visibleText.replaceAll("\\s+", "").toLowerCase();

            for (WebElement option : select.getOptions()) {
                String optText = option.getText();
                String normalizedOpt = optText.replaceAll("\\s+", "").toLowerCase();
                if (normalizedOpt.contains(normalizedWanted) || normalizedWanted.contains(normalizedOpt)) {
                    select.selectByVisibleText(optText);
                    System.out.println("✅ Matched approximately with: " + optText);
                    return;
                }
            }
            System.out.println("❌ No matching sort option found for: " + visibleText);

        } catch (TimeoutException e) {
            System.out.println("❌ Sort dropdown not visible within timeout.");
        }
    }
    public void verifyResultsWithKeywordOnly(String keyword){

        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        assert driver.findElement(resultsContainer).getText().contains(keyword);

    }

    public void verifyResultWithLocationOnly(String location){
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        assert driver.findElement(resultsContainer).getText().contains(location);
    }
    public void totalResults(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        assert driver.findElement((resultsContainer)).getText().contains("jobs found");
    }
    public void getAdvancedSearch(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(moreSearchOptionField)).click();
    }
    public void getEmployer(String employer){
        wait.until(ExpectedConditions.visibilityOfElementLocated(employerField)).sendKeys(employer);
    }

    public void isResultDisplayed(String keyword, String location, String employer) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        String text = driver.findElement(resultsContainer).getText();
        assert text.contains(keyword);
        assert text.contains(location);
        assert text.contains(employer);

    }
    public void selectDistance(String distance){
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(distanceDropdown));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(distance);
        System.out.println("✅ Selected distance: " + distance);
    }

}

