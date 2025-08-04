package com.vaadin.starter.business.ui.views.masterdata.notification;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.views.masterdata.message.MessageTypeCatalogDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for displaying notification message details.
 */
public class NotificationMessageDetails extends Dialog {

    private final NotificationMessageCatalogDTOWrapper message;

    private TextField messageCodeField;
    private ComboBox<MessageTypeCatalogDTO> messageTypeField;
    private TextField eventTypeField;
    private TextField descriptionField;
    private TextField defaultSubjectField;
    private TextArea defaultMessageField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the NotificationMessageDetails dialog.
     *
     * @param message the notification message to display details for
     */
    public NotificationMessageDetails(NotificationMessageCatalogDTOWrapper message) {
        this.message = message;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Notification Message Details: " + (message.getMessageCode() != null ? message.getMessageCode() : "New Message"));
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Message ID
        TextField messageIdField = new TextField();
        messageIdField.setValue(message.getMessageId() != null ? message.getMessageId().toString() : "");
        messageIdField.setReadOnly(true);
        messageIdField.setWidthFull();

        // Message Code
        messageCodeField = new TextField();
        messageCodeField.setValue(message.getMessageCode() != null ? message.getMessageCode() : "");
        messageCodeField.setWidthFull();

        // Message Type
        messageTypeField = new ComboBox<>();
        messageTypeField.setItemLabelGenerator(MessageTypeCatalogDTO::getTypeName);
        
        // Populate with mock message types for demo
        List<MessageTypeCatalogDTO> messageTypes = getMockMessageTypes();
        messageTypeField.setItems(messageTypes);
        
        if (message.getMessageType() != null) {
            messageTypeField.setValue(message.getMessageType());
        } else if (message.getTypeId() != null) {
            messageTypes.stream()
                .filter(type -> type.getTypeId().equals(message.getTypeId()))
                .findFirst()
                .ifPresent(messageTypeField::setValue);
        }
        messageTypeField.setWidthFull();

        // Event Type
        eventTypeField = new TextField();
        eventTypeField.setValue(message.getEventType() != null ? message.getEventType() : "");
        eventTypeField.setWidthFull();

        // Description
        descriptionField = new TextField();
        descriptionField.setValue(message.getDescription() != null ? message.getDescription() : "");
        descriptionField.setWidthFull();

        // Default Subject
        defaultSubjectField = new TextField();
        defaultSubjectField.setValue(message.getDefaultSubject() != null ? message.getDefaultSubject() : "");
        defaultSubjectField.setWidthFull();

        // Default Message
        defaultMessageField = new TextArea();
        defaultMessageField.setValue(message.getDefaultMessage() != null ? message.getDefaultMessage() : "");
        defaultMessageField.setWidthFull();
        defaultMessageField.setHeight("150px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(message.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(message.getDateCreated() != null ? message.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(message.getDateUpdated() != null ? message.getDateUpdated().toLocalDate() : LocalDate.now());
        updateDateField.setReadOnly(true);
        updateDateField.setWidthFull();

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(messageIdField, "Message ID");
        form.addFormItem(messageCodeField, "Message Code");
        form.addFormItem(messageTypeField, "Message Type");
        form.addFormItem(eventTypeField, "Event Type");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(defaultSubjectField, "Default Subject");
        form.addFormItem(defaultMessageField, "Default Message");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveMessage();
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

    private void saveMessage() {
        // In a real application, this would save the message data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Notification message details saved.");

        // Here you would update the message with the new values
        message.setMessageCode(messageCodeField.getValue());
        
        if (messageTypeField.getValue() != null) {
            message.setMessageType(messageTypeField.getValue());
            message.setTypeId(messageTypeField.getValue().getTypeId());
        }
        
        message.setEventType(eventTypeField.getValue());
        message.setDescription(descriptionField.getValue());
        message.setDefaultSubject(defaultSubjectField.getValue());
        message.setDefaultMessage(defaultMessageField.getValue());
        message.setStatus("Active".equals(statusField.getValue()) 
            ? NotificationMessageCatalogDTOWrapper.Status.ACTIVE 
            : NotificationMessageCatalogDTOWrapper.Status.INACTIVE);
    }
    
    // Mock data for message types
    private List<MessageTypeCatalogDTO> getMockMessageTypes() {
        List<MessageTypeCatalogDTO> messageTypes = new ArrayList<>();
        
        messageTypes.add(MessageTypeCatalogDTO.builder()
                .typeId(1L)
                .typeCode("EMAIL")
                .typeName("Email")
                .description("Email notifications")
                .build());
                
        messageTypes.add(MessageTypeCatalogDTO.builder()
                .typeId(2L)
                .typeCode("SMS")
                .typeName("SMS")
                .description("SMS notifications")
                .build());
                
        messageTypes.add(MessageTypeCatalogDTO.builder()
                .typeId(3L)
                .typeCode("PUSH")
                .typeName("Push Notification")
                .description("Mobile push notifications")
                .build());
                
        return messageTypes;
    }
}