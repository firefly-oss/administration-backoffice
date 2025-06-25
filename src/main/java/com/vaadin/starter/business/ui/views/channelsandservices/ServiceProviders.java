package com.vaadin.starter.business.ui.views.channelsandservices;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
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

    private final ChannelsAndServicesService channelsAndServicesService;

    public ServiceProviders(ChannelsAndServicesService channelsAndServicesService) {
        this.channelsAndServicesService = channelsAndServicesService;

        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        return content;
    }

    private Grid createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem()
                .ifPresent(this::showDetails));
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
                .setAutoWidth(true)
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

        return grid;
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

    // No need for filtering in this view
    // If filtering is needed, it can be implemented based on ServiceProvider properties
}
