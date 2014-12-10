package com.boot.gwt.shared.model;

import java.io.Serializable;

/**
 * TodoObj Entity
 *
 * @author AGI
 */
public class TodoObj implements Serializable {

    private static final long serialVersionUID = -5744307016899515615L;

    String title;

    public TodoObj(String title) {
        this.title = title;
    }

    public TodoObj() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title =  title;
    }

}
