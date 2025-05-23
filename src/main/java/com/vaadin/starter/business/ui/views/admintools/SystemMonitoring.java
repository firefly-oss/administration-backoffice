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
import com.vaadin.starter.business.backend.SystemComponent;
import com.vaadin.starter.business.backend.service.AdminToolsService;
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

import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "system-monitoring", layout = MainLayout.class)
@PageTitle("System Monitoring")
public class SystemMonitoring extends SplitViewFrame {

    private Grid<SystemComponent> grid;
    private ListDataProvider<SystemComponent> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;
    
    private final AdminToolsService adminToolsService;
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public SystemMonitoring(AdminToolsService adminToolsService) {
        this.adminToolsService = adminToolsService;
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
        dataProvider = DataProvider.ofCollection(adminToolsService.getSystemComponents());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(SystemComponent::getId)
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

    private Component createSystemInfo(SystemComponent component) {
        // Get the first letter of each word in the component name for initials
        String componentName = component.getComponentName();
        String initials = componentName.substring(0, 1);
        if (componentName.contains(" ")) {
            initials += componentName.substring(componentName.indexOf(" ") + 1, componentName.indexOf(" ") + 2);
        }
        
        ListItem item = new ListItem(
                new Initials(initials), 
                component.getComponentName(),
                "Server: " + component.getServerName());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(SystemComponent component) {
        String status = component.getStatus();
        Icon icon;
        String color;

        switch (status) {
            case "Healthy":
                icon = UIUtils.createSuccessIcon(VaadinIcon.CHECK);
                color = "var(--lumo-success-color)";
                break;
            case "Warning":
                icon = UIUtils.createPrimaryIcon(VaadinIcon.WARNING);
                color = "var(--lumo-primary-color)";
                break;
            default: // Critical
                icon = UIUtils.createErrorIcon(VaadinIcon.CLOSE_CIRCLE);
                color = "var(--lumo-error-color)";
                break;
        }

        Span span = new Span(icon, new Span(" " + status));
        span.getElement().getStyle().set("color", color);
        return span;
    }

    private Component createMetric(SystemComponent component) {
        return new Span(component.getKeyMetric());
    }

    private Component createLastChecked(SystemComponent component) {
        return new Span(component.getLastChecked().format(DATE_TIME_FORMATTER));
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

    private void showDetails(SystemComponent component) {
        detailsDrawerHeader.setTitle("Component: " + component.getComponentName());
        detailsDrawer.setContent(createDetails(component));
        detailsDrawer.show();
    }

    private FormLayout createDetails(SystemComponent component) {
        TextField componentName = new TextField();
        componentName.setValue(component.getComponentName());
        componentName.setWidthFull();

        TextField serverName = new TextField();
        serverName.setValue(component.getServerName());
        serverName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(component.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        ComboBox<String> status = new ComboBox<>();
        String[] statuses = {"Healthy", "Warning", "Critical"};
        status.setItems(statuses);
        status.setValue(component.getStatus());
        status.setWidthFull();

        TextField cpuUsage = new TextField();
        cpuUsage.setValue(component.getCpuUsage());
        cpuUsage.setWidthFull();

        TextField memoryUsage = new TextField();
        memoryUsage.setValue(component.getMemoryUsage());
        memoryUsage.setWidthFull();

        TextField diskUsage = new TextField();
        diskUsage.setValue(component.getDiskUsage());
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
}