package com.vaadin.starter.business.ui.views.finance;

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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
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
import java.util.Arrays;
import java.util.List;

@Route(value = "financial-calendar", layout = MainLayout.class)
@PageTitle("Financial Calendar")
public class FinancialCalendar extends SplitViewFrame {

    private Grid<FinancialEvent> grid;
    private ListDataProvider<FinancialEvent> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Sample FinancialEvent class for demonstration
    private static class FinancialEvent {
        private String id;
        private String title;
        private String description;
        private LocalDate date;
        private String category;
        private double amount;
        private String status;
        private String assignedTo;

        public FinancialEvent(String id, String title, String description, LocalDate date, 
                            String category, double amount, String status, String assignedTo) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.date = date;
            this.category = category;
            this.amount = amount;
            this.status = status;
            this.assignedTo = assignedTo;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }

        public String getAssignedTo() {
            return assignedTo;
        }
    }

    // Sample data
    private List<FinancialEvent> getFinancialEvents() {
        LocalDate now = LocalDate.now();
        return Arrays.asList(
            new FinancialEvent("FE001", "Quarterly Tax Payment", 
                "Q1 corporate tax payment", 
                now.plusDays(15), "Tax", 12500.00, "Pending", "Sarah Johnson"),
            new FinancialEvent("FE002", "Annual Audit", 
                "Annual financial audit with external auditors", 
                now.plusDays(30), "Audit", 0.00, "Scheduled", "Michael Brown"),
            new FinancialEvent("FE003", "Dividend Payment", 
                "Q2 dividend payment to shareholders", 
                now.plusDays(45), "Dividend", 50000.00, "Pending", "Jennifer Lee"),
            new FinancialEvent("FE004", "Budget Planning", 
                "Annual budget planning meeting", 
                now.plusDays(7), "Planning", 0.00, "Scheduled", "David Miller"),
            new FinancialEvent("FE005", "Loan Payment", 
                "Monthly loan payment", 
                now.plusDays(3), "Loan", 8750.00, "Pending", "Patricia White"),
            new FinancialEvent("FE006", "Financial Report", 
                "Monthly financial report preparation", 
                now.plusDays(10), "Reporting", 0.00, "In Progress", "Robert Johnson"),
            new FinancialEvent("FE007", "Payroll Processing", 
                "Bi-weekly payroll processing", 
                now.plusDays(2), "Payroll", 125000.00, "Pending", "Lisa Taylor")
        );
    }

    public FinancialCalendar() {
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
        dataProvider = DataProvider.ofCollection(getFinancialEvents());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(FinancialEvent::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(FinancialEvent::getTitle)
                .setAutoWidth(true)
                .setHeader("Event Title")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createDateInfo))
                .setAutoWidth(true)
                .setHeader("Date")
                .setSortable(true);
        grid.addColumn(FinancialEvent::getCategory)
                .setAutoWidth(true)
                .setHeader("Category")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createAmountInfo))
                .setAutoWidth(true)
                .setHeader("Amount")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createStatusBadge))
                .setAutoWidth(true)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(FinancialEvent::getAssignedTo)
                .setAutoWidth(true)
                .setHeader("Assigned To")
                .setSortable(true);

        return grid;
    }

    private Component createDateInfo(FinancialEvent event) {
        return new Span(UIUtils.formatDate(event.getDate()));
    }

    private Component createAmountInfo(FinancialEvent event) {
        if (event.getAmount() == 0.0) {
            return new Span("N/A");
        } else {
            return new Span(UIUtils.formatAmount(event.getAmount()));
        }
    }

    private Component createStatusBadge(FinancialEvent event) {
        String status = event.getStatus();
        String theme = "";
        
        if ("Completed".equals(status)) {
            theme = "success";
        } else if ("Pending".equals(status)) {
            theme = "warning";
        } else if ("Scheduled".equals(status)) {
            theme = "contrast";
        } else if ("In Progress".equals(status)) {
            theme = "primary";
        } else if ("Cancelled".equals(status)) {
            theme = "error";
        }
        
        Span badge = new Span(status);
        badge.getElement().setAttribute("theme", "badge " + theme);
        return badge;
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
            UIUtils.showNotification("Changes saved.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(FinancialEvent event) {
        detailsDrawerHeader.setTitle(event.getTitle());
        detailsDrawer.setContent(createDetails(event));
        detailsDrawer.show();
    }

    private FormLayout createDetails(FinancialEvent event) {
        TextField id = new TextField();
        id.setValue(event.getId());
        id.setWidthFull();

        TextField title = new TextField();
        title.setValue(event.getTitle());
        title.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(event.getDescription());
        description.setWidthFull();
        description.setHeight("100px");

        DatePicker date = new DatePicker();
        date.setValue(event.getDate());
        date.setWidthFull();

        ComboBox<String> category = new ComboBox<>();
        category.setItems("Tax", "Audit", "Dividend", "Planning", "Loan", "Reporting", "Payroll", "Investment", "Budget");
        category.setValue(event.getCategory());
        category.setWidthFull();

        NumberField amount = new NumberField();
        amount.setValue(event.getAmount());
        amount.setWidthFull();
        amount.setPrefixComponent(new Span("$"));

        ComboBox<String> status = new ComboBox<>();
        status.setItems("Pending", "In Progress", "Completed", "Scheduled", "Cancelled");
        status.setValue(event.getStatus());
        status.setWidthFull();

        TextField assignedTo = new TextField();
        assignedTo.setValue(event.getAssignedTo());
        assignedTo.setWidthFull();

        // Notification settings
        RadioButtonGroup<String> notifications = new RadioButtonGroup<>();
        notifications.setLabel("Notifications");
        notifications.setItems("None", "Email", "SMS", "Both");
        notifications.setValue("Email");

        // Recurrence settings
        ComboBox<String> recurrence = new ComboBox<>();
        recurrence.setLabel("Recurrence");
        recurrence.setItems("None", "Daily", "Weekly", "Monthly", "Quarterly", "Annually");
        recurrence.setValue("None");
        recurrence.setWidthFull();

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
        form.addFormItem(id, "Event ID");
        form.addFormItem(title, "Event Title");
        form.addFormItem(description, "Description");
        form.addFormItem(date, "Date");
        form.addFormItem(category, "Category");
        form.addFormItem(amount, "Amount");
        form.addFormItem(status, "Status");
        form.addFormItem(assignedTo, "Assigned To");
        form.add(notifications);
        form.addFormItem(recurrence, "Recurrence");
        
        return form;
    }
}