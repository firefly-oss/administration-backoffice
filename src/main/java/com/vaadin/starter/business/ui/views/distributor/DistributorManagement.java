package com.vaadin.starter.business.ui.views.distributor;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.Distributor;
import com.vaadin.starter.business.dummy.DummyData;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

@Route(value = "distributor-management", layout = MainLayout.class)
@PageTitle("Distributor Management")
public class DistributorManagement extends SplitViewFrame {

    private Grid<Distributor> grid;
    private ListDataProvider<Distributor> dataProvider;

    // Search form fields
    private TextField idFilter;
    private TextField nameFilter;
    private TextField contactPersonFilter;
    private TextField emailFilter;
    private ComboBox<Distributor.Status> statusFilter;
    private DatePicker contractDateFilter;

    public DistributorManagement() {
        setViewContent(createContent());

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

        contactPersonFilter = new TextField();
        contactPersonFilter.setPlaceholder("Contact Person");
        contactPersonFilter.setClearButtonVisible(true);
        contactPersonFilter.setValueChangeMode(ValueChangeMode.EAGER);
        contactPersonFilter.addValueChangeListener(e -> applyFilter());

        emailFilter = new TextField();
        emailFilter.setPlaceholder("Email");
        emailFilter.setClearButtonVisible(true);
        emailFilter.setValueChangeMode(ValueChangeMode.EAGER);
        emailFilter.addValueChangeListener(e -> applyFilter());

        statusFilter = new ComboBox<>();
        statusFilter.setPlaceholder("Status");
        statusFilter.setItems(Distributor.Status.values());
        statusFilter.setItemLabelGenerator(Distributor.Status::getName);
        statusFilter.setClearButtonVisible(true);
        statusFilter.addValueChangeListener(e -> applyFilter());

        contractDateFilter = new DatePicker();
        contractDateFilter.setPlaceholder("Contract Date");
        contractDateFilter.setClearButtonVisible(true);
        contractDateFilter.addValueChangeListener(e -> applyFilter());

        // Add fields to form
        formLayout.add(idFilter, nameFilter, contactPersonFilter, emailFilter, statusFilter, contractDateFilter);

        // Create a button layout
        Button resetBtn = new Button("Reset");
        resetBtn.addClickListener(e -> resetFilter());
        UIUtils.setPointerCursor(resetBtn);

        Button searchBtn = new Button("Search");
        searchBtn.addClickListener(e -> applyFilter());
        searchBtn.getElement().getThemeList().add("primary");
        UIUtils.setPointerCursor(searchBtn);

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
        dataProvider = DataProvider.ofCollection(DummyData.getDistributors());
        grid.setDataProvider(dataProvider);
        grid.setHeight("100%");

        grid.addColumn(Distributor::getId)
                .setHeader("ID")
                .setWidth("120px")
                .setFlexGrow(0);

        grid.addColumn(Distributor::getName)
                .setHeader("Name")
                .setWidth("200px")
                .setFlexGrow(1);

        grid.addColumn(Distributor::getContactPerson)
                .setHeader("Contact Person")
                .setWidth("200px")
                .setFlexGrow(1);

        grid.addColumn(Distributor::getEmail)
                .setHeader("Email")
                .setWidth("200px")
                .setFlexGrow(1);

        grid.addColumn(Distributor::getPhone)
                .setHeader("Phone")
                .setWidth("150px")
                .setFlexGrow(1);

        grid.addColumn(new ComponentRenderer<>(distributor -> {
            Distributor.Status status = distributor.getStatus();
            Span badge = new Span(status.getName());

            String theme = "badge primary";
            if (status == Distributor.Status.ACTIVE) {
                theme = "badge success";
            } else if (status == Distributor.Status.INACTIVE) {
                theme = "badge";
            } else if (status == Distributor.Status.SUSPENDED) {
                theme = "badge error";
            }

            badge.getElement().getThemeList().add(theme);
            return badge;
        }))
                .setHeader("Status")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn(distributor -> UIUtils.formatDate(distributor.getContractDate()))
                .setHeader("Contract Date")
                .setWidth("150px")
                .setFlexGrow(0);

        grid.addColumn(distributor -> distributor.getCommissionRate() + "%")
                .setHeader("Commission")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }


    private void showDetails(Distributor distributor) {
        // Navigate to the DistributorDetails view with the distributor ID as a parameter
        getUI().ifPresent(ui -> ui.navigate("distributor-details/" + distributor.getId()));
    }


    private void filter() {
        dataProvider.clearFilters();

        if (idFilter.getValue() != null && !idFilter.getValue().isEmpty()) {
            dataProvider.addFilter(distributor -> 
                    distributor.getId().toString().contains(idFilter.getValue()));
        }

        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            dataProvider.addFilter(distributor -> 
                    distributor.getName().toLowerCase().contains(nameFilter.getValue().toLowerCase()));
        }

        if (contactPersonFilter.getValue() != null && !contactPersonFilter.getValue().isEmpty()) {
            dataProvider.addFilter(distributor -> 
                    distributor.getContactPerson().toLowerCase().contains(contactPersonFilter.getValue().toLowerCase()));
        }

        if (emailFilter.getValue() != null && !emailFilter.getValue().isEmpty()) {
            dataProvider.addFilter(distributor -> 
                    distributor.getEmail().toLowerCase().contains(emailFilter.getValue().toLowerCase()));
        }

        if (statusFilter.getValue() != null) {
            dataProvider.addFilter(distributor -> 
                    distributor.getStatus().equals(statusFilter.getValue()));
        }

        if (contractDateFilter.getValue() != null) {
            dataProvider.addFilter(distributor -> 
                    distributor.getContractDate().equals(contractDateFilter.getValue()));
        }
    }

    private void resetFilter() {
        idFilter.clear();
        nameFilter.clear();
        contactPersonFilter.clear();
        emailFilter.clear();
        statusFilter.clear();
        contractDateFilter.clear();
        filter();
    }

    private void applyFilter() {
        filter();
    }
}
