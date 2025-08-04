package com.vaadin.starter.business.ui.views.masterdata.language;

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
import com.vaadin.starter.business.ui.views.masterdata.language.LanguageLocales.LanguageLocale;

import java.time.LocalDate;

/**
 * Dialog for displaying language locale details.
 */
public class LanguageLocaleDetails extends Dialog {

    private final LanguageLocale locale;

    private TextField languageCodeField;
    private TextField countryCodeField;
    private TextField localeCodeField;
    private TextField languageNameField;
    private TextField nativeNameField;
    private TextField regionNameField;
    private ComboBox<String> rtlField;
    private TextField sortOrderField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the LanguageLocaleDetails dialog.
     *
     * @param locale the language locale to display details for
     */
    public LanguageLocaleDetails(LanguageLocale locale) {
        this.locale = locale;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Language Locale Details: " + locale.getLanguageName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Locale ID
        TextField localeIdField = new TextField();
        localeIdField.setValue(locale.getLocaleId() != null ? locale.getLocaleId().toString() : "");
        localeIdField.setReadOnly(true);
        localeIdField.setWidthFull();

        // Language Code
        languageCodeField = new TextField();
        languageCodeField.setValue(locale.getLanguageCode() != null ? locale.getLanguageCode() : "");
        languageCodeField.setWidthFull();

        // Country Code
        countryCodeField = new TextField();
        countryCodeField.setValue(locale.getCountryCode() != null ? locale.getCountryCode() : "");
        countryCodeField.setWidthFull();

        // Locale Code
        localeCodeField = new TextField();
        localeCodeField.setValue(locale.getLocaleCode() != null ? locale.getLocaleCode() : "");
        localeCodeField.setWidthFull();

        // Language Name
        languageNameField = new TextField();
        languageNameField.setValue(locale.getLanguageName() != null ? locale.getLanguageName() : "");
        languageNameField.setWidthFull();

        // Native Name
        nativeNameField = new TextField();
        nativeNameField.setValue(locale.getNativeName() != null ? locale.getNativeName() : "");
        nativeNameField.setWidthFull();

        // Region Name
        regionNameField = new TextField();
        regionNameField.setValue(locale.getRegionName() != null ? locale.getRegionName() : "");
        regionNameField.setWidthFull();

        // RTL
        rtlField = new ComboBox<>();
        rtlField.setItems("Yes", "No");
        rtlField.setValue(locale.getRtl() != null && locale.getRtl() ? "Yes" : "No");
        rtlField.setWidthFull();

        // Sort Order
        sortOrderField = new TextField();
        sortOrderField.setValue(locale.getSortOrder() != null ? locale.getSortOrder().toString() : "");
        sortOrderField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(locale.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(locale.getDateCreated() != null ? locale.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(locale.getDateUpdated() != null ? locale.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(localeIdField, "Locale ID");
        form.addFormItem(languageCodeField, "Language Code");
        form.addFormItem(countryCodeField, "Country Code");
        form.addFormItem(localeCodeField, "Locale Code");
        form.addFormItem(languageNameField, "Language Name");
        form.addFormItem(nativeNameField, "Native Name");
        form.addFormItem(regionNameField, "Region Name");
        form.addFormItem(rtlField, "RTL");
        form.addFormItem(sortOrderField, "Sort Order");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveLocale();
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

    private void saveLocale() {
        // In a real application, this would save the locale data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Language locale details saved.");

        // Here you would update the locale with the new values
        // Example: locale.setLanguageName(languageNameField.getValue());
        // localeService.updateLocale(locale);
    }
}