package com.vaadin.starter.business.ui.views.masterdata.country;

import com.catalis.common.reference.master.data.sdk.model.CountryDTO;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.starter.business.backend.sdks.services.MasterDataService;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.CountryRequest;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Dialog for displaying country details.
 */
public class CountryDetails extends Dialog {

    private final CountryDTO country;
    private final MasterDataService masterDataService;
    private final UI ui; // Store the current UI instance
    private final Consumer<CountryDTO> updateCallback; // Callback to be called when a country is updated

    // Form fields
    private TextField countryIdField;
    private TextField isoCodeField;
    private TextField countryNameField;
    private ComboBox<CountryDTO.RegionEnum> regionField;
    private ComboBox<String> statusField;
    private Image svgFlagField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the CountryDetails dialog.
     *
     * @param country the country to display details for
     * @param masterDataService the service for master data operations
     * @param updateCallback callback to be called when a country is updated
     */
    public CountryDetails(CountryDTO country, MasterDataService masterDataService, Consumer<CountryDTO> updateCallback) {
        this.country = country;
        this.masterDataService = masterDataService;
        this.updateCallback = updateCallback;
        this.ui = UI.getCurrent(); // Get the current UI instance

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
        countryIdField = new TextField();
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

        // Region - ComboBox with RegionEnum values
        regionField = new ComboBox<>();
        regionField.setItems(CountryDTO.RegionEnum.values());
        regionField.setItemLabelGenerator(region -> {
            // Format the enum name to make it more readable
            // Convert NORTH_AMERICA to North America
            String name = region.name().replace('_', ' ');
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        });
        regionField.setValue(country.getRegion());
        regionField.setWidthFull();

        // SVG Flag
        StreamResource svgResource = new StreamResource(UUID.randomUUID() + ".svg", () ->
                new ByteArrayInputStream(Objects.requireNonNull(country.getSvgFlag()).getBytes(StandardCharsets.UTF_8)));
        svgResource.setContentType("image/svg+xml");
        svgFlagField = new Image(svgResource, "SVG Image");
        svgFlagField.setWidth("100px");
        svgFlagField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(country.getStatus() != null && country.getStatus() == CountryDTO.StatusEnum.ACTIVE ? "Active" : "Inactive");
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
        form.addFormItem(regionField, "Region");
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
        if (country == null || country.getCountryId() == null) {
            UIUtils.showNotification("Cannot update country: missing country ID");
            return;
        }

        System.out.println("[DEBUG_LOG] Updating country with ID: " + country.getCountryId());

        // Create CountryRequest from form field values
        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setCountryName(countryNameField.getValue());
        countryRequest.setIsoCode(isoCodeField.getValue());
        countryRequest.setRegion(regionField.getValue() != null ? regionField.getValue().name() : null);
        countryRequest.setSvgFlag(country.getSvgFlag()); // SVG flag is not editable in the form
        countryRequest.setActive("Active".equals(statusField.getValue()));
        countryRequest.setDateCreated(LocalDateTime.now());
        countryRequest.setDateUpdated(country.getDateUpdated()); // Keep original update date

        // Call the service to update the country
        masterDataService.updateCountry(country.getCountryId(), countryRequest)
            .subscribe(
                response -> {
                    // On success
                    if (response != null && response.getBody() != null) {
                        // Access UI thread to update UI components
                        ui.access(() -> {
                            // Call the update callback with the updated country
                            updateCallback.accept(response.getBody());

                            // Show success notification
                            UIUtils.showNotification("Country updated successfully.");
                        });
                    }
                },
                error -> {
                    // On error - access UI thread to show notification
                    System.out.println("[DEBUG_LOG] Error updating country: " + error.getMessage());
                    ui.access(() -> {
                        UIUtils.showNotification("Error updating country: " + error.getMessage());
                    });
                }
            );
    }
}
