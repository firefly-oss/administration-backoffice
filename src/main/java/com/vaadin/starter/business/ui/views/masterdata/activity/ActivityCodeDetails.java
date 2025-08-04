package com.vaadin.starter.business.ui.views.masterdata.activity;

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
import com.vaadin.starter.business.ui.views.masterdata.activity.ActivityCodes.ActivityCode;

/**
 * Dialog for displaying activity code details.
 */
public class ActivityCodeDetails extends Dialog {

    private final ActivityCode activityCode;

    private TextField codeField;
    private TextField nameField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private TextArea descriptionField;

    /**
     * Constructor for the ActivityCodeDetails dialog.
     *
     * @param activityCode the activity code to display details for
     */
    public ActivityCodeDetails(ActivityCode activityCode) {
        this.activityCode = activityCode;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Activity Code Details: " + activityCode.getCode());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Activity Code ID
        TextField activityCodeIdField = new TextField();
        activityCodeIdField.setValue(activityCode.getActivityCodeId() != null ? activityCode.getActivityCodeId().toString() : "");
        activityCodeIdField.setReadOnly(true);
        activityCodeIdField.setWidthFull();

        // Country ID
        TextField countryIdField = new TextField();
        countryIdField.setValue(activityCode.getCountryId() != null ? activityCode.getCountryId().toString() : "");
        countryIdField.setWidthFull();

        // Code
        codeField = new TextField();
        codeField.setValue(activityCode.getCode());
        codeField.setReadOnly(true);
        codeField.setWidthFull();

        // Classification System
        TextField classificationSysField = new TextField();
        classificationSysField.setValue(activityCode.getClassificationSys() != null ? activityCode.getClassificationSys() : "");
        classificationSysField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(activityCode.getDescription() != null ? activityCode.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Parent Code ID
        TextField parentCodeIdField = new TextField();
        parentCodeIdField.setValue(activityCode.getParentCodeId() != null ? activityCode.getParentCodeId().toString() : "");
        parentCodeIdField.setWidthFull();

        // High Risk
        ComboBox<String> highRiskField = new ComboBox<>();
        highRiskField.setItems("Yes", "No");
        highRiskField.setValue(activityCode.getHighRisk() != null && activityCode.getHighRisk() ? "Yes" : "No");
        highRiskField.setWidthFull();

        // Risk Factors
        TextArea riskFactorsField = new TextArea();
        riskFactorsField.setValue(activityCode.getRiskFactors() != null ? activityCode.getRiskFactors() : "");
        riskFactorsField.setWidthFull();
        riskFactorsField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(activityCode.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(activityCode.getCreationDate() != null ? activityCode.getCreationDate() : java.time.LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(activityCodeIdField, "Activity Code ID");
        form.addFormItem(countryIdField, "Country ID");
        form.addFormItem(codeField, "Code");
        form.addFormItem(classificationSysField, "Classification System");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(parentCodeIdField, "Parent Code ID");
        form.addFormItem(highRiskField, "High Risk");
        form.addFormItem(riskFactorsField, "Risk Factors");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveActivityCode();
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

    private void saveActivityCode() {
        // In a real application, this would save the activity code data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Activity code details saved.");

        // Here you would update the activity code with the new values
        // Example: activityCode.setName(nameField.getValue());
        // activityCodeService.updateActivityCode(activityCode);
    }
}