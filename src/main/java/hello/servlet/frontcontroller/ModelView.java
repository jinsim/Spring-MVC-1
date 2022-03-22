package hello.servlet.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String viewName;  // 뷰의 논리적 이름
    private Map<String, Object> model = new HashMap<>();  // model 역할

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
