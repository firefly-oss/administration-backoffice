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

@Route(value = "version-management", layout = MainLayout.class)
@PageTitle("Version Management")
public class VersionManagement extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public VersionManagement() {
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
        grid.addColumn(new ComponentRenderer<>(this::createVersionInfo))
                .setAutoWidth(true)
                .setHeader("Version");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createEnvironment))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Environment")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createReleaseDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Release Date")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createVersionInfo(Person person) {
        // Generate a version number like 2.3.1, 3.0.0, etc.
        int major = 1 + (int)(Math.abs(person.getId()) % 5);
        int minor = (int)(Math.abs(person.getId()) % 10);
        int patch = (int)(Math.abs(person.getId()) % 5);
        String version = major + "." + minor + "." + patch;
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                "Version " + version,
                "Released by " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        String[] statuses = {
            "Current", 
            "Deprecated", 
            "Planned", 
            "Testing"
        };
        
        String status = statuses[(int)(Math.abs(person.getId()) % statuses.length)];
        Icon icon;
        String color;

        switch (status) {
            case "Current":
                icon = UIUtils.createSuccessIcon(VaadinIcon.CHECK);
                color = "var(--lumo-success-color)";
                break;
            case "Deprecated":
                icon = UIUtils.createErrorIcon(VaadinIcon.CLOSE_CIRCLE);
                color = "var(--lumo-error-color)";
                break;
            case "Planned":
                icon = UIUtils.createPrimaryIcon(VaadinIcon.CALENDAR);
                color = "var(--lumo-primary-color)";
                break;
            default: // Testing
                icon = UIUtils.createSecondaryIcon(VaadinIcon.FLASK);
                color = "var(--lumo-contrast-color)";
                break;
        }

        Span span = new Span(icon, new Span(" " + status));
        span.getElement().getStyle().set("color", color);
        return span;
    }

    private Component createEnvironment(Person person) {
        String[] environments = {
            "Production", 
            "Staging", 
            "QA", 
            "Development"
        };
        
        String environment = environments[(int)(Math.abs(person.getId()) % environments.length)];
        return new Span(environment);
    }

    private Component createReleaseDate(Person person) {
        // Create a date in the past or future
        LocalDate now = LocalDate.now();
        LocalDate releaseDate = person.getRandomBoolean() ? 
                now.minusDays(person.getRandomInteger() % 180) : 
                now.plusDays(person.getRandomInteger() % 180);
        
        return new Span(UIUtils.formatDate(releaseDate));
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
            UIUtils.showNotification("Version information updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        // Generate a version number like 2.3.1, 3.0.0, etc.
        int major = 1 + (int)(Math.abs(person.getId()) % 5);
        int minor = (int)(Math.abs(person.getId()) % 10);
        int patch = (int)(Math.abs(person.getId()) % 5);
        String version = major + "." + minor + "." + patch;
        
        detailsDrawerHeader.setTitle("Version " + version);
        detailsDrawer.setContent(createDetails(person, version));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String version) {
        TextField versionNumber = new TextField();
        versionNumber.setValue(version);
        versionNumber.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("This version includes new features, bug fixes, and performance improvements.");
        description.setWidthFull();
        description.setMinHeight("100px");

        ComboBox<String> status = new ComboBox<>();
        String[] statuses = {"Current", "Deprecated", "Planned", "Testing"};
        status.setItems(statuses);
        status.setValue(statuses[(int)(Math.abs(person.getId()) % statuses.length)]);
        status.setWidthFull();

        ComboBox<String> environment = new ComboBox<>();
        String[] environments = {"Production", "Staging", "QA", "Development"};
        environment.setItems(environments);
        environment.setValue(environments[(int)(Math.abs(person.getId()) % environments.length)]);
        environment.setWidthFull();

        DatePicker releaseDate = new DatePicker();
        LocalDate now = LocalDate.now();
        releaseDate.setValue(person.getRandomBoolean() ? 
                now.minusDays(person.getRandomInteger() % 180) : 
                now.plusDays(person.getRandomInteger() % 180));
        releaseDate.setWidthFull();

        TextField releasedBy = new TextField();
        releasedBy.setValue(person.getName());
        releasedBy.setWidthFull();

        TextArea changeLog = new TextArea();
        changeLog.setValue("- Added new feature X\n- Fixed bug in module Y\n- Improved performance of Z");
        changeLog.setWidthFull();
        changeLog.setMinHeight("150px");

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
        form.addFormItem(versionNumber, "Version Number");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(environment, "Environment");
        form.addFormItem(releaseDate, "Release Date");
        form.addFormItem(releasedBy, "Released By");
        form.addFormItem(changeLog, "Change Log");

        return form;
    }

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}