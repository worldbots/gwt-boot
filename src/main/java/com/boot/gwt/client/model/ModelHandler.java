package com.boot.gwt.client.model;

import com.boot.gwt.shared.model.TodoObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple model handler
 * manage todo list
 *
 * @author AGI
 */
public class ModelHandler {

    List<TodoObj> todoObjList;

    public ModelHandler() {
        todoObjList = new ArrayList<>();
    }

    public void add(TodoObj t) {
        todoObjList.add(t);
    }

    public void remove(TodoObj t) {
        todoObjList.remove(t);
    }

    public void removeAll() {
        todoObjList.clear();
    }

    public List<TodoObj> getAll() {
        return todoObjList;
    }

    public void reloadAll(List<TodoObj> list) {
        todoObjList.clear();
        for (TodoObj t : list) {
            add(t);
        }
    }

}
