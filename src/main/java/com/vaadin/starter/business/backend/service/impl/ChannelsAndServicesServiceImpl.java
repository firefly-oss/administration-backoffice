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


package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.backend.Channel;
import com.vaadin.starter.business.backend.ServiceProvider;
import com.vaadin.starter.business.backend.dto.channelsandservices.ChannelDTO;
import com.vaadin.starter.business.backend.dto.channelsandservices.ServiceProviderDTO;
import com.vaadin.starter.business.backend.mapper.channelsandservices.ChannelMapper;
import com.vaadin.starter.business.backend.mapper.channelsandservices.ServiceProviderMapper;
import com.vaadin.starter.business.backend.service.ChannelsAndServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Implementation of the ChannelsAndServicesService interface.
 */
@Service
public class ChannelsAndServicesServiceImpl implements ChannelsAndServicesService {

    private final ChannelMapper channelMapper;
    private final ServiceProviderMapper serviceProviderMapper;
    
    private final Map<UUID, Channel> channels = new HashMap<>();
    private final Map<UUID, ServiceProvider> serviceProviders = new HashMap<>();
    
    private final Random random = new Random();
    
    // Constants for generating mock data
    private static final String[] CHANNEL_NAMES = {
        "Mobile Banking App", 
        "Web Portal", 
        "ATM Network", 
        "Branch Network", 
        "Call Center", 
        "Partner API"
    };
    
    private static final String[] INTEGRATION_TYPES = {
        "API", 
        "Webhook", 
        "File Transfer", 
        "Direct Database", 
        "Message Queue"
    };
    
    private static final String[] SECURITY_LEVELS = {
        "Basic", 
        "OAuth 2.0", 
        "API Key", 
        "JWT", 
        "mTLS"
    };
    
    private static final String[] PROVIDER_NAMES = {
        "Global Payments Inc.", 
        "Fiserv", 
        "FIS Global", 
        "Temenos", 
        "Jack Henry & Associates", 
        "Finastra"
    };
    
    private static final String[] SERVICE_TYPES = {
        "Payment Processing", 
        "Core Banking", 
        "Credit Scoring", 
        "KYC/AML", 
        "Fraud Detection", 
        "Digital Onboarding"
    };
    
    private static final String[] SLA_LEVELS = {
        "Basic", 
        "Standard", 
        "Premium", 
        "Enterprise"
    };
    
    private static final String[] STATUSES = {
        "Active", 
        "Pending", 
        "Terminated"
    };

    /**
     * Constructor with required dependencies.
     *
     * @param channelMapper the channel mapper
     * @param serviceProviderMapper the service provider mapper
     */
    @Autowired
    public ChannelsAndServicesServiceImpl(ChannelMapper channelMapper, ServiceProviderMapper serviceProviderMapper) {
        this.channelMapper = channelMapper;
        this.serviceProviderMapper = serviceProviderMapper;
        
        // Initialize mock data
        initChannels();
        initServiceProviders();
    }

    @Override
    public Collection<Channel> getChannels() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<ChannelDTO> dtos = channelMapper.toDtoList(channels.values());
        return channelMapper.toEntityList(dtos);
    }

    @Override
    public Channel getChannel(UUID id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        ChannelDTO dto = channelMapper.toDto(channels.get(id));
        return channelMapper.toEntity(dto);
    }

    @Override
    public Collection<ServiceProvider> getServiceProviders() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<ServiceProviderDTO> dtos = serviceProviderMapper.toDtoList(serviceProviders.values());
        return serviceProviderMapper.toEntityList(dtos);
    }

    @Override
    public ServiceProvider getServiceProvider(UUID id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        ServiceProviderDTO dto = serviceProviderMapper.toDto(serviceProviders.get(id));
        return serviceProviderMapper.toEntity(dto);
    }

    /**
     * Initialize channel data.
     */
    private void initChannels() {
        for (long i = 0; i < 20; i++) {
            UUID id = UUID.randomUUID();
            
            String name = CHANNEL_NAMES[(int)(Math.abs(i) % CHANNEL_NAMES.length)];
            String description = "Integration for " + name + " with backend systems.";
            boolean active = random.nextBoolean();
            String integrationType = INTEGRATION_TYPES[(int)(Math.abs(i) % INTEGRATION_TYPES.length)];
            LocalDateTime lastUpdated = LocalDateTime.now().minusDays(random.nextInt(30));
            String endpoint = "https://api.example.com/v1/" + name.toLowerCase().replace(" ", "-");
            String securityLevel = SECURITY_LEVELS[(int)(Math.abs(i) % SECURITY_LEVELS.length)];
            
            channels.put(id, new Channel(id, name, description, active, integrationType, 
                    lastUpdated, endpoint, securityLevel));
        }
    }

    /**
     * Initialize service provider data.
     */
    private void initServiceProviders() {
        for (long i = 0; i < 20; i++) {
            UUID id = UUID.randomUUID();
            
            String name = PROVIDER_NAMES[(int)(Math.abs(i) % PROVIDER_NAMES.length)];
            String description = "Service provider for financial services and technology solutions.";
            String status = STATUSES[(int)(Math.abs(i) % STATUSES.length)];
            String serviceType = SERVICE_TYPES[(int)(Math.abs(i) % SERVICE_TYPES.length)];
            
            // Generate contact information
            String contactPerson = "Contact Person " + (i + 1);
            String contactEmail = "contact" + (i + 1) + "@" + name.toLowerCase().replace(" ", "") + ".com";
            
            // Generate contract dates
            LocalDate contractStart = LocalDate.now().minusDays(random.nextInt(365));
            LocalDate contractExpiry = LocalDate.now().plusDays(30 + random.nextInt(335));
            
            String slaLevel = SLA_LEVELS[(int)(Math.abs(i) % SLA_LEVELS.length)];
            
            serviceProviders.put(id, new ServiceProvider(id, name, description, status, serviceType,
                    contactPerson, contactEmail, contractStart, contractExpiry, slaLevel));
        }
    }
}