package com.vaadin.starter.business.ui.views.masterdata.title;

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
import com.vaadin.starter.business.ui.views.masterdata.title.Titles.Title;

import java.time.LocalDate;

/**
 * Dialog for displaying title details.
 */
public class TitleDetails extends Dialog {

    private final Title title;

    private TextField prefixField;
    private TextArea descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the TitleDetails dialog.
     *
     * @param title the title to display details for
     */
    public TitleDetails(Title title) {
        this.title = title;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 titleHeader = new H3("Title Details: " + title.getPrefix());
        content.add(titleHeader);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Title ID
        TextField titleIdField = new TextField();
        titleIdField.setValue(title.getTitleId() != null ? title.getTitleId().toString() : "");
        titleIdField.setReadOnly(true);
        titleIdField.setWidthFull();

        // Prefix
        prefixField = new TextField();
        prefixField.setValue(title.getPrefix() != null ? title.getPrefix() : "");
        prefixField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(title.getDescription() != null ? title.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(title.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(title.getDateCreated() != null ? title.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(title.getDateUpdated() != null ? title.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(titleIdField, "Title ID");
        form.addFormItem(prefixField, "Prefix");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveTitle();
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

    private void saveTitle() {
        // In a real application, this would save the title data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Title details saved.");

        // Here you would update the title with the new values
        // Example: title.setDescription(descriptionField.getValue());
        // titleService.updateTitle(title);
    }
}