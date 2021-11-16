package dataProviders;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import managers.FileReaderManager;
import testDataTypes.Book;

public class BookDataReader {
  private static int index = 0;
  private final String bookFilePath =
      FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "Book.json";
  private List<Book> bookList;

  public BookDataReader() {
    bookList = getBookData();
  }

  private List<Book> getBookData() {
    Gson gson = new Gson();
    BufferedReader bufferReader = null;
    try {
      bufferReader = new BufferedReader(new FileReader(bookFilePath));
      Book[] customers = gson.fromJson(bufferReader, Book[].class);
      return Arrays.asList(customers);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Json file not found at path : " + bookFilePath);
    } finally {
      try {
        if (bufferReader != null) bufferReader.close();
      } catch (IOException ignore) {
      }
    }
  }

  public Book getBookByName(String name) {
    return bookList.stream().filter(x -> x.name.equalsIgnoreCase(name)).findAny().get();
  }

  public Book getBook() {
    if (index >= bookList.size()) {
      index = 0;
    }
    try {
      return bookList.get(index++);
    } catch (IndexOutOfBoundsException e) {
      throw new RuntimeException("Book list is empty");
    }
  }
}
