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
import java.util.List;

@Route(value = "document-template-localizations", layout = MainLayout.class)
@PageTitle("Document Template Localizations")
public class DocumentTemplateLocalizations extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<DocumentTemplateLocalization> grid;
    private Registration resizeListener;
    private ListDataProvider<DocumentTemplateLocalization> dataProvider;
    private UI ui;

    // Search form fields
    private TextField templateIdFilter;
    private TextField localeIdFilter;
    private TextField templateNameFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public DocumentTemplateLocalizations() {
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
        templateIdFilter = new TextField();
        templateIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        templateIdFilter.setClearButtonVisible(true);

        localeIdFilter = new TextField();
        localeIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        localeIdFilter.setClearButtonVisible(true);

        templateNameFilter = new TextField();
        templateNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        templateNameFilter.setClearButtonVisible(true);

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

        Button createLocalizationButton = UIUtils.createSuccessButton("Create Template Localization");
        createLocalizationButton.addClickListener(e -> openCreateLocalizationDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Template Localization on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createLocalizationButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(templateIdFilter, "Template ID");
        formLayout.addFormItem(localeIdFilter, "Locale ID");
        formLayout.addFormItem(templateNameFilter, "Template Name");
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

    private Grid<DocumentTemplateLocalization> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("document-template-localizations");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(DocumentTemplateLocalization::getLocalizationId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Localization ID")
                .setSortable(true);
        grid.addColumn(DocumentTemplateLocalization::getTemplateId)
                .setAutoWidth(true)
                .setHeader("Template ID")
                .setSortable(true);
        grid.addColumn(DocumentTemplateLocalization::getLocaleId)
                .setAutoWidth(true)
                .setHeader("Locale ID")
                .setSortable(true);
        grid.addColumn(DocumentTemplateLocalization::getTemplateName)
                .setAutoWidth(true)
                .setHeader("Template Name")
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
        dataProvider = DataProvider.ofCollection(getMockDocumentTemplateLocalizations());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(DocumentTemplateLocalization localization) {
        Icon icon;
        if (localization.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(DocumentTemplateLocalization localization) {
        return new Span(UIUtils.formatDate(localization.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(DocumentTemplateLocalization localization) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(localization));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteLocalization(localization));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(DocumentTemplateLocalization localization) {
        DocumentTemplateLocalizationDetails localizationDetails = new DocumentTemplateLocalizationDetails(localization);
        localizationDetails.open();
    }

    private void deleteLocalization(DocumentTemplateLocalization localization) {
        // This would be implemented to delete the localization
        System.out.println("[DEBUG_LOG] Delete document template localization: " + localization.getLocalizationId());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply template ID filter if not empty
        if (templateIdFilter.getValue() != null && !templateIdFilter.getValue().isEmpty()) {
            try {
                Long templateIdFilterValue = Long.parseLong(templateIdFilter.getValue());
                dataProvider.addFilter(localization -> 
                    localization.getTemplateId() != null &&
                    localization.getTemplateId().equals(templateIdFilterValue));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply locale ID filter if not empty
        if (localeIdFilter.getValue() != null && !localeIdFilter.getValue().isEmpty()) {
            try {
                Long localeIdFilterValue = Long.parseLong(localeIdFilter.getValue());
                dataProvider.addFilter(localization -> 
                    localization.getLocaleId() != null &&
                    localization.getLocaleId().equals(localeIdFilterValue));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply template name filter if not empty
        if (templateNameFilter.getValue() != null && !templateNameFilter.getValue().isEmpty()) {
            String templateNameFilterValue = templateNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(localization -> 
                localization.getTemplateName() != null &&
                localization.getTemplateName().toLowerCase().contains(templateNameFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(localization -> 
                localization.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(localization -> 
                localization.getDateCreated() != null && 
                !localization.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        templateIdFilter.clear();
        localeIdFilter.clear();
        templateNameFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateLocalizationDialog() {
        // This would be implemented to open a dialog for creating a new document template localization
        System.out.println("[DEBUG_LOG] Create document template localization dialog would open here");
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
        List<Grid.Column<DocumentTemplateLocalization>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<DocumentTemplateLocalization> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<DocumentTemplateLocalization> getMockDocumentTemplateLocalizations() {
        List<DocumentTemplateLocalization> localizations = new ArrayList<>();

        localizations.add(new DocumentTemplateLocalization(1L, 1L, 1L, "Welcome Email - English", "<html><body>Welcome to our service!</body></html>", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        localizations.add(new DocumentTemplateLocalization(2L, 1L, 2L, "Welcome Email - Spanish", "<html><body>¡Bienvenido a nuestro servicio!</body></html>", Status.ACTIVE, LocalDateTime.now().minusDays(29), LocalDateTime.now().minusDays(4)));
        localizations.add(new DocumentTemplateLocalization(3L, 2L, 1L, "Invoice Template - English", "<html><body>Invoice details in English</body></html>", Status.ACTIVE, LocalDateTime.now().minusDays(28), LocalDateTime.now().minusDays(3)));
        localizations.add(new DocumentTemplateLocalization(4L, 2L, 2L, "Invoice Template - Spanish", "<html><body>Detalles de factura en español</body></html>", Status.ACTIVE, LocalDateTime.now().minusDays(27), LocalDateTime.now().minusDays(2)));
        localizations.add(new DocumentTemplateLocalization(5L, 3L, 1L, "Contract Agreement - English", "<html><body>Contract terms in English</body></html>", Status.ACTIVE, LocalDateTime.now().minusDays(26), LocalDateTime.now().minusDays(1)));
        localizations.add(new DocumentTemplateLocalization(6L, 3L, 2L, "Contract Agreement - Spanish", "<html><body>Términos del contrato en español</body></html>", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now()));
        localizations.add(new DocumentTemplateLocalization(7L, 4L, 1L, "Password Reset - English", "<html><body>Click here to reset your password</body></html>", Status.ACTIVE, LocalDateTime.now().minusDays(24), LocalDateTime.now()));
        localizations.add(new DocumentTemplateLocalization(8L, 4L, 2L, "Password Reset - Spanish", "<html><body>Haga clic aquí para restablecer su contraseña</body></html>", Status.INACTIVE, LocalDateTime.now().minusDays(23), LocalDateTime.now()));
        localizations.add(new DocumentTemplateLocalization(9L, 5L, 1L, "Feedback Form - English", "<html><body>Please provide your feedback in English</body></html>", Status.ACTIVE, LocalDateTime.now().minusDays(22), LocalDateTime.now()));
        localizations.add(new DocumentTemplateLocalization(10L, 5L, 2L, "Feedback Form - Spanish", "<html><body>Por favor proporcione sus comentarios en español</body></html>", Status.INACTIVE, LocalDateTime.now().minusDays(21), LocalDateTime.now()));

        return localizations;
    }

    // DocumentTemplateLocalization model class
    public static class DocumentTemplateLocalization {
        private Long localizationId;
        private Long templateId;
        private Long localeId;
        private String templateName;
        private String templateContent;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public DocumentTemplateLocalization(Long localizationId, Long templateId, Long localeId, 
                      String templateName, String templateContent, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.localizationId = localizationId;
            this.templateId = templateId;
            this.localeId = localeId;
            this.templateName = templateName;
            this.templateContent = templateContent;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getLocalizationId() {
            return localizationId;
        }

        public Long getTemplateId() {
            return templateId;
        }

        public Long getLocaleId() {
            return localeId;
        }

        public String getTemplateName() {
            return templateName;
        }

        public String getTemplateContent() {
            return templateContent;
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