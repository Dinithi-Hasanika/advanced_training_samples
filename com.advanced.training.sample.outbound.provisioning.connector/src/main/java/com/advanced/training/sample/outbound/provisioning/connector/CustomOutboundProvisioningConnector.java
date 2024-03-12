package com.advanced.training.sample.outbound.provisioning.connector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.Property;
import org.wso2.carbon.identity.provisioning.AbstractOutboundProvisioningConnector;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningConstants;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningException;
import org.wso2.carbon.identity.provisioning.ProvisionedIdentifier;
import org.wso2.carbon.identity.provisioning.ProvisioningEntity;
import org.wso2.carbon.identity.provisioning.ProvisioningEntityType;
import org.wso2.carbon.identity.provisioning.ProvisioningOperation;

import java.util.Properties;

/**
 * This class handles the user provisioning operations to the desired system.
 */
public class CustomOutboundProvisioningConnector extends AbstractOutboundProvisioningConnector {

    private static final Log LOG = LogFactory.getLog(CustomOutboundProvisioningConnector.class);
    private CustomOutboundProvisioningConnectorConfig configHolder;

    @Override
    public void init(Property[] provisioningProperties) throws IdentityProvisioningException {

        Properties configs = new Properties();

        if (provisioningProperties != null && provisioningProperties.length > 0) {
            for (Property property : provisioningProperties) {
                configs.put(property.getName(), property.getValue());
            }
        }
        configHolder = new CustomOutboundProvisioningConnectorConfig(configs);
    }

    @Override
    public ProvisionedIdentifier provision(ProvisioningEntity provisioningEntity)
            throws IdentityProvisioningException {

        String provisionedId = null;

        if (provisioningEntity != null) {

            if (ProvisioningEntityType.USER == provisioningEntity.getEntityType()) {
                if (ProvisioningOperation.DELETE == provisioningEntity.getOperation()) {
                    deleteUser(provisioningEntity);
                } else if (ProvisioningOperation.POST == provisioningEntity.getOperation()) {
                    provisionedId = createUser(provisioningEntity);
                } else if (ProvisioningOperation.PUT == provisioningEntity.getOperation()) {
                    updateUser(provisioningEntity);
                } else {
                    LOG.warn("Unsupported provisioning operation " + provisioningEntity.getOperation() +
                            " for entity type " + provisioningEntity.getEntityType());
                }
            } else {
                LOG.warn("Unsupported provisioning entity type " + provisioningEntity.getEntityType());
            }
        }

        // Creates a provisioned identifier for the provisioned user.
        ProvisionedIdentifier identifier = new ProvisionedIdentifier();
        identifier.setIdentifier(provisionedId);
        return identifier;
    }

    private String createUser(ProvisioningEntity provisioningEntity) {

        // Implement user creation logic.
        String provisionedId = null;
        LOG.info("Creating the copy of the user in the external system.");
        return provisionedId;
    }

    private void deleteUser(ProvisioningEntity provisioningEntity) {

        // Implement user deletion logic.
        LOG.info("Delete the user account from the external system.");
    }

    private void updateUser(ProvisioningEntity provisioningEntity) {

        // Implement user update logic.
        LOG.info("Update user account in the external system.");
    }
}
