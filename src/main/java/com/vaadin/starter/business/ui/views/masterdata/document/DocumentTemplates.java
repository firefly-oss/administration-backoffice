package com.vaadin.starter.business.ui.views.masterdata.document;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "document-templates", layout = MainLayout.class)
@PageTitle("Document Templates")
public class DocumentTemplates extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<DocumentTemplate> grid;
    private Registration resizeListener;
    private ListDataProvider<DocumentTemplate> dataProvider;
    private UI ui;

    // Search form fields
    private TextField templateCodeFilter;
    private TextField templateNameFilter;
    private TextField categoryFilter;
    private ComboBox<String> templateTypeFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public DocumentTemplates() {
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
        templateCodeFilter = new TextField();
        templateCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        templateCodeFilter.setClearButtonVisible(true);

        templateNameFilter = new TextField();
        templateNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        templateNameFilter.setClearButtonVisible(true);

        categoryFilter = new TextField();
        categoryFilter.setValueChangeMode(ValueChangeMode.EAGER);
        categoryFilter.setClearButtonVisible(true);

        templateTypeFilter = new ComboBox<>();
        templateTypeFilter.setItems("Email Template", "PDF Template", "Contract Template", "Form Template");
        templateTypeFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        creationDateFilter = new DatePicker();
        creationDateFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createTemplateButton = UIUtils.createSuccessButton("Create Document Template");
        createTemplateButton.addClickListener(e -> openCreateTemplateDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Document Template on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createTemplateButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(templateCodeFilter, "Template Code");
        formLayout.addFormItem(templateNameFilter, "Template Name");
        formLayout.addFormItem(categoryFilter, "Category");
        formLayout.addFormItem(templateTypeFilter, "Template Type");
        formLayout.addFormItem(statusFilter, "Status");
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

    private Grid<DocumentTemplate> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("document-templates");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(DocumentTemplate::getTemplateCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Template Code")
                .setSortable(true);
        grid.addColumn(DocumentTemplate::getTemplateName)
                .setAutoWidth(true)
                .setHeader("Template Name")
                .setSortable(true);
        grid.addColumn(DocumentTemplate::getCategory)
                .setAutoWidth(true)
                .setHeader("Category")
                .setSortable(true);
        grid.addColumn(DocumentTemplate::getTemplateTypeName)
                .setAutoWidth(true)
                .setHeader("Template Type")
                .setSortable(true);
        grid.addColumn(DocumentTemplate::getVersion)
                .setAutoWidth(true)
                .setHeader("Version")
                .setSortable(true);
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
        dataProvider = DataProvider.ofCollection(getMockDocumentTemplates());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(DocumentTemplate template) {
        Icon icon;
        if (template.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(DocumentTemplate template) {
        return new Span(UIUtils.formatDate(template.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(DocumentTemplate template) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(template));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteTemplate(template));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(DocumentTemplate template) {
        DocumentTemplateDetails templateDetails = new DocumentTemplateDetails(template);
        templateDetails.open();
    }

    private void deleteTemplate(DocumentTemplate template) {
        // This would be implemented to delete the template
        System.out.println("[DEBUG_LOG] Delete document template: " + template.getTemplateCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply template code filter if not empty
        if (templateCodeFilter.getValue() != null && !templateCodeFilter.getValue().isEmpty()) {
            String templateCodeFilterValue = templateCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(template -> 
                template.getTemplateCode() != null &&
                template.getTemplateCode().toLowerCase().contains(templateCodeFilterValue));
        }

        // Apply template name filter if not empty
        if (templateNameFilter.getValue() != null && !templateNameFilter.getValue().isEmpty()) {
            String templateNameFilterValue = templateNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(template -> 
                template.getTemplateName() != null &&
                template.getTemplateName().toLowerCase().contains(templateNameFilterValue));
        }

        // Apply category filter if not empty
        if (categoryFilter.getValue() != null && !categoryFilter.getValue().isEmpty()) {
            String categoryFilterValue = categoryFilter.getValue().toLowerCase();
            dataProvider.addFilter(template -> 
                template.getCategory() != null &&
                template.getCategory().toLowerCase().contains(categoryFilterValue));
        }

        // Apply template type filter if selected
        if (templateTypeFilter.getValue() != null) {
            String templateTypeFilterValue = templateTypeFilter.getValue();
            dataProvider.addFilter(template -> 
                template.getTemplateTypeName() != null &&
                template.getTemplateTypeName().equals(templateTypeFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(template -> 
                template.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(template -> 
                template.getDateCreated() != null && 
                !template.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        templateCodeFilter.clear();
        templateNameFilter.clear();
        categoryFilter.clear();
        templateTypeFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateTemplateDialog() {
        // This would be implemented to open a dialog for creating a new document template
        System.out.println("[DEBUG_LOG] Create document template dialog would open here");
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
        List<Grid.Column<DocumentTemplate>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<DocumentTemplate> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<DocumentTemplate> getMockDocumentTemplates() {
        List<DocumentTemplate> templates = new ArrayList<>();

        templates.add(new DocumentTemplate(1L, "TPL-001", "Welcome Email", "Email", 1L, "Email Template", "Marketing", "Welcome email for new customers", "Welcome to our service", "<html><body>Welcome to our service!</body></html>", createVariables(), "1.0", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        templates.add(new DocumentTemplate(2L, "TPL-002", "Invoice Template", "PDF", 2L, "PDF Template", "Billing", "Standard invoice template", "Invoice", "<html><body>Invoice details</body></html>", createVariables(), "2.1", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        templates.add(new DocumentTemplate(3L, "TPL-003", "Contract Agreement", "Legal", 3L, "Contract Template", "Legal", "Standard contract agreement", "Contract Agreement", "<html><body>Contract terms</body></html>", createVariables(), "1.2", Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        templates.add(new DocumentTemplate(4L, "TPL-004", "Password Reset", "Email", 1L, "Email Template", "System", "Password reset email", "Reset Your Password", "<html><body>Click here to reset your password</body></html>", createVariables(), "1.0", Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        templates.add(new DocumentTemplate(5L, "TPL-005", "Feedback Form", "Form", 4L, "Form Template", "Customer Service", "Customer feedback form", "Feedback Form", "<html><body>Please provide your feedback</body></html>", createVariables(), "1.1", Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        templates.add(new DocumentTemplate(6L, "TPL-006", "Account Statement", "PDF", 2L, "PDF Template", "Billing", "Monthly account statement", "Account Statement", "<html><body>Account statement details</body></html>", createVariables(), "1.3", Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        templates.add(new DocumentTemplate(7L, "TPL-007", "Subscription Confirmation", "Email", 1L, "Email Template", "Marketing", "Subscription confirmation email", "Subscription Confirmed", "<html><body>Thank you for subscribing</body></html>", createVariables(), "1.0", Status.INACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        templates.add(new DocumentTemplate(8L, "TPL-008", "Terms and Conditions", "Legal", 3L, "Contract Template", "Legal", "Terms and conditions document", "Terms and Conditions", "<html><body>Terms and conditions content</body></html>", createVariables(), "2.0", Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        templates.add(new DocumentTemplate(9L, "TPL-009", "Order Confirmation", "Email", 1L, "Email Template", "Sales", "Order confirmation email", "Order Confirmed", "<html><body>Your order has been confirmed</body></html>", createVariables(), "1.1", Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        templates.add(new DocumentTemplate(10L, "TPL-010", "Receipt Template", "PDF", 2L, "PDF Template", "Billing", "Receipt template for purchases", "Receipt", "<html><body>Receipt details</body></html>", createVariables(), "1.0", Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        return templates;
    }

    private Map<String, Object> createVariables() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", "Customer Name");
        variables.put("orderNumber", "Order Number");
        variables.put("date", "Current Date");
        return variables;
    }

    // DocumentTemplate model class
    public static class DocumentTemplate {
        private Long templateId;
        private String templateCode;
        private String templateName;
        private String category;
        private Long typeId;
        private String templateTypeName;
        private String description;
        private String templateContent;
        private Map<String, Object> templateVariables;
        private String version;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public DocumentTemplate(Long templateId, String templateCode, String templateName, String category,
                      Long typeId, String templateTypeName, String description, String templateContent,
                      String templateName2, String templateContent2, Map<String, Object> templateVariables, 
                      String version, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.templateId = templateId;
            this.templateCode = templateCode;
            this.templateName = templateName;
            this.category = category;
            this.typeId = typeId;
            this.templateTypeName = templateTypeName;
            this.description = description;
            this.templateContent = templateContent;
            this.templateVariables = templateVariables;
            this.version = version;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getTemplateId() {
            return templateId;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public String getTemplateName() {
            return templateName;
        }

        public String getCategory() {
            return category;
        }

        public Long getTypeId() {
            return typeId;
        }

        public String getTemplateTypeName() {
            return templateTypeName;
        }

        public String getDescription() {
            return description;
        }

        public String getTemplateContent() {
            return templateContent;
        }

        public Map<String, Object> getTemplateVariables() {
            return templateVariables;
        }

        public String getVersion() {
            return version;
        }

        public Status getStatus() {
            return status;
        }

        public boolean isActive() {
            return status == Status.ACTIVE;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }
    }
}
