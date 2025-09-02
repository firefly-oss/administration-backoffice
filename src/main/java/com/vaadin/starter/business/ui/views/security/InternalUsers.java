package com.vaadin.starter.business.ui.views.security;

import com.vaadin.flow.component.Component;
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
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.Person;
import com.vaadin.starter.business.backend.service.SecurityService;
import com.vaadin.starter.business.ui.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.UUID;

@Route(value = "internal-users", layout = MainLayout.class)
@PageTitle("Internal Users")
public class InternalUsers extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

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
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);

        // Initialize with default filter
        filter();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createSearchForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createSearchForm() {
        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addClassName(LumoStyles.Padding.Bottom.M);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 3,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));

        // Initialize filter fields
        idFilter = new TextField();
        idFilter.setPlaceholder("ID");
        idFilter.setClearButtonVisible(true);
        idFilter.setValueChangeMode(ValueChangeMode.EAGER);
        idFilter.addValueChangeListener(e -> applyFilter());

        nameFilter = new TextField();
        nameFilter.setPlaceholder("Name");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.addValueChangeListener(e -> applyFilter());

        emailFilter = new TextField();
        emailFilter.setPlaceholder("Email");
        emailFilter.setClearButtonVisible(true);
        emailFilter.setValueChangeMode(ValueChangeMode.EAGER);
        emailFilter.addValueChangeListener(e -> applyFilter());

        roleFilter = new ComboBox<>();
        roleFilter.setPlaceholder("Role");
        roleFilter.setItems(Person.Role.values());
        roleFilter.setClearButtonVisible(true);
        roleFilter.addValueChangeListener(e -> applyFilter());

        statusFilter = new ComboBox<>();
        statusFilter.setPlaceholder("Status");
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);
        statusFilter.addValueChangeListener(e -> applyFilter());

        lastLoginFilter = new DatePicker();
        lastLoginFilter.setPlaceholder("Last Login After");
        lastLoginFilter.setClearButtonVisible(true);
        lastLoginFilter.addValueChangeListener(e -> applyFilter());

        // Add fields to form
        formLayout.addFormItem(idFilter, "ID");
        formLayout.addFormItem(nameFilter, "Name");
        formLayout.addFormItem(emailFilter, "Email");
        formLayout.addFormItem(roleFilter, "Role");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(lastLoginFilter, "Last Login After");

        // Create buttons
        Button searchButton = new Button("Search", VaadinIcon.SEARCH.create(), e -> applyFilter());
        searchButton.getElement().getThemeList().add("primary");
        searchButton.addClassName(LumoStyles.Margin.Right.S);

        Button clearButton = new Button("Clear", VaadinIcon.CLOSE.create(), e -> clearFilter());
        clearButton.getElement().getThemeList().add("tertiary");

        // Add buttons to horizontal layout
        HorizontalLayout buttonsLayout = new HorizontalLayout(searchButton, clearButton);
        buttonsLayout.setSpacing(true);
        buttonsLayout.setPadding(true);
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        // Create container for the form
        Div formContainer = new Div(formLayout, buttonsLayout);
        formContainer.addClassName(LumoStyles.Padding.Bottom.L);
        formContainer.addClassName(LumoStyles.Padding.Horizontal.L);
        formContainer.addClassName(LumoStyles.Padding.Top.M);
        formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
        formContainer.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
        formContainer.getStyle().set("box-shadow", "var(--lumo-box-shadow-xs)");

        return formContainer;
    }

    private Grid createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem()
                .ifPresent(this::showDetails));
        dataProvider = DataProvider.ofCollection(securityService.getInternalUsers());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Person::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createUserInfo))
                .setAutoWidth(true)
                .setHeader("Name");
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

    private void showDetails(Person person) {
        detailsDrawerHeader.setTitle(person.getName());
        detailsDrawer.setContent(createDetails(person));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person) {
        TextField firstName = new TextField();
        firstName.setValue(person.getFirstName());
        firstName.setWidthFull();

        TextField lastName = new TextField();
        lastName.setValue(person.getLastName());
        lastName.setWidthFull();

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(person.getRandomBoolean() ? "Active" : "Inactive");

        TextField username = new TextField();
        username.setValue(person.getEmail().split("@")[0]);
        username.setWidthFull();

        TextField email = new TextField();
        email.setValue(person.getEmail());
        email.setWidthFull();

        ComboBox<String> role = new ComboBox<>();
        role.setItems("Admin", "User", "Guest");
        role.setValue("User");
        role.setWidthFull();

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
        form.addFormItem(firstName, "First Name");
        form.addFormItem(lastName, "Last Name");
        form.addFormItem(username, "Username");
        form.addFormItem(email, "Email");
        form.addFormItem(status, "Status");
        form.addFormItem(role, "Role");
        form.addFormItem(new Upload(), "Profile Picture");
        return form;
    }

    private void filter() {
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply ID filter if not empty
        if (idFilter.getValue() != null && !idFilter.getValue().isEmpty()) {
            try {
                UUID id = UUID.fromString(idFilter.getValue());
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
}
