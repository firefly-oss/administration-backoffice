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
import com.vaadin.flow.component.timepicker.TimePicker;
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
import java.time.LocalTime;

@Route(value = "database-maintenance", layout = MainLayout.class)
@PageTitle("Database Maintenance")
public class DatabaseMaintenance extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public DatabaseMaintenance() {
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
        grid.addColumn(new ComponentRenderer<>(this::createTaskInfo))
                .setAutoWidth(true)
                .setHeader("Maintenance Task");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDatabase))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Database")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createSchedule))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Schedule")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createTaskInfo(Person person) {
        String[] tasks = {
            "Database Backup", 
            "Index Optimization", 
            "Data Archiving", 
            "Integrity Check", 
            "Statistics Update", 
            "Log Cleanup"
        };
        
        String task = tasks[(int)(Math.abs(person.getId()) % tasks.length)];
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                task,
                "Managed by " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        String[] statuses = {
            "Scheduled", 
            "Running", 
            "Completed", 
            "Failed"
        };
        
        String status = statuses[(int)(Math.abs(person.getId()) % statuses.length)];
        Icon icon;
        String color;

        switch (status) {
            case "Scheduled":
                icon = UIUtils.createPrimaryIcon(VaadinIcon.CALENDAR_CLOCK);
                color = "var(--lumo-primary-color)";
                break;
            case "Running":
                icon = UIUtils.createSecondaryIcon(VaadinIcon.HOURGLASS);
                color = "var(--lumo-contrast-color)";
                break;
            case "Completed":
                icon = UIUtils.createSuccessIcon(VaadinIcon.CHECK);
                color = "var(--lumo-success-color)";
                break;
            default: // Failed
                icon = UIUtils.createErrorIcon(VaadinIcon.CLOSE_CIRCLE);
                color = "var(--lumo-error-color)";
                break;
        }

        Span span = new Span(icon, new Span(" " + status));
        span.getElement().getStyle().set("color", color);
        return span;
    }

    private Component createDatabase(Person person) {
        String[] databases = {
            "Main Database", 
            "Reporting DB", 
            "Archive DB", 
            "Customer DB", 
            "Transaction DB"
        };
        
        String database = databases[(int)(Math.abs(person.getId()) % databases.length)];
        return new Span(database);
    }

    private Component createSchedule(Person person) {
        // Create a schedule string like "Daily at 02:00" or "Weekly on Sunday"
        String[] frequencies = {"Daily", "Weekly", "Monthly"};
        String frequency = frequencies[(int)(Math.abs(person.getId()) % frequencies.length)];
        
        String schedule;
        if (frequency.equals("Daily")) {
            int hour = (int)(Math.abs(person.getId()) % 24);
            schedule = "Daily at " + String.format("%02d", hour) + ":00";
        } else if (frequency.equals("Weekly")) {
            String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            String day = days[(int)(Math.abs(person.getId()) % days.length)];
            schedule = "Weekly on " + day;
        } else {
            int day = 1 + (int)(Math.abs(person.getId()) % 28);
            schedule = "Monthly on day " + day;
        }
        
        return new Span(schedule);
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
            UIUtils.showNotification("Maintenance task updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        String[] tasks = {
            "Database Backup", 
            "Index Optimization", 
            "Data Archiving", 
            "Integrity Check", 
            "Statistics Update", 
            "Log Cleanup"
        };
        
        String task = tasks[(int)(Math.abs(person.getId()) % tasks.length)];
        
        detailsDrawerHeader.setTitle("Task: " + task);
        detailsDrawer.setContent(createDetails(person, task));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String task) {
        TextField taskName = new TextField();
        taskName.setValue(task);
        taskName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("Database maintenance task to ensure optimal performance and data integrity.");
        description.setWidthFull();
        description.setMinHeight("100px");

        ComboBox<String> status = new ComboBox<>();
        String[] statuses = {"Scheduled", "Running", "Completed", "Failed"};
        status.setItems(statuses);
        status.setValue(statuses[(int)(Math.abs(person.getId()) % statuses.length)]);
        status.setWidthFull();

        ComboBox<String> database = new ComboBox<>();
        String[] databases = {"Main Database", "Reporting DB", "Archive DB", "Customer DB", "Transaction DB"};
        database.setItems(databases);
        database.setValue(databases[(int)(Math.abs(person.getId()) % databases.length)]);
        database.setWidthFull();

        ComboBox<String> frequency = new ComboBox<>();
        String[] frequencies = {"Daily", "Weekly", "Monthly", "On Demand"};
        frequency.setItems(frequencies);
        frequency.setValue(frequencies[(int)(Math.abs(person.getId()) % frequencies.length)]);
        frequency.setWidthFull();

        TimePicker scheduledTime = new TimePicker();
        scheduledTime.setValue(LocalTime.of((int)(Math.abs(person.getId()) % 24), 0));
        scheduledTime.setWidthFull();

        TextField duration = new TextField();
        duration.setValue((5 + (int)(Math.abs(person.getId()) % 55)) + " minutes");
        duration.setWidthFull();

        DatePicker lastRun = new DatePicker();
        lastRun.setValue(LocalDate.now().minusDays(1 + (int)(Math.abs(person.getId()) % 10)));
        lastRun.setWidthFull();

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
        form.addFormItem(taskName, "Task Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(database, "Database");
        form.addFormItem(frequency, "Frequency");
        form.addFormItem(scheduledTime, "Scheduled Time");
        form.addFormItem(duration, "Estimated Duration");
        form.addFormItem(lastRun, "Last Run Date");

        return form;
    }

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}