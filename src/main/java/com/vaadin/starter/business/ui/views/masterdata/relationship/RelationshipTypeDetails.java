package com.vaadin.starter.business.ui.views.masterdata.relationship;

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
import com.vaadin.starter.business.ui.views.masterdata.relationship.RelationshipTypes.RelationshipType;

import java.time.LocalDate;

/**
 * Dialog for displaying relationship type details.
 */
public class RelationshipTypeDetails extends Dialog {

    private final RelationshipType relationshipType;

    private TextField codeField;
    private TextArea descriptionField;
    private ComboBox<String> statusField;
    private DatePicker creationDateField;
    private DatePicker updateDateField;

    /**
     * Constructor for the RelationshipTypeDetails dialog.
     *
     * @param relationshipType the relationship type to display details for
     */
    public RelationshipTypeDetails(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Relationship Type Details: " + relationshipType.getCode());
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Relationship Type ID
        TextField relationshipTypeIdField = new TextField();
        relationshipTypeIdField.setValue(relationshipType.getRelationshipTypeId() != null ? relationshipType.getRelationshipTypeId().toString() : "");
        relationshipTypeIdField.setReadOnly(true);
        relationshipTypeIdField.setWidthFull();

        // Code
        codeField = new TextField();
        codeField.setValue(relationshipType.getCode() != null ? relationshipType.getCode() : "");
        codeField.setWidthFull();

        // Description
        descriptionField = new TextArea();
        descriptionField.setValue(relationshipType.getDescription() != null ? relationshipType.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("100px");

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(relationshipType.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Creation Date
        creationDateField = new DatePicker();
        creationDateField.setValue(relationshipType.getDateCreated() != null ? relationshipType.getDateCreated().toLocalDate() : LocalDate.now());
        creationDateField.setReadOnly(true);
        creationDateField.setWidthFull();

        // Update Date
        updateDateField = new DatePicker();
        updateDateField.setValue(relationshipType.getDateUpdated() != null ? relationshipType.getDateUpdated().toLocalDate() : LocalDate.now());
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
        form.addFormItem(relationshipTypeIdField, "Relationship Type ID");
        form.addFormItem(codeField, "Code");
        form.addFormItem(descriptionField, "Description");
        form.addFormItem(statusField, "Status");
        form.addFormItem(creationDateField, "Creation Date");
        form.addFormItem(updateDateField, "Update Date");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveRelationshipType();
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

    private void saveRelationshipType() {
        // In a real application, this would save the relationship type data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Relationship type details saved.");

        // Here you would update the relationship type with the new values
        // Example: relationshipType.setDescription(descriptionField.getValue());
        // relationshipTypeService.updateRelationshipType(relationshipType);
    }
}