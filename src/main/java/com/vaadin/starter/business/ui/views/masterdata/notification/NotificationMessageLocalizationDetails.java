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
 * Dialog for displaying notification message localization details.
 */
public class NotificationMessageLocalizationDetails extends Dialog {

    private final NotificationMessageLocalization localization;

    private TextField messageIdField;
    private ComboBox<NotificationMessageCatalog> messageField;
    private TextField localeIdField;
    private ComboBox<String> localeField;
    private TextField subjectField;
    private TextArea messageContentField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the NotificationMessageLocalizationDetails dialog.
     *
     * @param localization the notification message localization to display details for
     */
    public NotificationMessageLocalizationDetails(NotificationMessageLocalization localization) {
        this.localization = localization;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Notification Message Localization Details: " + 
                (localization.getLocalizationId() != null ? "ID " + localization.getLocalizationId() : "New Localization"));
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Localization ID
        TextField localizationIdField = new TextField();
        localizationIdField.setValue(localization.getLocalizationId() != null ? localization.getLocalizationId().toString() : "");
        localizationIdField.setReadOnly(true);
        localizationIdField.setWidthFull();

        // Message ID
        messageIdField = new TextField();
        messageIdField.setValue(localization.getMessageId() != null ? localization.getMessageId().toString() : "");
        messageIdField.setWidthFull();

        // Message
        messageField = new ComboBox<>();
        messageField.setItemLabelGenerator(message -> message.getMessageCode() + " - " + message.getDescription());
        
        // Populate with mock messages for demo
        List<NotificationMessageCatalog> messages = getMockMessages();
        messageField.setItems(messages);
        
        if (localization.getNotificationMessage() != null) {
            messageField.setValue(localization.getNotificationMessage());
        } else if (localization.getMessageId() != null) {
            messages.stream()
                .filter(message -> message.getMessageId().equals(localization.getMessageId()))
                .findFirst()
                .ifPresent(messageField::setValue);
        }
        messageField.setWidthFull();
        messageField.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                messageIdField.setValue(e.getValue().getMessageId().toString());
            }
        });

        // Locale ID
        localeIdField = new TextField();
        localeIdField.setValue(localization.getLocaleId() != null ? localization.getLocaleId().toString() : "");
        localeIdField.setWidthFull();

        // Locale
        localeField = new ComboBox<>();
        localeField.setItems(getMockLocales());
        
        if (localization.getLocaleCode() != null) {
            localeField.setValue(localization.getLocaleCode() + " - " + localization.getLocaleName());
        }
        localeField.setWidthFull();
        localeField.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                String[] parts = e.getValue().split(" - ");
                if (parts.length > 0) {
                    // In a real app, you would look up the locale ID from the code
                    switch (parts[0]) {
                        case "en-US":
                            localeIdField.setValue("1");
                            break;
                        case "es-ES":
                            localeIdField.setValue("2");
                            break;
                        case "fr-FR":
                            localeIdField.setValue("3");
                            break;
                        default:
                            localeIdField.setValue("");
                    }
                }
            }
        });

        // Subject
        subjectField = new TextField();
        subjectField.setValue(localization.getSubject() != null ? localization.getSubject() : "");
        subjectField.setWidthFull();

        // Message Content
        messageContentField = new TextArea();
        messageContentField.setValue(localization.getMessage() != null ? localization.getMessage() : "");
        messageContentField.setWidthFull();
        messageContentField.setHeight("150px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(localization.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(localization.getDateCreated() != null ? localization.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(localization.getDateUpdated() != null ? localization.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(localizationIdField, "Localization ID");
        form.addFormItem(messageIdField, "Message ID");
        form.addFormItem(messageField, "Message");
        form.addFormItem(localeIdField, "Locale ID");
        form.addFormItem(localeField, "Locale");
        form.addFormItem(subjectField, "Subject");
        form.addFormItem(messageContentField, "Message Content");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveLocalization();
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

    private void saveLocalization() {
        // In a real application, this would save the localization data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Notification message localization details saved.");

        // Here you would update the localization with the new values
        try {
            localization.setMessageId(Long.parseLong(messageIdField.getValue()));
        } catch (NumberFormatException e) {
            // Ignore invalid number format
        }
        
        if (messageField.getValue() != null) {
            localization.setNotificationMessage(messageField.getValue());
        }
        
        try {
            localization.setLocaleId(Long.parseLong(localeIdField.getValue()));
        } catch (NumberFormatException e) {
            // Ignore invalid number format
        }
        
        if (localeField.getValue() != null) {
            String[] parts = localeField.getValue().split(" - ");
            if (parts.length > 0) {
                localization.setLocaleCode(parts[0]);
                if (parts.length > 1) {
                    localization.setLocaleName(parts[1]);
                }
            }
        }
        
        localization.setSubject(subjectField.getValue());
        localization.setMessage(messageContentField.getValue());
        localization.setStatus("Active".equals(statusField.getValue()) 
            ? NotificationMessageLocalization.Status.ACTIVE 
            : NotificationMessageLocalization.Status.INACTIVE);
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
    
    // Mock data for locales
    private List<String> getMockLocales() {
        List<String> locales = new ArrayList<>();
        locales.add("en-US - English (US)");
        locales.add("es-ES - Spanish (Spain)");
        locales.add("fr-FR - French (France)");
        locales.add("de-DE - German (Germany)");
        locales.add("it-IT - Italian (Italy)");
        locales.add("pt-BR - Portuguese (Brazil)");
        locales.add("ja-JP - Japanese (Japan)");
        locales.add("zh-CN - Chinese (Simplified)");
        return locales;
    }
}