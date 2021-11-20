package managers;

import dataProviders.ConfigFileReader;
import enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
  private static EnvironmentType environmentType;
  private WebDriver driver;
  private ConfigFileReader configReader;
  private String remoteDriverUrl;
  private Boolean maximizeWindow;

  public WebDriverManager() {
    configReader = FileReaderManager.getInstance().getConfigReader();
    remoteDriverUrl = configReader.getRemoteDriverUrl();
    maximizeWindow = configReader.getMaximizeWindow();
    environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
  }

  public WebDriver getDriver() {
    return (driver == null) ? driver = createDriver() : driver;
  }

  private WebDriver createDriver() {
    switch (environmentType) {
      case LOCAL:
        driver = createLocalDriver();
        break;
      case REMOTE:
        driver = createRemoteDriver();
        break;
    }
    return driver;
  }

  private WebDriver createRemoteDriver() {
    try {
      driver = new RemoteWebDriver(new URL(remoteDriverUrl), createChromeOptions());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return driver;
  }

  private WebDriver createLocalDriver() {
    driver = new ChromeDriver();
    if (FileReaderManager.getInstance().getConfigReader().getMaximizeWindow())
      driver.manage().window().maximize();
    return driver;
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

  public void getUrl(String url) {
    driver.get(url);
  }
}
