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
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.Report;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route(value = "report-designer", layout = MainLayout.class)
@PageTitle("Report Designer")
public class ReportDesigner extends SplitViewFrame {

    private Grid<Report> grid;
    private ListDataProvider<Report> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;
    
    private final ReportsService reportsService;
    
    @Autowired
    public ReportDesigner(ReportsService reportsService) {
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
        
        Collection<Report> reports = reportsService.getReports();
        dataProvider = DataProvider.ofCollection(reports);
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Report::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createReportInfo))
                .setAutoWidth(true)
                .setHeader("Report");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createCategory))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Category")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createLastModified))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Modified")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createReportInfo(Report report) {
        String initials = getInitials(report.getCreatedBy());
        
        ListItem item = new ListItem(
                new Initials(initials), 
                report.getName(),
                "Created by " + report.getCreatedBy());
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

    private Component createStatus(Report report) {
        boolean isPublished = "Published".equals(report.getStatus());
        Icon icon;
        String text;

        if (isPublished) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
            text = "Published";
        } else {
            icon = UIUtils.createSecondaryIcon(VaadinIcon.PENCIL);
            text = "Draft";
        }

        Span span = new Span(icon, new Span(" " + text));
        return span;
    }

    private Component createCategory(Report report) {
        return new Span(report.getCategory());
    }

    private Component createLastModified(Report report) {
        return new Span(UIUtils.formatDate(report.getLastModified()));
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
            UIUtils.showNotification("Report design updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Report report) {
        detailsDrawerHeader.setTitle("Report: " + report.getName());
        detailsDrawer.setContent(createDetails(report));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Report report) {
        TextField reportName = new TextField();
        reportName.setValue(report.getName());
        reportName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(report.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Draft", "Published", "Archived");
        status.setValue(report.getStatus());

        ComboBox<String> category = new ComboBox<>();
        String[] categories = {"Financial", "Operational", "Customer", "Compliance", "Executive"};
        category.setItems(categories);
        category.setValue(report.getCategory());
        category.setWidthFull();

        ComboBox<String> dataSource = new ComboBox<>();
        dataSource.setItems("Main Database", "Data Warehouse", "Analytics Platform", "External API", "Custom Query");
        dataSource.setValue(report.getDataSource());
        dataSource.setWidthFull();

        ComboBox<String> format = new ComboBox<>();
        format.setItems("PDF", "Excel", "CSV", "HTML", "Interactive Dashboard");
        format.setValue(report.getOutputFormat());
        format.setWidthFull();

        Checkbox includeCharts = new Checkbox("Include Charts and Graphs");
        includeCharts.setValue(report.isIncludeCharts());

        Checkbox scheduledDelivery = new Checkbox("Enable Scheduled Delivery");
        scheduledDelivery.setValue(report.isScheduledDelivery());

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
        form.addFormItem(reportName, "Report Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(category, "Category");
        form.addFormItem(dataSource, "Data Source");
        form.addFormItem(format, "Output Format");
        form.addFormItem(includeCharts, "");
        form.addFormItem(scheduledDelivery, "");

        return form;
    }
}