package com.vaadin.starter.business.ui.views.distributor;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.Distributor;
import com.vaadin.starter.business.dummy.DummyData;
import com.vaadin.starter.business.dummy.Item;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.constants.NavigationConstants;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route(value = "distributor-details", layout = MainLayout.class)
@PageTitle("Distributor Details")
public class DistributorDetails extends SplitViewFrame implements HasUrlParameter<Long> {

    private Distributor distributor;
    private FormLayout detailsForm;
    private Grid<Item> itemsGrid;
    private ListDataProvider<Item> itemsDataProvider;
    private List<Item> distributorItems = new ArrayList<>();

    // Flag to track if the form is in edit mode
    private boolean editMode = false;

    // Form fields
    private ComboBox<Distributor.Status> statusField;
    private TextField contactPersonField;
    private TextField emailField;
    private TextField phoneField;
    private TextField addressField;
    private DatePicker contractDateField;
    private TextField commissionField;

    // Buttons
    private Button editButton;
    private Button saveButton;

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        // Load the distributor by ID
        distributor = DummyData.getDistributor(parameter);
        if (distributor == null) {
            // Handle case where distributor is not found
            UIUtils.showNotification("Distributor not found");
            return;
        }

        // Load distributor items
        distributorItems = new ArrayList<>(DummyData.getDistributorItems(distributor.getId()));

        // Initialize the view
        setViewContent(createContent());
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createDistributorDetails(), createItemsSection());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createDistributorDetails() {
        H3 sectionTitle = new H3("Distributor Information");
        sectionTitle.addClassName(LumoStyles.Heading.H3);

        // Create a form layout for horizontal arrangement
        detailsForm = new FormLayout();
        detailsForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("800px", 3,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));

        // Status field (ComboBox)
        statusField = new ComboBox<>("Status");
        statusField.setItems(Distributor.Status.values());
        statusField.setItemLabelGenerator(Distributor.Status::getName);
        statusField.setValue(distributor.getStatus());
        statusField.setWidth("100%");
        statusField.setId("status-field");
        statusField.setReadOnly(true);

        // Contact Person field (TextField)
        contactPersonField = new TextField("Contact Person");
        contactPersonField.setValue(distributor.getContactPerson());
        contactPersonField.setWidth("100%");
        contactPersonField.setId("contact-person-field");
        contactPersonField.setReadOnly(true);

        // Email field (TextField)
        emailField = new TextField("Email");
        emailField.setValue(distributor.getEmail());
        emailField.setWidth("100%");
        emailField.setId("email-field");
        emailField.setReadOnly(true);

        // Phone field (TextField)
        phoneField = new TextField("Phone");
        phoneField.setValue(distributor.getPhone());
        phoneField.setWidth("100%");
        phoneField.setId("phone-field");
        phoneField.setReadOnly(true);

        // Address field (TextField)
        addressField = new TextField("Address");
        addressField.setValue(distributor.getAddress());
        addressField.setWidth("100%");
        addressField.setId("address-field");
        addressField.setReadOnly(true);

        // Contract Date field (DatePicker)
        contractDateField = new DatePicker("Contract Date");
        contractDateField.setValue(distributor.getContractDate());
        contractDateField.setWidth("100%");
        contractDateField.setId("contract-date-field");
        contractDateField.setReadOnly(true);

        // Commission Rate field (TextField)
        commissionField = new TextField("Commission Rate (%)");
        commissionField.setValue(String.valueOf(distributor.getCommissionRate()));
        commissionField.setWidth("100%");
        commissionField.setId("commission-field");
        commissionField.setReadOnly(true);

        // Add all fields to the form layout
        detailsForm.add(statusField, contactPersonField, emailField, phoneField, addressField, contractDateField, commissionField);

        // Back button
        Button backButton = new Button("Back to List", e -> {
            getUI().ifPresent(ui -> ui.navigate("distributor-management"));
        });
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT));

        // Edit button
        editButton = new Button("Edit", e -> {
            enableEditMode();
        });
        editButton.setIcon(new Icon(VaadinIcon.EDIT));

        // Save button
        saveButton = new Button("Save Changes", e -> {
            saveChanges();
        });
        saveButton.getElement().getThemeList().add("primary");
        saveButton.setVisible(false); // Initially hidden

        HorizontalLayout buttonLayout = new HorizontalLayout(backButton, editButton, saveButton);
        buttonLayout.setSpacing(true);
        buttonLayout.setPadding(true);

        VerticalLayout layout = new VerticalLayout(sectionTitle, detailsForm, buttonLayout);
        layout.setPadding(false);
        layout.setSpacing(true);
        return layout;
    }

    private Component createItemsSection() {
        H3 sectionTitle = new H3("Distributor Items");
        sectionTitle.addClassName(LumoStyles.Heading.H3);

        // Create grid for items
        itemsGrid = new Grid<>();
        itemsDataProvider = DataProvider.ofCollection(distributorItems);
        itemsGrid.setDataProvider(itemsDataProvider);
        itemsGrid.setHeight("400px");

        // Add columns to the grid
        itemsGrid.addColumn(Item::getName)
                .setHeader("Name")
                .setWidth("200px")
                .setFlexGrow(1);

        itemsGrid.addColumn(Item::getDesc)
                .setHeader("Description")
                .setWidth("300px")
                .setFlexGrow(2);

        itemsGrid.addColumn(new ComponentRenderer<>(item -> {
            Item.Category category = item.getCategory();
            Span badge = new Span(category.getName());

            String theme = "badge primary";
            if (category == Item.Category.HEALTHCARE) {
                theme = "badge success";
            } else if (category == Item.Category.DENTAL) {
                theme = "badge";
            } else if (category == Item.Category.CONSTRUCTION) {
                theme = "badge error";
            }

            badge.getElement().getThemeList().add(theme);
            return badge;
        }))
                .setHeader("Category")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        itemsGrid.addColumn(Item::getVendor)
                .setHeader("Vendor")
                .setWidth("200px")
                .setFlexGrow(1);

        itemsGrid.addColumn(item -> "$" + String.format("%.2f", item.getPrice()))
                .setHeader("Price")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        itemsGrid.addColumn(Item::getStock)
                .setHeader("Stock")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        itemsGrid.addColumn(Item::getSold)
                .setHeader("Sold")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        // Add action column for removing items
        itemsGrid.addColumn(new ComponentRenderer<>(item -> {
            Button removeButton = new Button(new Icon(VaadinIcon.TRASH), e -> {
                distributorItems.remove(item);
                itemsDataProvider.refreshAll();
                UIUtils.showNotification("Item removed");
            });
            removeButton.addThemeVariants();
            return removeButton;
        }))
                .setHeader("Actions")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Add button
        Button addItemButton = new Button("Add Item", e -> showAddItemDialog());
        addItemButton.setIcon(new Icon(VaadinIcon.PLUS));
        addItemButton.getElement().getThemeList().add("primary");

        VerticalLayout layout = new VerticalLayout(sectionTitle, itemsGrid, addItemButton);
        layout.setPadding(false);
        layout.setSpacing(true);
        return layout;
    }

    /**
     * Sets the read-only state of all form fields
     * @param readOnly true to make fields read-only, false to make them editable
     */
    private void setFieldsReadOnly(boolean readOnly) {
        statusField.setReadOnly(readOnly);
        contactPersonField.setReadOnly(readOnly);
        emailField.setReadOnly(readOnly);
        phoneField.setReadOnly(readOnly);
        addressField.setReadOnly(readOnly);
        contractDateField.setReadOnly(readOnly);
        commissionField.setReadOnly(readOnly);
    }

    /**
     * Enables edit mode for the form
     */
    private void enableEditMode() {
        editMode = true;
        setFieldsReadOnly(false);

        // Update button visibility
        editButton.setVisible(false);
        saveButton.setVisible(true);
    }

    /**
     * Saves changes and disables edit mode
     */
    private void saveChanges() {
        // In a real application, you would update the distributor object with the form values
        // Since our Distributor class is immutable (no setters), we can't update it directly
        // For this demo, we'll just show a notification
        UIUtils.showNotification("Changes saved.");

        editMode = false;
        setFieldsReadOnly(true);

        // Update button visibility
        saveButton.setVisible(false);
        editButton.setVisible(true);
    }

    private void showAddItemDialog() {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(false);

        H3 dialogTitle = new H3("Add New Item");
        dialogTitle.getStyle().set("margin-top", "0");

        // Create form for adding a new item
        FormLayout form = new FormLayout();
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));

        // Fields for the new item
        ComboBox<Item.Category> categoryField = new ComboBox<>("Category");
        categoryField.setItems(Item.Category.values());
        categoryField.setItemLabelGenerator(Item.Category::getName);
        categoryField.setRequired(true);

        TextField nameField = new TextField("Name");
        nameField.setRequired(true);

        TextField descField = new TextField("Description");
        descField.setRequired(true);

        TextField vendorField = new TextField("Vendor");
        vendorField.setRequired(true);

        NumberField priceField = new NumberField("Price");
        priceField.setMin(0);
        priceField.setRequiredIndicatorVisible(true);

        NumberField stockField = new NumberField("Stock");
        stockField.setMin(0);
        stockField.setRequiredIndicatorVisible(true);

        form.add(categoryField, nameField, descField, vendorField, priceField, stockField);

        // Buttons
        Button cancelButton = new Button("Cancel", e -> dialog.close());

        Button addButton = new Button("Add", e -> {
            // Validate form
            if (categoryField.getValue() == null || nameField.getValue().isEmpty() || 
                descField.getValue().isEmpty() || vendorField.getValue().isEmpty() ||
                priceField.getValue() == null || stockField.getValue() == null) {
                Notification.show("Please fill all required fields");
                return;
            }

            // Create new item
            Item newItem = new Item(
                    categoryField.getValue(),
                    nameField.getValue(),
                    descField.getValue(),
                    vendorField.getValue(),
                    priceField.getValue(),
                    stockField.getValue().intValue(),
                    0 // New item, so sold = 0
            );

            // Add to list and refresh grid
            distributorItems.add(newItem);
            itemsDataProvider.refreshAll();

            // Close dialog
            dialog.close();
            UIUtils.showNotification("Item added");
        });
        addButton.getElement().getThemeList().add("primary");

        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton, addButton);
        buttonLayout.setSpacing(true);
        buttonLayout.getStyle().set("margin-top", "20px");

        VerticalLayout dialogLayout = new VerticalLayout(dialogTitle, form, buttonLayout);
        dialogLayout.setPadding(true);
        dialogLayout.setSpacing(true);

        dialog.add(dialogLayout);
        dialog.open();
    }
}
