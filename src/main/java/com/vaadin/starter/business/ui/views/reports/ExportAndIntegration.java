package com.vaadin.starter.business.ui.views.reports;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
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

@Route(value = "export-integration", layout = MainLayout.class)
@PageTitle("Export and Integration")
public class ExportAndIntegration extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public ExportAndIntegration() {
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
        grid.addColumn(new ComponentRenderer<>(this::createIntegrationInfo))
                .setAutoWidth(true)
                .setHeader("Integration");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createType))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Type")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createLastSync))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Sync")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createIntegrationInfo(Person person) {
        String[] integrations = {
            "Salesforce Export", 
            "Google Analytics", 
            "Microsoft Power BI", 
            "Tableau Connection", 
            "Email Distribution", 
            "FTP Data Transfer"
        };
        
        String integration = integrations[(int)(Math.abs(person.getId()) % integrations.length)];
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                integration,
                "Configured by " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        boolean isActive = person.getRandomBoolean();
        Icon icon;
        String text;
        String color;

        if (isActive) {
            icon = UIUtils.createSuccessIcon(VaadinIcon.CHECK);
            text = "Active";
            color = "var(--lumo-success-color)";
        } else {
            icon = UIUtils.createErrorIcon(VaadinIcon.CLOSE_CIRCLE);
            text = "Inactive";
            color = "var(--lumo-error-color)";
        }

        Span span = new Span(icon, new Span(" " + text));
        span.getElement().getStyle().set("color", color);
        return span;
    }

    private Component createType(Person person) {
        String[] types = {
            "Export", 
            "Import", 
            "Bidirectional", 
            "Webhook", 
            "API"
        };
        
        String type = types[(int)(Math.abs(person.getId()) % types.length)];
        return new Span(type);
    }

    private Component createLastSync(Person person) {
        // Create a date in the past
        LocalDate lastSync = LocalDate.now().minusDays(person.getRandomInteger() % 30);
        return new Span(UIUtils.formatDate(lastSync));
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
            UIUtils.showNotification("Integration configuration updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        String[] integrations = {
            "Salesforce Export", 
            "Google Analytics", 
            "Microsoft Power BI", 
            "Tableau Connection", 
            "Email Distribution", 
            "FTP Data Transfer"
        };
        
        String integration = integrations[(int)(Math.abs(person.getId()) % integrations.length)];
        
        detailsDrawerHeader.setTitle("Integration: " + integration);
        detailsDrawer.setContent(createDetails(person, integration));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String integration) {
        TextField integrationName = new TextField();
        integrationName.setValue(integration);
        integrationName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("Integration for exporting and sharing reports with external systems and stakeholders.");
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive", "Testing");
        status.setValue(person.getRandomBoolean() ? "Active" : "Inactive");

        ComboBox<String> type = new ComboBox<>();
        String[] types = {"Export", "Import", "Bidirectional", "Webhook", "API"};
        type.setItems(types);
        type.setValue(types[(int)(Math.abs(person.getId()) % types.length)]);
        type.setWidthFull();

        TextField endpoint = new TextField();
        endpoint.setValue("https://api.example.com/integration/" + person.getId());
        endpoint.setWidthFull();

        ComboBox<String> format = new ComboBox<>();
        format.setItems("JSON", "XML", "CSV", "Excel", "PDF");
        format.setValue("JSON");
        format.setWidthFull();

        ComboBox<String> schedule = new ComboBox<>();
        schedule.setItems("Hourly", "Daily", "Weekly", "Monthly", "On Demand");
        schedule.setValue("Daily");
        schedule.setWidthFull();

        DatePicker lastSync = new DatePicker();
        lastSync.setValue(LocalDate.now().minusDays(person.getRandomInteger() % 30));
        lastSync.setWidthFull();

        Checkbox secureTransfer = new Checkbox("Use Secure Transfer (SSL/TLS)");
        secureTransfer.setValue(true);

        Checkbox authentication = new Checkbox("Require Authentication");
        authentication.setValue(true);

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
        form.addFormItem(integrationName, "Integration Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(type, "Integration Type");
        form.addFormItem(endpoint, "Endpoint URL");
        form.addFormItem(format, "Data Format");
        form.addFormItem(schedule, "Schedule");
        form.addFormItem(lastSync, "Last Synchronization");
        form.addFormItem(secureTransfer, "");
        form.addFormItem(authentication, "");

        return form;
    }

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}