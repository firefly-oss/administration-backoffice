package com.vaadin.starter.business.ui.views.masterdata.notification;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.starter.business.ui.constants.NavigationConstants;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import com.vaadin.starter.business.ui.views.masterdata.message.MessageTypeCatalogDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "notification-message-templates", layout = MainLayout.class)
@PageTitle(NavigationConstants.NOTIFICATION_MESSAGE_TEMPLATES)
public class NotificationMessageTemplates extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<NotificationMessageTemplate> grid;
    private Registration resizeListener;
    private ListDataProvider<NotificationMessageTemplate> dataProvider;
    private UI ui;

    // Search form fields
    private TextField templateNameFilter;
    private TextField templateTypeFilter;
    private TextField versionFilter;
    private ComboBox<String> statusFilter;
    private ComboBox<Long> messageIdFilter;

    public NotificationMessageTemplates() {
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
        templateNameFilter = new TextField();
        templateNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        templateNameFilter.setClearButtonVisible(true);

        templateTypeFilter = new TextField();
        templateTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        templateTypeFilter.setClearButtonVisible(true);

        versionFilter = new TextField();
        versionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        versionFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        messageIdFilter = new ComboBox<>();
        // Populate with message IDs from mock data
        List<Long> messageIds = new ArrayList<>();
        messageIds.add(1L);
        messageIds.add(2L);
        messageIds.add(3L);
        messageIdFilter.setItems(messageIds);
        messageIdFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createTemplateButton = UIUtils.createSuccessButton("Create Message Template");
        createTemplateButton.addClickListener(e -> openCreateTemplateDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Message Template on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createTemplateButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(templateNameFilter, "Template Name");
        formLayout.addFormItem(templateTypeFilter, "Template Type");
        formLayout.addFormItem(versionFilter, "Version");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(messageIdFilter, "Message ID");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 3, FormLayout.ResponsiveStep.LabelsPosition.TOP)
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

    private Grid<NotificationMessageTemplate> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("notification-message-templates");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(NotificationMessageTemplate::getTemplateId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Template ID")
                .setSortable(true);
        grid.addColumn(NotificationMessageTemplate::getMessageId)
                .setAutoWidth(true)
                .setHeader("Message ID")
                .setSortable(true);
        grid.addColumn(template -> template.getNotificationMessage() != null ? template.getNotificationMessage().getMessageCode() : "")
                .setAutoWidth(true)
                .setHeader("Message Code")
                .setSortable(true);
        grid.addColumn(NotificationMessageTemplate::getTemplateName)
                .setAutoWidth(true)
                .setHeader("Template Name")
                .setSortable(true);
        grid.addColumn(NotificationMessageTemplate::getTemplateType)
                .setAutoWidth(true)
                .setHeader("Template Type")
                .setSortable(true);
        grid.addColumn(NotificationMessageTemplate::getVersion)
                .setAutoWidth(true)
                .setHeader("Version")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createActive))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);
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
        dataProvider = DataProvider.ofCollection(getMockNotificationMessageTemplates());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(NotificationMessageTemplate template) {
        Icon icon;
        if (template.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(NotificationMessageTemplate template) {
        return new Span(UIUtils.formatDate(template.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(NotificationMessageTemplate template) {
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

    private void showDetails(NotificationMessageTemplate template) {
        NotificationMessageTemplateDetails templateDetails = new NotificationMessageTemplateDetails(template);
        templateDetails.open();
    }

    private void deleteTemplate(NotificationMessageTemplate template) {
        // This would be implemented to delete the template
        System.out.println("[DEBUG_LOG] Delete notification message template: " + template.getTemplateName());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply template name filter if not empty
        if (templateNameFilter.getValue() != null && !templateNameFilter.getValue().isEmpty()) {
            String templateNameFilterValue = templateNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(template -> 
                template.getTemplateName() != null &&
                template.getTemplateName().toLowerCase().contains(templateNameFilterValue));
        }

        // Apply template type filter if not empty
        if (templateTypeFilter.getValue() != null && !templateTypeFilter.getValue().isEmpty()) {
            String templateTypeFilterValue = templateTypeFilter.getValue().toLowerCase();
            dataProvider.addFilter(template -> 
                template.getTemplateType() != null &&
                template.getTemplateType().toLowerCase().contains(templateTypeFilterValue));
        }

        // Apply version filter if not empty
        if (versionFilter.getValue() != null && !versionFilter.getValue().isEmpty()) {
            String versionFilterValue = versionFilter.getValue().toLowerCase();
            dataProvider.addFilter(template -> 
                template.getVersion() != null &&
                template.getVersion().toLowerCase().contains(versionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            NotificationMessageTemplate.Status statusValue = "Active".equals(statusFilter.getValue())
                ? NotificationMessageTemplate.Status.ACTIVE
                : NotificationMessageTemplate.Status.INACTIVE;
            dataProvider.addFilter(template -> 
                template.getStatus() == statusValue);
        }

        // Apply message ID filter if selected
        if (messageIdFilter.getValue() != null) {
            Long messageId = messageIdFilter.getValue();
            dataProvider.addFilter(template -> 
                template.getMessageId() != null && 
                template.getMessageId().equals(messageId));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        templateNameFilter.clear();
        templateTypeFilter.clear();
        versionFilter.clear();
        statusFilter.clear();
        messageIdFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateTemplateDialog() {
        // This would be implemented to open a dialog for creating a new notification message template
        NotificationMessageTemplate newTemplate = NotificationMessageTemplate.builder()
                .status(NotificationMessageTemplate.Status.ACTIVE)
                .build();
        NotificationMessageTemplateDetails templateDetails = new NotificationMessageTemplateDetails(newTemplate);
        templateDetails.open();
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
        List<Grid.Column<NotificationMessageTemplate>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<NotificationMessageTemplate> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<NotificationMessageTemplate> getMockNotificationMessageTemplates() {
        List<NotificationMessageTemplate> templates = new ArrayList<>();

        // Create message types
        MessageTypeCatalogDTO emailType = MessageTypeCatalogDTO.builder()
                .typeId(1L)
                .typeCode("EMAIL")
                .typeName("Email")
                .description("Email notifications")
                .build();

        // Create notification messages
        NotificationMessageCatalog welcomeEmail = NotificationMessageCatalog.builder()
                .messageId(1L)
                .messageCode("WELCOME_EMAIL")
                .typeId(1L)
                .messageType(emailType)
                .eventType("USER_REGISTRATION")
                .description("Welcome email sent after registration")
                .defaultSubject("Welcome to our platform!")
                .defaultMessage("Hello {userName}, welcome to our platform. Please activate your account using this link: {activationLink}")
                .status(NotificationMessageCatalog.Status.ACTIVE)
                .build();

        NotificationMessageCatalog passwordReset = NotificationMessageCatalog.builder()
                .messageId(2L)
                .messageCode("PASSWORD_RESET")
                .typeId(1L)
                .messageType(emailType)
                .eventType("PASSWORD_RESET_REQUEST")
                .description("Password reset instructions")
                .defaultSubject("Password Reset Request")
                .defaultMessage("Hello {userName}, you requested a password reset. Use this link: {resetLink}. It expires in {expiryTime} minutes.")
                .status(NotificationMessageCatalog.Status.ACTIVE)
                .build();

        // Create sample template variables
        Map<String, Object> welcomeVars = new HashMap<>();
        welcomeVars.put("userName", "String");
        welcomeVars.put("activationLink", "String");
        welcomeVars.put("companyName", "String");
        welcomeVars.put("supportEmail", "String");

        Map<String, Object> passwordResetVars = new HashMap<>();
        passwordResetVars.put("userName", "String");
        passwordResetVars.put("resetLink", "String");
        passwordResetVars.put("expiryTime", "Number");
        passwordResetVars.put("supportPhone", "String");

        // Create notification message templates
        templates.add(NotificationMessageTemplate.builder()
                .templateId(1L)
                .messageId(1L)
                .notificationMessage(welcomeEmail)
                .templateName("Welcome Email - HTML")
                .templateContent("<html><body><h1>Welcome to {companyName}!</h1><p>Hello {userName},</p><p>Welcome to our platform. Please activate your account using this link: <a href=\"{activationLink}\">Activate Account</a></p><p>If you have any questions, contact us at {supportEmail}.</p></body></html>")
                .templateType("HTML")
                .version("1.0")
                .templateVariables(welcomeVars)
                .status(NotificationMessageTemplate.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(30))
                .dateUpdated(LocalDateTime.now().minusDays(5))
                .build());

        templates.add(NotificationMessageTemplate.builder()
                .templateId(2L)
                .messageId(1L)
                .notificationMessage(welcomeEmail)
                .templateName("Welcome Email - Text")
                .templateContent("Welcome to {companyName}!\n\nHello {userName},\n\nWelcome to our platform. Please activate your account using this link: {activationLink}\n\nIf you have any questions, contact us at {supportEmail}.")
                .templateType("TEXT")
                .version("1.0")
                .templateVariables(welcomeVars)
                .status(NotificationMessageTemplate.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(29))
                .dateUpdated(LocalDateTime.now().minusDays(4))
                .build());

        templates.add(NotificationMessageTemplate.builder()
                .templateId(3L)
                .messageId(2L)
                .notificationMessage(passwordReset)
                .templateName("Password Reset - HTML")
                .templateContent("<html><body><h1>Password Reset Request</h1><p>Hello {userName},</p><p>You requested a password reset. Use this link: <a href=\"{resetLink}\">Reset Password</a>. It expires in {expiryTime} minutes.</p><p>If you didn't request this, please contact support at {supportPhone}.</p></body></html>")
                .templateType("HTML")
                .version("1.0")
                .templateVariables(passwordResetVars)
                .status(NotificationMessageTemplate.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(25))
                .dateUpdated(LocalDateTime.now().minusDays(3))
                .build());

        templates.add(NotificationMessageTemplate.builder()
                .templateId(4L)
                .messageId(2L)
                .notificationMessage(passwordReset)
                .templateName("Password Reset - Text")
                .templateContent("Password Reset Request\n\nHello {userName},\n\nYou requested a password reset. Use this link: {resetLink}. It expires in {expiryTime} minutes.\n\nIf you didn't request this, please contact support at {supportPhone}.")
                .templateType("TEXT")
                .version("1.0")
                .templateVariables(passwordResetVars)
                .status(NotificationMessageTemplate.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(24))
                .dateUpdated(LocalDateTime.now().minusDays(2))
                .build());

        templates.add(NotificationMessageTemplate.builder()
                .templateId(5L)
                .messageId(2L)
                .notificationMessage(passwordReset)
                .templateName("Password Reset - HTML (New Design)")
                .templateContent("<html><body style=\"font-family: Arial, sans-serif;\"><div style=\"background-color: #f5f5f5; padding: 20px;\"><h1 style=\"color: #333;\">Password Reset Request</h1><p>Hello {userName},</p><p>You requested a password reset. Click the button below to reset your password:</p><p><a href=\"{resetLink}\" style=\"background-color: #4CAF50; color: white; padding: 10px 15px; text-decoration: none; border-radius: 5px;\">Reset Password</a></p><p>This link will expire in {expiryTime} minutes.</p><p>If you didn't request this, please contact support at {supportPhone}.</p></div></body></html>")
                .templateType("HTML")
                .version("2.0")
                .templateVariables(passwordResetVars)
                .status(NotificationMessageTemplate.Status.INACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(10))
                .dateUpdated(LocalDateTime.now().minusDays(1))
                .build());

        return templates;
    }
}