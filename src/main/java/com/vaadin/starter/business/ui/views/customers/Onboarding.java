package com.vaadin.starter.business.ui.views.customers;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
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

@Route(value = "onboarding", layout = MainLayout.class)
@PageTitle("Onboarding Processes")
public class Onboarding extends SplitViewFrame {

    private Grid<OnboardingProcess> grid;
    private ListDataProvider<OnboardingProcess> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Sample OnboardingProcess class for demonstration
    private static class OnboardingProcess {
        private String id;
        private String name;
        private String description;
        private String accountType;
        private int stepCount;
        private int completedSteps;
        private String status;
        private String assignedTo;
        private LocalDate createdDate;
        private LocalDate lastModified;

        public OnboardingProcess(String id, String name, String description, String accountType, 
                               int stepCount, int completedSteps, String status, String assignedTo, 
                               LocalDate createdDate, LocalDate lastModified) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.accountType = accountType;
            this.stepCount = stepCount;
            this.completedSteps = completedSteps;
            this.status = status;
            this.assignedTo = assignedTo;
            this.createdDate = createdDate;
            this.lastModified = lastModified;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getAccountType() {
            return accountType;
        }

        public int getStepCount() {
            return stepCount;
        }

        public int getCompletedSteps() {
            return completedSteps;
        }

        public double getCompletionPercentage() {
            return (double) completedSteps / stepCount;
        }

        public String getStatus() {
            return status;
        }

        public String getAssignedTo() {
            return assignedTo;
        }

        public LocalDate getCreatedDate() {
            return createdDate;
        }

        public LocalDate getLastModified() {
            return lastModified;
        }
    }

    // Sample data
    private List<OnboardingProcess> getOnboardingProcesses() {
        LocalDate now = LocalDate.now();
        return Arrays.asList(
            new OnboardingProcess("OBP001", "Personal Account Onboarding", 
                "Standard process for new personal account customers", 
                "Personal", 5, 5, "Completed", "Sarah Johnson", 
                now.minusDays(30), now.minusDays(15)),
            new OnboardingProcess("OBP002", "Business Account Onboarding", 
                "Process for new business account customers", 
                "Business", 7, 3, "In Progress", "Michael Brown", 
                now.minusDays(20), now.minusDays(2)),
            new OnboardingProcess("OBP003", "Premium Account Onboarding", 
                "Enhanced process for high-value customers", 
                "Premium", 6, 2, "In Progress", "Jennifer Lee", 
                now.minusDays(15), now.minusDays(1)),
            new OnboardingProcess("OBP004", "Student Account Onboarding", 
                "Simplified process for student accounts", 
                "Student", 4, 4, "Completed", "David Miller", 
                now.minusDays(45), now.minusDays(30)),
            new OnboardingProcess("OBP005", "Joint Account Onboarding", 
                "Process for joint account holders", 
                "Joint", 8, 5, "In Progress", "Patricia White", 
                now.minusDays(10), now.minusDays(1)),
            new OnboardingProcess("OBP006", "Senior Account Onboarding", 
                "Specialized process for senior citizens", 
                "Senior", 5, 0, "Not Started", "Robert Johnson", 
                now.minusDays(5), now.minusDays(5)),
            new OnboardingProcess("OBP007", "Digital-Only Onboarding", 
                "Fully digital onboarding process with no branch visit", 
                "Digital", 3, 1, "In Progress", "Lisa Taylor", 
                now.minusDays(3), now.minusDays(1))
        );
    }

    public Onboarding() {
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
        dataProvider = DataProvider.ofCollection(getOnboardingProcesses());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(OnboardingProcess::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(OnboardingProcess::getName)
                .setAutoWidth(true)
                .setHeader("Process Name")
                .setSortable(true);
        grid.addColumn(OnboardingProcess::getAccountType)
                .setAutoWidth(true)
                .setHeader("Account Type")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createProgressBar))
                .setAutoWidth(true)
                .setHeader("Progress")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(new ComponentRenderer<>(this::createStatusBadge))
                .setAutoWidth(true)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(OnboardingProcess::getAssignedTo)
                .setAutoWidth(true)
                .setHeader("Assigned To")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Modified")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createProgressBar(OnboardingProcess process) {
        ProgressBar progressBar = new ProgressBar();
        progressBar.setValue(process.getCompletionPercentage());
        progressBar.setWidth("100px");
        
        Span progressText = new Span(process.getCompletedSteps() + "/" + process.getStepCount());
        progressText.getStyle().set("margin-left", "8px");
        
        FlexBoxLayout layout = new FlexBoxLayout(progressBar, progressText);
        layout.setAlignItems(FlexLayout.Alignment.CENTER);
        return layout;
    }

    private Component createStatusBadge(OnboardingProcess process) {
        String status = process.getStatus();
        String theme = "";
        
        if ("Completed".equals(status)) {
            theme = "success";
        } else if ("In Progress".equals(status)) {
            theme = "warning";
        } else if ("Not Started".equals(status)) {
            theme = "contrast";
        } else if ("On Hold".equals(status)) {
            theme = "error";
        }
        
        Span badge = new Span(status);
        badge.getElement().setAttribute("theme", "badge " + theme);
        return badge;
    }

    private Component createDate(OnboardingProcess process) {
        return new Span(UIUtils.formatDate(process.getLastModified()));
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

    private void showDetails(OnboardingProcess process) {
        detailsDrawerHeader.setTitle(process.getName());
        detailsDrawer.setContent(createDetails(process));
        detailsDrawer.show();
    }

    private FormLayout createDetails(OnboardingProcess process) {
        TextField id = new TextField();
        id.setValue(process.getId());
        id.setWidthFull();

        TextField name = new TextField();
        name.setValue(process.getName());
        name.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(process.getDescription());
        description.setWidthFull();
        description.setHeight("100px");

        ComboBox<String> accountType = new ComboBox<>();
        accountType.setItems("Personal", "Business", "Premium", "Student", "Joint", "Senior", "Digital");
        accountType.setValue(process.getAccountType());
        accountType.setWidthFull();

        NumberField stepCount = new NumberField();
        stepCount.setValue((double) process.getStepCount());
        stepCount.setWidthFull();

        NumberField completedSteps = new NumberField();
        completedSteps.setValue((double) process.getCompletedSteps());
        completedSteps.setWidthFull();

        ComboBox<String> status = new ComboBox<>();
        status.setItems("Not Started", "In Progress", "On Hold", "Completed");
        status.setValue(process.getStatus());
        status.setWidthFull();

        TextField assignedTo = new TextField();
        assignedTo.setValue(process.getAssignedTo());
        assignedTo.setWidthFull();

        // Process steps (simplified representation)
        ComboBox<String> currentStep = new ComboBox<>();
        currentStep.setItems("Identity Verification", "Document Collection", "Account Setup", 
                           "Card Issuance", "Digital Banking Setup", "Welcome Call", "Follow-up");
        currentStep.setValue("Document Collection");
        currentStep.setWidthFull();

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
        form.addFormItem(id, "Process ID");
        form.addFormItem(name, "Process Name");
        form.addFormItem(description, "Description");
        form.addFormItem(accountType, "Account Type");
        form.addFormItem(stepCount, "Total Steps");
        form.addFormItem(completedSteps, "Completed Steps");
        form.addFormItem(status, "Status");
        form.addFormItem(assignedTo, "Assigned To");
        form.addFormItem(currentStep, "Current Step");
        
        return form;
    }
}