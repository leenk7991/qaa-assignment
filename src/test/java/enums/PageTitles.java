package enums;

public enum PageTitles {
  HOME("Home"),
  BOOKS("Books"),
  AUTHORS("Authors"),
  EDIT_BOOK("Books Edit"),
  CREATE_BOOK("Create Book");
  private String title = "";

  PageTitles(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }
}
