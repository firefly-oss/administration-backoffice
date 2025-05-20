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

@Route(value = "report-designer", layout = MainLayout.class)
@PageTitle("Report Designer")
public class ReportDesigner extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public ReportDesigner() {
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

    private Component createReportInfo(Person person) {
        String[] reports = {
            "Monthly Financial Summary", 
            "Customer Activity Report", 
            "Transaction Analysis", 
            "Compliance Dashboard", 
            "Performance Metrics", 
            "Risk Assessment"
        };
        
        String report = reports[(int)(Math.abs(person.getId()) % reports.length)];
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                report,
                "Created by " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        boolean isPublished = person.getRandomBoolean();
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

    private Component createCategory(Person person) {
        String[] categories = {
            "Financial", 
            "Operational", 
            "Customer", 
            "Compliance", 
            "Executive"
        };
        
        String category = categories[(int)(Math.abs(person.getId()) % categories.length)];
        return new Span(category);
    }

    private Component createLastModified(Person person) {
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
            UIUtils.showNotification("Report design updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        String[] reports = {
            "Monthly Financial Summary", 
            "Customer Activity Report", 
            "Transaction Analysis", 
            "Compliance Dashboard", 
            "Performance Metrics", 
            "Risk Assessment"
        };
        
        String report = reports[(int)(Math.abs(person.getId()) % reports.length)];
        
        detailsDrawerHeader.setTitle("Report: " + report);
        detailsDrawer.setContent(createDetails(person, report));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String report) {
        TextField reportName = new TextField();
        reportName.setValue(report);
        reportName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("Comprehensive report providing insights and analytics for business decision-making.");
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Draft", "Published", "Archived");
        status.setValue(person.getRandomBoolean() ? "Published" : "Draft");

        ComboBox<String> category = new ComboBox<>();
        String[] categories = {"Financial", "Operational", "Customer", "Compliance", "Executive"};
        category.setItems(categories);
        category.setValue(categories[(int)(Math.abs(person.getId()) % categories.length)]);
        category.setWidthFull();

        ComboBox<String> dataSource = new ComboBox<>();
        dataSource.setItems("Main Database", "Data Warehouse", "Analytics Platform", "External API", "Custom Query");
        dataSource.setValue("Data Warehouse");
        dataSource.setWidthFull();

        ComboBox<String> format = new ComboBox<>();
        format.setItems("PDF", "Excel", "CSV", "HTML", "Interactive Dashboard");
        format.setValue("Interactive Dashboard");
        format.setWidthFull();

        Checkbox includeCharts = new Checkbox("Include Charts and Graphs");
        includeCharts.setValue(true);

        Checkbox scheduledDelivery = new Checkbox("Enable Scheduled Delivery");
        scheduledDelivery.setValue(person.getRandomBoolean());

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

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}