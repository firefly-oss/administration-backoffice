package com.vaadin.starter.business.ui.views.masterdata.message;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.views.masterdata.message.MessageTypes.MessageType;
import com.vaadin.starter.business.ui.views.masterdata.message.MessageTypes.Status;

import java.time.LocalDate;

/**
 * Dialog for displaying message type details.
 */
public class MessageTypeDetails extends Dialog {

    private final MessageType messageType;

    private TextField typeCodeField;
    private TextField typeNameField;
    private TextField descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the MessageTypeDetails dialog.
     *
     * @param messageType the message type to display details for
     */
    public MessageTypeDetails(MessageType messageType) {
        this.messageType = messageType;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Message Type Details: " + (messageType.getTypeName() != null ? messageType.getTypeName() : "New Message Type"));
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Type ID
        TextField typeIdField = new TextField();
        typeIdField.setValue(messageType.getTypeId() != null ? messageType.getTypeId().toString() : "");
        typeIdField.setReadOnly(true);
        typeIdField.setWidthFull();

        // Type Code
        typeCodeField = new TextField();
        typeCodeField.setValue(messageType.getTypeCode() != null ? messageType.getTypeCode() : "");
        typeCodeField.setWidthFull();

        // Type Name
        typeNameField = new TextField();
        typeNameField.setValue(messageType.getTypeName() != null ? messageType.getTypeName() : "");
        typeNameField.setWidthFull();

        // Description
        descriptionField = new TextField();
        descriptionField.setValue(messageType.getDescription() != null ? messageType.getDescription() : "");
        descriptionField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(messageType.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(messageType.getDateCreated() != null ? messageType.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(messageType.getDateUpdated() != null ? messageType.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(typeIdField, "Type ID");
        form.addFormItem(typeCodeField, "Type Code");
        form.addFormItem(typeNameField, "Type Name");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveMessageType();
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

    private void saveMessageType() {
        // In a real application, this would save the message type data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Message type details saved.");

        // Here you would update the message type with the new values
        messageType.setTypeCode(typeCodeField.getValue());
        messageType.setTypeName(typeNameField.getValue());
        messageType.setDescription(descriptionField.getValue());
        messageType.setStatus("Active".equals(statusField.getValue()) ? Status.ACTIVE : Status.INACTIVE);
    }
}