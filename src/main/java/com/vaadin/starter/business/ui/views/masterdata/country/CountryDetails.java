package com.vaadin.starter.business.ui.views.masterdata.country;

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
import com.vaadin.starter.business.ui.views.masterdata.country.Countries.Country;

import java.time.LocalDate;

/**
 * Dialog for displaying country details.
 */
public class CountryDetails extends Dialog {

    private final Country country;

    private TextField isoCodeField;
    private TextField countryNameField;
    private TextField regionLkpIdField;
    private ComboBox<String> statusField;
    private TextArea svgFlagField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the CountryDetails dialog.
     *
     * @param country the country to display details for
     */
    public CountryDetails(Country country) {
        this.country = country;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Country Details: " + country.getCountryName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Country ID
        TextField countryIdField = new TextField();
        countryIdField.setValue(country.getCountryId() != null ? country.getCountryId().toString() : "");
        countryIdField.setReadOnly(true);
        countryIdField.setWidthFull();

        // ISO Code
        isoCodeField = new TextField();
        isoCodeField.setValue(country.getIsoCode() != null ? country.getIsoCode() : "");
        isoCodeField.setWidthFull();

        // Country Name
        countryNameField = new TextField();
        countryNameField.setValue(country.getCountryName() != null ? country.getCountryName() : "");
        countryNameField.setWidthFull();

        // Region ID
        regionLkpIdField = new TextField();
        regionLkpIdField.setValue(country.getRegionLkpId() != null ? country.getRegionLkpId().toString() : "");
        regionLkpIdField.setWidthFull();

        // SVG Flag
        svgFlagField = new TextArea();
        svgFlagField.setValue(country.getSvgFlag() != null ? country.getSvgFlag() : "");
        svgFlagField.setWidthFull();
        svgFlagField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(country.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(country.getDateCreated() != null ? country.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(country.getDateUpdated() != null ? country.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(countryIdField, "Country ID");
        form.addFormItem(isoCodeField, "ISO Code");
        form.addFormItem(countryNameField, "Country Name");
        form.addFormItem(regionLkpIdField, "Region ID");
        form.addFormItem(statusField, "Status");
        form.addFormItem(svgFlagField, "SVG Flag");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveCountry();
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

    private void saveCountry() {
        // In a real application, this would save the country data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Country details saved.");

        // Here you would update the country with the new values
        // Example: country.setCountryName(countryNameField.getValue());
        // countryService.updateCountry(country);
    }
}