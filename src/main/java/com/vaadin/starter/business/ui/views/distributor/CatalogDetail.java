package com.vaadin.starter.business.ui.views.distributor;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.Catalog;
import com.vaadin.starter.business.dummy.DummyData;
import com.vaadin.starter.business.dummy.Item;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.navigation.bar.AppBar;
import com.vaadin.starter.business.ui.layout.size.Bottom;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.BoxShadowBorders;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.TextColor;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.views.ViewFrame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@PageTitle("Catalog Detail")
@Route(value = "catalog-detail", layout = MainLayout.class)
public class CatalogDetail extends ViewFrame implements HasUrlParameter<Long> {

    private Catalog catalog;
    private List<Item> catalogItems = new ArrayList<>();
    private Grid<Item> itemsGrid;
    private ListDataProvider<Item> itemsDataProvider;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long catalogId) {
        if (catalogId == null) {
            setViewContent(createErrorView("Invalid catalog ID"));
            return;
        }

        // Load catalog data
        catalog = DummyData.getCatalog(catalogId);
        if (catalog == null) {
            setViewContent(createErrorView("Catalog not found"));
            return;
        }

        // Load catalog items (in a real app, this would come from a service)
        catalogItems = new ArrayList<>(DummyData.getDistributorItems(catalog.getDistributorId()));

        // Set up the view content
        setViewContent(createContent());
        initAppBar();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(
                createCatalogInfoSection(),
                createItemsSection()
        );
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        content.setMargin(Horizontal.AUTO, Vertical.RESPONSIVE_L);
        content.setMaxWidth("1200px");
        return content;
    }

    private Component createCatalogInfoSection() {
        // Create catalog info header
        H3 catalogName = new H3(catalog.getName());
        catalogName.addClassName(LumoStyles.Heading.H2);

        // Create edit button
        Button editButton = UIUtils.createSuccessButton("Edit Catalog");
        editButton.addClickListener(e -> UIUtils.showNotification("Edit functionality not implemented"));

        // Create a container for catalog name and edit button
        FlexBoxLayout nameAndEdit = new FlexBoxLayout(catalogName, editButton);
        nameAndEdit.setFlexDirection(FlexLayout.FlexDirection.ROW);
        nameAndEdit.setAlignItems(FlexComponent.Alignment.CENTER);
        nameAndEdit.getStyle().set("gap", "16px");

        // Create catalog info items
        ListItem description = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CLIPBOARD_TEXT),
                "Description", catalog.getDescription());
        description.getContent().addClassName(LumoStyles.Margin.Vertical.S);
        description.setWidth("300px");

        ListItem type = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.TAGS),
                "Type", catalog.getType().getName());
        type.getContent().addClassName(LumoStyles.Margin.Vertical.S);
        type.setWidth("200px");

        ListItem distributor = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.BUILDING),
                "Distributor", catalog.getDistributorName());
        distributor.getContent().addClassName(LumoStyles.Margin.Vertical.S);
        distributor.setWidth("200px");

        ListItem startDate = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CALENDAR_O),
                "Start Date", UIUtils.formatDate(catalog.getStartDate()));
        startDate.getContent().addClassName(LumoStyles.Margin.Vertical.S);
        startDate.setWidth("200px");

        ListItem endDate = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CALENDAR),
                "End Date", UIUtils.formatDate(catalog.getEndDate()));
        endDate.getContent().addClassName(LumoStyles.Margin.Vertical.S);
        endDate.setWidth("200px");

        ListItem items = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.LIST),
                "Items", String.valueOf(catalog.getItemCount()));
        items.getContent().addClassName(LumoStyles.Margin.Vertical.S);
        items.setWidth("150px");

        ListItem status = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CHECK),
                "Status", catalog.isActive() ? "Active" : "Inactive");
        status.getContent().addClassName(LumoStyles.Margin.Vertical.S);
        status.setWidth("150px");

        // Create a container for catalog info items in a horizontal grid layout
        FlexBoxLayout infoItems = new FlexBoxLayout(
                description, type, distributor, startDate, endDate, items, status);
        infoItems.setFlexDirection(FlexLayout.FlexDirection.ROW);
        infoItems.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        infoItems.setPadding(Horizontal.L, Vertical.M);
        infoItems.getStyle().set("gap", "var(--lumo-space-m)");
        infoItems.getStyle().set("justify-content", "space-between");

        // Create the main section layout with a card-like appearance
        FlexBoxLayout section = new FlexBoxLayout(nameAndEdit, infoItems);
        section.addClassName(BoxShadowBorders.BOTTOM);
        section.setAlignItems(FlexComponent.Alignment.START);
        section.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        section.setPadding(Vertical.L, Horizontal.L);
        section.getStyle().set("background-color", "var(--lumo-base-color)");
        section.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
        section.getStyle().set("margin-bottom", "var(--lumo-space-m)");
        return section;
    }

    private Component createItemsSection() {
        // Create items section header
        H3 sectionTitle = new H3("Catalog Items");
        sectionTitle.addClassName(LumoStyles.Heading.H3);

        // Create add item button
        Button addItemButton = UIUtils.createPrimaryButton("Add Item");
        addItemButton.setIcon(VaadinIcon.PLUS.create());
        addItemButton.addClickListener(e -> openAddItemDialog());

        // Create header layout
        HorizontalLayout headerLayout = new HorizontalLayout(sectionTitle, addItemButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        headerLayout.setPadding(true);

        // Create items grid
        itemsGrid = new Grid<>();
        itemsDataProvider = DataProvider.ofCollection(catalogItems);
        itemsGrid.setDataProvider(itemsDataProvider);
        itemsGrid.setHeight("500px");

        itemsGrid.addColumn(Item::getName)
                .setHeader("Name")
                .setWidth("200px")
                .setFlexGrow(1);

        itemsGrid.addColumn(item -> item.getCategory().getName())
                .setHeader("Category")
                .setWidth("120px")
                .setFlexGrow(0);

        itemsGrid.addColumn(Item::getVendor)
                .setHeader("Vendor")
                .setWidth("150px")
                .setFlexGrow(1);

        itemsGrid.addColumn(item -> UIUtils.formatAmount(item.getPrice()))
                .setHeader("Price")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        itemsGrid.addColumn(Item::getStock)
                .setHeader("Stock")
                .setWidth("80px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        itemsGrid.addColumn(new ComponentRenderer<>(item -> {
            boolean inStock = item.getStock() > 0;
            Span badge = new Span(inStock ? "In Stock" : "Out of Stock");

            String theme = inStock ? "badge success" : "badge error";
            badge.getElement().getThemeList().add(theme);
            return badge;
        }))
                .setHeader("Status")
                .setWidth("120px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Add action column with remove button
        itemsGrid.addColumn(new ComponentRenderer<>(item -> {
            Button removeButton = UIUtils.createErrorButton("Remove");
            removeButton.addClickListener(e -> removeItemFromCatalog(item));
            return removeButton;
        }))
                .setHeader("Actions")
                .setWidth("120px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Create the main section layout with a card-like appearance
        FlexBoxLayout section = new FlexBoxLayout(headerLayout, itemsGrid);
        section.addClassName(BoxShadowBorders.BOTTOM);
        section.setAlignItems(FlexComponent.Alignment.START);
        section.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        section.setPadding(Bottom.L);
        section.getStyle().set("background-color", "var(--lumo-base-color)");
        section.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
        return section;
    }

    private void openAddItemDialog() {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");

        H3 title = new H3("Add Item to Catalog");

        // In a real application, you would have a form to select items
        // For this example, we'll just show a grid of available items

        Grid<Item> availableItemsGrid = new Grid<>();
        Collection<Item> allItems = DummyData.getItems();

        // Filter out items already in the catalog
        List<Item> availableItems = new ArrayList<>();
        for (Item item : allItems) {
            if (!catalogItems.contains(item)) {
                availableItems.add(item);
            }
        }

        availableItemsGrid.setItems(availableItems);
        availableItemsGrid.setHeight("300px");

        availableItemsGrid.addColumn(Item::getName)
                .setHeader("Name")
                .setFlexGrow(1);

        availableItemsGrid.addColumn(item -> item.getCategory().getName())
                .setHeader("Category");

        availableItemsGrid.addColumn(item -> UIUtils.formatAmount(item.getPrice()))
                .setHeader("Price")
                .setTextAlign(ColumnTextAlign.END);

        Button addButton = UIUtils.createPrimaryButton("Add Selected");
        addButton.setEnabled(false);

        availableItemsGrid.addSelectionListener(event -> {
            addButton.setEnabled(event.getFirstSelectedItem().isPresent());
        });

        addButton.addClickListener(event -> {
            availableItemsGrid.getSelectedItems().forEach(item -> {
                catalogItems.add(item);
            });
            itemsDataProvider.refreshAll();
            dialog.close();
            UIUtils.showNotification("Item(s) added to catalog");
        });

        Button cancelButton = UIUtils.createTertiaryButton("Cancel");
        cancelButton.addClickListener(e -> dialog.close());

        HorizontalLayout buttons = new HorizontalLayout(cancelButton, addButton);
        buttons.setWidthFull();
        buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        dialog.add(
                title,
                availableItemsGrid,
                buttons
        );

        dialog.open();
    }

    private void removeItemFromCatalog(Item item) {
        Dialog confirmDialog = new Dialog();
        confirmDialog.setWidth("400px");

        H3 title = new H3("Confirm Removal");
        Span message = new Span("Are you sure you want to remove this item from the catalog?");

        Button confirmButton = UIUtils.createErrorButton("Remove");
        confirmButton.addClickListener(e -> {
            catalogItems.remove(item);
            itemsDataProvider.refreshAll();
            confirmDialog.close();
            UIUtils.showNotification("Item removed from catalog");
        });

        Button cancelButton = UIUtils.createTertiaryButton("Cancel");
        cancelButton.addClickListener(e -> confirmDialog.close());

        HorizontalLayout buttons = new HorizontalLayout(cancelButton, confirmButton);
        buttons.setWidthFull();
        buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        confirmDialog.add(
                title,
                message,
                buttons
        );

        confirmDialog.open();
    }

    private Component createErrorView(String errorMessage) {
        FlexBoxLayout errorLayout = new FlexBoxLayout();
        errorLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        errorLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        errorLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        errorLayout.setHeightFull();
        errorLayout.setWidthFull();

        H3 errorTitle = new H3("Error");
        Span errorText = new Span(errorMessage);
        UIUtils.setTextColor(TextColor.ERROR, errorText);

        Button backButton = UIUtils.createTertiaryButton("Back to Catalogs");
        backButton.addClickListener(e -> UI.getCurrent().navigate(Catalogs.class));

        errorLayout.add(errorTitle, errorText, backButton);
        return errorLayout;
    }

    private AppBar initAppBar() {
        AppBar appBar = MainLayout.get().getAppBar();
        appBar.setNaviMode(AppBar.NaviMode.CONTEXTUAL);
        appBar.getContextIcon().addClickListener(e -> UI.getCurrent().navigate(Catalogs.class));
        appBar.setTitle(catalog.getName());
        return appBar;
    }
}
