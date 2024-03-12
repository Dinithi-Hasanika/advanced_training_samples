package com.advanced.training.sample.user.operation.event.listener.internal;

import com.advanced.training.sample.user.operation.event.listener.CustomUserOperationEventListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.user.core.listener.UserOperationEventListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(
        name = "custom.user.operation.event.listener",
        immediate = true)
public class CustomUserOperationEventListenerService {

    private static Log log = LogFactory.getLog(CustomUserOperationEventListenerService.class);

    private ServiceRegistration serviceRegistration = null;

    @Activate
    protected void activate(ComponentContext context) {

        try {
            CustomUserOperationEventListener listener = new CustomUserOperationEventListener();
            serviceRegistration =
                    context.getBundleContext().registerService(UserOperationEventListener.class.getName(),
                            listener, null);

            log.info("custom user operation event listener is activated");

        } catch (Throwable e) {
            log.error("Error while activating custom user operation event listener.", e);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        log.debug("custom user operation event listener is de-activated");
    }
}
