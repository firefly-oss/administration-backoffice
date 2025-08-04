package com.vaadin.starter.business.ui.views.masterdata.legalform;

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
import java.util.List;

@Route(value = "legal-forms", layout = MainLayout.class)
@PageTitle("Legal Forms")
public class LegalForms extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<LegalForm> grid;
    private Registration resizeListener;
    private ListDataProvider<LegalForm> dataProvider;
    private UI ui;

    // Search form fields
    private TextField codeFilter;
    private TextField nameFilter;
    private TextField descriptionFilter;
    private ComboBox<Long> countryIdFilter;
    private ComboBox<String> isCorporateFilter;
    private TextField entityTypeFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public LegalForms() {
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

        nameFilter = new TextField();
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        countryIdFilter = new ComboBox<>();
        countryIdFilter.setItems(1L, 2L, 3L, 4L, 5L); // Mock country IDs
        countryIdFilter.setClearButtonVisible(true);

        isCorporateFilter = new ComboBox<>();
        isCorporateFilter.setItems("Yes", "No");
        isCorporateFilter.setClearButtonVisible(true);

        entityTypeFilter = new TextField();
        entityTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        entityTypeFilter.setClearButtonVisible(true);

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

        Button createLegalFormButton = UIUtils.createSuccessButton("Create Legal Form");
        createLegalFormButton.addClickListener(e -> openCreateLegalFormDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Legal Form on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createLegalFormButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(codeFilter, "Code");
        formLayout.addFormItem(nameFilter, "Name");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(countryIdFilter, "Country");
        formLayout.addFormItem(isCorporateFilter, "Is Corporate");
        formLayout.addFormItem(entityTypeFilter, "Entity Type");
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

    private Grid<LegalForm> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("legal-forms");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(LegalForm::getCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Code")
                .setSortable(true);
        grid.addColumn(LegalForm::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        grid.addColumn(LegalForm::getDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(LegalForm::getCountryId)
                .setAutoWidth(true)
                .setHeader("Country ID")
                .setSortable(true);
        grid.addColumn(LegalForm::getIsCorporate)
                .setAutoWidth(true)
                .setHeader("Is Corporate")
                .setSortable(true);
        grid.addColumn(LegalForm::getEntityType)
                .setAutoWidth(true)
                .setHeader("Entity Type")
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
        dataProvider = DataProvider.ofCollection(getMockLegalForms());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(LegalForm legalForm) {
        Icon icon;
        if (legalForm.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(LegalForm legalForm) {
        return new Span(UIUtils.formatDate(legalForm.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(LegalForm legalForm) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(legalForm));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteLegalForm(legalForm));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(LegalForm legalForm) {
        LegalFormDetails legalFormDetails = new LegalFormDetails(legalForm);
        legalFormDetails.open();
    }

    private void deleteLegalForm(LegalForm legalForm) {
        // This would be implemented to delete the legal form
        System.out.println("[DEBUG_LOG] Delete legal form: " + legalForm.getCode());
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
            dataProvider.addFilter(legalForm -> 
                legalForm.getCode() != null &&
                legalForm.getCode().toLowerCase().contains(codeFilterValue));
        }

        // Apply name filter if not empty
        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            String nameFilterValue = nameFilter.getValue().toLowerCase();
            dataProvider.addFilter(legalForm -> 
                legalForm.getName() != null &&
                legalForm.getName().toLowerCase().contains(nameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(legalForm -> 
                legalForm.getDescription() != null &&
                legalForm.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply country ID filter if selected
        if (countryIdFilter.getValue() != null) {
            Long countryId = countryIdFilter.getValue();
            dataProvider.addFilter(legalForm -> 
                legalForm.getCountryId() != null && legalForm.getCountryId().equals(countryId));
        }

        // Apply is corporate filter if selected
        if (isCorporateFilter.getValue() != null) {
            boolean isCorporate = "Yes".equals(isCorporateFilter.getValue());
            dataProvider.addFilter(legalForm -> 
                legalForm.getIsCorporate() != null && legalForm.getIsCorporate() == isCorporate);
        }

        // Apply entity type filter if not empty
        if (entityTypeFilter.getValue() != null && !entityTypeFilter.getValue().isEmpty()) {
            String entityTypeFilterValue = entityTypeFilter.getValue().toLowerCase();
            dataProvider.addFilter(legalForm -> 
                legalForm.getEntityType() != null &&
                legalForm.getEntityType().toLowerCase().contains(entityTypeFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(legalForm -> 
                legalForm.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(legalForm -> 
                legalForm.getDateCreated() != null && 
                !legalForm.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        codeFilter.clear();
        nameFilter.clear();
        descriptionFilter.clear();
        countryIdFilter.clear();
        isCorporateFilter.clear();
        entityTypeFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateLegalFormDialog() {
        // This would be implemented to open a dialog for creating a new legal form
        System.out.println("[DEBUG_LOG] Create legal form dialog would open here");
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
        List<Grid.Column<LegalForm>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<LegalForm> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<LegalForm> getMockLegalForms() {
        List<LegalForm> legalForms = new ArrayList<>();

        legalForms.add(new LegalForm(1L, 1L, "LLC", "Limited Liability Company", "A company where owners are not personally liable for debts", true, "Corporation", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        legalForms.add(new LegalForm(2L, 1L, "CORP", "Corporation", "A legal entity separate from its owners", true, "Corporation", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        legalForms.add(new LegalForm(3L, 1L, "SP", "Sole Proprietorship", "Business owned and run by one person", false, "Individual", Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        legalForms.add(new LegalForm(4L, 2L, "LTD", "Limited Company", "A private company with limited liability", true, "Corporation", Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        legalForms.add(new LegalForm(5L, 2L, "PLC", "Public Limited Company", "A company that offers shares to the public", true, "Corporation", Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        legalForms.add(new LegalForm(6L, 3L, "GMBH", "Gesellschaft mit beschränkter Haftung", "German limited liability company", true, "Corporation", Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        legalForms.add(new LegalForm(7L, 3L, "AG", "Aktiengesellschaft", "German public company", true, "Corporation", Status.ACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        legalForms.add(new LegalForm(8L, 4L, "SAS", "Société par actions simplifiée", "French simplified joint-stock company", true, "Corporation", Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        legalForms.add(new LegalForm(9L, 5L, "SRL", "Sociedad de Responsabilidad Limitada", "Spanish limited liability company", true, "Corporation", Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        legalForms.add(new LegalForm(10L, 5L, "SA", "Sociedad Anónima", "Spanish public limited company", true, "Corporation", Status.INACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        return legalForms;
    }

    // LegalForm model class
    public static class LegalForm {
        private Long legalFormId;
        private Long countryId;
        private String code;
        private String name;
        private String description;
        private Boolean isCorporate;
        private String entityType;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public LegalForm(Long legalFormId, Long countryId, String code, String name, 
                      String description, Boolean isCorporate, String entityType, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.legalFormId = legalFormId;
            this.countryId = countryId;
            this.code = code;
            this.name = name;
            this.description = description;
            this.isCorporate = isCorporate;
            this.entityType = entityType;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getLegalFormId() {
            return legalFormId;
        }

        public Long getCountryId() {
            return countryId;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Boolean getIsCorporate() {
            return isCorporate;
        }

        public String getEntityType() {
            return entityType;
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