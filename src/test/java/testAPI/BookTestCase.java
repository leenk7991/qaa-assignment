package testAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import managers.FileReaderManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testDataTypes.Book;

import java.util.List;

public class BookTestCase {
  String baseUrl = FileReaderManager.getInstance().getConfigReader().getAppUrl();
  Book book = FileReaderManager.getInstance().getBookDataReader().getBook();
  List<Book> allBooks = FileReaderManager.getInstance().getBookDataReader().getAllBooks();

  @BeforeTest
  public void beforeTest() {
    RestAssured.baseURI = baseUrl;
  }

  @Test
  public void searchBook() {
    RequestSpecification httpRequest = RestAssured.given();
    Response response = httpRequest.queryParam("title", book.title).get("/api/books");
    JsonPath jsonPathEvaluator = response.jsonPath();
    Assert.assertEquals(200, response.getStatusCode());
    Assert.assertEquals(jsonPathEvaluator.getList("title").get(0), book.title);
    Assert.assertEquals(response.header("Content-Type"), "application/json");
  }

  @Test
  public void getAllBooks() {
    RequestSpecification httpRequest = RestAssured.given();
    Response response = httpRequest.queryParam("title", book.title).get("/api/books/");
    Assert.assertEquals(200, response.getStatusCode());
    Assert.assertEquals(response.header("Content-Type"), "application/json");
    Assert.assertEquals(response.jsonPath().getList("title").size(), allBooks.size());
  }

  @Test
  public void getBookById() {
    RequestSpecification httpRequest = RestAssured.given();
    Response response = httpRequest.queryParam("id", 1).get("/api/books/1");
    Assert.assertEquals(200, response.getStatusCode());
    Assert.assertEquals(response.header("Content-Type"), "application/json");
    Assert.assertEquals(response.getBody().asString(), book.getInfo());
  }

  @Test
  public void createBookError() {
    RequestSpecification request = RestAssured.given();
    request.contentType("application/json");
    request.body("{\"title\": \"newbook\", \"year\": \"1997\"}");
    Response response = request.post("/api/books");
    Assert.assertTrue(
        response
            .getBody()
            .asString()
            .contains("\"defaultMessage\":\"Title should have at least 8 characters\""));
    Assert.assertEquals(response.statusCode(), 400);
  }

  //  @Test(priority = 3)
  //  public void delete() {
  //    RequestSpecification request = RestAssured.given();
  //    Book bookToDelete = FileReaderManager.getInstance().getBookDataReader().getLast();
  //    Response response = request.get(String.format("/books/%s/delete", bookToDelete.id));
  //    Assert.assertEquals(200, response.getStatusCode());
  //  }
}
