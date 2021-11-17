package cucumber;

import enums.ContextTypes;
import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
  private Map<String, Object> scenarioContext;

  public ScenarioContext() {
    scenarioContext = new HashMap<>();
  }

  public void setContext(ContextTypes key, Object value) {
    scenarioContext.put(key.toString(), value);
  }

  public Object getContext(ContextTypes key) {
    return scenarioContext.get(key.toString());
  }

  public Boolean contains(String key) {
    return scenarioContext.containsKey(key);
  }
}
