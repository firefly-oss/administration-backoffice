package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.dummy.Channel;
import com.vaadin.starter.business.dummy.ServiceProvider;
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

/**
 * Implementation of the ChannelsAndServicesService interface.
 */
@Service
public class ChannelsAndServicesServiceImpl implements ChannelsAndServicesService {

    private final ChannelMapper channelMapper;
    private final ServiceProviderMapper serviceProviderMapper;
    
    private final Map<Long, Channel> channels = new HashMap<>();
    private final Map<Long, ServiceProvider> serviceProviders = new HashMap<>();
    
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
    public Channel getChannel(Long id) {
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
    public ServiceProvider getServiceProvider(Long id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        ServiceProviderDTO dto = serviceProviderMapper.toDto(serviceProviders.get(id));
        return serviceProviderMapper.toEntity(dto);
    }

    /**
     * Initialize channel data.
     */
    private void initChannels() {
        int startingPoint = 3000;
        for (long i = 0; i < 20; i++) {
            long id = i + startingPoint;
            
            String name = CHANNEL_NAMES[(int)(Math.abs(id) % CHANNEL_NAMES.length)];
            String description = "Integration for " + name + " with backend systems.";
            boolean active = random.nextBoolean();
            String integrationType = INTEGRATION_TYPES[(int)(Math.abs(id) % INTEGRATION_TYPES.length)];
            LocalDateTime lastUpdated = LocalDateTime.now().minusDays(random.nextInt(30));
            String endpoint = "https://api.example.com/v1/" + name.toLowerCase().replace(" ", "-");
            String securityLevel = SECURITY_LEVELS[(int)(Math.abs(id) % SECURITY_LEVELS.length)];
            
            channels.put(id, new Channel(id, name, description, active, integrationType, 
                    lastUpdated, endpoint, securityLevel));
        }
    }

    /**
     * Initialize service provider data.
     */
    private void initServiceProviders() {
        int startingPoint = 4000;
        for (long i = 0; i < 20; i++) {
            long id = i + startingPoint;
            
            String name = PROVIDER_NAMES[(int)(Math.abs(id) % PROVIDER_NAMES.length)];
            String description = "Service provider for financial services and technology solutions.";
            String status = STATUSES[(int)(Math.abs(id) % STATUSES.length)];
            String serviceType = SERVICE_TYPES[(int)(Math.abs(id) % SERVICE_TYPES.length)];
            
            // Generate contact information
            String contactPerson = "Contact Person " + (i + 1);
            String contactEmail = "contact" + (i + 1) + "@" + name.toLowerCase().replace(" ", "") + ".com";
            
            // Generate contract dates
            LocalDate contractStart = LocalDate.now().minusDays(random.nextInt(365));
            LocalDate contractExpiry = LocalDate.now().plusDays(30 + random.nextInt(335));
            
            String slaLevel = SLA_LEVELS[(int)(Math.abs(id) % SLA_LEVELS.length)];
            
            serviceProviders.put(id, new ServiceProvider(id, name, description, status, serviceType,
                    contactPerson, contactEmail, contractStart, contractExpiry, slaLevel));
        }
    }
}