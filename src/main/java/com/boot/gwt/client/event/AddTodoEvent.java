package com.boot.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Add a TODO event
 *
 * @author AGI
 */
public class AddTodoEvent extends GwtEvent<AddTodoEventHandler> {

    public static Type<AddTodoEventHandler> TYPE = new Type<>();

    /**
     * todo title
     */
    private String todoTitle;

    public String getTodoTitle() {
        return todoTitle;
    }

    public AddTodoEvent(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    @Override
    protected void dispatch(AddTodoEventHandler handler) {
        handler.onAddTodoEventHandler(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<AddTodoEventHandler> getAssociatedType() {
        return TYPE;
    }

}
