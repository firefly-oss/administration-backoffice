package com.vaadin.starter.business.ui.views.masterdata.document;

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
import com.vaadin.starter.business.ui.views.masterdata.document.DocumentTemplates.DocumentTemplate;

import java.time.LocalDate;

/**
 * Dialog for displaying document template details.
 */
public class DocumentTemplateDetails extends Dialog {

    private final DocumentTemplate template;

    private TextField templateIdField;
    private TextField templateCodeField;
    private TextField templateNameField;
    private TextField categoryField;
    private TextField typeIdField;
    private TextField templateTypeNameField;
    private TextArea descriptionField;
    private TextArea templateContentField;
    private TextField versionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the DocumentTemplateDetails dialog.
     *
     * @param template the document template to display details for
     */
    public DocumentTemplateDetails(DocumentTemplate template) {
        this.template = template;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Document Template Details: " + template.getTemplateName());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Template ID
        templateIdField = new TextField();
        templateIdField.setValue(template.getTemplateId() != null ? template.getTemplateId().toString() : "");
        templateIdField.setReadOnly(true);
        templateIdField.setWidthFull();

        // Template Code
        templateCodeField = new TextField();
        templateCodeField.setValue(template.getTemplateCode() != null ? template.getTemplateCode() : "");
        templateCodeField.setWidthFull();

        // Template Name
        templateNameField = new TextField();
        templateNameField.setValue(template.getTemplateName() != null ? template.getTemplateName() : "");
        templateNameField.setWidthFull();

        // Category
        categoryField = new TextField();
        categoryField.setValue(template.getCategory() != null ? template.getCategory() : "");
        categoryField.setWidthFull();

        // Type ID
        typeIdField = new TextField();
        typeIdField.setValue(template.getTypeId() != null ? template.getTypeId().toString() : "");
        typeIdField.setReadOnly(true);
        typeIdField.setWidthFull();

        // Template Type Name
        templateTypeNameField = new TextField();
        templateTypeNameField.setValue(template.getTemplateTypeName() != null ? template.getTemplateTypeName() : "");
        templateTypeNameField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(template.getDescription() != null ? template.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Template Content
        templateContentField = new TextArea();
        templateContentField.setValue(template.getTemplateContent() != null ? template.getTemplateContent() : "");
        templateContentField.setWidthFull();
        templateContentField.setHeight("200px");

        // Version
        versionField = new TextField();
        versionField.setValue(template.getVersion() != null ? template.getVersion() : "");
        versionField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(template.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(template.getDateCreated() != null ? template.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(template.getDateUpdated() != null ? template.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(templateIdField, "Template ID");
        form.addFormItem(templateCodeField, "Template Code");
        form.addFormItem(templateNameField, "Template Name");
        form.addFormItem(categoryField, "Category");
        form.addFormItem(typeIdField, "Type ID");
        form.addFormItem(templateTypeNameField, "Template Type");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(templateContentField, "Template Content");
        form.addFormItem(versionField, "Version");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveTemplate();
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

    private void saveTemplate() {
        // In a real application, this would save the template data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Document template details saved.");

        // Here you would update the template with the new values
        // Example: template.setTemplateName(templateNameField.getValue());
        // templateService.updateTemplate(template);
    }
}