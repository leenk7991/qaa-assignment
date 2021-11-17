package enums;

public enum PageObjects {
  HOME("Home Page"),
  BOOKS("Books Page"),
  EDIT_BOOK("Edit Book Page");
  private String pageName = "";

  PageObjects(String pageName) {
    this.pageName = pageName;
  }

  public String getName() {
    return this.pageName;
  }
}
