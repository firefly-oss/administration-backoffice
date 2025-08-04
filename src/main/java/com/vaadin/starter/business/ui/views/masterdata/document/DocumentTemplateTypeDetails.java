package com.vaadin.starter.business.ui.views.masterdata.document;

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
import com.vaadin.starter.business.ui.views.masterdata.document.DocumentTemplateTypes.DocumentTemplateType;

import java.time.LocalDate;

/**
 * Dialog for displaying document template type details.
 */
public class DocumentTemplateTypeDetails extends Dialog {

    private final DocumentTemplateType type;

    private TextField typeIdField;
    private TextField typeCodeField;
    private TextField typeNameField;
    private TextArea descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the DocumentTemplateTypeDetails dialog.
     *
     * @param type the document template type to display details for
     */
    public DocumentTemplateTypeDetails(DocumentTemplateType type) {
        this.type = type;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Document Template Type Details: " + type.getTypeName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Type ID
        typeIdField = new TextField();
        typeIdField.setValue(type.getTypeId() != null ? type.getTypeId().toString() : "");
        typeIdField.setReadOnly(true);
        typeIdField.setWidthFull();

        // Type Code
        typeCodeField = new TextField();
        typeCodeField.setValue(type.getTypeCode() != null ? type.getTypeCode() : "");
        typeCodeField.setWidthFull();

        // Type Name
        typeNameField = new TextField();
        typeNameField.setValue(type.getTypeName() != null ? type.getTypeName() : "");
        typeNameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(type.getDescription() != null ? type.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("150px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(type.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(type.getDateCreated() != null ? type.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(type.getDateUpdated() != null ? type.getDateUpdated().toLocalDate() : LocalDate.now());
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
            saveType();
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

    private void saveType() {
        // In a real application, this would save the type data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Document template type details saved.");

        // Here you would update the type with the new values
        // Example: type.setTypeName(typeNameField.getValue());
        // typeService.updateType(type);
    }
}