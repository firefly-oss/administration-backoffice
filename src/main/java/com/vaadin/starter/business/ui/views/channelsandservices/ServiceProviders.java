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
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.DummyData;
import com.vaadin.starter.business.backend.Person;
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

import java.time.LocalDate;

@Route(value = "service-providers", layout = MainLayout.class)
@PageTitle("Service Providers")
public class ServiceProviders extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public ServiceProviders() {
        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);

        filter();
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
        dataProvider = DataProvider.ofCollection(DummyData.getPersons());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Person::getId)
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

    private Component createProviderInfo(Person person) {
        String[] providers = {
            "Global Payments Inc.", 
            "Fiserv", 
            "FIS Global", 
            "Temenos", 
            "Jack Henry & Associates", 
            "Finastra"
        };
        
        String provider = providers[(int)(Math.abs(person.getId()) % providers.length)];
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                provider,
                "Contact: " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        boolean isActive = person.getRandomBoolean();
        Icon icon;
        String text;

        if (isActive) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
            text = "Active";
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOCK);
            text = "Pending";
        }

        Span span = new Span(icon, new Span(" " + text));
        return span;
    }

    private Component createServiceType(Person person) {
        String[] types = {
            "Payment Processing", 
            "Core Banking", 
            "Credit Scoring", 
            "KYC/AML", 
            "Fraud Detection", 
            "Digital Onboarding"
        };
        
        String type = types[(int)(Math.abs(person.getId()) % types.length)];
        return new Span(type);
    }

    private Component createContractExpiry(Person person) {
        // Create a date between 1 and 365 days from now
        LocalDate expiryDate = LocalDate.now().plusDays(30 + person.getRandomInteger() % 335);
        return new Span(UIUtils.formatDate(expiryDate));
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

    private void showDetails(Person person) {
        String[] providers = {
            "Global Payments Inc.", 
            "Fiserv", 
            "FIS Global", 
            "Temenos", 
            "Jack Henry & Associates", 
            "Finastra"
        };
        
        String provider = providers[(int)(Math.abs(person.getId()) % providers.length)];
        
        detailsDrawerHeader.setTitle("Provider: " + provider);
        detailsDrawer.setContent(createDetails(person, provider));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String provider) {
        TextField providerName = new TextField();
        providerName.setValue(provider);
        providerName.setWidthFull();

        TextField contactPerson = new TextField();
        contactPerson.setValue(person.getName());
        contactPerson.setWidthFull();

        TextField contactEmail = new TextField();
        contactEmail.setValue(person.getEmail());
        contactEmail.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("Service provider for financial services and technology solutions.");
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Pending", "Terminated");
        status.setValue(person.getRandomBoolean() ? "Active" : "Pending");

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
        serviceType.setValue(types[(int)(Math.abs(person.getId()) % types.length)]);
        serviceType.setWidthFull();

        DatePicker contractStart = new DatePicker();
        contractStart.setValue(LocalDate.now().minusDays(person.getRandomInteger() % 365));
        contractStart.setWidthFull();

        DatePicker contractExpiry = new DatePicker();
        contractExpiry.setValue(LocalDate.now().plusDays(30 + person.getRandomInteger() % 335));
        contractExpiry.setWidthFull();

        ComboBox<String> slaLevel = new ComboBox<>();
        slaLevel.setItems("Basic", "Standard", "Premium", "Enterprise");
        slaLevel.setValue("Standard");
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

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}