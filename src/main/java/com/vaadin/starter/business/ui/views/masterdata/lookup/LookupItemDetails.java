package com.vaadin.starter.business.ui.views.masterdata.lookup;

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
import com.vaadin.starter.business.ui.views.masterdata.lookup.LookupItems.LookupItem;
import com.vaadin.starter.business.ui.views.masterdata.lookup.LookupItems.Status;

/**
 * Dialog for displaying lookup item details.
 */
public class LookupItemDetails extends Dialog {

    private final LookupItem item;

    private TextField itemCodeField;
    private TextField itemLabelField;
    private TextField itemDescField;
    private TextField domainIdField;
    private TextField parentItemIdField;
    private TextField sortOrderField;
    private DatePicker effectiveFromField;
    private DatePicker effectiveToField;
    private ComboBox<String> isCurrentField;
    private TextField extraJsonField;
    private TextField tenantIdField;
    private ComboBox<String> statusField;

    /**
     * Constructor for the LookupItemDetails dialog.
     *
     * @param item the lookup item to display details for
     */
    public LookupItemDetails(LookupItem item) {
        this.item = item;

        setWidth("800px");
        setHeight("auto");

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        H3 title = new H3("Lookup Item Details: " + (item.getItemLabelDefault() != null ? item.getItemLabelDefault() : "New Item"));
        content.add(title);

        content.add(createForm());
        content.add(createButtonLayout());

        add(content);
    }

    private FormLayout createForm() {
        // Item ID
        TextField itemIdField = new TextField();
        itemIdField.setValue(item.getItemId() != null ? item.getItemId().toString() : "");
        itemIdField.setReadOnly(true);
        itemIdField.setWidthFull();

        // Domain ID
        domainIdField = new TextField();
        domainIdField.setValue(item.getDomainId() != null ? item.getDomainId().toString() : "");
        domainIdField.setWidthFull();

        // Item Code
        itemCodeField = new TextField();
        itemCodeField.setValue(item.getItemCode() != null ? item.getItemCode() : "");
        itemCodeField.setWidthFull();

        // Item Label
        itemLabelField = new TextField();
        itemLabelField.setValue(item.getItemLabelDefault() != null ? item.getItemLabelDefault() : "");
        itemLabelField.setWidthFull();

        // Item Description
        itemDescField = new TextField();
        itemDescField.setValue(item.getItemDesc() != null ? item.getItemDesc() : "");
        itemDescField.setWidthFull();

        // Parent Item ID
        parentItemIdField = new TextField();
        parentItemIdField.setValue(item.getParentItemId() != null ? item.getParentItemId().toString() : "");
        parentItemIdField.setWidthFull();

        // Sort Order
        sortOrderField = new TextField();
        sortOrderField.setValue(item.getSortOrder() != null ? item.getSortOrder().toString() : "");
        sortOrderField.setWidthFull();

        // Effective From
        effectiveFromField = new DatePicker();
        effectiveFromField.setValue(item.getEffectiveFrom());
        effectiveFromField.setWidthFull();

        // Effective To
        effectiveToField = new DatePicker();
        effectiveToField.setValue(item.getEffectiveTo());
        effectiveToField.setWidthFull();

        // Is Current
        isCurrentField = new ComboBox<>();
        isCurrentField.setItems("Yes", "No");
        isCurrentField.setValue(item.getIsCurrent() != null && item.getIsCurrent() ? "Yes" : "No");
        isCurrentField.setWidthFull();

        // Extra JSON
        extraJsonField = new TextField();
        extraJsonField.setValue(item.getExtraJson() != null ? item.getExtraJson() : "");
        extraJsonField.setWidthFull();

        // Tenant ID
        tenantIdField = new TextField();
        tenantIdField.setValue(item.getTenantId() != null ? item.getTenantId().toString() : "");
        tenantIdField.setWidthFull();

        // Status
        statusField = new ComboBox<>();
        statusField.setItems("Active", "Inactive");
        statusField.setValue(item.isActive() ? "Active" : "Inactive");
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
        form.addFormItem(itemIdField, "Item ID");
        form.addFormItem(domainIdField, "Domain ID");
        form.addFormItem(itemCodeField, "Item Code");
        form.addFormItem(itemLabelField, "Item Label");
        form.addFormItem(itemDescField, "Description");
        form.addFormItem(parentItemIdField, "Parent Item ID");
        form.addFormItem(sortOrderField, "Sort Order");
        form.addFormItem(effectiveFromField, "Effective From");
        form.addFormItem(effectiveToField, "Effective To");
        form.addFormItem(isCurrentField, "Is Current");
        form.addFormItem(extraJsonField, "Extra JSON");
        form.addFormItem(tenantIdField, "Tenant ID");
        form.addFormItem(statusField, "Status");

        return form;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveItem();
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

    private void saveItem() {
        // In a real application, this would save the item data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("Lookup item details saved.");

        // Here you would update the item with the new values
        try {
            item.setDomainId(Long.parseLong(domainIdField.getValue()));
        } catch (NumberFormatException e) {
            // Handle invalid number format
        }
        item.setItemCode(itemCodeField.getValue());
        item.setItemLabelDefault(itemLabelField.getValue());
        item.setItemDesc(itemDescField.getValue());
        try {
            item.setParentItemId(parentItemIdField.getValue().isEmpty() ? null : Long.parseLong(parentItemIdField.getValue()));
        } catch (NumberFormatException e) {
            // Handle invalid number format
        }
        try {
            item.setSortOrder(sortOrderField.getValue().isEmpty() ? null : Integer.parseInt(sortOrderField.getValue()));
        } catch (NumberFormatException e) {
            // Handle invalid number format
        }
        item.setEffectiveFrom(effectiveFromField.getValue());
        item.setEffectiveTo(effectiveToField.getValue());
        item.setIsCurrent("Yes".equals(isCurrentField.getValue()));
        item.setExtraJson(extraJsonField.getValue());
        try {
            item.setTenantId(tenantIdField.getValue().isEmpty() ? null : Long.parseLong(tenantIdField.getValue()));
        } catch (NumberFormatException e) {
            // Handle invalid number format
        }
        item.setStatus("Active".equals(statusField.getValue()) ? Status.ACTIVE : Status.INACTIVE);
    }
}