package com.boot.gwt.client.controller;

import com.boot.gwt.client.event.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.boot.gwt.client.json.JsonHelper;
import com.boot.gwt.client.model.ModelHandler;
import com.boot.gwt.client.ui.MainPanel;
import com.boot.gwt.shared.model.TodoObj;

import java.util.List;

/**
 * Web App Controller Manage all business events and communicate with server services.
 */
public class WebAppController {

    /**
     * Event Bus
     */
    private SimpleEventBus eventBus;

    /**
     * Model Handler
     */
    private ModelHandler modelHandler;

    /**
     * Main panel UI
     */
    private MainPanel mainPanel;

    @Inject
    public WebAppController(SimpleEventBus eventBus, ModelHandler modelHandler, MainPanel mainPanel) {
        this.eventBus = eventBus;
        this.modelHandler = modelHandler;
        this.mainPanel = mainPanel;
    }

    /**
     * Bind all events handler
     */
    public void bindHandlers() {

        eventBus.addHandler(AddTodoEvent.TYPE, event -> WebAppController.this.addTodo(event.getTodoTitle()));
        eventBus.addHandler(DeleteTodoEvent.TYPE, event -> WebAppController.this.deleteTodo(event.getTodoObj()));
        eventBus.addHandler(DeleteAllTodoEvent.TYPE, event -> WebAppController.this.deleteAll());
        eventBus.addHandler(LoadEvent.TYPE, event -> WebAppController.this.loadTodoList());
    }


    /**
     * ask server for stored {@TodoObj} list
     */
    void loadTodoList() {
        String pageBaseUrl = GWT.getHostPageBaseURL();
        // String baseUrl = GWT.getModuleBaseURL();
        RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, pageBaseUrl + "/rest/todos/");
        rb.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                // some error handling code here
                Window.alert("error = " + e.getMessage());
            }

            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    String text = response.getText();
                    // some code to further handle the response here
                    System.out.println("text = " + text);
                    Window.alert("response = " + text);
                    List<TodoObj> todoObjList = JsonHelper.parseDataList(text);
                    reloadList(todoObjList);
                }
            }
        });
        try {
            rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
            Window.alert("error = " + e.getMessage());
        }
    }

    /**
     * get {@TodoObj} list from model and reload UI
     *
     * @param list
     */
    void reloadList(List<TodoObj> list) {
        modelHandler.reloadAll(list);
        mainPanel.reloadTodoList();
    }

    /**
     * delete all todo from model and UI
     */
    void deleteAll() {
        modelHandler.removeAll();
        mainPanel.removeAllTodo();
    }

    /**
     * delete a {@link com.boot.gwt.shared.model.TodoObj} (ui & model) from given id
     *
     * @param todoObj {@link com.boot.gwt.shared.model.TodoObj}.
     */
    void deleteTodo(TodoObj todoObj) {
        modelHandler.remove(todoObj);
        mainPanel.removeTodoFromPanel(todoObj);
    }

    /**
     * create and add a {@TodoObj} with given label
     *
     * @param todoTitle
     */
    void addTodo(String todoTitle) {
        TodoObj t = new TodoObj(todoTitle);
        modelHandler.add(t);
        mainPanel.addTodoToPanel(t);
    }
}
