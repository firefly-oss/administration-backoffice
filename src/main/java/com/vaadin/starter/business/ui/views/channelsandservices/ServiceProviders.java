package com.vaadin.starter.business.ui.views.channelsandservices;

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
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.ServiceProvider;
import com.vaadin.starter.business.backend.service.ChannelsAndServicesService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.Initials;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawer;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerFooter;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerHeader;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Right;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

@Route(value = "service-providers", layout = MainLayout.class)
@PageTitle("Service Providers")
public class ServiceProviders extends SplitViewFrame {

    private Grid<ServiceProvider> grid;
    private ListDataProvider<ServiceProvider> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Search form fields
    private TextField idFilter;
    private TextField nameFilter;
    private TextField contactEmailFilter;
    private ComboBox<String> statusFilter;
    private ComboBox<String> serviceTypeFilter;
    private DatePicker contractExpiryFilter;

    private final ChannelsAndServicesService channelsAndServicesService;

    public ServiceProviders(ChannelsAndServicesService channelsAndServicesService) {
        this.channelsAndServicesService = channelsAndServicesService;

        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);

        // Initialize with empty filter (show all providers)
        clearFilter();
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
        idFilter = new TextField();
        idFilter.setValueChangeMode(ValueChangeMode.EAGER);
        idFilter.setClearButtonVisible(true);

        nameFilter = new TextField();
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.setClearButtonVisible(true);

        contactEmailFilter = new TextField();
        contactEmailFilter.setValueChangeMode(ValueChangeMode.EAGER);
        contactEmailFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Pending", "Terminated");
        statusFilter.setClearButtonVisible(true);

        serviceTypeFilter = new ComboBox<>();
        String[] types = {
            "Payment Processing", 
            "Core Banking", 
            "Credit Scoring", 
            "KYC/AML", 
            "Fraud Detection", 
            "Digital Onboarding"
        };
        serviceTypeFilter.setItems(types);
        serviceTypeFilter.setClearButtonVisible(true);

        contractExpiryFilter = new DatePicker();
        contractExpiryFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createProviderButton = UIUtils.createSuccessButton("Create Provider");
        createProviderButton.addClickListener(e -> openCreateProviderDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Provider on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createProviderButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(idFilter, "ID");
        formLayout.addFormItem(nameFilter, "Provider Name");
        formLayout.addFormItem(contactEmailFilter, "Contact Email");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(serviceTypeFilter, "Service Type");
        formLayout.addFormItem(contractExpiryFilter, "Contract Expiry Before");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 3, FormLayout.ResponsiveStep.LabelsPosition.TOP)
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

    private Grid createGrid() {
        grid = new Grid<>();
        dataProvider = DataProvider.ofCollection(channelsAndServicesService.getServiceProviders());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(ServiceProvider::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createProviderInfo))
                .setWidth("200px") // Fixed width instead of auto width
                .setHeader("Provider");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createServiceType))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Service Type")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createContractExpiry))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Contract Expiry")
                .setTextAlign(ColumnTextAlign.END);

        // Add Actions column with configure flows button
        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        return grid;
    }

    private Component createActionButtons(ServiceProvider provider) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        layout.getStyle().set("gap", "0.2em"); // Use smaller gap instead of default spacing
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetailsModal(provider));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create configure flows button with connect icon
        Button configureFlowsButton = UIUtils.createButton(VaadinIcon.CONNECT);
        configureFlowsButton.addClickListener(e -> navigateToFlowsConfiguration(provider));
        configureFlowsButton.getElement().getThemeList().add("small");
        configureFlowsButton.getElement().getThemeList().add("tertiary");
        configureFlowsButton.getElement().setAttribute("title", "Configure Flows");
        UIUtils.setPointerCursor(configureFlowsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteProvider(provider));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().setAttribute("title", "Delete Provider");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, configureFlowsButton, deleteButton);
        return layout;
    }

    private void navigateToFlowsConfiguration(ServiceProvider provider) {
        // Navigate to the provider flows configuration view
        UI.getCurrent().navigate(ProviderFlowsConfiguration.class);
    }

    private Component createProviderInfo(ServiceProvider provider) {
        ListItem item = new ListItem(
                new Initials(provider.getName().substring(0, 2)), 
                provider.getName(),
                "Contact: " + provider.getContactEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(ServiceProvider provider) {
        Icon icon;
        String text;

        if ("Active".equals(provider.getStatus())) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
            text = "Active";
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOCK);
            text = provider.getStatus();
        }

        Span span = new Span(icon, new Span(" " + text));
        return span;
    }

    private Component createServiceType(ServiceProvider provider) {
        return new Span(provider.getServiceType());
    }

    private Component createContractExpiry(ServiceProvider provider) {
        return new Span(UIUtils.formatDate(provider.getContractExpiry()));
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
            UIUtils.showNotification("Service provider updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(ServiceProvider provider) {
        detailsDrawerHeader.setTitle("Provider: " + provider.getName());
        detailsDrawer.setContent(createDetails(provider));
        detailsDrawer.show();
    }

    private FormLayout createDetails(ServiceProvider provider) {
        TextField providerName = new TextField();
        providerName.setValue(provider.getName());
        providerName.setWidthFull();

        TextField contactPerson = new TextField();
        contactPerson.setValue(provider.getContactPerson());
        contactPerson.setWidthFull();

        TextField contactEmail = new TextField();
        contactEmail.setValue(provider.getContactEmail());
        contactEmail.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(provider.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Pending", "Terminated");
        status.setValue(provider.getStatus());

        ComboBox<String> serviceType = new ComboBox<>();
        String[] types = {
            "Payment Processing", 
            "Core Banking", 
            "Credit Scoring", 
            "KYC/AML", 
            "Fraud Detection", 
            "Digital Onboarding"
        };
        serviceType.setItems(types);
        serviceType.setValue(provider.getServiceType());
        serviceType.setWidthFull();

        DatePicker contractStart = new DatePicker();
        contractStart.setValue(provider.getContractStart());
        contractStart.setWidthFull();

        DatePicker contractExpiry = new DatePicker();
        contractExpiry.setValue(provider.getContractExpiry());
        contractExpiry.setWidthFull();

        ComboBox<String> slaLevel = new ComboBox<>();
        slaLevel.setItems("Basic", "Standard", "Premium", "Enterprise");
        slaLevel.setValue(provider.getSlaLevel());
        slaLevel.setWidthFull();

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
        form.addFormItem(providerName, "Provider Name");
        form.addFormItem(contactPerson, "Contact Person");
        form.addFormItem(contactEmail, "Contact Email");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(serviceType, "Service Type");
        form.addFormItem(contractStart, "Contract Start");
        form.addFormItem(contractExpiry, "Contract Expiry");
        form.addFormItem(slaLevel, "SLA Level");

        return form;
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply ID filter if not empty
        if (idFilter.getValue() != null && !idFilter.getValue().isEmpty()) {
            try {
                Long id = Long.parseLong(idFilter.getValue());
                dataProvider.setFilter(provider -> provider.getId().equals(id));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply name filter if not empty
        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            String nameFilterValue = nameFilter.getValue().toLowerCase();
            dataProvider.addFilter(provider -> 
                provider.getName().toLowerCase().contains(nameFilterValue));
        }

        // Apply contact email filter if not empty
        if (contactEmailFilter.getValue() != null && !contactEmailFilter.getValue().isEmpty()) {
            String emailFilterValue = contactEmailFilter.getValue().toLowerCase();
            dataProvider.addFilter(provider -> 
                provider.getContactEmail().toLowerCase().contains(emailFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            dataProvider.addFilter(provider -> 
                statusFilter.getValue().equals(provider.getStatus()));
        }

        // Apply service type filter if selected
        if (serviceTypeFilter.getValue() != null) {
            dataProvider.addFilter(provider -> 
                serviceTypeFilter.getValue().equals(provider.getServiceType()));
        }

        // Apply contract expiry filter if selected
        if (contractExpiryFilter.getValue() != null) {
            dataProvider.addFilter(provider -> 
                provider.getContractExpiry() != null && 
                !provider.getContractExpiry().isAfter(contractExpiryFilter.getValue()));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        idFilter.clear();
        nameFilter.clear();
        contactEmailFilter.clear();
        statusFilter.clear();
        serviceTypeFilter.clear();
        contractExpiryFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateProviderDialog() {
        // This would be implemented to open a dialog for creating a new provider
        System.out.println("[DEBUG_LOG] Create provider dialog would open here");
        // Example: CreateProvider createProviderDialog = new CreateProvider(channelsAndServicesService, this::refreshData);
        // createProviderDialog.open();
    }

    private void showDetailsModal(ServiceProvider provider) {
        // Create and open a modal dialog with provider details
        ProviderDetails providerDetails = new ProviderDetails(provider, channelsAndServicesService);
        providerDetails.open();
    }

    private void deleteProvider(ServiceProvider provider) {
        // In a real application, this would call a service method to delete the provider from the database
        // Since we're working with mock data and don't have a deleteProvider method in the service,
        // we'll just remove it from the data provider

        // Remove from grid's data provider
        dataProvider.getItems().remove(provider);
        dataProvider.refreshAll();

        // Show success notification
        UIUtils.showNotification("Provider " + provider.getName() + " deleted successfully");
    }
}
