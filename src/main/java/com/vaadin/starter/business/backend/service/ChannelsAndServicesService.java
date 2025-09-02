package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.backend.Channel;
import com.vaadin.starter.business.backend.ServiceProvider;
import java.util.UUID;

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
    Channel getChannel(UUID id);

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
    ServiceProvider getServiceProvider(UUID id);
}