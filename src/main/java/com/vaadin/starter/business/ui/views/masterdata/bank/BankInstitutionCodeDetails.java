package com.vaadin.starter.business.ui.views.masterdata.bank;

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
import com.vaadin.starter.business.ui.views.masterdata.bank.BankInstitutionCodes.BankInstitutionCode;

import java.time.LocalDate;

/**
 * Dialog for displaying bank institution code details.
 */
public class BankInstitutionCodeDetails extends Dialog {

    private final BankInstitutionCode bankInstitutionCode;

    private TextField bankNameField;
    private TextField swiftCodeField;
    private TextField routingNumberField;
    private TextField ibanPrefixField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the BankInstitutionCodeDetails dialog.
     *
     * @param bankInstitutionCode the bank institution code to display details for
     */
    public BankInstitutionCodeDetails(BankInstitutionCode bankInstitutionCode) {
        this.bankInstitutionCode = bankInstitutionCode;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Bank Institution Code Details: " + bankInstitutionCode.getBankName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Institution ID
        TextField institutionIdField = new TextField();
        institutionIdField.setValue(bankInstitutionCode.getInstitutionId() != null ? bankInstitutionCode.getInstitutionId().toString() : "");
        institutionIdField.setReadOnly(true);
        institutionIdField.setWidthFull();

        // Bank Name
        bankNameField = new TextField();
        bankNameField.setValue(bankInstitutionCode.getBankName() != null ? bankInstitutionCode.getBankName() : "");
        bankNameField.setWidthFull();

        // SWIFT Code
        swiftCodeField = new TextField();
        swiftCodeField.setValue(bankInstitutionCode.getSwiftCode() != null ? bankInstitutionCode.getSwiftCode() : "");
        swiftCodeField.setWidthFull();

        // Routing Number
        routingNumberField = new TextField();
        routingNumberField.setValue(bankInstitutionCode.getRoutingNumber() != null ? bankInstitutionCode.getRoutingNumber() : "");
        routingNumberField.setWidthFull();

        // IBAN Prefix
        ibanPrefixField = new TextField();
        ibanPrefixField.setValue(bankInstitutionCode.getIbanPrefix() != null ? bankInstitutionCode.getIbanPrefix() : "");
        ibanPrefixField.setWidthFull();

        // Country ID
        TextField countryIdField = new TextField();
        countryIdField.setValue(bankInstitutionCode.getCountryId() != null ? bankInstitutionCode.getCountryId().toString() : "");
        countryIdField.setWidthFull();

        // Institution Type
        TextField institutionTypeField = new TextField();
        institutionTypeField.setValue(bankInstitutionCode.getInstitutionTypeLkpId() != null ? bankInstitutionCode.getInstitutionTypeLkpId().toString() : "");
        institutionTypeField.setWidthFull();

        // SVG Icon
        TextArea svgIconField = new TextArea();
        svgIconField.setValue(bankInstitutionCode.getSvgIcon() != null ? bankInstitutionCode.getSvgIcon() : "");
        svgIconField.setWidthFull();
        svgIconField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(bankInstitutionCode.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(bankInstitutionCode.getDateCreated() != null ? bankInstitutionCode.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(bankInstitutionCode.getDateUpdated() != null ? bankInstitutionCode.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(institutionIdField, "Institution ID");
        form.addFormItem(bankNameField, "Bank Name");
        form.addFormItem(swiftCodeField, "SWIFT Code");
        form.addFormItem(routingNumberField, "Routing Number");
        form.addFormItem(ibanPrefixField, "IBAN Prefix");
        form.addFormItem(countryIdField, "Country ID");
        form.addFormItem(institutionTypeField, "Institution Type ID");
        form.addFormItem(statusField, "Status");
        form.addFormItem(svgIconField, "SVG Icon");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveBankInstitutionCode();
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

    private void saveBankInstitutionCode() {
        // In a real application, this would save the bank institution code data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Bank institution code details saved.");

        // Here you would update the bank institution code with the new values
        // Example: bankInstitutionCode.setBankName(bankNameField.getValue());
        // bankInstitutionCodeService.updateBankInstitutionCode(bankInstitutionCode);
    }
}