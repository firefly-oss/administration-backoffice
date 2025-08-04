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
import com.vaadin.starter.business.ui.views.masterdata.identity.IdentityDocumentLocalizations.IdentityDocumentLocalization;

import java.time.LocalDate;

/**
 * Dialog for displaying identity document localization details.
 */
public class IdentityDocumentLocalizationDetails extends Dialog {

    private final IdentityDocumentLocalization localization;

    private TextField localizationIdField;
    private TextField documentIdField;
    private TextField localeIdField;
    private TextField documentNameField;
    private TextArea descriptionField;
    private TextField formatDescriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the IdentityDocumentLocalizationDetails dialog.
     *
     * @param localization the identity document localization to display details for
     */
    public IdentityDocumentLocalizationDetails(IdentityDocumentLocalization localization) {
        this.localization = localization;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Identity Document Localization Details: " + localization.getDocumentName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Localization ID
        localizationIdField = new TextField();
        localizationIdField.setValue(localization.getLocalizationId() != null ? localization.getLocalizationId().toString() : "");
        localizationIdField.setReadOnly(true);
        localizationIdField.setWidthFull();

        // Document ID
        documentIdField = new TextField();
        documentIdField.setValue(localization.getDocumentId() != null ? localization.getDocumentId().toString() : "");
        documentIdField.setReadOnly(true);
        documentIdField.setWidthFull();

        // Locale ID
        localeIdField = new TextField();
        localeIdField.setValue(localization.getLocaleId() != null ? localization.getLocaleId().toString() : "");
        localeIdField.setReadOnly(true);
        localeIdField.setWidthFull();

        // Document Name
        documentNameField = new TextField();
        documentNameField.setValue(localization.getDocumentName() != null ? localization.getDocumentName() : "");
        documentNameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(localization.getDescription() != null ? localization.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("150px");

        // Format Description
        formatDescriptionField = new TextField();
        formatDescriptionField.setValue(localization.getFormatDescription() != null ? localization.getFormatDescription() : "");
        formatDescriptionField.setWidthFull();

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
        form.addFormItem(documentIdField, "Document ID");
        form.addFormItem(localeIdField, "Locale ID");
        form.addFormItem(documentNameField, "Document Name");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(formatDescriptionField, "Format Description");
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
        UIUtils.showNotification("Identity document localization details saved.");

        // Here you would update the localization with the new values
        // Example: localization.setDocumentName(documentNameField.getValue());
        // localizationService.updateLocalization(localization);
    }
}