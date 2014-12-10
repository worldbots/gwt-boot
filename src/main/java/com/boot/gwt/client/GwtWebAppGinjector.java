package com.boot.gwt.client;

import com.boot.gwt.client.controller.WebAppController;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.boot.gwt.client.model.ModelHandler;
import com.boot.gwt.client.ui.resource.ApplicationResources;
import com.boot.gwt.client.ui.resource.Messages;
import com.boot.gwt.client.ui.MainPanel;

/**
 * Google gin injector
 * <p>
 * all components to inject
 *
 * @author AGI
 */
@GinModules(GwtWebAppGinModule.class)
public interface GwtWebAppGinjector extends Ginjector {

    public SimpleEventBus getEventBus();

    public ApplicationResources getApplicationResources();

    public Messages getMessages();

    public WebAppController getWebAppController();

    public ModelHandler getModelHandler();

    public MainPanel getMainPanel();
}
