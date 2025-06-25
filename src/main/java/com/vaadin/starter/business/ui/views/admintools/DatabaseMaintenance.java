package com.vaadin.starter.business.ui.views.admintools;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.MaintenanceTask;
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

import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "database-maintenance", layout = MainLayout.class)
@PageTitle("Database Maintenance")
public class DatabaseMaintenance extends SplitViewFrame {

    private Grid<MaintenanceTask> grid;
    private ListDataProvider<MaintenanceTask> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;
    
    private final AdminToolsService adminToolsService;

    @Autowired
    public DatabaseMaintenance(AdminToolsService adminToolsService) {
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
        dataProvider = DataProvider.ofCollection(adminToolsService.getMaintenanceTasks());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(MaintenanceTask::getId)
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

    private Component createTaskInfo(MaintenanceTask task) {
        // Get the first letter of each word in the task name for initials
        String taskName = task.getTaskName();
        String initials = taskName.substring(0, 1);
        if (taskName.contains(" ")) {
            initials += taskName.substring(taskName.indexOf(" ") + 1, taskName.indexOf(" ") + 2);
        }
        
        ListItem item = new ListItem(
                new Initials(initials), 
                task.getTaskName(),
                "Managed by " + task.getManagedBy());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(MaintenanceTask task) {
        String status = task.getStatus();
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

    private Component createDatabase(MaintenanceTask task) {
        return new Span(task.getDatabase());
    }

    private Component createSchedule(MaintenanceTask task) {
        String frequency = task.getFrequency();
        LocalTime time = task.getScheduledTime();
        String timeStr = String.format("%02d:%02d", time.getHour(), time.getMinute());
        
        if (frequency.equals("Daily")) {
            return new Span("Daily at " + timeStr);
        } else if (frequency.equals("Weekly")) {
            return new Span("Weekly on Monday");  // Simplified, could be enhanced
        } else if (frequency.equals("Monthly")) {
            return new Span("Monthly on day 1");  // Simplified, could be enhanced
        } else {
            return new Span(frequency);
        }
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

    private void showDetails(MaintenanceTask task) {
        detailsDrawerHeader.setTitle("Task: " + task.getTaskName());
        detailsDrawer.setContent(createDetails(task));
        detailsDrawer.show();
    }

    private FormLayout createDetails(MaintenanceTask task) {
        TextField taskName = new TextField();
        taskName.setValue(task.getTaskName());
        taskName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(task.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        ComboBox<String> status = new ComboBox<>();
        String[] statuses = {"Scheduled", "Running", "Completed", "Failed"};
        status.setItems(statuses);
        status.setValue(task.getStatus());
        status.setWidthFull();

        ComboBox<String> database = new ComboBox<>();
        String[] databases = {"Main Database", "Reporting DB", "Archive DB", "Customer DB", "Transaction DB"};
        database.setItems(databases);
        database.setValue(task.getDatabase());
        database.setWidthFull();

        ComboBox<String> frequency = new ComboBox<>();
        String[] frequencies = {"Daily", "Weekly", "Monthly", "On Demand"};
        frequency.setItems(frequencies);
        frequency.setValue(task.getFrequency());
        frequency.setWidthFull();

        TimePicker scheduledTime = new TimePicker();
        scheduledTime.setValue(task.getScheduledTime());
        scheduledTime.setWidthFull();

        TextField duration = new TextField();
        duration.setValue(task.getDuration());
        duration.setWidthFull();

        DatePicker lastRun = new DatePicker();
        lastRun.setValue(task.getLastRun());
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
}