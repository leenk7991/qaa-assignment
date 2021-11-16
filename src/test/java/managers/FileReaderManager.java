package managers;

import dataProviders.BookDataReader;
import dataProviders.ConfigFileReader;

public class FileReaderManager {
  private static final FileReaderManager fileReaderManager = new FileReaderManager();
  private static ConfigFileReader configFileReader;
  private static BookDataReader bookDataReader;

  private FileReaderManager() {}

  public static FileReaderManager getInstance() {
    return fileReaderManager;
  }

  public ConfigFileReader getConfigReader() {
    return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
  }

  public BookDataReader getBookDataReader() {
    return (bookDataReader == null) ? new BookDataReader() : bookDataReader;
  }
}
