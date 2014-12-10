package com.boot.gwt.client;

import com.boot.gwt.client.controller.WebAppController;
import com.boot.gwt.client.ui.MainPanel;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.boot.gwt.client.model.ModelHandler;
import com.boot.gwt.client.ui.resource.ApplicationResources;
import com.boot.gwt.client.ui.resource.Messages;

/**
 * Google gin module configuration
 *
 * @author AGI
 */
public class GwtWebAppGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        // Resources
        bind(Messages.class).in(Singleton.class);
        bind(ApplicationResources.class).in(Singleton.class);

        // Core
        bind(SimpleEventBus.class).in(Singleton.class);
        bind(WebAppController.class).in(Singleton.class);
        bind(ModelHandler.class).in(Singleton.class);

        // UI
        bind(MainPanel.class).in(Singleton.class);
    }

}
