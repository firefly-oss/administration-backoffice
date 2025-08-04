package com.vaadin.starter.business.ui.views.masterdata.currency;

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
import com.vaadin.starter.business.ui.views.masterdata.currency.Currencies.Currency;

import java.time.LocalDate;

/**
 * Dialog for displaying currency details.
 */
public class CurrencyDetails extends Dialog {

    private final Currency currency;

    private TextField isoCodeField;
    private TextField currencyNameField;
    private TextField symbolField;
    private TextField decimalPrecisionField;
    private ComboBox<String> isMajorField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the CurrencyDetails dialog.
     *
     * @param currency the currency to display details for
     */
    public CurrencyDetails(Currency currency) {
        this.currency = currency;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Currency Details: " + currency.getCurrencyName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Currency ID
        TextField currencyIdField = new TextField();
        currencyIdField.setValue(currency.getCurrencyId() != null ? currency.getCurrencyId().toString() : "");
        currencyIdField.setReadOnly(true);
        currencyIdField.setWidthFull();

        // ISO Code
        isoCodeField = new TextField();
        isoCodeField.setValue(currency.getIsoCode() != null ? currency.getIsoCode() : "");
        isoCodeField.setWidthFull();

        // Currency Name
        currencyNameField = new TextField();
        currencyNameField.setValue(currency.getCurrencyName() != null ? currency.getCurrencyName() : "");
        currencyNameField.setWidthFull();

        // Symbol
        symbolField = new TextField();
        symbolField.setValue(currency.getSymbol() != null ? currency.getSymbol() : "");
        symbolField.setWidthFull();

        // Decimal Precision
        decimalPrecisionField = new TextField();
        decimalPrecisionField.setValue(currency.getDecimalPrecision() != null ? currency.getDecimalPrecision().toString() : "");
        decimalPrecisionField.setWidthFull();

        // Is Major
        isMajorField = new ComboBox<>();
        isMajorField.setItems("Yes", "No");
        isMajorField.setValue(currency.getIsMajor() != null && currency.getIsMajor() ? "Yes" : "No");
        isMajorField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(currency.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(currency.getDateCreated() != null ? currency.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(currency.getDateUpdated() != null ? currency.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(currencyIdField, "Currency ID");
        form.addFormItem(isoCodeField, "ISO Code");
        form.addFormItem(currencyNameField, "Currency Name");
        form.addFormItem(symbolField, "Symbol");
        form.addFormItem(decimalPrecisionField, "Decimal Precision");
        form.addFormItem(isMajorField, "Is Major");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveCurrency();
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

    private void saveCurrency() {
        // In a real application, this would save the currency data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Currency details saved.");

        // Here you would update the currency with the new values
        // Example: currency.setCurrencyName(currencyNameField.getValue());
        // currencyService.updateCurrency(currency);
    }
}