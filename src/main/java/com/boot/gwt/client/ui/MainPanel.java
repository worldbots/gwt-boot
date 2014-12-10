package com.boot.gwt.client.ui;

import com.boot.gwt.client.event.AddTodoEvent;
import com.boot.gwt.client.ui.component.ImageButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.boot.gwt.client.event.DeleteAllTodoEvent;
import com.boot.gwt.client.event.LoadEvent;
import com.boot.gwt.client.model.ModelHandler;
import com.boot.gwt.client.ui.schedule.ReloadTodoListCommand;
import com.boot.gwt.shared.model.TodoObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main UI component
 *
 * @author AGI
 */
public class MainPanel extends Composite {

    private static MainPanelUiBinder uiBinder = GWT.create(MainPanelUiBinder.class);

    interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
    }

	/*
     * UI components
	 */

    @UiField
    ImageButton addButton;

    @UiField
    ImageButton clearButton;

    @UiField
    ImageButton loadButton;

    @UiField
    TextBox textBox;

    @UiField
    FlowPanel todoPanel;

    /**
     * todo widgets references
     */
    Map<String, TodoWidget> todoWidgets;

    /**
     * event bus
     */
    private SimpleEventBus eventBus;

    /**
     * model
     */
    private ModelHandler modelHandler;

    @Inject
    public MainPanel(SimpleEventBus eventBus, ModelHandler modelHandler) {
        this.eventBus = eventBus;
        // init display
        initWidget(uiBinder.createAndBindUi(this));
        this.todoWidgets = new HashMap<>();
        this.modelHandler = modelHandler;
    }

    @UiHandler("addButton")
    void onAddButtonClick(ClickEvent e) {
        // retrieve textbox text
        String todoText = textBox.getText();
        // send it to controller for handle business event
        eventBus.fireEvent(new AddTodoEvent(todoText));
    }

    @UiHandler("clearButton")
    void onClearButtonClick(ClickEvent e) {
        // ask controller for delete all event
        eventBus.fireEvent(new DeleteAllTodoEvent());
    }

    @UiHandler("loadButton")
    void onLoadButtonClick(ClickEvent e) {
        // ask controller for load event
        eventBus.fireEvent(new LoadEvent());
    }

    public void addTodoToPanel(TodoObj t) {
        if (todoWidgets.get(t.getTitle()) == null) {
            // create a Todo
            TodoWidget w = new TodoWidget(t, eventBus);
            // add it to panel
            todoPanel.add(w);
            // keep a reference of the widget for later usage (see
            // removeTodoFromPanel)
            todoWidgets.put(t.getTitle(), w);
        } else {
            // some error handling code here
            Window.alert("Already existing Todo : " + t.getTitle());
        }
    }

    public void removeTodoFromPanel(TodoObj t) {
        // retrieve todo from the references
        TodoWidget todoWidget = todoWidgets.get(t.getTitle());
        // remove it from panel
        todoPanel.remove(todoWidget);

        todoWidgets.remove(t.getTitle());
    }

    public void removeAllTodo() {
        // clear todo panel
        todoPanel.clear();
        // clear references
        todoWidgets.clear();
    }

    public void reloadTodoList() {
        // clear all todo
        removeAllTodo();
        // retrieve new model
        List<TodoObj> all = modelHandler.getAll();
        // usae defered command for incremental UI refresh
        if (all.size() > 0) {
            // create the command
            ReloadTodoListCommand reloadCommand = new ReloadTodoListCommand(all, this);
            // schedule the command call
            Scheduler.get().scheduleDeferred(reloadCommand);
        }
    }
}
