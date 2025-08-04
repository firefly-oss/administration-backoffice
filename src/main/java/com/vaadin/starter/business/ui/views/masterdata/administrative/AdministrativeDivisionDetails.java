package com.vaadin.starter.business.ui.views.masterdata.administrative;

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
import com.vaadin.starter.business.ui.views.masterdata.administrative.AdministrativeDivisions.AdministrativeDivision;
import com.vaadin.starter.business.ui.views.masterdata.administrative.AdministrativeDivisions.Status;

import java.time.LocalDate;

/**
 * Dialog for displaying administrative division details.
 */
public class AdministrativeDivisionDetails extends Dialog {

    private final AdministrativeDivision division;

    private TextField codeField;
    private TextField nameField;
    private TextField levelField;
    private TextField countryIdField;
    private TextField parentDivisionIdField;
    private TextField postalCodePatternField;
    private TextField timeZoneField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the AdministrativeDivisionDetails dialog.
     *
     * @param division the division to display details for
     */
    public AdministrativeDivisionDetails(AdministrativeDivision division) {
        this.division = division;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Administrative Division Details: " + division.getName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Division ID
        TextField divisionIdField = new TextField();
        divisionIdField.setValue(division.getDivisionId() != null ? division.getDivisionId().toString() : "");
        divisionIdField.setReadOnly(true);
        divisionIdField.setWidthFull();

        // Code
        codeField = new TextField();
        codeField.setValue(division.getCode() != null ? division.getCode() : "");
        codeField.setWidthFull();

        // Name
        nameField = new TextField();
        nameField.setValue(division.getName() != null ? division.getName() : "");
        nameField.setWidthFull();

        // Level
        levelField = new TextField();
        levelField.setValue(division.getLevel() != null ? division.getLevel() : "");
        levelField.setWidthFull();

        // Country ID
        countryIdField = new TextField();
        countryIdField.setValue(division.getCountryId() != null ? division.getCountryId().toString() : "");
        countryIdField.setWidthFull();

        // Parent Division ID
        parentDivisionIdField = new TextField();
        parentDivisionIdField.setValue(division.getParentDivisionId() != null ? division.getParentDivisionId().toString() : "");
        parentDivisionIdField.setWidthFull();

        // Postal Code Pattern
        postalCodePatternField = new TextField();
        postalCodePatternField.setValue(division.getPostalCodePattern() != null ? division.getPostalCodePattern() : "");
        postalCodePatternField.setWidthFull();

        // Time Zone
        timeZoneField = new TextField();
        timeZoneField.setValue(division.getTimeZone() != null ? division.getTimeZone() : "");
        timeZoneField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(division.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(division.getDateCreated() != null ? division.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(division.getDateUpdated() != null ? division.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(divisionIdField, "Division ID");
        form.addFormItem(codeField, "Code");
        form.addFormItem(nameField, "Name");
        form.addFormItem(levelField, "Level");
        form.addFormItem(countryIdField, "Country ID");
        form.addFormItem(parentDivisionIdField, "Parent Division ID");
        form.addFormItem(postalCodePatternField, "Postal Code Pattern");
        form.addFormItem(timeZoneField, "Time Zone");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveDivision();
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

    private void saveDivision() {
        // In a real application, this would save the division data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Administrative division details saved.");

        // Here you would update the division with the new values
        // Example: division.setName(nameField.getValue());
        // divisionService.updateDivision(division);
    }
}