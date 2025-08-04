package com.vaadin.starter.business.ui.views.masterdata.asset;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
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
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Route(value = "asset-types", layout = MainLayout.class)
@PageTitle("Asset Types")
public class AssetTypes extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<AssetType> grid;
    private Registration resizeListener;
    private ListDataProvider<AssetType> dataProvider;
    private UI ui;

    // Search form fields
    private TextField assetCodeFilter;
    private TextField nameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public AssetTypes() {
        setViewContent(createContent());

        // Initialize with default filter
        filter();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createFilterForm() {
        // Initialize filter fields
        assetCodeFilter = new TextField();
        assetCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        assetCodeFilter.setClearButtonVisible(true);

        nameFilter = new TextField();
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        creationDateFilter = new DatePicker();
        creationDateFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createAssetTypeButton = UIUtils.createSuccessButton("Create Asset Type");
        createAssetTypeButton.addClickListener(e -> openCreateAssetTypeDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Asset Type on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createAssetTypeButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(assetCodeFilter, "Asset Code");
        formLayout.addFormItem(nameFilter, "Name");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(creationDateFilter, "Creation Date After");

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

    private Grid<AssetType> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("asset-types");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(AssetType::getAssetCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Asset Code")
                .setSortable(true);
        grid.addColumn(AssetType::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        grid.addColumn(AssetType::getDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createActive))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Creation Date")
                .setTextAlign(ColumnTextAlign.END);

        // Add Actions column with view and delete buttons
        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Initialize with data provider
        dataProvider = DataProvider.ofCollection(getMockAssetTypes());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(AssetType assetType) {
        Icon icon;
        if (assetType.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(AssetType assetType) {
        return new Span(UIUtils.formatDate(assetType.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(AssetType assetType) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(assetType));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteAssetType(assetType));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(AssetType assetType) {
        AssetTypeDetails assetTypeDetails = new AssetTypeDetails(assetType);
        assetTypeDetails.open();
    }

    private void deleteAssetType(AssetType assetType) {
        // This would be implemented to delete the asset type
        System.out.println("[DEBUG_LOG] Delete asset type: " + assetType.getAssetCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply asset code filter if not empty
        if (assetCodeFilter.getValue() != null && !assetCodeFilter.getValue().isEmpty()) {
            String assetCodeFilterValue = assetCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(assetType -> 
                assetType.getAssetCode().toLowerCase().contains(assetCodeFilterValue));
        }

        // Apply name filter if not empty
        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            String nameFilterValue = nameFilter.getValue().toLowerCase();
            dataProvider.addFilter(assetType -> 
                assetType.getName() != null &&
                assetType.getName().toLowerCase().contains(nameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(assetType -> 
                assetType.getDescription() != null &&
                assetType.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(assetType -> 
                assetType.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(assetType -> 
                assetType.getDateCreated() != null && 
                !assetType.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        assetCodeFilter.clear();
        nameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateAssetTypeDialog() {
        // This would be implemented to open a dialog for creating a new asset type
        System.out.println("[DEBUG_LOG] Create asset type dialog would open here");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(currentUI -> {
            this.ui = currentUI;
            Page page = currentUI.getPage();
            resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));
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
        List<Grid.Column<AssetType>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<AssetType> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<AssetType> getMockAssetTypes() {
        List<AssetType> assetTypes = new ArrayList<>();

        assetTypes.add(new AssetType(1L, "AT001", "Building", "Commercial building asset", true, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        assetTypes.add(new AssetType(2L, "AT002", "Vehicle", "Company vehicle asset", true, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        assetTypes.add(new AssetType(3L, "AT003", "Equipment", "Office equipment asset", false, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        assetTypes.add(new AssetType(4L, "AT004", "Land", "Land property asset", true, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        assetTypes.add(new AssetType(5L, "AT005", "Software", "Software license asset", true, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        assetTypes.add(new AssetType(6L, "AT006", "Furniture", "Office furniture asset", false, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        assetTypes.add(new AssetType(7L, "AT007", "Intellectual Property", "Patents and trademarks", true, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        assetTypes.add(new AssetType(8L, "AT008", "Machinery", "Manufacturing machinery", true, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        assetTypes.add(new AssetType(9L, "AT009", "Infrastructure", "IT infrastructure assets", false, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        assetTypes.add(new AssetType(10L, "AT010", "Financial", "Financial instruments", true, LocalDateTime.now(), LocalDateTime.now()));

        return assetTypes;
    }

    // Asset Type model class
    public static class AssetType {
        private Long assetId;
        private String assetCode;
        private String name;
        private String description;
        private Boolean isActive;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public AssetType(Long assetId, String assetCode, String name, String description, 
                        Boolean isActive, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.assetId = assetId;
            this.assetCode = assetCode;
            this.name = name;
            this.description = description;
            this.isActive = isActive;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getAssetId() {
            return assetId;
        }

        public String getAssetCode() {
            return assetCode;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public boolean isActive() {
            return isActive != null && isActive;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }
    }
}