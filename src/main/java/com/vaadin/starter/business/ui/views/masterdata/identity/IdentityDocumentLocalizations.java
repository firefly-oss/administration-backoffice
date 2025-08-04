package com.vaadin.starter.business.ui.views.masterdata.identity;
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

@Route(value = "identity-document-localizations", layout = MainLayout.class)
@PageTitle("Identity Document Localizations")
public class IdentityDocumentLocalizations extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<IdentityDocumentLocalization> grid;
    private Registration resizeListener;
    private ListDataProvider<IdentityDocumentLocalization> dataProvider;
    private UI ui;

    // Search form fields
    private TextField documentIdFilter;
    private TextField localeIdFilter;
    private TextField documentNameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public IdentityDocumentLocalizations() {
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
        documentIdFilter = new TextField();
        documentIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        documentIdFilter.setClearButtonVisible(true);

        localeIdFilter = new TextField();
        localeIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        localeIdFilter.setClearButtonVisible(true);

        documentNameFilter = new TextField();
        documentNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        documentNameFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

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

        Button createLocalizationButton = UIUtils.createSuccessButton("Create Document Localization");
        createLocalizationButton.addClickListener(e -> openCreateLocalizationDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Document Localization on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createLocalizationButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(documentIdFilter, "Document ID");
        formLayout.addFormItem(localeIdFilter, "Locale ID");
        formLayout.addFormItem(documentNameFilter, "Document Name");
        formLayout.addFormItem(descriptionFilter, "Description");
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

    private Grid<IdentityDocumentLocalization> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("identity-document-localizations");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(IdentityDocumentLocalization::getLocalizationId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Localization ID")
                .setSortable(true);
        grid.addColumn(IdentityDocumentLocalization::getDocumentId)
                .setAutoWidth(true)
                .setHeader("Document ID")
                .setSortable(true);
        grid.addColumn(IdentityDocumentLocalization::getLocaleId)
                .setAutoWidth(true)
                .setHeader("Locale ID")
                .setSortable(true);
        grid.addColumn(IdentityDocumentLocalization::getDocumentName)
                .setAutoWidth(true)
                .setHeader("Document Name")
                .setSortable(true);
        grid.addColumn(IdentityDocumentLocalization::getDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(IdentityDocumentLocalization::getFormatDescription)
                .setAutoWidth(true)
                .setHeader("Format Description")
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
        dataProvider = DataProvider.ofCollection(getMockIdentityDocumentLocalizations());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(IdentityDocumentLocalization localization) {
        Icon icon;
        if (localization.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(IdentityDocumentLocalization localization) {
        return new Span(UIUtils.formatDate(localization.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(IdentityDocumentLocalization localization) {
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

    private void showDetails(IdentityDocumentLocalization localization) {
        IdentityDocumentLocalizationDetails localizationDetails = new IdentityDocumentLocalizationDetails(localization);
        localizationDetails.open();
    }

    private void deleteLocalization(IdentityDocumentLocalization localization) {
        // This would be implemented to delete the localization
        System.out.println("[DEBUG_LOG] Delete identity document localization: " + localization.getLocalizationId());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply document ID filter if not empty
        if (documentIdFilter.getValue() != null && !documentIdFilter.getValue().isEmpty()) {
            try {
                Long documentIdFilterValue = Long.parseLong(documentIdFilter.getValue());
                dataProvider.addFilter(localization -> 
                    localization.getDocumentId() != null &&
                    localization.getDocumentId().equals(documentIdFilterValue));
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

        // Apply document name filter if not empty
        if (documentNameFilter.getValue() != null && !documentNameFilter.getValue().isEmpty()) {
            String documentNameFilterValue = documentNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(localization -> 
                localization.getDocumentName() != null &&
                localization.getDocumentName().toLowerCase().contains(documentNameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(localization -> 
                localization.getDescription() != null &&
                localization.getDescription().toLowerCase().contains(descriptionFilterValue));
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
        documentIdFilter.clear();
        localeIdFilter.clear();
        documentNameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateLocalizationDialog() {
        // This would be implemented to open a dialog for creating a new identity document localization
        System.out.println("[DEBUG_LOG] Create identity document localization dialog would open here");
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
        List<Grid.Column<IdentityDocumentLocalization>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<IdentityDocumentLocalization> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<IdentityDocumentLocalization> getMockIdentityDocumentLocalizations() {
        List<IdentityDocumentLocalization> localizations = new ArrayList<>();

        localizations.add(new IdentityDocumentLocalization(1L, 1L, 1L, "National ID Card - English", "Standard national identification card in English", "8 digits followed by 1 letter", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        localizations.add(new IdentityDocumentLocalization(2L, 1L, 2L, "National ID Card - Spanish", "Tarjeta de identificación nacional estándar", "8 dígitos seguidos de 1 letra", Status.ACTIVE, LocalDateTime.now().minusDays(29), LocalDateTime.now().minusDays(4)));
        localizations.add(new IdentityDocumentLocalization(3L, 2L, 1L, "Passport - English", "International passport in English", "2 letters followed by 6 digits", Status.ACTIVE, LocalDateTime.now().minusDays(28), LocalDateTime.now().minusDays(3)));
        localizations.add(new IdentityDocumentLocalization(4L, 2L, 2L, "Passport - Spanish", "Pasaporte internacional", "2 letras seguidas de 6 dígitos", Status.ACTIVE, LocalDateTime.now().minusDays(27), LocalDateTime.now().minusDays(2)));
        localizations.add(new IdentityDocumentLocalization(5L, 3L, 1L, "Driver's License - English", "Driver's license with photo ID in English", "9 digits", Status.ACTIVE, LocalDateTime.now().minusDays(26), LocalDateTime.now().minusDays(1)));
        localizations.add(new IdentityDocumentLocalization(6L, 3L, 2L, "Driver's License - Spanish", "Licencia de conducir con identificación con foto", "9 dígitos", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now()));
        localizations.add(new IdentityDocumentLocalization(7L, 4L, 1L, "Social Security Card - English", "Social security identification in English", "Format: XXX-XX-XXXX", Status.ACTIVE, LocalDateTime.now().minusDays(24), LocalDateTime.now()));
        localizations.add(new IdentityDocumentLocalization(8L, 4L, 2L, "Social Security Card - Spanish", "Identificación de seguridad social", "Formato: XXX-XX-XXXX", Status.INACTIVE, LocalDateTime.now().minusDays(23), LocalDateTime.now()));
        localizations.add(new IdentityDocumentLocalization(9L, 5L, 1L, "Birth Certificate - English", "Official birth certificate in English", "BC- followed by 8 digits", Status.ACTIVE, LocalDateTime.now().minusDays(22), LocalDateTime.now()));
        localizations.add(new IdentityDocumentLocalization(10L, 5L, 2L, "Birth Certificate - Spanish", "Certificado de nacimiento oficial", "BC- seguido de 8 dígitos", Status.INACTIVE, LocalDateTime.now().minusDays(21), LocalDateTime.now()));

        return localizations;
    }

    // IdentityDocumentLocalization model class
    public static class IdentityDocumentLocalization {
        private Long localizationId;
        private Long documentId;
        private Long localeId;
        private String documentName;
        private String description;
        private String formatDescription;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public IdentityDocumentLocalization(Long localizationId, Long documentId, Long localeId, 
                      String documentName, String description, String formatDescription, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.localizationId = localizationId;
            this.documentId = documentId;
            this.localeId = localeId;
            this.documentName = documentName;
            this.description = description;
            this.formatDescription = formatDescription;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getLocalizationId() {
            return localizationId;
        }

        public Long getDocumentId() {
            return documentId;
        }

        public Long getLocaleId() {
            return localeId;
        }

        public String getDocumentName() {
            return documentName;
        }

        public String getDescription() {
            return description;
        }

        public String getFormatDescription() {
            return formatDescription;
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