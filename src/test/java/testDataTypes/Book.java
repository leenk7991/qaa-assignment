package testDataTypes;

public class Book implements java.io.Serializable {
  public int id;
  public String title;
  public String year;
  public int authorId;
  public String category;

  public String getInfo() {
    return String.format(
        "{\"id\":%d,\"title\":\"%s\",\"year\":\"%s\",\"authorId\":%d,\"category\":\"%s\"}",
        id, title, year, authorId, category);
  }
}
