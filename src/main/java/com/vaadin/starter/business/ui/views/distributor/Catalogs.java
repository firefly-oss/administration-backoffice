package com.vaadin.starter.business.ui.views.distributor;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import com.vaadin.starter.business.dummy.Catalog;
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

@Route(value = "catalogs", layout = MainLayout.class)
@PageTitle("Catalogs")
public class Catalogs extends SplitViewFrame {

    private Grid<Catalog> grid;
    private ListDataProvider<Catalog> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Search form fields
    private TextField idFilter;
    private TextField nameFilter;
    private TextField distributorFilter;
    private ComboBox<Catalog.Type> typeFilter;
    private DatePicker startDateFilter;
    private ComboBox<String> activeFilter;

    public Catalogs() {
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
        idFilter = new TextField();
        idFilter.setPlaceholder("ID");
        idFilter.setClearButtonVisible(true);
        idFilter.setValueChangeMode(ValueChangeMode.EAGER);
        idFilter.addValueChangeListener(e -> applyFilter());

        nameFilter = new TextField();
        nameFilter.setPlaceholder("Name");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.addValueChangeListener(e -> applyFilter());

        distributorFilter = new TextField();
        distributorFilter.setPlaceholder("Distributor");
        distributorFilter.setClearButtonVisible(true);
        distributorFilter.setValueChangeMode(ValueChangeMode.EAGER);
        distributorFilter.addValueChangeListener(e -> applyFilter());

        typeFilter = new ComboBox<>();
        typeFilter.setPlaceholder("Type");
        typeFilter.setItems(Catalog.Type.values());
        typeFilter.setItemLabelGenerator(Catalog.Type::getName);
        typeFilter.setClearButtonVisible(true);
        typeFilter.addValueChangeListener(e -> applyFilter());

        startDateFilter = new DatePicker();
        startDateFilter.setPlaceholder("Start Date");
        startDateFilter.setClearButtonVisible(true);
        startDateFilter.addValueChangeListener(e -> applyFilter());

        activeFilter = new ComboBox<>();
        activeFilter.setPlaceholder("Status");
        activeFilter.setItems("Active", "Inactive");
        activeFilter.setClearButtonVisible(true);
        activeFilter.addValueChangeListener(e -> applyFilter());

        // Add fields to form
        formLayout.add(idFilter, nameFilter, distributorFilter, typeFilter, startDateFilter, activeFilter);

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
                .ifPresent(catalog -> UI.getCurrent().navigate(CatalogDetail.class, catalog.getId())));
        dataProvider = DataProvider.ofCollection(DummyData.getCatalogs());
        grid.setDataProvider(dataProvider);
        grid.setHeight("100%");

        grid.addColumn(Catalog::getId)
                .setHeader("ID")
                .setWidth("80px")
                .setFlexGrow(0);

        grid.addColumn(Catalog::getName)
                .setHeader("Name")
                .setWidth("200px")
                .setFlexGrow(1);

        grid.addColumn(Catalog::getDistributorName)
                .setHeader("Distributor")
                .setWidth("150px")
                .setFlexGrow(1);

        grid.addColumn(catalog -> catalog.getType().getName())
                .setHeader("Type")
                .setWidth("120px")
                .setFlexGrow(0);

        grid.addColumn(catalog -> UIUtils.formatDate(catalog.getStartDate()))
                .setHeader("Start Date")
                .setWidth("120px")
                .setFlexGrow(0);

        grid.addColumn(catalog -> UIUtils.formatDate(catalog.getEndDate()))
                .setHeader("End Date")
                .setWidth("120px")
                .setFlexGrow(0);

        grid.addColumn(Catalog::getItemCount)
                .setHeader("Items")
                .setWidth("80px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        grid.addColumn(new ComponentRenderer<>(catalog -> {
            boolean active = catalog.isActive();
            Span badge = new Span(active ? "Active" : "Inactive");

            String theme = active ? "badge success" : "badge";
            badge.getElement().getThemeList().add(theme);
            return badge;
        }))
                .setHeader("Status")
                .setWidth("100px")
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

    private void showDetails(Catalog catalog) {
        detailsDrawerHeader.setTitle(catalog.getName());
        detailsDrawer.setContent(createDetails(catalog));
        detailsDrawer.show();
    }

    private Component createDetails(Catalog catalog) {
        ListItem name = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.PACKAGE),
                "Name", catalog.getName());
        name.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem description = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CLIPBOARD_TEXT),
                "Description", catalog.getDescription());
        description.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem type = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.TAGS),
                "Type", catalog.getType().getName());
        type.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem distributor = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.BUILDING),
                "Distributor", catalog.getDistributorName());
        distributor.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem startDate = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CALENDAR_O),
                "Start Date", UIUtils.formatDate(catalog.getStartDate()));
        startDate.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem endDate = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CALENDAR),
                "End Date", UIUtils.formatDate(catalog.getEndDate()));
        endDate.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem items = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.LIST),
                "Items", String.valueOf(catalog.getItemCount()));
        items.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        ListItem status = new ListItem(
                UIUtils.createPrimaryIcon(VaadinIcon.CHECK),
                "Status", catalog.isActive() ? "Active" : "Inactive");
        status.getContent().addClassName(LumoStyles.Margin.Vertical.S);

        FlexBoxLayout content = new FlexBoxLayout(name, description, type, distributor, startDate, endDate, items, status);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        content.setPadding(Horizontal.RESPONSIVE_L, Vertical.RESPONSIVE_M);
        return content;
    }

    private void filter() {
        dataProvider.clearFilters();

        if (idFilter.getValue() != null && !idFilter.getValue().isEmpty()) {
            dataProvider.addFilter(catalog -> 
                    catalog.getId().toString().contains(idFilter.getValue()));
        }

        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            dataProvider.addFilter(catalog -> 
                    catalog.getName().toLowerCase().contains(nameFilter.getValue().toLowerCase()));
        }

        if (distributorFilter.getValue() != null && !distributorFilter.getValue().isEmpty()) {
            dataProvider.addFilter(catalog -> 
                    catalog.getDistributorName().toLowerCase().contains(distributorFilter.getValue().toLowerCase()));
        }

        if (typeFilter.getValue() != null) {
            dataProvider.addFilter(catalog -> 
                    catalog.getType().equals(typeFilter.getValue()));
        }

        if (startDateFilter.getValue() != null) {
            dataProvider.addFilter(catalog -> 
                    !catalog.getStartDate().isBefore(startDateFilter.getValue()));
        }

        if (activeFilter.getValue() != null) {
            boolean isActive = "Active".equals(activeFilter.getValue());
            dataProvider.addFilter(catalog -> catalog.isActive() == isActive);
        }
    }

    private void resetFilter() {
        idFilter.clear();
        nameFilter.clear();
        distributorFilter.clear();
        typeFilter.clear();
        startDateFilter.clear();
        activeFilter.clear();
        filter();
    }

    private void applyFilter() {
        filter();
    }
}
