package com.vaadin.starter.business.ui.views.masterdata.identity;

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
import com.vaadin.starter.business.ui.views.masterdata.identity.IdentityDocuments.IdentityDocument;

import java.time.LocalDate;

/**
 * Dialog for displaying identity document details.
 */
public class IdentityDocumentDetails extends Dialog {

    private final IdentityDocument document;

    private TextField documentIdField;
    private TextField documentCodeField;
    private TextField documentNameField;
    private TextField categoryIdField;
    private TextField categoryNameField;
    private TextField countryIdField;
    private TextArea descriptionField;
    private TextField validationRegexField;
    private TextField formatDescriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the IdentityDocumentDetails dialog.
     *
     * @param document the identity document to display details for
     */
    public IdentityDocumentDetails(IdentityDocument document) {
        this.document = document;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Identity Document Details: " + document.getDocumentName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Document ID
        documentIdField = new TextField();
        documentIdField.setValue(document.getDocumentId() != null ? document.getDocumentId().toString() : "");
        documentIdField.setReadOnly(true);
        documentIdField.setWidthFull();

        // Document Code
        documentCodeField = new TextField();
        documentCodeField.setValue(document.getDocumentCode() != null ? document.getDocumentCode() : "");
        documentCodeField.setWidthFull();

        // Document Name
        documentNameField = new TextField();
        documentNameField.setValue(document.getDocumentName() != null ? document.getDocumentName() : "");
        documentNameField.setWidthFull();

        // Category ID
        categoryIdField = new TextField();
        categoryIdField.setValue(document.getCategoryId() != null ? document.getCategoryId().toString() : "");
        categoryIdField.setReadOnly(true);
        categoryIdField.setWidthFull();

        // Category Name
        categoryNameField = new TextField();
        categoryNameField.setValue(document.getCategoryName() != null ? document.getCategoryName() : "");
        categoryNameField.setWidthFull();

        // Country ID
        countryIdField = new TextField();
        countryIdField.setValue(document.getCountryId() != null ? document.getCountryId().toString() : "");
        countryIdField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(document.getDescription() != null ? document.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Validation Regex
        validationRegexField = new TextField();
        validationRegexField.setValue(document.getValidationRegex() != null ? document.getValidationRegex() : "");
        validationRegexField.setWidthFull();

        // Format Description
        formatDescriptionField = new TextField();
        formatDescriptionField.setValue(document.getFormatDescription() != null ? document.getFormatDescription() : "");
        formatDescriptionField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(document.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(document.getDateCreated() != null ? document.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(document.getDateUpdated() != null ? document.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(documentIdField, "Document ID");
        form.addFormItem(documentCodeField, "Document Code");
        form.addFormItem(documentNameField, "Document Name");
        form.addFormItem(categoryIdField, "Category ID");
        form.addFormItem(categoryNameField, "Category Name");
        form.addFormItem(countryIdField, "Country ID");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(validationRegexField, "Validation Regex");
        form.addFormItem(formatDescriptionField, "Format Description");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveDocument();
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

    private void saveDocument() {
        // In a real application, this would save the document data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Identity document details saved.");

        // Here you would update the document with the new values
        // Example: document.setDocumentName(documentNameField.getValue());
        // documentService.updateDocument(document);
    }
}