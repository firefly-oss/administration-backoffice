package com.vaadin.starter.business.ui.views.masterdata.legalform;

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
import com.vaadin.starter.business.ui.views.masterdata.legalform.LegalForms.LegalForm;

import java.time.LocalDate;

/**
 * Dialog for displaying legal form details.
 */
public class LegalFormDetails extends Dialog {

    private final LegalForm legalForm;

    private TextField codeField;
    private TextField nameField;
    private TextArea descriptionField;
    private TextField countryIdField;
    private ComboBox<String> isCorporateField;
    private TextField entityTypeField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the LegalFormDetails dialog.
     *
     * @param legalForm the legal form to display details for
     */
    public LegalFormDetails(LegalForm legalForm) {
        this.legalForm = legalForm;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Legal Form Details: " + legalForm.getName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Legal Form ID
        TextField legalFormIdField = new TextField();
        legalFormIdField.setValue(legalForm.getLegalFormId() != null ? legalForm.getLegalFormId().toString() : "");
        legalFormIdField.setReadOnly(true);
        legalFormIdField.setWidthFull();

        // Code
        codeField = new TextField();
        codeField.setValue(legalForm.getCode() != null ? legalForm.getCode() : "");
        codeField.setWidthFull();

        // Name
        nameField = new TextField();
        nameField.setValue(legalForm.getName() != null ? legalForm.getName() : "");
        nameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(legalForm.getDescription() != null ? legalForm.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Country ID
        countryIdField = new TextField();
        countryIdField.setValue(legalForm.getCountryId() != null ? legalForm.getCountryId().toString() : "");
        countryIdField.setWidthFull();

        // Is Corporate
        isCorporateField = new ComboBox<>();
        isCorporateField.setItems("Yes", "No");
        isCorporateField.setValue(legalForm.getIsCorporate() != null && legalForm.getIsCorporate() ? "Yes" : "No");
        isCorporateField.setWidthFull();

        // Entity Type
        entityTypeField = new TextField();
        entityTypeField.setValue(legalForm.getEntityType() != null ? legalForm.getEntityType() : "");
        entityTypeField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(legalForm.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(legalForm.getDateCreated() != null ? legalForm.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(legalForm.getDateUpdated() != null ? legalForm.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(legalFormIdField, "Legal Form ID");
        form.addFormItem(codeField, "Code");
        form.addFormItem(nameField, "Name");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(countryIdField, "Country ID");
        form.addFormItem(isCorporateField, "Is Corporate");
        form.addFormItem(entityTypeField, "Entity Type");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveLegalForm();
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

    private void saveLegalForm() {
        // In a real application, this would save the legal form data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Legal form details saved.");

        // Here you would update the legal form with the new values
        // Example: legalForm.setName(nameField.getValue());
        // legalFormService.updateLegalForm(legalForm);
    }
}