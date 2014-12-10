package com.boot.gwt.client.ui.schedule;

import com.boot.gwt.client.ui.MainPanel;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.boot.gwt.shared.model.TodoObj;

import java.util.List;

/**
 * Deferred command to do incremental UI refresh
 * for reloading Todo in the UI
 *
 * @author AGI
 */
public class ReloadTodoListCommand implements ScheduledCommand {

    private List<TodoObj> todoObjList;

    private MainPanel mainPanel;

    private int index;

    public ReloadTodoListCommand(List<TodoObj> list, MainPanel mainPanel) {
        this.todoObjList = list;
        this.mainPanel = mainPanel;
        index = 0;
    }

    /**
     * incremental add todo to the panel
     * executed after each call of Scheduler.get().scheduleDeferred(this)
     */
    @Override
    public void execute() {
        if (index < todoObjList.size()) {
            TodoObj todoObj = todoObjList.get(index);
            mainPanel.addTodoToPanel(todoObj);
            // schedule for next iteration
            Scheduler.get().scheduleDeferred(this);
            index++;
        }
    }

}
