package dataProviders;

import enums.EnvironmentType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
  private final String propertyFilePath = "src/test/resources/configs/config.properties";
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

  public long getWebDriverWait() {
    String webDriverWait = properties.getProperty("webdriver_wait");
    if (webDriverWait != null) return Long.parseLong(webDriverWait);
    else throw new RuntimeException("webdriver_wait not specified in the config.properties file.");
  }

  public EnvironmentType getEnvironment() {
    String environmentName = properties.getProperty("environment");
    if (environmentName == null || environmentName.equalsIgnoreCase("local"))
      return EnvironmentType.LOCAL;
    else if (environmentName.equals("remote")) return EnvironmentType.REMOTE;
    else
      throw new RuntimeException(
          "Environment Type Key value in Configuration.properties is not matched : "
              + environmentName);
  }
}
