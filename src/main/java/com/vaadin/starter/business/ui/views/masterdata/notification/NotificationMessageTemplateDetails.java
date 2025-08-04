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
 * Dialog for displaying notification message template details.
 */
public class NotificationMessageTemplateDetails extends Dialog {

    private final NotificationMessageTemplate template;

    private TextField templateNameField;
    private ComboBox<NotificationMessageCatalog> messageField;
    private TextField templateTypeField;
    private TextField versionField;
    private TextArea templateContentField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the NotificationMessageTemplateDetails dialog.
     *
     * @param template the notification message template to display details for
     */
    public NotificationMessageTemplateDetails(NotificationMessageTemplate template) {
        this.template = template;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Notification Message Template Details: " + (template.getTemplateName() != null ? template.getTemplateName() : "New Template"));
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Template ID
        TextField templateIdField = new TextField();
        templateIdField.setValue(template.getTemplateId() != null ? template.getTemplateId().toString() : "");
        templateIdField.setReadOnly(true);
        templateIdField.setWidthFull();

        // Message ID
        TextField messageIdField = new TextField();
        messageIdField.setValue(template.getMessageId() != null ? template.getMessageId().toString() : "");
        messageIdField.setReadOnly(true);
        messageIdField.setWidthFull();

        // Message
        messageField = new ComboBox<>();
        messageField.setItemLabelGenerator(message -> message.getMessageCode() + " - " + message.getDescription());
        
        // Populate with mock messages for demo
        List<NotificationMessageCatalog> messages = getMockMessages();
        messageField.setItems(messages);
        
        if (template.getNotificationMessage() != null) {
            messageField.setValue(template.getNotificationMessage());
        } else if (template.getMessageId() != null) {
            messages.stream()
                .filter(message -> message.getMessageId().equals(template.getMessageId()))
                .findFirst()
                .ifPresent(messageField::setValue);
        }
        messageField.setWidthFull();
        messageField.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                messageIdField.setValue(e.getValue().getMessageId().toString());
            }
        });

        // Template Name
        templateNameField = new TextField();
        templateNameField.setValue(template.getTemplateName() != null ? template.getTemplateName() : "");
        templateNameField.setWidthFull();

        // Template Type
        templateTypeField = new TextField();
        templateTypeField.setValue(template.getTemplateType() != null ? template.getTemplateType() : "");
        templateTypeField.setWidthFull();

        // Version
        versionField = new TextField();
        versionField.setValue(template.getVersion() != null ? template.getVersion() : "");
        versionField.setWidthFull();

        // Template Content
        templateContentField = new TextArea();
        templateContentField.setValue(template.getTemplateContent() != null ? template.getTemplateContent() : "");
        templateContentField.setWidthFull();
        templateContentField.setHeight("300px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(template.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(template.getDateCreated() != null ? template.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(template.getDateUpdated() != null ? template.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(templateIdField, "Template ID");
        form.addFormItem(messageIdField, "Message ID");
        form.addFormItem(messageField, "Message");
        form.addFormItem(templateNameField, "Template Name");
        form.addFormItem(templateTypeField, "Template Type");
        form.addFormItem(versionField, "Version");
        form.addFormItem(templateContentField, "Template Content");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveTemplate();
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

    private void saveTemplate() {
        // In a real application, this would save the template data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Notification message template details saved.");

        // Here you would update the template with the new values
        if (messageField.getValue() != null) {
            template.setNotificationMessage(messageField.getValue());
            template.setMessageId(messageField.getValue().getMessageId());
        }
        
        template.setTemplateName(templateNameField.getValue());
        template.setTemplateType(templateTypeField.getValue());
        template.setVersion(versionField.getValue());
        template.setTemplateContent(templateContentField.getValue());
        template.setStatus("Active".equals(statusField.getValue()) 
            ? NotificationMessageTemplate.Status.ACTIVE 
            : NotificationMessageTemplate.Status.INACTIVE);
    }
    
    // Mock data for messages
    private List<NotificationMessageCatalog> getMockMessages() {
        List<NotificationMessageCatalog> messages = new ArrayList<>();
        
        // Create message types
        MessageTypeCatalogDTO emailType = MessageTypeCatalogDTO.builder()
                .typeId(1L)
                .typeCode("EMAIL")
                .typeName("Email")
                .description("Email notifications")
                .build();
        
        messages.add(NotificationMessageCatalog.builder()
                .messageId(1L)
                .messageCode("WELCOME_EMAIL")
                .typeId(1L)
                .messageType(emailType)
                .eventType("USER_REGISTRATION")
                .description("Welcome email sent after registration")
                .defaultSubject("Welcome to our platform!")
                .defaultMessage("Hello {userName}, welcome to our platform. Please activate your account using this link: {activationLink}")
                .status(NotificationMessageCatalog.Status.ACTIVE)
                .build());
                
        messages.add(NotificationMessageCatalog.builder()
                .messageId(2L)
                .messageCode("PASSWORD_RESET")
                .typeId(1L)
                .messageType(emailType)
                .eventType("PASSWORD_RESET_REQUEST")
                .description("Password reset instructions")
                .defaultSubject("Password Reset Request")
                .defaultMessage("Hello {userName}, you requested a password reset. Use this link: {resetLink}. It expires in {expiryTime} minutes.")
                .status(NotificationMessageCatalog.Status.ACTIVE)
                .build());
                
        return messages;
    }
}