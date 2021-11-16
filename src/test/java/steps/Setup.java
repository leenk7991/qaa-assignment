package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Setup {
  static final String APP_URL = "localhost:8080";
  static final String HOST_URL = "http://localhost:4444";
  WebDriver driver;

  @Before
  public void setUp() {
    ChromeOptions opts = new ChromeOptions();
    try {
      driver = new RemoteWebDriver(new URL(HOST_URL), opts);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(APP_URL);
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
