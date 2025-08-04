package com.vaadin.starter.business.ui.views.masterdata.identity;

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
import com.vaadin.starter.business.ui.views.masterdata.identity.IdentityDocumentCategories.IdentityDocumentCategory;

import java.time.LocalDate;

/**
 * Dialog for displaying identity document category details.
 */
public class IdentityDocumentCategoryDetails extends Dialog {

    private final IdentityDocumentCategory category;

    private TextField categoryIdField;
    private TextField categoryCodeField;
    private TextField categoryNameField;
    private TextArea descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the IdentityDocumentCategoryDetails dialog.
     *
     * @param category the identity document category to display details for
     */
    public IdentityDocumentCategoryDetails(IdentityDocumentCategory category) {
        this.category = category;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Identity Document Category Details: " + category.getCategoryName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Category ID
        categoryIdField = new TextField();
        categoryIdField.setValue(category.getCategoryId() != null ? category.getCategoryId().toString() : "");
        categoryIdField.setReadOnly(true);
        categoryIdField.setWidthFull();

        // Category Code
        categoryCodeField = new TextField();
        categoryCodeField.setValue(category.getCategoryCode() != null ? category.getCategoryCode() : "");
        categoryCodeField.setWidthFull();

        // Category Name
        categoryNameField = new TextField();
        categoryNameField.setValue(category.getCategoryName() != null ? category.getCategoryName() : "");
        categoryNameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(category.getDescription() != null ? category.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(category.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(category.getDateCreated() != null ? category.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(category.getDateUpdated() != null ? category.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(categoryIdField, "Category ID");
        form.addFormItem(categoryCodeField, "Category Code");
        form.addFormItem(categoryNameField, "Category Name");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveCategory();
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

    private void saveCategory() {
        // In a real application, this would save the category data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Identity document category details saved.");

        // Here you would update the category with the new values
        // Example: category.setCategoryName(categoryNameField.getValue());
        // categoryService.updateCategory(category);
    }
}