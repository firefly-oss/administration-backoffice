/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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