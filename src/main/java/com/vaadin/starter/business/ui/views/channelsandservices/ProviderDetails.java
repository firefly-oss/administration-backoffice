package com.vaadin.starter.business.ui.views.channelsandservices;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.starter.business.backend.service.ChannelsAndServicesService;
import com.vaadin.starter.business.dummy.ServiceProvider;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;

/**
 * Dialog for displaying and editing service provider details.
 */
public class ProviderDetails extends Dialog {

    private final ServiceProvider provider;
    private final ChannelsAndServicesService channelsAndServicesService;
    
    private TextField providerName;
    private TextField contactPerson;
    private TextField contactEmail;
    private TextArea description;
    private RadioButtonGroup<String> status;
    private ComboBox<String> serviceType;
    private DatePicker contractStart;
    private DatePicker contractExpiry;
    private ComboBox<String> slaLevel;
    
    /**
     * Constructor for the ProviderDetails dialog.
     *
     * @param provider the service provider to display details for
     * @param channelsAndServicesService the service for accessing provider data
     */
    public ProviderDetails(ServiceProvider provider, ChannelsAndServicesService channelsAndServicesService) {
        this.provider = provider;
        this.channelsAndServicesService = channelsAndServicesService;
        
        setWidth("800px");
        setHeight("auto");
        
        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);
        
        H3 title = new H3("Provider Details: " + provider.getName());
        content.add(title);
        
        content.add(createForm());
        content.add(createButtonLayout());
        
        add(content);
    }
    
    private FormLayout createForm() {
        providerName = new TextField();
        providerName.setValue(provider.getName());
        providerName.setWidthFull();
        
        contactPerson = new TextField();
        contactPerson.setValue(provider.getContactPerson());
        contactPerson.setWidthFull();
        
        contactEmail = new TextField();
        contactEmail.setValue(provider.getContactEmail());
        contactEmail.setWidthFull();
        
        description = new TextArea();
        description.setValue(provider.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");
        
        status = new RadioButtonGroup<>();
        status.setItems("Active", "Pending", "Terminated");
        status.setValue(provider.getStatus());
        
        serviceType = new ComboBox<>();
        String[] types = {
            "Payment Processing", 
            "Core Banking", 
            "Credit Scoring", 
            "KYC/AML", 
            "Fraud Detection", 
            "Digital Onboarding"
        };
        serviceType.setItems(types);
        serviceType.setValue(provider.getServiceType());
        serviceType.setWidthFull();
        
        contractStart = new DatePicker();
        contractStart.setValue(provider.getContractStart());
        contractStart.setWidthFull();
        
        contractExpiry = new DatePicker();
        contractExpiry.setValue(provider.getContractExpiry());
        contractExpiry.setWidthFull();
        
        slaLevel = new ComboBox<>();
        slaLevel.setItems("Basic", "Standard", "Premium", "Enterprise");
        slaLevel.setValue(provider.getSlaLevel());
        slaLevel.setWidthFull();
        
        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(providerName, "Provider Name");
        form.addFormItem(contactPerson, "Contact Person");
        form.addFormItem(contactEmail, "Contact Email");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(serviceType, "Service Type");
        form.addFormItem(contractStart, "Contract Start");
        form.addFormItem(contractExpiry, "Contract Expiry");
        form.addFormItem(slaLevel, "SLA Level");
        
        return form;
    }
    
    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveProvider();
            close();
        });
        
        Button cancelButton = UIUtils.createTertiaryButton("Cancel");
        cancelButton.addClickListener(e -> close());
        
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        
        return buttonLayout;
    }
    
    private void saveProvider() {
        // In a real application, this would save the provider data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Provider details saved.");
        
        // Here you would update the provider with the selected values
        // Example: channelsAndServicesService.updateProvider(provider);
    }
}