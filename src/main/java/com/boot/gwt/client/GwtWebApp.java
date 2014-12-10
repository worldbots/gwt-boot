package com.boot.gwt.client;

import com.boot.gwt.client.controller.WebAppController;
import com.boot.gwt.client.ui.resource.ApplicationResources;
import com.boot.gwt.client.ui.MainPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * <p>
 * Entry point for GWT module.
 */
public class GwtWebApp implements EntryPoint {

    /**
     * Gin injector
     */
    private final GwtWebAppGinjector injector = GWT.create(GwtWebAppGinjector.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // ensure resources are injected
        ApplicationResources.INSTANCE.style().ensureInjected();
        // get controler from gin jector
        WebAppController controller = injector.getWebAppController();
        // bind event handlers
        controller.bindHandlers();
        // get main panel
        MainPanel mainPanel = injector.getMainPanel();
        // add for display
        RootLayoutPanel.get().add(mainPanel);
    }
}
