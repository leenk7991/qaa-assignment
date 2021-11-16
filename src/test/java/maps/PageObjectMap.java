package maps;

import enums.PageObjects;
import java.util.HashMap;
import java.util.Map;
import pages.BasePage;

public class PageObjectMap {
  public static final PageObjectMap pageObjectMap = new PageObjectMap();
  private static Map<String, BasePage> map = new HashMap<>();

  private PageObjectMap() {}

  public static PageObjectMap getInstance() {
    return pageObjectMap;
  }

  public void setPage(PageObjects key, BasePage value) {
    map.put(key.getName().toLowerCase(), value);
  }

  public BasePage getPage(PageObjects key) {
    return map.get(key.getName().toLowerCase());
  }

  public BasePage getPage(String key) {
    return map.get(key.toLowerCase());
  }
}
