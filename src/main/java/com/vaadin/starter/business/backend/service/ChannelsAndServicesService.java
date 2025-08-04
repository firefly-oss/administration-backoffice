package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.dummy.Channel;
import com.vaadin.starter.business.dummy.Flow;
import com.vaadin.starter.business.dummy.ServiceProvider;

/**
 * Service interface for channels and services functionality.
 */
public interface ChannelsAndServicesService {

    /**
     * Get all channels.
     *
     * @return a collection of all channels
     */
    Collection<Channel> getChannels();

    /**
     * Get a specific channel by its ID.
     *
     * @param id the ID of the channel to retrieve
     * @return the channel with the specified ID, or null if not found
     */
    Channel getChannel(Long id);

    /**
     * Get all service providers.
     *
     * @return a collection of all service providers
     */
    Collection<ServiceProvider> getServiceProviders();

    /**
     * Get a specific service provider by its ID.
     *
     * @param id the ID of the service provider to retrieve
     * @return the service provider with the specified ID, or null if not found
     */
    ServiceProvider getServiceProvider(Long id);

    /**
     * Get all flows for a specific service provider.
     *
     * @param providerId the ID of the service provider
     * @return a collection of flows for the specified provider
     */
    Collection<Flow> getFlowsForProvider(Long providerId);

    /**
     * Add a new flow for a service provider.
     *
     * @param flow the flow to add
     * @return the added flow with its ID set
     */
    Flow addFlow(Flow flow);

    /**
     * Update a flow's active status.
     *
     * @param flowId the ID of the flow to update
     * @param active the new active status
     * @return the updated flow
     */
    Flow updateFlowActiveStatus(Long flowId, boolean active);
}
