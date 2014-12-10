package com.boot.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.boot.gwt.shared.model.TodoObj;

/**
 * Delete a Todo event
 *
 * @author AGI
 */
public class DeleteTodoEvent extends GwtEvent<DeleteTodoEventHandler> {

    public static Type<DeleteTodoEventHandler> TYPE = new Type<>();

    /**
     * todo Id
     */
    TodoObj todoObj;

    public TodoObj getTodoObj() {
        return todoObj;
    }

    public DeleteTodoEvent(TodoObj t) {
        this.todoObj = t;
    }

    @Override
    protected void dispatch(DeleteTodoEventHandler handler) {
        handler.onDeleteTodoEventHandler(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DeleteTodoEventHandler> getAssociatedType() {
        return TYPE;
    }

}
