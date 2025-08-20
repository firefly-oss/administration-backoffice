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
import com.vaadin.starter.business.ui.views.masterdata.contract.RoleOperationTypes.RoleOperationType;

import java.time.LocalDate;

/**
 * Dialog for displaying role operation type details.
 */
public class RoleOperationTypeDetails extends Dialog {

    private final RoleOperationType item;

    private TextField idField;
    private TextField operationCodeField;
    private TextField nameField;
    private TextArea descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;

    public RoleOperationTypeDetails(RoleOperationType item) {
        this.item = item;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Role Operation Type Details: " + (item.getOperationCode() != null ? item.getOperationCode() : ""));
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        idField = new TextField();
        idField.setValue(item.getId() != null ? item.getId().toString() : "");
        idField.setReadOnly(true);
        idField.setWidthFull();

        operationCodeField = new TextField();
        operationCodeField.setValue(item.getOperationCode() != null ? item.getOperationCode() : "");
        operationCodeField.setWidthFull();

        nameField = new TextField();
        nameField.setValue(item.getName() != null ? item.getName() : "");
        nameField.setWidthFull();

        descriptionField = new TextArea();
        descriptionField.setValue(item.getDescription() != null ? item.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(item.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        creationDateField = new DatePicker();
        creationDateField.setValue(item.getDateCreated() != null ? item.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(idField, "ID");
        form.addFormItem(operationCodeField, "Operation Code");
        form.addFormItem(nameField, "Name");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            save();
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

    private void save() {
        UIUtils.showNotification("Role operation type details saved.");
    }
}
