package enums;

public enum Urls {
  LOCAL("localhost:8080"),
  HOME(""),
  BOOKS("/books"),
  AUTHORS("/authors"),
  CREATE_BOOK("/books/create");

  private String url = "";

  Urls(String url) {
    this.url = url;
  }

  public String getValue() {
    return this.url;
  }

  public String getUrl() {
    return (this.url.equals(LOCAL.getValue())) ? LOCAL.getValue() : LOCAL.getValue() + this.url;
  }
}
