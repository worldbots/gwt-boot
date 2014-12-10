package com.boot.gwt.server.web;

import com.boot.gwt.shared.model.TodoObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/todos")
public class TodoController {

    final static Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    List<TodoObj> todoObjList = new ArrayList<>();

    public TodoController() {
        todoObjList.add(new TodoObj("Todo #1"));
        todoObjList.add(new TodoObj("Todo #2"));
        todoObjList.add(new TodoObj("Todo #3"));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TodoObj> all() {
        return todoObjList;
    }
}
