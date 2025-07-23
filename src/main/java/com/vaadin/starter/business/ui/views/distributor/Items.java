package com.vaadin.starter.business.ui.views.distributor;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.Item;
import com.vaadin.starter.business.dummy.DummyData;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawer;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerFooter;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerHeader;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

@Route(value = "distributor-items", layout = MainLayout.class)
@PageTitle("Items")
public class Items extends SplitViewFrame {

    private Grid<Item> grid;
    private ListDataProvider<Item> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Search form fields
    private TextField nameFilter;
    private ComboBox<Item.Category> categoryFilter;
    private TextField vendorFilter;
    private TextField priceMinFilter;
    private TextField priceMaxFilter;
    private TextField stockFilter;

    public Items() {
        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);

        // Initialize with default filter
        filter();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createSearchForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createSearchForm() {
        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addClassName(LumoStyles.Padding.Bottom.M);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 3,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));

        // Initialize filter fields
        nameFilter = new TextField();
        nameFilter.setPlaceholder("Name");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.addValueChangeListener(e -> applyFilter());

        categoryFilter = new ComboBox<>();
        categoryFilter.setPlaceholder("Category");
        categoryFilter.setItems(Item.Category.values());
        categoryFilter.setItemLabelGenerator(Item.Category::getName);
        categoryFilter.setClearButtonVisible(true);
        categoryFilter.addValueChangeListener(e -> applyFilter());

        vendorFilter = new TextField();
        vendorFilter.setPlaceholder("Vendor");
        vendorFilter.setClearButtonVisible(true);
        vendorFilter.setValueChangeMode(ValueChangeMode.EAGER);
        vendorFilter.addValueChangeListener(e -> applyFilter());

        priceMinFilter = new TextField();
        priceMinFilter.setPlaceholder("Min Price");
        priceMinFilter.setClearButtonVisible(true);
        priceMinFilter.setValueChangeMode(ValueChangeMode.EAGER);
        priceMinFilter.addValueChangeListener(e -> applyFilter());

        priceMaxFilter = new TextField();
        priceMaxFilter.setPlaceholder("Max Price");
        priceMaxFilter.setClearButtonVisible(true);
        priceMaxFilter.setValueChangeMode(ValueChangeMode.EAGER);
        priceMaxFilter.addValueChangeListener(e -> applyFilter());

        stockFilter = new TextField();
        stockFilter.setPlaceholder("Min Stock");
        stockFilter.setClearButtonVisible(true);
        stockFilter.setValueChangeMode(ValueChangeMode.EAGER);
        stockFilter.addValueChangeListener(e -> applyFilter());

        // Add fields to form
        formLayout.add(nameFilter, categoryFilter, vendorFilter, priceMinFilter, priceMaxFilter, stockFilter);

        // Create a button layout
        Button resetBtn = new Button("Reset");
        resetBtn.addClickListener(e -> resetFilter());
        
        Button searchBtn = new Button("Search");
        searchBtn.addClickListener(e -> applyFilter());
        searchBtn.getElement().getThemeList().add("primary");
        
        HorizontalLayout buttonLayout = new HorizontalLayout(resetBtn, searchBtn);
        buttonLayout.setSpacing(true);
        
        Div wrapper = new Div(formLayout, buttonLayout);
        wrapper.addClassName(LumoStyles.Padding.Bottom.L);
        return wrapper;
    }

    private Component createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem()
                .ifPresent(this::showDetails));
        dataProvider = DataProvider.ofCollection(DummyData.getItems());
        grid.setDataProvider(dataProvider);
        grid.setHeight("100%");

        grid.addColumn(Item::getName)
                .setHeader("Name")
                .setWidth("200px")
                .setFlexGrow(1);
        
        grid.addColumn(item -> item.getCategory().getName())
                .setHeader("Category")
                .setWidth("120px")
                .setFlexGrow(0);
        
        grid.addColumn(Item::getVendor)
                .setHeader("Vendor")
                .setWidth("150px")
                .setFlexGrow(1);
        
        grid.addColumn(item -> UIUtils.formatAmount(item.getPrice()))
                .setHeader("Price")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);
        
        grid.addColumn(Item::getStock)
                .setHeader("Stock")
                .setWidth("80px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);
        
        grid.addColumn(Item::getSold)
                .setHeader("Sold")
                .setWidth("80px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);
        
        grid.addColumn(new ComponentRenderer<>(item -> {
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

        return grid;
    }

    private DetailsDrawer createDetailsDrawer() {
        detailsDrawer = new DetailsDrawer(DetailsDrawer.Position.BOTTOM);

        // Header
        detailsDrawerHeader = new DetailsDrawerHeader("");
        detailsDrawerHeader.addCloseListener(buttonClickEvent -> detailsDrawer.hide());
        detailsDrawer.setHeader(detailsDrawerHeader);

        // Footer
        DetailsDrawerFooter footer = new DetailsDrawerFooter();
        footer.addSaveListener(e -> {
            detailsDrawer.hide();
            UIUtils.showNotification("Changes saved.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Item item) {
        detailsDrawerHeader.setTitle(item.getName());
        detailsDrawer.setContent(createDetails(item));
        detailsDrawer.show();
    }

    private Component createDetails(Item item) {
        ListItem name = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.PACKAGE),
                "Name", item.getName());
        name.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem description = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CLIPBOARD_TEXT),
                "Description", item.getDesc());
        description.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem category = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.TAGS),
                "Category", item.getCategory().getName());
        category.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem vendor = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.BUILDING),
                "Vendor", item.getVendor());
        vendor.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem price = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.MONEY),
                "Price", UIUtils.formatAmount(item.getPrice()));
        price.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem stock = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.STORAGE),
                "Stock", String.valueOf(item.getStock()));
        stock.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem sold = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CART),
                "Sold", String.valueOf(item.getSold()));
        sold.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem status = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CHECK),
                "Status", item.getStock() > 0 ? "In Stock" : "Out of Stock");
        status.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        FlexBoxLayout content = new FlexBoxLayout(name, description, category, vendor, price, stock, sold, status);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        content.setPadding(Horizontal.RESPONSIVE_L, Vertical.RESPONSIVE_M);
        return content;
    }

    private void filter() {
        dataProvider.clearFilters();
        
        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            dataProvider.addFilter(item -> 
                    item.getName().toLowerCase().contains(nameFilter.getValue().toLowerCase()));
        }
        
        if (categoryFilter.getValue() != null) {
            dataProvider.addFilter(item -> 
                    item.getCategory().equals(categoryFilter.getValue()));
        }
        
        if (vendorFilter.getValue() != null && !vendorFilter.getValue().isEmpty()) {
            dataProvider.addFilter(item -> 
                    item.getVendor().toLowerCase().contains(vendorFilter.getValue().toLowerCase()));
        }
        
        if (priceMinFilter.getValue() != null && !priceMinFilter.getValue().isEmpty()) {
            try {
                double minPrice = Double.parseDouble(priceMinFilter.getValue());
                dataProvider.addFilter(item -> item.getPrice() >= minPrice);
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }
        
        if (priceMaxFilter.getValue() != null && !priceMaxFilter.getValue().isEmpty()) {
            try {
                double maxPrice = Double.parseDouble(priceMaxFilter.getValue());
                dataProvider.addFilter(item -> item.getPrice() <= maxPrice);
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }
        
        if (stockFilter.getValue() != null && !stockFilter.getValue().isEmpty()) {
            try {
                int minStock = Integer.parseInt(stockFilter.getValue());
                dataProvider.addFilter(item -> item.getStock() >= minStock);
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }
    }

    private void resetFilter() {
        nameFilter.clear();
        categoryFilter.clear();
        vendorFilter.clear();
        priceMinFilter.clear();
        priceMaxFilter.clear();
        stockFilter.clear();
        filter();
    }

    private void applyFilter() {
        filter();
    }
}