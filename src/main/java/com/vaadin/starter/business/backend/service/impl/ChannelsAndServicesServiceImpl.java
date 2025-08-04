package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.dummy.Channel;
import com.vaadin.starter.business.dummy.Flow;
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
    private final Map<Long, Flow> flows = new HashMap<>();

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

    private static final String[] FLOW_NAMES = {
        "Payment Authorization", 
        "User Authentication", 
        "Account Verification", 
        "Transaction Processing", 
        "Fraud Detection", 
        "KYC Verification"
    };

    private static final String[] FLOW_DESCRIPTIONS = {
        "Authorizes payment transactions through the payment gateway",
        "Authenticates users through the identity provider",
        "Verifies account details with the banking core",
        "Processes transactions through the payment processor",
        "Detects fraudulent activities using AI algorithms",
        "Verifies customer identity through KYC provider"
    };

    private static final String[] DEFAULT_XML_TEMPLATES = {
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<bpmn2:definitions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n                   xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" \n                   xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" \n                   xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" \n                   xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" \n                   id=\"sample-diagram\" \n                   targetNamespace=\"http://bpmn.io/schema/bpmn\">\n  \n  <bpmn2:process id=\"Process_1\" isExecutable=\"false\">\n    \n    <!-- Evento de Inicio -->\n    <bpmn2:startEvent id=\"StartEvent_1\" name=\"Solicitud Recibida\">\n      <bpmn2:outgoing>Flow_1</bpmn2:outgoing>\n    </bpmn2:startEvent>\n    \n    <!-- Tarea de Usuario -->\n    <bpmn2:userTask id=\"Task_1\" name=\"Revisar Solicitud\">\n      <bpmn2:incoming>Flow_1</bpmn2:incoming>\n      <bpmn2:outgoing>Flow_2</bpmn2:outgoing>\n    </bpmn2:userTask>\n    \n    <!-- Gateway Exclusivo -->\n    <bpmn2:exclusiveGateway id=\"Gateway_1\" name=\"¿Aprobada?\">\n      <bpmn2:incoming>Flow_2</bpmn2:incoming>\n      <bpmn2:outgoing>Flow_3</bpmn2:outgoing>\n      <bpmn2:outgoing>Flow_4</bpmn2:outgoing>\n    </bpmn2:exclusiveGateway>\n    \n    <!-- Tarea Automática - Aprobación -->\n    <bpmn2:serviceTask id=\"Task_2\" name=\"Procesar Aprobación\">\n      <bpmn2:incoming>Flow_3</bpmn2:incoming>\n      <bpmn2:outgoing>Flow_5</bpmn2:outgoing>\n    </bpmn2:serviceTask>\n    \n    <!-- Tarea Automática - Rechazo -->\n    <bpmn2:serviceTask id=\"Task_3\" name=\"Enviar Notificación de Rechazo\">\n      <bpmn2:incoming>Flow_4</bpmn2:incoming>\n      <bpmn2:outgoing>Flow_6</bpmn2:outgoing>\n    </bpmn2:serviceTask>\n    \n    <!-- Evento de Fin - Aprobada -->\n    <bpmn2:endEvent id=\"EndEvent_1\" name=\"Solicitud Aprobada\">\n      <bpmn2:incoming>Flow_5</bpmn2:incoming>\n    </bpmn2:endEvent>\n    \n    <!-- Evento de Fin - Rechazada -->\n    <bpmn2:endEvent id=\"EndEvent_2\" name=\"Solicitud Rechazada\">\n      <bpmn2:incoming>Flow_6</bpmn2:incoming>\n    </bpmn2:endEvent>\n    \n    <!-- Flujos de Secuencia -->\n    <bpmn2:sequenceFlow id=\"Flow_1\" sourceRef=\"StartEvent_1\" targetRef=\"Task_1\" />\n    <bpmn2:sequenceFlow id=\"Flow_2\" sourceRef=\"Task_1\" targetRef=\"Gateway_1\" />\n    <bpmn2:sequenceFlow id=\"Flow_3\" name=\"Sí\" sourceRef=\"Gateway_1\" targetRef=\"Task_2\" />\n    <bpmn2:sequenceFlow id=\"Flow_4\" name=\"No\" sourceRef=\"Gateway_1\" targetRef=\"Task_3\" />\n    <bpmn2:sequenceFlow id=\"Flow_5\" sourceRef=\"Task_2\" targetRef=\"EndEvent_1\" />\n    <bpmn2:sequenceFlow id=\"Flow_6\" sourceRef=\"Task_3\" targetRef=\"EndEvent_2\" />\n    \n  </bpmn2:process>\n  \n  <!-- Información de Diagrama -->\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"Process_1\">\n      \n      <bpmndi:BPMNShape id=\"StartEvent_1_di\" bpmnElement=\"StartEvent_1\">\n        <dc:Bounds x=\"100\" y=\"100\" width=\"36\" height=\"36\" />\n        <bpmndi:BPMNLabel>\n          <dc:Bounds x=\"80\" y=\"143\" width=\"76\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      \n      <bpmndi:BPMNShape id=\"Task_1_di\" bpmnElement=\"Task_1\">\n        <dc:Bounds x=\"200\" y=\"78\" width=\"100\" height=\"80\" />\n      </bpmndi:BPMNShape>\n      \n      <bpmndi:BPMNShape id=\"Gateway_1_di\" bpmnElement=\"Gateway_1\" isMarkerVisible=\"true\">\n        <dc:Bounds x=\"375\" y=\"93\" width=\"50\" height=\"50\" />\n        <bpmndi:BPMNLabel>\n          <dc:Bounds x=\"375\" y=\"73\" width=\"50\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      \n      <bpmndi:BPMNShape id=\"Task_2_di\" bpmnElement=\"Task_2\">\n        <dc:Bounds x=\"500\" y=\"78\" width=\"100\" height=\"80\" />\n      </bpmndi:BPMNShape>\n      \n      <bpmndi:BPMNShape id=\"Task_3_di\" bpmnElement=\"Task_3\">\n        <dc:Bounds x=\"500\" y=\"200\" width=\"100\" height=\"80\" />\n      </bpmndi:BPMNShape>\n      \n      <bpmndi:BPMNShape id=\"EndEvent_1_di\" bpmnElement=\"EndEvent_1\">\n        <dc:Bounds x=\"682\" y=\"100\" width=\"36\" height=\"36\" />\n        <bpmndi:BPMNLabel>\n          <dc:Bounds x=\"670\" y=\"143\" width=\"60\" height=\"27\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      \n      <bpmndi:BPMNShape id=\"EndEvent_2_di\" bpmnElement=\"EndEvent_2\">\n        <dc:Bounds x=\"682\" y=\"222\" width=\"36\" height=\"36\" />\n        <bpmndi:BPMNLabel>\n          <dc:Bounds x=\"669\" y=\"265\" width=\"62\" height=\"27\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      \n      <!-- Conexiones -->\n      <bpmndi:BPMNEdge id=\"Flow_1_di\" bpmnElement=\"Flow_1\">\n        <di:waypoint x=\"136\" y=\"118\" />\n        <di:waypoint x=\"200\" y=\"118\" />\n      </bpmndi:BPMNEdge>\n      \n      <bpmndi:BPMNEdge id=\"Flow_2_di\" bpmnElement=\"Flow_2\">\n        <di:waypoint x=\"300\" y=\"118\" />\n        <di:waypoint x=\"375\" y=\"118\" />\n      </bpmndi:BPMNEdge>\n      \n      <bpmndi:BPMNEdge id=\"Flow_3_di\" bpmnElement=\"Flow_3\">\n        <di:waypoint x=\"425\" y=\"118\" />\n        <di:waypoint x=\"500\" y=\"118\" />\n        <bpmndi:BPMNLabel>\n          <dc:Bounds x=\"458\" y=\"100\" width=\"11\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      \n      <bpmndi:BPMNEdge id=\"Flow_4_di\" bpmnElement=\"Flow_4\">\n        <di:waypoint x=\"400\" y=\"143\" />\n        <di:waypoint x=\"400\" y=\"240\" />\n        <di:waypoint x=\"500\" y=\"240\" />\n        <bpmndi:BPMNLabel>\n          <dc:Bounds x=\"408\" y=\"189\" width=\"15\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      \n      <bpmndi:BPMNEdge id=\"Flow_5_di\" bpmnElement=\"Flow_5\">\n        <di:waypoint x=\"600\" y=\"118\" />\n        <di:waypoint x=\"682\" y=\"118\" />\n      </bpmndi:BPMNEdge>\n      \n      <bpmndi:BPMNEdge id=\"Flow_6_di\" bpmnElement=\"Flow_6\">\n        <di:waypoint x=\"600\" y=\"240\" />\n        <di:waypoint x=\"682\" y=\"240\" />\n      </bpmndi:BPMNEdge>\n      \n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n  \n</bpmn2:definitions>",
        "<flow name=\"userAuth\">\n  <step id=\"1\" type=\"validation\"/>\n  <step id=\"2\" type=\"authentication\"/>\n</flow>",
        "<flow name=\"accountVerify\">\n  <step id=\"1\" type=\"validation\"/>\n  <step id=\"2\" type=\"verification\"/>\n</flow>",
        "<flow name=\"transactionProcess\">\n  <step id=\"1\" type=\"validation\"/>\n  <step id=\"2\" type=\"processing\"/>\n</flow>",
        "<flow name=\"fraudDetect\">\n  <step id=\"1\" type=\"validation\"/>\n  <step id=\"2\" type=\"detection\"/>\n</flow>",
        "<flow name=\"kycVerify\">\n  <step id=\"1\" type=\"validation\"/>\n  <step id=\"2\" type=\"verification\"/>\n</flow>"
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
        initFlows();
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

    /**
     * Initialize flow data.
     */
    private void initFlows() {
        int startingPoint = 5000;

        // Create default flows (not associated with any provider)
        for (long i = 0; i < FLOW_NAMES.length; i++) {
            long id = i + startingPoint;

            String name = FLOW_NAMES[(int)i];
            String description = FLOW_DESCRIPTIONS[(int)i];
            Flow.FlowType type = Flow.FlowType.DEFAULT;
            String xmlDefinition = DEFAULT_XML_TEMPLATES[(int)i];
            Long providerId = null; // Default flows are not associated with any provider
            boolean active = true;

            flows.put(id, new Flow(id, name, description, type, xmlDefinition, providerId, active));
        }

        // Create provider-specific flows
        for (long i = 0; i < 10; i++) {
            long id = i + startingPoint + FLOW_NAMES.length;

            String name = "Custom " + FLOW_NAMES[(int)(i % FLOW_NAMES.length)];
            String description = "Custom provider flow: " + FLOW_DESCRIPTIONS[(int)(i % FLOW_DESCRIPTIONS.length)];
            Flow.FlowType type = Flow.FlowType.PROVIDER;
            String xmlDefinition = DEFAULT_XML_TEMPLATES[(int)(i % DEFAULT_XML_TEMPLATES.length)];

            // Associate with a random provider
            Long providerId = (long)(4000 + (i % serviceProviders.size()));
            boolean active = random.nextBoolean();

            flows.put(id, new Flow(id, name, description, type, xmlDefinition, providerId, active));
        }
    }

    @Override
    public Collection<Flow> getFlowsForProvider(Long providerId) {
        Collection<Flow> result = new java.util.ArrayList<>();

        // Add all default flows
        for (Flow flow : flows.values()) {
            if (flow.getType() == Flow.FlowType.DEFAULT) {
                result.add(flow);
            }
        }

        // Add provider-specific flows
        for (Flow flow : flows.values()) {
            if (flow.getType() == Flow.FlowType.PROVIDER && 
                (providerId == null || (flow.getProviderId() != null && flow.getProviderId().equals(providerId)))) {
                result.add(flow);
            }
        }

        return result;
    }

    @Override
    public Flow addFlow(Flow flow) {
        // Generate a new ID for the flow
        long newId = flows.size() + 5000;
        while (flows.containsKey(newId)) {
            newId++;
        }

        // Create a new flow with the generated ID
        Flow newFlow = new Flow(
            newId,
            flow.getName(),
            flow.getDescription(),
            flow.getType(),
            flow.getXmlDefinition(),
            flow.getProviderId(),
            flow.isActive()
        );

        // Add the flow to the map
        flows.put(newId, newFlow);

        return newFlow;
    }

    @Override
    public Flow updateFlowActiveStatus(Long flowId, boolean active) {
        // Get the flow from the map
        Flow flow = flows.get(flowId);

        if (flow != null) {
            // Update the active status
            flow.setActive(active);

            // Update the flow in the map
            flows.put(flowId, flow);
        }

        return flow;
    }
}
