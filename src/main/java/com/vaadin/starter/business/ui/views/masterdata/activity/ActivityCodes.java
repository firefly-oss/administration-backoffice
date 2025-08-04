package com.vaadin.starter.business.ui.views.masterdata.activity;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Route(value = "activity-codes", layout = MainLayout.class)
@PageTitle("Activity Codes")
public class ActivityCodes extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<ActivityCode> grid;
    private Registration resizeListener;
    private ListDataProvider<ActivityCode> dataProvider;
    private UI ui;

    // Search form fields
    private TextField codeFilter;
    private TextField descriptionFilter;
    private TextField classificationSysFilter;
    private ComboBox<String> statusFilter;
    private ComboBox<String> highRiskFilter;
    private TextField countryIdFilter;
    private DatePicker creationDateFilter;

    public ActivityCodes() {
        setViewContent(createContent());

        // Initialize with default filter
        filter();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createFilterForm() {
        // Initialize filter fields
        codeFilter = new TextField();
        codeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        codeFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        classificationSysFilter = new TextField();
        classificationSysFilter.setValueChangeMode(ValueChangeMode.EAGER);
        classificationSysFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        highRiskFilter = new ComboBox<>();
        highRiskFilter.setItems("Yes", "No");
        highRiskFilter.setClearButtonVisible(true);

        countryIdFilter = new TextField();
        countryIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        countryIdFilter.setClearButtonVisible(true);

        creationDateFilter = new DatePicker();
        creationDateFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createActivityCodeButton = UIUtils.createSuccessButton("Create Activity Code");
        createActivityCodeButton.addClickListener(e -> openCreateActivityCodeDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Activity Code on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createActivityCodeButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(codeFilter, "Code");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(classificationSysFilter, "Classification");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(highRiskFilter, "High Risk");
        formLayout.addFormItem(countryIdFilter, "Country ID");
        formLayout.addFormItem(creationDateFilter, "Creation Date After");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        // Create a container for the form and buttons
        Div formContainer = new Div(formLayout, buttonLayout);
        formContainer.getStyle().set("padding", "1em");
        formContainer.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
        formContainer.getStyle().set("border-radius", "var(--lumo-border-radius)");
        formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
        formContainer.getStyle().set("margin-bottom", "1em");

        return formContainer;
    }

    private Grid<ActivityCode> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("activity-codes");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(ActivityCode::getCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Code")
                .setSortable(true);
        grid.addColumn(ActivityCode::getDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(ActivityCode::getClassificationSys)
                .setAutoWidth(true)
                .setHeader("Classification")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createHighRisk))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("High Risk")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createActive))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Creation Date")
                .setTextAlign(ColumnTextAlign.END);

        // Add Actions column with view and delete buttons
        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Initialize with data provider
        dataProvider = DataProvider.ofCollection(getMockActivityCodes());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createHighRisk(ActivityCode activityCode) {
        Icon icon;
        if (activityCode.getHighRisk() != null && activityCode.getHighRisk()) {
            icon = UIUtils.createErrorIcon(VaadinIcon.WARNING);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CHECK);
        }
        return icon;
    }

    private Component createActive(ActivityCode activityCode) {
        Icon icon;
        if (activityCode.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(ActivityCode activityCode) {
        return new Span(UIUtils.formatDate(activityCode.getCreationDate()));
    }

    private Component createActionButtons(ActivityCode activityCode) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(activityCode));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteActivityCode(activityCode));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(ActivityCode activityCode) {
        ActivityCodeDetails activityCodeDetails = new ActivityCodeDetails(activityCode);
        activityCodeDetails.open();
    }

    private void deleteActivityCode(ActivityCode activityCode) {
        // This would be implemented to delete the activity code
        System.out.println("[DEBUG_LOG] Delete activity code: " + activityCode.getCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply code filter if not empty
        if (codeFilter.getValue() != null && !codeFilter.getValue().isEmpty()) {
            String codeFilterValue = codeFilter.getValue().toLowerCase();
            dataProvider.addFilter(activityCode -> 
                activityCode.getCode().toLowerCase().contains(codeFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(activityCode -> 
                activityCode.getDescription() != null &&
                activityCode.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply classification filter if not empty
        if (classificationSysFilter.getValue() != null && !classificationSysFilter.getValue().isEmpty()) {
            String classificationFilterValue = classificationSysFilter.getValue().toLowerCase();
            dataProvider.addFilter(activityCode -> 
                activityCode.getClassificationSys() != null &&
                activityCode.getClassificationSys().toLowerCase().contains(classificationFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(activityCode -> 
                activityCode.isActive() == isActive);
        }

        // Apply high risk filter if selected
        if (highRiskFilter.getValue() != null) {
            boolean isHighRisk = "Yes".equals(highRiskFilter.getValue());
            dataProvider.addFilter(activityCode -> 
                activityCode.getHighRisk() != null && activityCode.getHighRisk() == isHighRisk);
        }

        // Apply country ID filter if not empty
        if (countryIdFilter.getValue() != null && !countryIdFilter.getValue().isEmpty()) {
            try {
                Long countryId = Long.parseLong(countryIdFilter.getValue());
                dataProvider.addFilter(activityCode -> 
                    activityCode.getCountryId() != null && activityCode.getCountryId().equals(countryId));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            dataProvider.addFilter(activityCode -> 
                activityCode.getCreationDate() != null && 
                !activityCode.getCreationDate().isBefore(creationDateFilter.getValue()));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        codeFilter.clear();
        descriptionFilter.clear();
        classificationSysFilter.clear();
        statusFilter.clear();
        highRiskFilter.clear();
        countryIdFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateActivityCodeDialog() {
        // This would be implemented to open a dialog for creating a new activity code
        System.out.println("[DEBUG_LOG] Create activity code dialog would open here");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(currentUI -> {
            this.ui = currentUI;
            Page page = currentUI.getPage();
            resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        if (resizeListener != null) {
            resizeListener.remove();
        }
        super.onDetach(detachEvent);
    }

    private void updateVisibleColumns(int width) {
        boolean mobile = width < MOBILE_BREAKPOINT;
        List<Grid.Column<ActivityCode>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<ActivityCode> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<ActivityCode> getMockActivityCodes() {
        List<ActivityCode> activityCodes = new ArrayList<>();
        Random random = new Random();

        activityCodes.add(new ActivityCode(1L, 1L, "AC001", "NAICS", "Customer Onboarding", null, false, null, true, LocalDate.now().minusDays(30)));
        activityCodes.add(new ActivityCode(2L, 1L, "AC002", "NAICS", "Account Verification", null, false, null, true, LocalDate.now().minusDays(25)));
        activityCodes.add(new ActivityCode(3L, 1L, "AC003", "NAICS", "Document Processing", null, false, null, false, LocalDate.now().minusDays(20)));
        activityCodes.add(new ActivityCode(4L, 1L, "AC004", "NAICS", "Credit Assessment", null, true, "High debt ratio", true, LocalDate.now().minusDays(15)));
        activityCodes.add(new ActivityCode(5L, 1L, "AC005", "NAICS", "Loan Approval", null, false, null, true, LocalDate.now().minusDays(10)));
        activityCodes.add(new ActivityCode(6L, 1L, "AC006", "NAICS", "Payment Processing", null, false, null, false, LocalDate.now().minusDays(5)));
        activityCodes.add(new ActivityCode(7L, 1L, "AC007", "NAICS", "Customer Support", null, false, null, true, LocalDate.now().minusDays(3)));
        activityCodes.add(new ActivityCode(8L, 1L, "AC008", "NAICS", "Fraud Detection", null, true, "Suspicious patterns", true, LocalDate.now().minusDays(2)));
        activityCodes.add(new ActivityCode(9L, 1L, "AC009", "NAICS", "Account Closure", null, false, null, false, LocalDate.now().minusDays(1)));
        activityCodes.add(new ActivityCode(10L, 1L, "AC010", "NAICS", "Reporting", null, false, null, true, LocalDate.now()));

        return activityCodes;
    }

    // Activity Code model class
    public static class ActivityCode {
        private Long activityCodeId;
        private Long countryId;
        private String code;
        private String classificationSys;
        private String description;
        private Long parentCodeId;
        private Boolean highRisk;
        private String riskFactors;
        private boolean active; // Represents StatusEnum
        private LocalDate creationDate; // Added for UI purposes

        public ActivityCode(Long activityCodeId, Long countryId, String code, String classificationSys, 
                           String description, Long parentCodeId, Boolean highRisk, 
                           String riskFactors, boolean active, LocalDate creationDate) {
            this.activityCodeId = activityCodeId;
            this.countryId = countryId;
            this.code = code;
            this.classificationSys = classificationSys;
            this.description = description;
            this.parentCodeId = parentCodeId;
            this.highRisk = highRisk;
            this.riskFactors = riskFactors;
            this.active = active;
            this.creationDate = creationDate;
        }

        public Long getActivityCodeId() {
            return activityCodeId;
        }

        public Long getCountryId() {
            return countryId;
        }

        public String getCode() {
            return code;
        }

        public String getClassificationSys() {
            return classificationSys;
        }

        public String getDescription() {
            return description;
        }

        public Long getParentCodeId() {
            return parentCodeId;
        }

        public Boolean getHighRisk() {
            return highRisk;
        }

        public String getRiskFactors() {
            return riskFactors;
        }

        public boolean isActive() {
            return active;
        }

        public LocalDate getCreationDate() {
            return creationDate;
        }

        // For display purposes in the grid
        public String getName() {
            return description;
        }
    }
}