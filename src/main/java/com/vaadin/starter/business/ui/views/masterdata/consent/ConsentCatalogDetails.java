package com.vaadin.starter.business.ui.views.masterdata.consent;

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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.views.masterdata.consent.ConsentCatalogs.ConsentCatalog;

import java.time.LocalDate;

/**
 * Dialog for displaying consent catalog details.
 */
public class ConsentCatalogDetails extends Dialog {

    private final ConsentCatalog consentCatalog;

    private TextField consentTypeField;
    private TextArea consentDescriptionField;
    private TextField consentVersionField;
    private TextField consentSourceField;
    private IntegerField expiryPeriodDaysField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the ConsentCatalogDetails dialog.
     *
     * @param consentCatalog the consent catalog to display details for
     */
    public ConsentCatalogDetails(ConsentCatalog consentCatalog) {
        this.consentCatalog = consentCatalog;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Consent Catalog Details: " + consentCatalog.getConsentType());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Consent ID
        TextField consentIdField = new TextField();
        consentIdField.setValue(consentCatalog.getConsentId() != null ? consentCatalog.getConsentId().toString() : "");
        consentIdField.setReadOnly(true);
        consentIdField.setWidthFull();

        // Consent Type
        consentTypeField = new TextField();
        consentTypeField.setValue(consentCatalog.getConsentType() != null ? consentCatalog.getConsentType() : "");
        consentTypeField.setWidthFull();

        // Consent Description
        consentDescriptionField = new TextArea();
        consentDescriptionField.setValue(consentCatalog.getConsentDescription() != null ? consentCatalog.getConsentDescription() : "");
        consentDescriptionField.setWidthFull();
        consentDescriptionField.setHeight("100px");

        // Consent Version
        consentVersionField = new TextField();
        consentVersionField.setValue(consentCatalog.getConsentVersion() != null ? consentCatalog.getConsentVersion() : "");
        consentVersionField.setWidthFull();

        // Consent Source
        consentSourceField = new TextField();
        consentSourceField.setValue(consentCatalog.getConsentSource() != null ? consentCatalog.getConsentSource() : "");
        consentSourceField.setWidthFull();

        // Expiry Period Days
        expiryPeriodDaysField = new IntegerField();
        expiryPeriodDaysField.setValue(consentCatalog.getExpiryPeriodDays() != null ? consentCatalog.getExpiryPeriodDays() : 0);
        expiryPeriodDaysField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(consentCatalog.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(consentCatalog.getDateCreated() != null ? consentCatalog.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(consentCatalog.getDateUpdated() != null ? consentCatalog.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(consentIdField, "Consent ID");
        form.addFormItem(consentTypeField, "Consent Type");
        form.addFormItem(consentDescriptionField, "Description");
        form.addFormItem(consentVersionField, "Version");
        form.addFormItem(consentSourceField, "Source");
        form.addFormItem(expiryPeriodDaysField, "Expiry Period (Days)");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveConsentCatalog();
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

    private void saveConsentCatalog() {
        // In a real application, this would save the consent catalog data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Consent catalog details saved.");

        // Here you would update the consent catalog with the new values
        // Example: consentCatalog.setConsentType(consentTypeField.getValue());
        // consentCatalogService.updateConsentCatalog(consentCatalog);
    }
}