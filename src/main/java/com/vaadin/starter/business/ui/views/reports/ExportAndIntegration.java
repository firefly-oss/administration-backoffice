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
import com.vaadin.starter.business.backend.Integration;
import com.vaadin.starter.business.backend.service.ReportsService;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Route(value = "export-integration", layout = MainLayout.class)
@PageTitle("Export and Integration")
public class ExportAndIntegration extends SplitViewFrame {

    private Grid<Integration> grid;
    private ListDataProvider<Integration> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;
    
    private final ReportsService reportsService;
    
    @Autowired
    public ExportAndIntegration(ReportsService reportsService) {
        this.reportsService = reportsService;
        
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
        
        Collection<Integration> integrations = reportsService.getIntegrations();
        dataProvider = DataProvider.ofCollection(integrations);
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Integration::getId)
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

    private Component createIntegrationInfo(Integration integration) {
        String initials = getInitials(integration.getConfiguredBy());
        
        ListItem item = new ListItem(
                new Initials(initials), 
                integration.getName(),
                "Configured by " + integration.getConfiguredBy());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }
    
    private String getInitials(String email) {
        if (email == null || email.isEmpty()) {
            return "??";
        }
        
        String[] parts = email.split("@")[0].split("\\.");
        if (parts.length >= 2) {
            return (parts[0].charAt(0) + "" + parts[1].charAt(0)).toUpperCase();
        } else if (parts.length == 1 && parts[0].length() >= 2) {
            return (parts[0].charAt(0) + "" + parts[0].charAt(1)).toUpperCase();
        } else {
            return parts[0].charAt(0) + "?";
        }
    }

    private Component createStatus(Integration integration) {
        boolean isActive = "Active".equals(integration.getStatus());
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

    private Component createType(Integration integration) {
        return new Span(integration.getType());
    }

    private Component createLastSync(Integration integration) {
        return new Span(UIUtils.formatDate(integration.getLastSync()));
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

    private void showDetails(Integration integration) {
        detailsDrawerHeader.setTitle("Integration: " + integration.getName());
        detailsDrawer.setContent(createDetails(integration));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Integration integration) {
        TextField integrationName = new TextField();
        integrationName.setValue(integration.getName());
        integrationName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(integration.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive", "Testing");
        status.setValue(integration.getStatus());

        ComboBox<String> type = new ComboBox<>();
        String[] types = {"Export", "Import", "Bidirectional", "Webhook", "API"};
        type.setItems(types);
        type.setValue(integration.getType());
        type.setWidthFull();

        TextField endpoint = new TextField();
        endpoint.setValue(integration.getEndpoint());
        endpoint.setWidthFull();

        ComboBox<String> format = new ComboBox<>();
        format.setItems("JSON", "XML", "CSV", "Excel", "PDF");
        format.setValue(integration.getDataFormat());
        format.setWidthFull();

        ComboBox<String> schedule = new ComboBox<>();
        schedule.setItems("Hourly", "Daily", "Weekly", "Monthly", "On Demand");
        schedule.setValue(integration.getSchedule());
        schedule.setWidthFull();

        DatePicker lastSync = new DatePicker();
        lastSync.setValue(integration.getLastSync());
        lastSync.setWidthFull();

        Checkbox secureTransfer = new Checkbox("Use Secure Transfer (SSL/TLS)");
        secureTransfer.setValue(integration.isSecureTransfer());

        Checkbox authentication = new Checkbox("Require Authentication");
        authentication.setValue(integration.isRequireAuthentication());

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
}