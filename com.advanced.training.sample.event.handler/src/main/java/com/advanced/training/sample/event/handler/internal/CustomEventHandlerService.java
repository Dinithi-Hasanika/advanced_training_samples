package com.advanced.training.sample.event.handler.internal;

import com.advanced.training.sample.event.handler.CustomEventHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;

import org.wso2.carbon.user.core.service.RealmService;

@Component(
        name = "custom.event.handler.component",
        immediate = true)
public class CustomEventHandlerService {

    private static Log log = LogFactory.getLog(CustomEventHandlerService.class);
    private CustomEventHandlerDataComponent dataHolder = CustomEventHandlerDataComponent
            .getInstance();

    @Activate
    protected void activate(ComponentContext context) {
        try {
            BundleContext bundleContext = context.getBundleContext();
            bundleContext.registerService(AbstractEventHandler.class.getName(), new CustomEventHandler(),
                    null);
            log.info("Custom event handler, handler component activated successfully.");
        } catch (Exception e) {
            log.error("Error while activating custom event handler handler handler component.", e);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("Custom event handler is de-activated");
        }
    }

    @Reference(name = "realm.service",
            service = org.wso2.carbon.user.core.service.RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRealmService")
    protected void setRealmService(RealmService realmService) {
        if (log.isDebugEnabled()) {
            log.debug("Setting the Realm Service");
        }
        dataHolder.setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {
        log.debug("UnSetting the Realm Service");
        dataHolder.setRealmService(null);
    }
}
