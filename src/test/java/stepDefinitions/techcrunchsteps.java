package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

import static org.junit.Assert.assertTrue;

public class techcrunchsteps extends baseClass {
    WebDriver driver = createDriver();

    @Given("I am on the TechCrunch website")
    public void iAmOnTheTechCrunchWebsite() {
        System.out.println("Helloo");
        driver.get("https://techcrunch.com/");
    }

    @Then("each news has an author")
    public void eachNewsHasAnAuthor() {
        try {
            WebElement postBlock = driver.findElement(By.cssSelector(".post-block"));
            if (postBlock.isDisplayed()) {
                WebElement riverByline = postBlock.findElement(By.cssSelector(".river-byline"));
                // Perform actions riverByline
                // For example, you can use the WebDriverWait to wait for the elements to be visible
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOf(riverByline));
                assert riverByline.isDisplayed() : "Author not found for a news";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }

    @And("each news has an image")
    public void eachNewsHasAnImage() {
        try {
            WebElement postBlock = driver.findElement(By.cssSelector(".post-block"));
            if (postBlock.isDisplayed()) {
                WebElement imgElement = postBlock.findElement(By.tagName("img"));
                // Perform actions image
                // For example, you can use the WebDriverWait to wait for the elements to be visible
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOf(imgElement));
                assert imgElement.isDisplayed() : "İmage not found for a news";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @And("close the browser")
    public void closeTheBrowser() {
        driver.quit();
    }

    @When("I click on a news from the latest news list")
    public void iClickOnANewsFromTheLatestNewsList() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement latestNewsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-blocks")));
        WebElement firstNews = latestNewsSection.findElements(By.className("post-block")).get(0);
        firstNews.click();
    }

    @Then("the browser title is the same as the news title")
    public void theBrowserTitleIsTheSameAsTheNewsTitle() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement fullContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("article-content")));
        String newsTitle = fullContent.findElement(By.tagName("h1")).getText();
        System.out.println("news title="+newsTitle);
        String pageTitle = driver.getTitle();
        System.out.println("page title="+pageTitle);
        assert pageTitle.contains(newsTitle) : "Browser title is not the same as news title";
    }

    @And("the links within the news content are valid")
    public void theLinksWithinTheNewsContentAreValid() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement fullContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("article-content")));
        for (WebElement link : fullContent.findElements(By.tagName("a"))) {
            String href = link.getAttribute("href");
            assert href != null && !href.isEmpty() : "Invalid link found within news content";
        }
    }
}

