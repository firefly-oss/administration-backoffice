package com.vaadin.starter.business.ui.views.admintools;

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

@Route(value = "system-monitoring", layout = MainLayout.class)
@PageTitle("System Monitoring")
public class SystemMonitoring extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public SystemMonitoring() {
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
        grid.addColumn(new ComponentRenderer<>(this::createSystemInfo))
                .setAutoWidth(true)
                .setHeader("System Component");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createMetric))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Key Metric")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createLastChecked))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Checked")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createSystemInfo(Person person) {
        String[] components = {
            "Application Server", 
            "Database Server", 
            "Message Queue", 
            "Cache Server", 
            "Load Balancer", 
            "API Gateway"
        };
        
        String component = components[(int)(Math.abs(person.getId()) % components.length)];
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                component,
                "Server: " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        int status = person.getRandomInteger() % 3;
        Icon icon;
        String text;
        String color;

        switch (status) {
            case 0:
                icon = UIUtils.createSuccessIcon(VaadinIcon.CHECK);
                text = "Healthy";
                color = "var(--lumo-success-color)";
                break;
            case 1:
                icon = UIUtils.createPrimaryIcon(VaadinIcon.WARNING);
                text = "Warning";
                color = "var(--lumo-primary-color)";
                break;
            default:
                icon = UIUtils.createErrorIcon(VaadinIcon.CLOSE_CIRCLE);
                text = "Critical";
                color = "var(--lumo-error-color)";
                break;
        }

        Span span = new Span(icon, new Span(" " + text));
        span.getElement().getStyle().set("color", color);
        return span;
    }

    private Component createMetric(Person person) {
        String[] metrics = {
            "CPU: 45%", 
            "Memory: 78%", 
            "Disk: 62%", 
            "Network: 120Mbps", 
            "Requests: 250/s", 
            "Latency: 85ms"
        };
        
        String metric = metrics[(int)(Math.abs(person.getId()) % metrics.length)];
        return new Span(metric);
    }

    private Component createLastChecked(Person person) {
        return new Span(UIUtils.formatDate(person.getLastModified()));
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
            UIUtils.showNotification("Monitoring configuration updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        String[] components = {
            "Application Server", 
            "Database Server", 
            "Message Queue", 
            "Cache Server", 
            "Load Balancer", 
            "API Gateway"
        };
        
        String component = components[(int)(Math.abs(person.getId()) % components.length)];
        
        detailsDrawerHeader.setTitle("Component: " + component);
        detailsDrawer.setContent(createDetails(person, component));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String component) {
        TextField componentName = new TextField();
        componentName.setValue(component);
        componentName.setWidthFull();

        TextField serverName = new TextField();
        serverName.setValue(person.getEmail().split("@")[0] + ".server.local");
        serverName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("System component providing critical functionality for the application infrastructure.");
        description.setWidthFull();
        description.setMinHeight("100px");

        ComboBox<String> status = new ComboBox<>();
        int statusValue = person.getRandomInteger() % 3;
        String[] statuses = {"Healthy", "Warning", "Critical"};
        status.setItems(statuses);
        status.setValue(statuses[statusValue]);
        status.setWidthFull();

        TextField cpuUsage = new TextField();
        cpuUsage.setValue((10 + (int)(Math.abs(person.getId()) % 80)) + "%");
        cpuUsage.setWidthFull();

        TextField memoryUsage = new TextField();
        memoryUsage.setValue((20 + (int)(Math.abs(person.getId()) % 70)) + "%");
        memoryUsage.setWidthFull();

        TextField diskUsage = new TextField();
        diskUsage.setValue((30 + (int)(Math.abs(person.getId()) % 60)) + "%");
        diskUsage.setWidthFull();

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
        form.addFormItem(componentName, "Component Name");
        form.addFormItem(serverName, "Server Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(cpuUsage, "CPU Usage");
        form.addFormItem(memoryUsage, "Memory Usage");
        form.addFormItem(diskUsage, "Disk Usage");

        return form;
    }

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}