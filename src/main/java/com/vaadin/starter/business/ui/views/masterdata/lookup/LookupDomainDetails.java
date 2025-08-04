package com.vaadin.starter.business.ui.views.masterdata.lookup;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.views.masterdata.lookup.LookupDomains.LookupDomain;
import com.vaadin.starter.business.ui.views.masterdata.lookup.LookupDomains.Status;

/**
 * Dialog for displaying lookup domain details.
 */
public class LookupDomainDetails extends Dialog {

    private final LookupDomain domain;

    private TextField domainCodeField;
    private TextField domainNameField;
    private TextField domainDescField;
    private ComboBox<String> hierarchyAllowedField;
    private ComboBox<String> multiselectAllowedField;
    private ComboBox<String> tenantOverridableField;
    private ComboBox<String> statusField;

    /**
     * Constructor for the LookupDomainDetails dialog.
     *
     * @param domain the lookup domain to display details for
     */
    public LookupDomainDetails(LookupDomain domain) {
        this.domain = domain;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Lookup Domain Details: " + (domain.getDomainName() != null ? domain.getDomainName() : "New Domain"));
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Domain ID
        TextField domainIdField = new TextField();
        domainIdField.setValue(domain.getDomainId() != null ? domain.getDomainId().toString() : "");
        domainIdField.setReadOnly(true);
        domainIdField.setWidthFull();

        // Domain Code
        domainCodeField = new TextField();
        domainCodeField.setValue(domain.getDomainCode() != null ? domain.getDomainCode() : "");
        domainCodeField.setWidthFull();

        // Domain Name
        domainNameField = new TextField();
        domainNameField.setValue(domain.getDomainName() != null ? domain.getDomainName() : "");
        domainNameField.setWidthFull();

        // Domain Description
        domainDescField = new TextField();
        domainDescField.setValue(domain.getDomainDesc() != null ? domain.getDomainDesc() : "");
        domainDescField.setWidthFull();

        // Parent Domain ID
        TextField parentDomainIdField = new TextField();
        parentDomainIdField.setValue(domain.getParentDomainId() != null ? domain.getParentDomainId().toString() : "");
        parentDomainIdField.setWidthFull();

        // Hierarchy Allowed
        hierarchyAllowedField = new ComboBox<>();
        hierarchyAllowedField.setItems("Yes", "No");
        hierarchyAllowedField.setValue(domain.getHierarchyAllowed() != null && domain.getHierarchyAllowed() ? "Yes" : "No");
        hierarchyAllowedField.setWidthFull();

        // Multiselect Allowed
        multiselectAllowedField = new ComboBox<>();
        multiselectAllowedField.setItems("Yes", "No");
        multiselectAllowedField.setValue(domain.getMultiselectAllowed() != null && domain.getMultiselectAllowed() ? "Yes" : "No");
        multiselectAllowedField.setWidthFull();

        // Tenant Overridable
        tenantOverridableField = new ComboBox<>();
        tenantOverridableField.setItems("Yes", "No");
        tenantOverridableField.setValue(domain.getTenantOverridable() != null && domain.getTenantOverridable() ? "Yes" : "No");
        tenantOverridableField.setWidthFull();

        // Extra JSON
        TextField extraJsonField = new TextField();
        extraJsonField.setValue(domain.getExtraJson() != null ? domain.getExtraJson() : "");
        extraJsonField.setWidthFull();

        // Tenant ID
        TextField tenantIdField = new TextField();
        tenantIdField.setValue(domain.getTenantId() != null ? domain.getTenantId().toString() : "");
        tenantIdField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(domain.isActive() ? "Active" : "Inactive");
        statusField.setWidthFull();

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(domainIdField, "Domain ID");
        form.addFormItem(domainCodeField, "Domain Code");
        form.addFormItem(domainNameField, "Domain Name");
        form.addFormItem(domainDescField, "Description");
        form.addFormItem(parentDomainIdField, "Parent Domain ID");
        form.addFormItem(hierarchyAllowedField, "Hierarchy Allowed");
        form.addFormItem(multiselectAllowedField, "Multiselect Allowed");
        form.addFormItem(tenantOverridableField, "Tenant Overridable");
        form.addFormItem(extraJsonField, "Extra JSON");
        form.addFormItem(tenantIdField, "Tenant ID");
        form.addFormItem(statusField, "Status");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveDomain();
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

    private void saveDomain() {
        // In a real application, this would save the domain data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Lookup domain details saved.");

        // Here you would update the domain with the new values
        domain.setDomainCode(domainCodeField.getValue());
        domain.setDomainName(domainNameField.getValue());
        domain.setDomainDesc(domainDescField.getValue());
        domain.setHierarchyAllowed("Yes".equals(hierarchyAllowedField.getValue()));
        domain.setMultiselectAllowed("Yes".equals(multiselectAllowedField.getValue()));
        domain.setTenantOverridable("Yes".equals(tenantOverridableField.getValue()));
        domain.setStatus("Active".equals(statusField.getValue()) ? Status.ACTIVE : Status.INACTIVE);
    }
}