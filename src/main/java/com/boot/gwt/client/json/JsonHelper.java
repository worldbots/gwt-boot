package com.boot.gwt.client.json;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.boot.gwt.shared.model.TodoObj;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    @SuppressWarnings("unchecked")
    public static List<TodoObj> parseDataList(String json) {
        List<TodoObj> todoObjList = new ArrayList<>();
        JSONValue jsonVal = JSONParser.parseStrict(json);
        JSONArray object = jsonVal.isArray();
        JsArray<JsTodo> array = (JsArray<JsTodo>) object.getJavaScriptObject();
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                JsTodo jsTodo = array.get(i);
                String title = jsTodo.title();
                todoObjList.add(new TodoObj(title));
            }
        }
        return todoObjList;
    }
}
