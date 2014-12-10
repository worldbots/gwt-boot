package com.boot.gwt.client.ui;

import com.boot.gwt.client.event.DeleteTodoEvent;
import com.boot.gwt.client.ui.component.ImageButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.boot.gwt.shared.model.TodoObj;

public class TodoWidget extends Composite {

    private static TodoWidgetUiBinder uiBinder = GWT
            .create(TodoWidgetUiBinder.class);

    interface TodoWidgetUiBinder extends UiBinder<Widget, TodoWidget> {
    }

    /*
     * UI components
     */
    @UiField
    ImageButton deleteButton;

    @UiField
    InlineLabel textBox;

    /**
     * current todo
     */
    private TodoObj currentTodoObj;

    /**
     * event bus
     */
    private SimpleEventBus eventBus;

    public TodoWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public TodoWidget(TodoObj t, SimpleEventBus eventBus) {
        this.eventBus = eventBus;
        // init display
        initWidget(uiBinder.createAndBindUi(this));
        this.currentTodoObj = t;
        // format display
        this.textBox.setText(t.getTitle());
    }

    @UiHandler("deleteButton")
    void onAddButtonClick(ClickEvent e) {
        // show confirm popup
        boolean confirm = Window.confirm("Supprimer " + currentTodoObj.getTitle() + " ?");
        if (confirm) {
            // ask controller for delete
            eventBus.fireEvent(new DeleteTodoEvent(currentTodoObj));
        }
    }

}
