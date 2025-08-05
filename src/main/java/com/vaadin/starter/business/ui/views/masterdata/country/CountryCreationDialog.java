package com.vaadin.starter.business.ui.views.masterdata.country;

import com.catalis.common.reference.master.data.sdk.model.CountryDTO;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.starter.business.backend.sdks.services.MasterDataService;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.CountryRequest;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.views.masterdata.country.Countries.Country;

import java.time.LocalDateTime;
import java.util.function.Consumer;

/**
 * Dialog for creating a new country.
 */
public class CountryCreationDialog extends Dialog {

    private TextField isoCodeField;
    private TextField countryNameField;
    private ComboBox<CountryDTO.RegionEnum> regionField;
    private ComboBox<String> statusField;
    private TextField svgFlagField;

    private final Consumer<Country> saveCallback;
    private final MasterDataService masterDataService;
    private UI ui; // Store the current UI instance

    /**
     * Constructor for the CountryCreationDialog.
     *
     * @param saveCallback callback to be called when a new country is saved
     * @param masterDataService service for master data operations
     */
    public CountryCreationDialog(Consumer<Country> saveCallback, MasterDataService masterDataService) {
        this.saveCallback = saveCallback;
        this.masterDataService = masterDataService;
        this.ui = UI.getCurrent(); // Get the current UI instance

        setWidth("600px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Create New Country");
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // ISO Code
        isoCodeField = new TextField();
        isoCodeField.setWidthFull();
        isoCodeField.setRequired(true);
        isoCodeField.setMaxLength(2);
        isoCodeField.setMinLength(2);
        isoCodeField.setHelperText("Two-letter country code (ISO 3166-1 alpha-2)");

        // Country Name
        countryNameField = new TextField();
        countryNameField.setWidthFull();
        countryNameField.setRequired(true);

        // Region Field
        regionField = new ComboBox<>();
        regionField.setItems(CountryDTO.RegionEnum.values());
        regionField.setItemLabelGenerator(region -> {
            // Format the enum name to make it more readable
            // Convert NORTH_AMERICA to North America
            String name = region.name().replace('_', ' ');
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        });
        regionField.setWidthFull();
        regionField.setRequired(true);
        regionField.setHelperText("Select a region");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue("Active");
        statusField.setWidthFull();
        statusField.setRequired(true);

        // SVG Flag Field
        svgFlagField = new TextField();
        svgFlagField.setWidthFull();
        svgFlagField.setHelperText("Enter SVG code or URL");

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(isoCodeField, "ISO Code");
        form.addFormItem(countryNameField, "Country Name");
        form.addFormItem(regionField, "Region");
        form.addFormItem(statusField, "Status");
        form.addFormItem(svgFlagField, "SVG Flag");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            if (validateForm()) {
                saveCountry();
                close();
            }
        });

        Button cancelButton = UIUtils.createTertiaryButton("Cancel");
        cancelButton.addClickListener(e -> close());

        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);

        return buttonLayout;
    }

    private boolean validateForm() {
        boolean isValid = true;

        // Validate ISO Code
        if (isoCodeField.getValue() == null || isoCodeField.getValue().isEmpty()) {
            isoCodeField.setInvalid(true);
            isoCodeField.setErrorMessage("ISO Code is required");
            isValid = false;
        } else if (isoCodeField.getValue().length() != 2) {
            isoCodeField.setInvalid(true);
            isoCodeField.setErrorMessage("ISO Code must be exactly 2 characters");
            isValid = false;
        } else {
            isoCodeField.setInvalid(false);
        }

        // Validate Country Name
        if (countryNameField.getValue() == null || countryNameField.getValue().isEmpty()) {
            countryNameField.setInvalid(true);
            countryNameField.setErrorMessage("Country Name is required");
            isValid = false;
        } else {
            countryNameField.setInvalid(false);
        }

        // Validate Region
        if (regionField.getValue() == null) {
            regionField.setInvalid(true);
            regionField.setErrorMessage("Region is required");
            isValid = false;
        } else {
            regionField.setInvalid(false);
        }

        // Validate Status
        if (statusField.getValue() == null) {
            statusField.setInvalid(true);
            statusField.setErrorMessage("Status is required");
            isValid = false;
        } else {
            statusField.setInvalid(false);
        }

        return isValid;
    }

    private void saveCountry() {
        try {
            // Get form values
            String isoCode = isoCodeField.getValue();
            String countryName = countryNameField.getValue();
            CountryDTO.RegionEnum region = regionField.getValue();
            Boolean isActive = "Active".equals(statusField.getValue());
            String svgFlag = svgFlagField.getValue();

            LocalDateTime now = LocalDateTime.now();

            // Create CountryRequest object
            CountryRequest countryRequest = new CountryRequest();
            countryRequest.setCountryName(countryName);
            countryRequest.setIsoCode(isoCode);
            countryRequest.setRegion(region.name());
            countryRequest.setActive(isActive);
            countryRequest.setDateCreated(now);
            countryRequest.setSvgFlag(svgFlag);

            // Call MasterDataService to create the country
            masterDataService.createCountry(countryRequest)
                .subscribe(
                    response -> {
                        // On success
                        if (response != null && response.getBody() != null) {
                            // Create a Country object for the callback
                            // Note: We're using the form values for fields that might not be in the CountryDTO
                            Country newCountry = new Country(
                                System.currentTimeMillis(), // Using placeholder ID since we don't know if CountryDTO has an ID
                                isoCode,
                                countryName,
                                0L, // Using placeholder for regionLkpId since we're now using RegionEnum
                                isActive,
                                svgFlag,
                                now,
                                now
                            );

                            // Access UI thread to update UI components
                            ui.access(() -> {
                                // Call the save callback with the new country
                                saveCallback.accept(newCountry);

                                UIUtils.showNotification("Country created successfully.");
                            });
                        }
                    },
                    error -> {
                        // On error - access UI thread to show notification
                        ui.access(() -> {
                            UIUtils.showNotification("Error creating country: " + error.getMessage());
                        });
                    }
                );
        } catch (Exception e) {
            // Access UI thread to show notification for exceptions in the try block
            ui.access(() -> {
                UIUtils.showNotification("Error creating country: " + e.getMessage());
            });
        }
    }
}
