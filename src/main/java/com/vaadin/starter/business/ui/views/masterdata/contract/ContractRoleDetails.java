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
import com.vaadin.starter.business.ui.views.masterdata.contract.ContractRoles.ContractRole;

import java.time.LocalDate;

/**
 * Dialog for displaying contract role details.
 */
public class ContractRoleDetails extends Dialog {

    private final ContractRole contractRole;

    private TextField roleIdField;
    private TextField roleCodeField;
    private TextField nameField;
    private TextArea descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;

    public ContractRoleDetails(ContractRole contractRole) {
        this.contractRole = contractRole;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Contract Role Details: " + (contractRole.getRoleCode() != null ? contractRole.getRoleCode() : ""));
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Role ID
        roleIdField = new TextField();
        roleIdField.setValue(contractRole.getRoleId() != null ? contractRole.getRoleId().toString() : "");
        roleIdField.setReadOnly(true);
        roleIdField.setWidthFull();

        // Role Code
        roleCodeField = new TextField();
        roleCodeField.setValue(contractRole.getRoleCode() != null ? contractRole.getRoleCode() : "");
        roleCodeField.setWidthFull();

        // Name
        nameField = new TextField();
        nameField.setValue(contractRole.getName() != null ? contractRole.getName() : "");
        nameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(contractRole.getDescription() != null ? contractRole.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(contractRole.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(contractRole.getDateCreated() != null ? contractRole.getDateCreated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(roleIdField, "Role ID");
        form.addFormItem(roleCodeField, "Role Code");
        form.addFormItem(nameField, "Name");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveContractRole();
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

    private void saveContractRole() {
        // Placeholder for persistence
        UIUtils.showNotification("Contract role details saved.");
    }
}
