package com.vaadin.starter.business.ui.views.security;

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
import com.vaadin.starter.business.backend.service.SecurityService;
import com.vaadin.starter.business.dummy.Person;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.Initials;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Right;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "internal-users", layout = MainLayout.class)
@PageTitle("Internal Users")
public class InternalUsers extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<Person> grid;
    private Registration resizeListener;
    private ListDataProvider<Person> dataProvider;
    private UI ui;

    // Details drawer components removed

    // Search form fields
    private TextField idFilter;
    private TextField nameFilter;
    private TextField emailFilter;
    private ComboBox<Person.Role> roleFilter;
    private ComboBox<String> statusFilter;
    private DatePicker lastLoginFilter;

    private final SecurityService securityService;

    @Autowired
    public InternalUsers(SecurityService securityService) {
        this.securityService = securityService;
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
        idFilter = new TextField();
        idFilter.setValueChangeMode(ValueChangeMode.EAGER);
        idFilter.setClearButtonVisible(true);

        nameFilter = new TextField();
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.setClearButtonVisible(true);

        emailFilter = new TextField();
        emailFilter.setValueChangeMode(ValueChangeMode.EAGER);
        emailFilter.setClearButtonVisible(true);

        roleFilter = new ComboBox<>();
        roleFilter.setItems(Person.Role.values());
        roleFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        lastLoginFilter = new DatePicker();
        lastLoginFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createUserButton = UIUtils.createSuccessButton("Create User");
        createUserButton.addClickListener(e -> openCreateUserDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create User on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createUserButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(idFilter, "ID");
        formLayout.addFormItem(nameFilter, "Name");
        formLayout.addFormItem(emailFilter, "Email");
        formLayout.addFormItem(roleFilter, "Role");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(lastLoginFilter, "Last Login After");

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

    private Grid<Person> createGrid() {
        grid = new Grid<>();
        // Row selection listener removed - details will be shown via action button
        grid.addThemeName("mobile");

        grid.setId("users");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(Person::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createUserInfo))
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createActive))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(Person::getEmail)
                .setAutoWidth(true)
                .setHeader("Email")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Login")
                .setTextAlign(ColumnTextAlign.END);

        // Add Actions column with eye icon
        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Initialize with data provider
        dataProvider = DataProvider.ofCollection(securityService.getInternalUsers());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createUserInfo(Person person) {
        ListItem item = new ListItem(
                new Initials(person.getInitials()), person.getName(),
                person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createActive(Person person) {
        Icon icon;
        if (person.getRandomBoolean()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(Person person) {
        return new Span(UIUtils.formatDate(person.getLastModified()));
    }

    private Component createActionButtons(Person person) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(person));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        layout.add(viewDetailsButton);
        return layout;
    }

    // Details drawer method removed

    private void showDetails(Person person) {
        UserDetails userDetails = new UserDetails(person, securityService);
        userDetails.open();
    }

    // Details are now shown in the UserDetails dialog

    private void filter() {
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply ID filter if not empty
        if (idFilter.getValue() != null && !idFilter.getValue().isEmpty()) {
            try {
                Long id = Long.parseLong(idFilter.getValue());
                dataProvider.setFilter(person -> person.getId().equals(id));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply name filter if not empty
        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            String nameFilterValue = nameFilter.getValue().toLowerCase();
            dataProvider.addFilter(person -> 
                person.getName().toLowerCase().contains(nameFilterValue) ||
                person.getFirstName().toLowerCase().contains(nameFilterValue) ||
                person.getLastName().toLowerCase().contains(nameFilterValue));
        }

        // Apply email filter if not empty
        if (emailFilter.getValue() != null && !emailFilter.getValue().isEmpty()) {
            String emailFilterValue = emailFilter.getValue().toLowerCase();
            dataProvider.addFilter(person -> 
                person.getEmail().toLowerCase().contains(emailFilterValue));
        }

        // Apply role filter if selected
        if (roleFilter.getValue() != null) {
            dataProvider.addFilter(person -> 
                person.getRole().equals(roleFilter.getValue()));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(person -> 
                person.getRandomBoolean() == isActive);
        }

        // Apply last login filter if selected
        if (lastLoginFilter.getValue() != null) {
            dataProvider.addFilter(person -> 
                person.getLastModified() != null && 
                !person.getLastModified().isBefore(lastLoginFilter.getValue()));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        idFilter.clear();
        nameFilter.clear();
        emailFilter.clear();
        roleFilter.clear();
        statusFilter.clear();
        lastLoginFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
        filter(); // Apply default filter
    }

    private void openCreateUserDialog() {
        // This would be implemented to open a dialog for creating a new user
        System.out.println("[DEBUG_LOG] Create user dialog would open here");
        // Example: CreateUser createUserDialog = new CreateUser(securityService, this::loadUsersData);
        // createUserDialog.open();
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
        List<Grid.Column<Person>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<Person> column : columns) {
            column.setVisible(!mobile);
        }
    }
}
