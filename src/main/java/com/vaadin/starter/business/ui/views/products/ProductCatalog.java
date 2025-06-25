package com.vaadin.starter.business.ui.views.products;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawer;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerFooter;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerHeader;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

import java.time.LocalDate;
import java.util.List;

import com.vaadin.starter.business.dummy.Product;
import com.vaadin.starter.business.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "product-catalog", layout = MainLayout.class)
@PageTitle("Product Catalog")
public class ProductCatalog extends SplitViewFrame {

    private Grid<Product> grid;
    private ListDataProvider<Product> dataProvider;

    private final ProductService productService;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Search form fields
    private TextField idFilter;
    private TextField nameFilter;
    private ComboBox<String> categoryFilter;
    private NumberField minPriceFilter;
    private NumberField maxPriceFilter;
    private ComboBox<String> statusFilter;
    private DatePicker createdDateFilter;

    // Using Product class from backend package

    // Get products from service
    private List<Product> getProducts() {
        return productService.getProducts().stream().toList();
    }

    @Autowired
    public ProductCatalog(ProductService productService) {
        this.productService = productService;
        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);

        // Initialize filters
        applyFilter();
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
        idFilter = new TextField();
        idFilter.setPlaceholder("Product ID");
        idFilter.setClearButtonVisible(true);
        idFilter.setValueChangeMode(ValueChangeMode.EAGER);
        idFilter.addValueChangeListener(e -> applyFilter());

        nameFilter = new TextField();
        nameFilter.setPlaceholder("Product Name");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.addValueChangeListener(e -> applyFilter());

        categoryFilter = new ComboBox<>();
        categoryFilter.setPlaceholder("Category");
        categoryFilter.setItems("Accounts", "Loans", "Cards", "Investments", "Insurance", "Services");
        categoryFilter.setClearButtonVisible(true);
        categoryFilter.addValueChangeListener(e -> applyFilter());

        minPriceFilter = new NumberField();
        minPriceFilter.setPlaceholder("Min Price/Rate");
        minPriceFilter.setClearButtonVisible(true);
        minPriceFilter.addValueChangeListener(e -> applyFilter());

        maxPriceFilter = new NumberField();
        maxPriceFilter.setPlaceholder("Max Price/Rate");
        maxPriceFilter.setClearButtonVisible(true);
        maxPriceFilter.addValueChangeListener(e -> applyFilter());

        statusFilter = new ComboBox<>();
        statusFilter.setPlaceholder("Status");
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);
        statusFilter.addValueChangeListener(e -> applyFilter());

        createdDateFilter = new DatePicker();
        createdDateFilter.setPlaceholder("Created After");
        createdDateFilter.setClearButtonVisible(true);
        createdDateFilter.addValueChangeListener(e -> applyFilter());

        // Add fields to form
        formLayout.addFormItem(idFilter, "Product ID");
        formLayout.addFormItem(nameFilter, "Product Name");
        formLayout.addFormItem(categoryFilter, "Category");
        formLayout.addFormItem(minPriceFilter, "Min Price/Rate");
        formLayout.addFormItem(maxPriceFilter, "Max Price/Rate");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(createdDateFilter, "Created After");

        // Create buttons
        Button searchButton = new Button("Search", VaadinIcon.SEARCH.create(), e -> applyFilter());
        searchButton.getElement().getThemeList().add("primary");
        searchButton.addClassName(LumoStyles.Margin.Right.S);

        Button clearButton = new Button("Clear", VaadinIcon.CLOSE.create(), e -> clearFilter());
        clearButton.getElement().getThemeList().add("tertiary");

        // Add buttons to horizontal layout
        HorizontalLayout buttonsLayout = new HorizontalLayout(searchButton, clearButton);
        buttonsLayout.setSpacing(true);
        buttonsLayout.setPadding(true);
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        // Create container for the form
        Div formContainer = new Div(formLayout, buttonsLayout);
        formContainer.addClassName(LumoStyles.Padding.Bottom.L);
        formContainer.addClassName(LumoStyles.Padding.Horizontal.L);
        formContainer.addClassName(LumoStyles.Padding.Top.M);
        formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
        formContainer.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
        formContainer.getStyle().set("box-shadow", "var(--lumo-box-shadow-xs)");

        return formContainer;
    }

    private Grid createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem()
                .ifPresent(this::showDetails));
        dataProvider = DataProvider.ofCollection(getProducts());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Product::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(Product::getName)
                .setAutoWidth(true)
                .setHeader("Product Name")
                .setSortable(true);
        grid.addColumn(Product::getCategory)
                .setAutoWidth(true)
                .setHeader("Category")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createPriceInfo))
                .setAutoWidth(true)
                .setHeader("Price/Rate")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Created Date")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createPriceInfo(Product product) {
        if (product.getPrice() == 0.0) {
            return new Span("N/A");
        } else if (product.getCategory().equals("Loans") || product.getCategory().equals("Cards")) {
            return new Span(product.getPrice() + "%");
        } else {
            return new Span("$" + product.getPrice());
        }
    }

    private Component createActiveStatus(Product product) {
        Icon icon;
        if (product.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(Product product) {
        return new Span(UIUtils.formatDate(product.getCreatedDate()));
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

    private void showDetails(Product product) {
        detailsDrawerHeader.setTitle(product.getName());
        detailsDrawer.setContent(createDetails(product));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Product product) {
        TextField id = new TextField();
        id.setValue(product.getId());
        id.setWidthFull();

        TextField name = new TextField();
        name.setValue(product.getName());
        name.setWidthFull();

        ComboBox<String> category = new ComboBox<>();
        category.setItems("Accounts", "Loans", "Cards", "Investments", "Insurance", "Services");
        category.setValue(product.getCategory());
        category.setWidthFull();

        NumberField price = new NumberField();
        price.setValue(product.getPrice());
        price.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(product.getDescription());
        description.setWidthFull();
        description.setHeight("150px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(product.isActive() ? "Active" : "Inactive");

        Upload productImage = new Upload();
        productImage.setMaxFiles(1);
        productImage.setDropLabel(new Span("Upload product image (or drop here)"));

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("1024px", 3,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(id, "Product ID");
        form.addFormItem(name, "Product Name");
        form.addFormItem(category, "Category");
        form.addFormItem(price, "Price/Rate");
        form.addFormItem(status, "Status");
        form.addFormItem(description, "Description");
        form.addFormItem(productImage, "Product Image");

        return form;
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply ID filter if not empty
        if (idFilter != null && idFilter.getValue() != null && !idFilter.getValue().isEmpty()) {
            String idValue = idFilter.getValue();
            dataProvider.setFilter(product -> product.getId().contains(idValue));
        }

        // Apply name filter if not empty
        if (nameFilter != null && nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            String nameValue = nameFilter.getValue().toLowerCase();
            dataProvider.addFilter(product -> 
                product.getName().toLowerCase().contains(nameValue));
        }

        // Apply category filter if selected
        if (categoryFilter != null && categoryFilter.getValue() != null) {
            String categoryValue = categoryFilter.getValue();
            dataProvider.addFilter(product -> 
                product.getCategory().equals(categoryValue));
        }

        // Apply min price filter if set
        if (minPriceFilter != null && minPriceFilter.getValue() != null) {
            Double minPrice = minPriceFilter.getValue();
            dataProvider.addFilter(product -> 
                product.getPrice() >= minPrice);
        }

        // Apply max price filter if set
        if (maxPriceFilter != null && maxPriceFilter.getValue() != null) {
            Double maxPrice = maxPriceFilter.getValue();
            dataProvider.addFilter(product -> 
                product.getPrice() <= maxPrice);
        }

        // Apply status filter if selected
        if (statusFilter != null && statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(product -> 
                product.isActive() == isActive);
        }

        // Apply created date filter if selected
        if (createdDateFilter != null && createdDateFilter.getValue() != null) {
            LocalDate date = createdDateFilter.getValue();
            dataProvider.addFilter(product -> 
                product.getCreatedDate() != null && 
                !product.getCreatedDate().isBefore(date));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        if (idFilter != null) idFilter.clear();
        if (nameFilter != null) nameFilter.clear();
        if (categoryFilter != null) categoryFilter.clear();
        if (minPriceFilter != null) minPriceFilter.clear();
        if (maxPriceFilter != null) maxPriceFilter.clear();
        if (statusFilter != null) statusFilter.clear();
        if (createdDateFilter != null) createdDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }
}
