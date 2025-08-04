package com.vaadin.starter.business.ui.views.masterdata.asset;

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
import com.vaadin.starter.business.ui.views.masterdata.asset.AssetTypes.AssetType;

import java.time.LocalDate;

/**
 * Dialog for displaying asset type details.
 */
public class AssetTypeDetails extends Dialog {

    private final AssetType assetType;

    private TextField assetCodeField;
    private TextField nameField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;
    private TextArea descriptionField;

    /**
     * Constructor for the AssetTypeDetails dialog.
     *
     * @param assetType the asset type to display details for
     */
    public AssetTypeDetails(AssetType assetType) {
        this.assetType = assetType;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Asset Type Details: " + assetType.getAssetCode());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Asset ID
        TextField assetIdField = new TextField();
        assetIdField.setValue(assetType.getAssetId() != null ? assetType.getAssetId().toString() : "");
        assetIdField.setReadOnly(true);
        assetIdField.setWidthFull();

        // Asset Code
        assetCodeField = new TextField();
        assetCodeField.setValue(assetType.getAssetCode() != null ? assetType.getAssetCode() : "");
        assetCodeField.setWidthFull();

        // Name
        nameField = new TextField();
        nameField.setValue(assetType.getName() != null ? assetType.getName() : "");
        nameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(assetType.getDescription() != null ? assetType.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(assetType.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(assetType.getDateCreated() != null ? assetType.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(assetType.getDateUpdated() != null ? assetType.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(assetIdField, "Asset ID");
        form.addFormItem(assetCodeField, "Asset Code");
        form.addFormItem(nameField, "Name");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveAssetType();
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

    private void saveAssetType() {
        // In a real application, this would save the asset type data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Asset type details saved.");

        // Here you would update the asset type with the new values
        // Example: assetType.setName(nameField.getValue());
        // assetTypeService.updateAssetType(assetType);
    }
}