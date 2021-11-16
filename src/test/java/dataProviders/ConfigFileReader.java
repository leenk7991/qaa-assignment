package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
  private final String propertyFilePath = "/src/test/resources/configs/config.properties";
  private final Properties properties;

  public ConfigFileReader() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(propertyFilePath));
      properties = new Properties();
      try {
        properties.load(reader);
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("config.properties not found at " + propertyFilePath);
    }
  }

  public String getRemoteDriverUrl() {
    String remoteDriverUrl = properties.getProperty("remote_driver_url");
    if (remoteDriverUrl != null) return remoteDriverUrl;
    else
      throw new RuntimeException("remote_driver_url not specified in the config.properties file.");
  }

  public long getImplicitWait() {
    String implicitWait = properties.getProperty("implicit_wait");
    if (implicitWait != null) return Long.parseLong(implicitWait);
    else throw new RuntimeException("implicit_wait not specified in the config.properties file.");
  }

  public String getAppUrl() {
    String appUrl = properties.getProperty("app_url");
    if (appUrl != null) return appUrl;
    else throw new RuntimeException("app_url not specified in the config.properties file.");
  }

  public Boolean getMaximizeWindow() {
    String maximizeWindow = properties.getProperty("maximize_window");
    if (maximizeWindow != null) return Boolean.valueOf(maximizeWindow);
    return false;
  }

  public String getTestDataResourcePath() {
    String testDataResourcePath = properties.getProperty("test_data_sources_path");
    if (testDataResourcePath != null) return testDataResourcePath;
    else
      throw new RuntimeException(
          "Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");
  }
}
