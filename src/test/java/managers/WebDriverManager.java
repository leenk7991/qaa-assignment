package managers;

import dataProviders.ConfigFileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManager {
  private WebDriver driver;
  private ConfigFileReader configReader;
  private String remoteDriverUrl;
  private String appUrl;
  private Boolean maximizeWindow;
  private long implicitWait;

  public WebDriverManager() {
    configReader = FileReaderManager.getInstance().getConfigReader();
    remoteDriverUrl = configReader.getRemoteDriverUrl();
    appUrl = configReader.getAppUrl();
    maximizeWindow = configReader.getMaximizeWindow();
    implicitWait = configReader.getImplicitWait();
  }

  public WebDriver getDriver() {
    if (driver == null) driver = createRemoteDriver();
    return driver;
  }

  private WebDriver createRemoteDriver() {
    try {
      driver = new RemoteWebDriver(new URL(remoteDriverUrl), createChromeOptions());
      driver.get("http://www.google.com");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    return driver;
  }

  private WebDriver createLocalDriver() {
    throw new RuntimeException("createLocalDriver is not yet implemented");
  }

  private ChromeOptions createChromeOptions() {
    ChromeOptions opts = new ChromeOptions();
    if (maximizeWindow) opts.addArguments("start-maximized");
    return opts;
  }

  public void closeDriver() {
    driver.close();
    driver.quit();
  }
}
