package com.vaadin.starter.business.ui.views.masterdata.transaction;

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
import com.vaadin.starter.business.ui.views.masterdata.transaction.TransactionCategoryLocalizations.TransactionCategoryLocalization;
import com.vaadin.starter.business.ui.views.masterdata.transaction.TransactionCategoryLocalizations.Status;

import java.time.LocalDate;

/**
 * Dialog for displaying transaction category localization details.
 */
public class TransactionCategoryLocalizationDetails extends Dialog {

    private final TransactionCategoryLocalization localization;

    private TextField localizationIdField;
    private TextField categoryIdField;
    private TextField localeIdField;
    private TextField categoryNameField;
    private TextArea descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the TransactionCategoryLocalizationDetails dialog.
     *
     * @param localization the transaction category localization to display details for
     */
    public TransactionCategoryLocalizationDetails(TransactionCategoryLocalization localization) {
        this.localization = localization;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Transaction Category Localization Details: " + 
            (localization.getCategoryName() != null ? localization.getCategoryName() : "New Localization"));
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

        // Category ID
        categoryIdField = new TextField();
        categoryIdField.setValue(localization.getCategoryId() != null ? localization.getCategoryId().toString() : "");
        categoryIdField.setWidthFull();

        // Locale ID
        localeIdField = new TextField();
        localeIdField.setValue(localization.getLocaleId() != null ? localization.getLocaleId().toString() : "");
        localeIdField.setWidthFull();

        // Category Name
        categoryNameField = new TextField();
        categoryNameField.setValue(localization.getCategoryName() != null ? localization.getCategoryName() : "");
        categoryNameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(localization.getDescription() != null ? localization.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("150px");

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
        form.addFormItem(categoryIdField, "Category ID");
        form.addFormItem(localeIdField, "Locale ID");
        form.addFormItem(categoryNameField, "Category Name");
        form.addFormItem(descriptionField, "Description");
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
        UIUtils.showNotification("Transaction category localization details saved.");

        // Here you would update the localization with the new values
        try {
            localization.setCategoryId(Long.parseLong(categoryIdField.getValue()));
        } catch (NumberFormatException e) {
            // Handle invalid number format
        }
        try {
            localization.setLocaleId(Long.parseLong(localeIdField.getValue()));
        } catch (NumberFormatException e) {
            // Handle invalid number format
        }
        localization.setCategoryName(categoryNameField.getValue());
        localization.setDescription(descriptionField.getValue());
        localization.setStatus("Active".equals(statusField.getValue()) ? Status.ACTIVE : Status.INACTIVE);
    }
}