package dataProviders;

import com.google.gson.Gson;
import managers.FileReaderManager;
import testDataTypes.Book;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
      Book[] books = gson.fromJson(bufferReader, Book[].class);
      return Arrays.asList(books);
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
    return bookList.stream().filter(x -> x.title.equalsIgnoreCase(name)).findAny().get();
  }

  public Book getBookById(int id) {
    return bookList.stream().filter(x -> x.id == id).findAny().get();
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

  public Book getLast() {
    return bookList.get(bookList.size() - 1);
  }

  public List<Book> getAllBooks() {
    return bookList;
  }
}
