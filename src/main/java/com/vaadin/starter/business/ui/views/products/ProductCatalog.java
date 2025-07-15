package com.vaadin.starter.business.ui.views.products;

import com.catalis.common.product.sdk.model.ProductDTO;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.backend.sdks.services.ProductsService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Route(value = "product-catalog", layout = MainLayout.class)
@PageTitle("Product Catalog")
public class ProductCatalog extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<ProductDTO> grid;
    private Registration resizeListener;
    private ListDataProvider<ProductDTO> dataProvider;
    private UI ui;

    // Filter form fields
    private TextField productNameFilter;
    private TextField productCodeFilter;
    private TextField productTypeFilter;
    private TextField productStatusFilter;
    private TextField productSubtypeIdFilter;
    private TextField productDescriptionFilter;
    private DatePicker launchDateFilter;
    private DatePicker endDateFilter;

    private final ProductsService productsService;

    @Autowired
    public ProductCatalog(ProductsService productsService) {
        this.productsService = productsService;
        setViewContent(createContent());
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexDirection.COLUMN);
        return content;
    }

    private Component createFilterForm() {
        // Initialize filter fields
        productNameFilter = new TextField();
        productNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        productNameFilter.setClearButtonVisible(true);

        productCodeFilter = new TextField();
        productCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        productCodeFilter.setClearButtonVisible(true);

        productTypeFilter = new TextField();
        productTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        productTypeFilter.setClearButtonVisible(true);

        productStatusFilter = new TextField();
        productStatusFilter.setValueChangeMode(ValueChangeMode.EAGER);
        productStatusFilter.setClearButtonVisible(true);

        productSubtypeIdFilter = new TextField();
        productSubtypeIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        productSubtypeIdFilter.setClearButtonVisible(true);

        productDescriptionFilter = new TextField();
        productDescriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        productDescriptionFilter.setClearButtonVisible(true);

        launchDateFilter = new DatePicker();
        launchDateFilter.setClearButtonVisible(true);

        endDateFilter = new DatePicker();
        endDateFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilters());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilters());

        Button createProductButton = UIUtils.createSuccessButton("Create Product");
        createProductButton.addClickListener(e -> openCreateProductDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Product on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createProductButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(productNameFilter, "Product Name");
        formLayout.addFormItem(productCodeFilter, "Product Code");
        formLayout.addFormItem(productTypeFilter, "Product Type");
        formLayout.addFormItem(productStatusFilter, "Status");
        formLayout.addFormItem(productSubtypeIdFilter, "Subtype ID");
        formLayout.addFormItem(productDescriptionFilter, "Description");
        formLayout.addFormItem(launchDateFilter, "Launch Date");
        formLayout.addFormItem(endDateFilter, "End Date");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        // Create a container for the form and buttons
        Div formContainer = new Div(formLayout, buttonLayout);
        formContainer.getStyle().set("padding", "1em");
        formContainer.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
        formContainer.getStyle().set("border-radius", "var(--lumo-border-radius)");
        formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
        formContainer.getStyle().set("margin-bottom", "1em");

        return formContainer;
    }

    private Grid<ProductDTO> createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewDetails));
        grid.addThemeName("mobile");

        grid.setId("products");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(ProductDTO::getProductId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Product ID")
                .setSortable(true);
        grid.addColumn(ProductDTO::getProductName)
                .setAutoWidth(true)
                .setHeader("Product Name")
                .setSortable(true);
        grid.addColumn(ProductDTO::getProductCode)
                .setAutoWidth(true)
                .setHeader("Product Code")
                .setSortable(true);
        grid.addColumn(product -> product.getProductType() != null ? String.valueOf(product.getProductType()) : "")
                .setAutoWidth(true)
                .setHeader("Product Type")
                .setSortable(true);
        grid.addColumn(product -> product.getProductStatus() != null ? String.valueOf(product.getProductStatus()) : "")
                .setAutoWidth(true)
                .setHeader("Status")
                .setSortable(true);
        grid.addColumn(ProductDTO::getProductDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(new LocalDateRenderer<>(ProductDTO::getLaunchDate, "MMM dd, YYYY"))
                .setAutoWidth(true)
                .setComparator(ProductDTO::getLaunchDate)
                .setFlexGrow(0)
                .setHeader("Launch Date");
        grid.addColumn(new LocalDateRenderer<>(ProductDTO::getEndDate, "MMM dd, YYYY"))
                .setAutoWidth(true)
                .setComparator(ProductDTO::getEndDate)
                .setFlexGrow(0)
                .setHeader("End Date");

        // Initialize with empty data provider
        dataProvider = new ListDataProvider<>(List.of());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private void loadProductsData() {
        System.out.println("[DEBUG_LOG] Starting to load products data");

        productsService.getAllProducts("0", "10", "product_id", "DESC")
                .subscribe(response -> {
                    System.out.println("[DEBUG_LOG] Response status: " + response.getStatusCode());
                    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                        // Create a new list to hold the products
                        List<ProductDTO> products = response.getBody().getContent();

                        System.out.println("[DEBUG_LOG] Received products: " + Objects.requireNonNull(products).size());

                        if (!products.isEmpty()) {
                            System.out.println("[DEBUG_LOG] First product ID: " + products.getFirst().getProductId());
                        }

                        // Use UI.access() to safely update the UI from the async callback
                        ui.access(() -> {
                            try {
                                dataProvider = new ListDataProvider<>(products);
                                grid.setDataProvider(dataProvider);
                                System.out.println("[DEBUG_LOG] Grid data provider updated successfully with " + products.size() + " items");
                                ui.push(); // Force UI update to the client
                            } catch (Exception e) {
                                System.out.println("[DEBUG_LOG] Error updating grid: " + e.getMessage());
                            }
                        });
                    } else {
                        System.out.println("[DEBUG_LOG] Response unsuccessful or body is null");
                    }
                }, error -> {
                    System.out.println("[DEBUG_LOG] Error in getAllProducts: " + error.getMessage());

                    // Handle error in UI thread
                    ui.access(() -> {
                        // You could show a notification here
                        System.out.println("[DEBUG_LOG] Error handled in UI thread");
                    });
                });
    }

    private void viewDetails(ProductDTO product) {
        // Navigate to product details view
        // Since we don't have a specific product details view, we'll just log it
        System.out.println("[DEBUG_LOG] Viewing product details for product ID: " + product.getProductId());
        // In a real application, you would navigate to a details view
        // UI.getCurrent().navigate(ProductDetails.class, product.getProductId());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(currentUI -> {
            this.ui = currentUI;
            Page page = currentUI.getPage();
            resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));

            // Load data after UI is completely initialized
            System.out.println("[DEBUG_LOG] UI attached, loading products data");
            loadProductsData();
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        if (resizeListener != null) {
            resizeListener.remove();
        }
        super.onDetach(detachEvent);
    }

    private void updateVisibleColumns(int width) {
        boolean mobile = width < MOBILE_BREAKPOINT;
        List<Grid.Column<ProductDTO>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<ProductDTO> column : columns) {
            column.setVisible(!mobile);
        }
    }

    private void applyFilters() {
        if (dataProvider == null) {
            System.out.println("[DEBUG_LOG] DataProvider is null, cannot apply filters");
            return;
        }

        dataProvider.clearFilters();

        // Apply product name filter
        if (productNameFilter.getValue() != null && !productNameFilter.getValue().isEmpty()) {
            String productNameValue = productNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(product ->
                    product.getProductName() != null &&
                            product.getProductName().toLowerCase().contains(productNameValue));
        }

        // Apply product code filter
        if (productCodeFilter.getValue() != null && !productCodeFilter.getValue().isEmpty()) {
            String productCodeValue = productCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(product ->
                    product.getProductCode() != null &&
                            product.getProductCode().toLowerCase().contains(productCodeValue));
        }

        // Apply product type filter
        if (productTypeFilter.getValue() != null && !productTypeFilter.getValue().isEmpty()) {
            String productTypeValue = productTypeFilter.getValue().toLowerCase();
            dataProvider.addFilter(product ->
                    product.getProductType() != null &&
                            String.valueOf(product.getProductType()).toLowerCase().contains(productTypeValue));
        }

        // Apply product status filter
        if (productStatusFilter.getValue() != null && !productStatusFilter.getValue().isEmpty()) {
            String statusValue = productStatusFilter.getValue().toLowerCase();
            dataProvider.addFilter(product ->
                    product.getProductStatus() != null &&
                            String.valueOf(product.getProductStatus()).toLowerCase().contains(statusValue));
        }

        // Apply product subtype ID filter
        if (productSubtypeIdFilter.getValue() != null && !productSubtypeIdFilter.getValue().isEmpty()) {
            try {
                Long subtypeId = Long.parseLong(productSubtypeIdFilter.getValue());
                dataProvider.addFilter(product -> product.getProductSubtypeId() != null && product.getProductSubtypeId().equals(subtypeId));
            } catch (NumberFormatException e) {
                // Invalid ID format, ignore this filter
                System.out.println("[DEBUG_LOG] Invalid subtype ID format: " + productSubtypeIdFilter.getValue());
            }
        }

        // Apply description filter
        if (productDescriptionFilter.getValue() != null && !productDescriptionFilter.getValue().isEmpty()) {
            String descriptionValue = productDescriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(product ->
                    product.getProductDescription() != null &&
                            product.getProductDescription().toLowerCase().contains(descriptionValue));
        }

        // Apply launch date filter
        if (launchDateFilter.getValue() != null) {
            LocalDate launchDate = launchDateFilter.getValue();
            dataProvider.addFilter(product ->
                    product.getLaunchDate() != null &&
                            product.getLaunchDate().equals(launchDate));
        }

        // Apply end date filter
        if (endDateFilter.getValue() != null) {
            LocalDate endDate = endDateFilter.getValue();
            dataProvider.addFilter(product ->
                    product.getEndDate() != null &&
                            product.getEndDate().equals(endDate));
        }

        System.out.println("[DEBUG_LOG] Filters applied successfully");
    }

    private void clearFilters() {
        // Clear all filter fields
        productNameFilter.clear();
        productCodeFilter.clear();
        productTypeFilter.clear();
        productStatusFilter.clear();
        productSubtypeIdFilter.clear();
        productDescriptionFilter.clear();
        launchDateFilter.clear();
        endDateFilter.clear();

        // Clear all filters from data provider
        if (dataProvider != null) {
            dataProvider.clearFilters();
            System.out.println("[DEBUG_LOG] All filters cleared");
        }
    }

    private void openCreateProductDialog() {
        // This would be implemented to open a dialog for creating a new product
        System.out.println("[DEBUG_LOG] Create product dialog would open here");
        // Example: CreateProduct createProductDialog = new CreateProduct(productsService, this::loadProductsData);
        // createProductDialog.open();
    }
}