package com.vaadin.starter.business.ui.views.masterdata.contract;

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
import com.vaadin.starter.business.ui.views.masterdata.contract.ContractTypes.ContractType;

import java.time.LocalDate;

/**
 * Dialog for displaying contract type details.
 */
public class ContractTypeDetails extends Dialog {

    private final ContractType contractType;

    private TextField contractCodeField;
    private TextField nameField;
    private TextArea descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the ContractTypeDetails dialog.
     *
     * @param contractType the contract type to display details for
     */
    public ContractTypeDetails(ContractType contractType) {
        this.contractType = contractType;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Contract Type Details: " + contractType.getContractCode());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Contract ID
        TextField contractIdField = new TextField();
        contractIdField.setValue(contractType.getContractId() != null ? contractType.getContractId().toString() : "");
        contractIdField.setReadOnly(true);
        contractIdField.setWidthFull();

        // Contract Code
        contractCodeField = new TextField();
        contractCodeField.setValue(contractType.getContractCode() != null ? contractType.getContractCode() : "");
        contractCodeField.setWidthFull();

        // Name
        nameField = new TextField();
        nameField.setValue(contractType.getName() != null ? contractType.getName() : "");
        nameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(contractType.getDescription() != null ? contractType.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(contractType.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(contractType.getDateCreated() != null ? contractType.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(contractType.getDateUpdated() != null ? contractType.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(contractIdField, "Contract ID");
        form.addFormItem(contractCodeField, "Contract Code");
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
            saveContractType();
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

    private void saveContractType() {
        // In a real application, this would save the contract type data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Contract type details saved.");

        // Here you would update the contract type with the new values
        // Example: contractType.setName(nameField.getValue());
        // contractTypeService.updateContractType(contractType);
    }
}