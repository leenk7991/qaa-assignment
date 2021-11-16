package enums;

public enum LinksText {
  HOME("Home"),
  BOOKS("Books"),
  AUTHORS("Authors");

  private String text = "";

  LinksText(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }
}
