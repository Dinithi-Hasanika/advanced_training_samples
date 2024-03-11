package com.advanced.training.sample.post.authentication.handler.internal;

import com.advanced.training.sample.post.authentication.handler.CustomPostAuthenticationHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.application.authentication.framework.handler.request.PostAuthenticationHandler;
import org.wso2.carbon.identity.core.util.IdentityCoreInitializedEvent;

@Component(
        name = "custom.post.authentication.handler",
        immediate = true
)
public class CustomPostAuthenticationHandlerService {

    private static final Log log = LogFactory.getLog(CustomPostAuthenticationHandlerService.class);

    @Activate
    protected void activate(ComponentContext context) {

        try {
            CustomPostAuthenticationHandler customPostAuthenticationHandler =
                    new CustomPostAuthenticationHandler();
            context.getBundleContext().registerService(PostAuthenticationHandler.class.getName(),
                    customPostAuthenticationHandler, null);
            log.info("custom post authentication handler is activated successfully.");
        } catch (Throwable e) {
            log.error("Error while activating custom post authentication handler.", e);
        }
    }

    @Reference(
            name = "identity.core.init.event.service",
            service = IdentityCoreInitializedEvent.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetIdentityCoreInitializedEventService"
    )
    protected void setIdentityCoreInitializedEventService(IdentityCoreInitializedEvent identityCoreInitializedEvent) {
        /* reference IdentityCoreInitializedEvent service to guarantee that this component will wait until identity core
         is started */
    }

    protected void unsetIdentityCoreInitializedEventService(IdentityCoreInitializedEvent identityCoreInitializedEvent) {
        /* reference IdentityCoreInitializedEvent service to guarantee that this component will wait until identity core
         is started */
    }
}
